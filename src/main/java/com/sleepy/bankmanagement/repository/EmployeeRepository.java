package com.sleepy.bankmanagement.repository;

import com.sleepy.bankmanagement.entity.Employee;
import com.sleepy.bankmanagement.tools.JpaProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeRepository implements AutoCloseable {

    private final EntityManager entityManager;

    public EmployeeRepository() {
        this.entityManager = JpaProvider.getProvider().getEntityManager();
    }

    public Employee save(Employee employee) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(employee);
        transaction.commit();
        return employee;
    }

    public Employee edit(Employee employee) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Employee updatedEmployee = entityManager.merge(employee);
        transaction.commit();
        return updatedEmployee;
    }

    public Employee deleteById(String id) {
        EntityTransaction transaction = entityManager.getTransaction();
        Employee employeeToDelete = findById(id);

        if (employeeToDelete != null) {
            transaction.begin();
            entityManager.remove(employeeToDelete);
            transaction.commit();
        }

        return employeeToDelete;
    }

    public Employee findById(String id) {
        return entityManager.find(Employee.class, id);
    }

    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM employeeEntity e", Employee.class);
        return query.getResultList();
    }

    public Employee findByUsername(String username) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM employeeEntity e WHERE e.username = :username", Employee.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}