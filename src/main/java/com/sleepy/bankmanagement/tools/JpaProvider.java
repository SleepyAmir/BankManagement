package com.sleepy.bankmanagement.tools;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import lombok.Getter;

public class JpaProvider {

    @Getter
    private static JpaProvider provider = new JpaProvider();
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("sleepy");

    private JpaProvider() {
    }

    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }



}
