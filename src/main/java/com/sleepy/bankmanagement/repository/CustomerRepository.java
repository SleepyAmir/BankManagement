package com.sleepy.bankmanagement.repository;

import com.sleepy.bankmanagement.entity.Customer;
import com.sleepy.bankmanagement.tools.JpaProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerRepository implements AutoCloseable {

    private final EntityManager entityManager;

    public CustomerRepository() {
        this.entityManager = JpaProvider.getProvider().getEntityManager();
    }

    public Customer save(Customer customer) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(customer);
        transaction.commit();
        return customer;
    }

    public Customer edit(Customer customer) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer updatedCustomer = entityManager.merge(customer);
        transaction.commit();
        return updatedCustomer;
    }

    public Customer deleteById(String id) {
        EntityTransaction transaction = entityManager.getTransaction();
        Customer customerToDelete = findById(id);

        if (customerToDelete != null) {
            transaction.begin();
            entityManager.remove(customerToDelete);
            transaction.commit();
        }

        return customerToDelete;
    }

    public Customer findById(String id) {
        return entityManager.find(Customer.class, id);
    }

    public List<Customer> findAll() {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM customerEntity c", Customer.class);
        return query.getResultList();
    }

    public Customer findByNationalId(String nationalId) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM customerEntity c WHERE c.nationalId = :nationalId", Customer.class);
        query.setParameter("nationalId", nationalId);
        return query.getSingleResult();
    }

    @Override
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
