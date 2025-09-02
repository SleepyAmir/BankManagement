package com.sleepy.bankmanagement.repository;

import com.sleepy.bankmanagement.entity.Loan;
import com.sleepy.bankmanagement.tools.JpaProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class LoanRepository implements AutoCloseable {

    private final EntityManager entityManager;

    public LoanRepository() {
        this.entityManager = JpaProvider.getProvider().getEntityManager();
    }

    public Loan save(Loan loan) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(loan);
        transaction.commit();
        return loan;
    }

    public Loan edit(Loan loan) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Loan updatedLoan = entityManager.merge(loan);
        transaction.commit();
        return updatedLoan;
    }

    public Loan deleteById(String id) {
        EntityTransaction transaction = entityManager.getTransaction();
        Loan loanToDelete = findById(id);

        if (loanToDelete != null) {
            transaction.begin();
            entityManager.remove(loanToDelete);
            transaction.commit();
        }

        return loanToDelete;
    }

    public Loan findById(String id) {
        return entityManager.find(Loan.class, id);
    }

    public List<Loan> findAll() {
        TypedQuery<Loan> query = entityManager.createQuery("SELECT l FROM loanEntity l", Loan.class);
        return query.getResultList();
    }

    public List<Loan> findByCustomerId(String customerId) {
        TypedQuery<Loan> query = entityManager.createQuery(
                "SELECT l FROM loanEntity l WHERE l.customerId = :customerId", Loan.class);
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