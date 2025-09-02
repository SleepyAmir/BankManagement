package com.sleepy.bankmanagement.repository;

import com.sleepy.bankmanagement.entity.Account;
import com.sleepy.bankmanagement.tools.JpaProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountRepository implements AutoCloseable {

    private final EntityManager entityManager;

    public AccountRepository() {
        this.entityManager = JpaProvider.getProvider().getEntityManager();
    }

    public Account save(Account account) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(account);
        transaction.commit();
        return account;
    }

    public Account edit(Account account) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Account updatedAccount = entityManager.merge(account);
        transaction.commit();
        return updatedAccount;
    }

    public Account deleteById(String id) {
        EntityTransaction transaction = entityManager.getTransaction();
        Account accountToDelete = findById(id);

        if (accountToDelete != null) {
            transaction.begin();
            entityManager.remove(accountToDelete);
            transaction.commit();
        }

        return accountToDelete;
    }

    public Account findById(String id) {
        return entityManager.find(Account.class, id);
    }

    public List<Account> findAll() {
        TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a", Account.class);
        return query.getResultList();
    }

    public List<Account> findByCustomerId(String customerId) {
        TypedQuery<Account> query = entityManager.createQuery(
                "SELECT a FROM Account a WHERE a.customerId = :customerId", Account.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    @Override
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}