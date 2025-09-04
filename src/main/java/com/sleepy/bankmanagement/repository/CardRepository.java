package com.sleepy.bankmanagement.repository;

import com.sleepy.bankmanagement.entity.Card;
import com.sleepy.bankmanagement.tools.JpaProvider; // Assuming your JpaProvider is here

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class CardRepository implements AutoCloseable {

    private final EntityManager entityManager;

    public CardRepository() {
        this.entityManager = JpaProvider.getProvider().getEntityManager();
    }

    public Card save(Card card) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(card);
        transaction.commit();
        return card;
    }

    public Card edit(Card card) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Card updatedCard = entityManager.merge(card);
        transaction.commit();
        return updatedCard;
    }

    public Card deleteById(String id) {
        EntityTransaction transaction = entityManager.getTransaction();
        Card cardToDelete = findById(id);

        if (cardToDelete != null) {
            transaction.begin();
            entityManager.remove(cardToDelete);
            transaction.commit();
        }

        return cardToDelete;
    }

    public Card findById(String id) {
        return entityManager.find(Card.class, id);
    }

    public List<Card> findAll() {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM cardEntity c", Card.class);
        return query.getResultList();
    }

    @Override
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}