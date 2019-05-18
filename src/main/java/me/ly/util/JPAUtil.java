package me.ly.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * JPA的工具类
 */
public class JPAUtil {
    private static EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("myJpa");
    }

    public static EntityManager getFactory(){
        return  factory.createEntityManager();
    }
}
