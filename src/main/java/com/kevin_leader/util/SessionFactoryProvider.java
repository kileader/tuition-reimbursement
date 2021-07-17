package com.kevin_leader.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Class for providing SessionFactory for use with Hibernate.
 * Revised from a design by Paula Waite at Madison College
 * @author Kevin Leader
 */
public class SessionFactoryProvider {

    private static SessionFactory sessionFactory;

    /**
     * Private constructor to prevent instantiation
     */
    private SessionFactoryProvider() {

    }

    /**
     * Create session factory
     */
    public static void createSessionFactory() {

        StandardServiceRegistry standardRegistry =
                new StandardServiceRegistryBuilder().configure().build();
        Metadata metaData =
                new MetadataSources(standardRegistry).getMetadataBuilder().build();
        sessionFactory = metaData.getSessionFactoryBuilder().build();
    }

    /**
     * Get session factory
     * @return the session factory
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            createSessionFactory();
        }
        return sessionFactory;
    }
    
}
