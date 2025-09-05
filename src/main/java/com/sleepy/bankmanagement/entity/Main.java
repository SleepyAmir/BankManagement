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

//        Card card = Card.builder()
//                .cardNumber("5555-6666-7777-8888")
//                .cardType(CardType.CREDIT)
//                .cvv("987")
//                .expiryDate(new Date())
//                .cardholderName("Bob Johnson")
//                .linkedAccountNumber("5544332211")
//                .isActive(true)
//                .build();
//
//        System.out.println("Testing card: " + card.getCardNumber());
//
//        EntityManagerFactory emf = null;
//        EntityManager em = null;
//
//        try {
//            System.out.println("Creating EntityManagerFactory...");
//            emf = Persistence.createEntityManagerFactory("sleepy");
//
//            System.out.println("Creating EntityManager...");
//            em = emf.createEntityManager();
//
//            System.out.println("Starting transaction...");
//            em.getTransaction().begin();
//
//            System.out.println("Persisting card...");
//            em.persist(card);
//
//            System.out.println("Committing transaction...");
//            em.getTransaction().commit();
//
//            System.out.println("✅ Card saved successfully!");
//            System.out.println("Card details: " + card);
//
//        } catch (Exception e) {
//            System.err.println("❌ Error occurred:");
//            e.printStackTrace();
//
//            // Rollback transaction if it's active
//            if (em != null && em.getTransaction().isActive()) {
//                System.out.println("Rolling back transaction...");
//                em.getTransaction().rollback();
//            }
//        } finally {
//            // Clean up resources
//            if (em != null) {
//                System.out.println("Closing EntityManager...");
//                em.close();
//            }
//            if (emf != null) {
//                System.out.println("Closing EntityManagerFactory...");
//                emf.close();
//            }
//        }
//    }

    }
}
