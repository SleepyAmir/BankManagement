package com.sleepy.bankmanagement.repository;

import com.sleepy.bankmanagement.entity.Transaction;
import com.sleepy.bankmanagement.tools.JpaProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class TransactionRepository implements AutoCloseable {

    private final EntityManager entityManager;

    public TransactionRepository() {
        this.entityManager = JpaProvider.getProvider().getEntityManager();
    }

    public Transaction save(Transaction transaction) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(transaction);
        entityTransaction.commit();
        return transaction;
    }

    public Transaction edit(Transaction transaction) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Transaction updatedTransaction = entityManager.merge(transaction);
        entityTransaction.commit();
        return updatedTransaction;
    }

    public Transaction deleteById(String id) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Transaction transactionToDelete = findById(id);

        if (transactionToDelete != null) {
            entityTransaction.begin();
            entityManager.remove(transactionToDelete);
            entityTransaction.commit();
        }

        return transactionToDelete;
    }

    public Transaction findById(String id) {
        return entityManager.find(Transaction.class, id);
    }

    public List<Transaction> findAll() {
        TypedQuery<Transaction> query = entityManager.createQuery("SELECT t FROM transactionEntity t", Transaction.class);
        return query.getResultList();
    }

    public List<Transaction> findByAccountNumber(String accountNumber) {
        TypedQuery<Transaction> query = entityManager.createQuery(
                "SELECT t FROM transactionEntity t WHERE t.sourceAccountNumber = :accountNumber OR t.destinationAccountNumber = :accountNumber",
                Transaction.class);
        query.setParameter("accountNumber", accountNumber);
        return query.getResultList();
    }

    @Override
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}