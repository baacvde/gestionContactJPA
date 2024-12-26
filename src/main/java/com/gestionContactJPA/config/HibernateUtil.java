package com.gestionContactJPA.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {
    private static final EntityManagerFactory entityManagerFactory;

    static {
        try {
            System.out.println("Attempting to create EntityManagerFactory...");
            entityManagerFactory = Persistence.createEntityManagerFactory("gestionnaireContact");
            System.out.println("EntityManagerFactory created successfully.");
        } catch (Throwable ex) {
            System.err.println("Initial EntityManagerFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static void shutdown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
