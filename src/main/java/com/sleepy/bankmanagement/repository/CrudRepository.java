package com.sleepy.bankmanagement.repository;

import com.sleepy.bankmanagement.tools.JpaProvider;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class CrudRepository<T, I> implements AutoCloseable {
    private final EntityManager entityManager;

    public CrudRepository() {
        entityManager = JpaProvider.getProvider().getEntityManager();
    }

    public T save(T t) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(t);
        entityTransaction.commit();
        return t;
    }

    public T edit(T t) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(t);
        entityTransaction.commit();
        return t;
    }

    public T deleteById(I id, Class<T> tClass) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        T t = entityManager.find(tClass, id);
        entityManager.remove(t);
        entityTransaction.commit();
        return t;
    }

    public List<T> findAll(Class<T> tClass) {
        TypedQuery<T> query = entityManager.createQuery("select p from " + tClass.getAnnotation(Entity.class).name() + " p", tClass);
        return query.getResultList();
    }

    public T findById(I id, Class<T> tClass) {
        return entityManager.find(tClass, id);
    }


    @Override
    public void close() throws Exception {
        entityManager.close();
    }
}
