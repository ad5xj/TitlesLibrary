package org.ad5xj.hibernate;

import org.ad5xj.mariadb.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class hibernateutil
{
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory()
    {
        if (sessionFactory == null)
        {
            try
            {
                Configuration configuration = new Configuration().addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

                System.out.println("Hibernate Java Config serviceRegistry created");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                return sessionFactory;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}