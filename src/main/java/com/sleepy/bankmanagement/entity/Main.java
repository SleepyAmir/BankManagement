package com.sleepy.bankmanagement.entity;

import com.sleepy.bankmanagement.entity.enums.CardType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;


public class Main {
    public static void main(String[] args) throws Exception {


        Card card = Card.builder()
                .cardNumber("5555-6666-7777-8888")
                .cardType(CardType.CREDIT)
                .cvv("987")
                .expiryDate(new Date())
                .cardholderName("Bob Johnson")
                .linkedAccountNumber("5544332211")
                .isActive(true)
                .build();

        System.out.println(card.getCardNumber());

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sleepy");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(card);
        em.getTransaction().commit();



    }
}
