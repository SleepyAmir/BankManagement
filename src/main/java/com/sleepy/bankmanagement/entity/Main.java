package com.sleepy.bankmanagement.entity;

import com.github.javafaker.Faker;
import com.sleepy.bankmanagement.entity.enums.AccountStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sleepy");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();




        // Use a stable locale like English to avoid library bugs
        Faker faker = new Faker(Locale.ENGLISH);

        try {
            transaction.begin();
            System.out.println("Starting to generate 10 customers and accounts...");

            for (int i = 0; i < 10; i++) {

                // 1. Create a Customer
                Customer customer = Customer.builder()
                        .customerId(UUID.randomUUID().toString())
                        .firstName(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .nationalId(faker.idNumber().ssnValid())
                        .phoneNumber(faker.phoneNumber().cellPhone())
                        .dateOfBirth(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                        .registrationDate(LocalDateTime.now())
                        .isActive(true) // Set the active status
                        .build();
                entityManager.persist(customer);

                // 2. Create a Checking Account for the Customer
                CheckingAccount account = CheckingAccount.builder()
                        .accountNumber(faker.finance().iban("DE")) // Use a stable IBAN code like Germany's
                        .balance(faker.number().randomDouble(2, 1000, 50000))
                        .status(AccountStatus.ACTIVE)
                        .dateOpened(LocalDateTime.now().minusDays(faker.number().numberBetween(10, 365)))
                        .customerId(customer.getCustomerId()) // Link using the customer's ID
                        .monthlyMaintenanceFee(10.0)
                        .freeTransactionLimit(20)
                        .checkBookFee(25.0)
                        .build();
                entityManager.persist(account);
            }

            transaction.commit();
            System.out.println("SUCCESS: 10 customers and their accounts have been created.");

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            factory.close();
        }
    }
}
