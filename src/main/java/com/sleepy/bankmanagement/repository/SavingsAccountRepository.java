package com.sleepy.bankmanagement.repository;

import com.sleepy.bankmanagement.entity.SavingsAccount;
import com.sleepy.bankmanagement.tools.JpaProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class SavingsAccountRepository implements AutoCloseable {

    private final EntityManager entityManager;

    public SavingsAccountRepository() {
        this.entityManager = JpaProvider.getProvider().getEntityManager();
    }

    public SavingsAccount save(SavingsAccount account) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(account);
        transaction.commit();
        return account;
    }

    public SavingsAccount edit(SavingsAccount account) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        SavingsAccount updatedAccount = entityManager.merge(account);
        transaction.commit();
        return updatedAccount;
    }

    public SavingsAccount deleteById(String id) {
        EntityTransaction transaction = entityManager.getTransaction();
        SavingsAccount accountToDelete = findById(id);

        if (accountToDelete != null) {
            transaction.begin();
            entityManager.remove(accountToDelete);
            transaction.commit();
        }

        return accountToDelete;
    }

    public SavingsAccount findById(String id) {
        return entityManager.find(SavingsAccount.class, id);
    }

    public List<SavingsAccount> findAll() {
        TypedQuery<SavingsAccount> query = entityManager.createQuery(
                "SELECT s FROM SavingsAccount s", SavingsAccount.class);
        return query.getResultList();
    }

    public List<SavingsAccount> findByCustomerId(String customerId) {
        TypedQuery<SavingsAccount> query = entityManager.createQuery(
                "SELECT s FROM SavingsAccount s WHERE s.customerId = :customerId",
                SavingsAccount.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    public List<SavingsAccount> findByInterestRateRange(double minRate, double maxRate) {
        TypedQuery<SavingsAccount> query = entityManager.createQuery(
                "SELECT s FROM SavingsAccount s WHERE s.interestRate BETWEEN :minRate AND :maxRate",
                SavingsAccount.class);
        query.setParameter("minRate", minRate);
        query.setParameter("maxRate", maxRate);
        return query.getResultList();
    }

    public List<SavingsAccount> findBelowMinimumBalance() {
        TypedQuery<SavingsAccount> query = entityManager.createQuery(
                "SELECT s FROM SavingsAccount s WHERE s.balance < s.minimumBalance",
                SavingsAccount.class);
        return query.getResultList();
    }

    @Override
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}