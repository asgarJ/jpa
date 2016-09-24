package com.javacodegeeks.ultimate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger LOGGER = Logger.getLogger("JPA");
    public static void main( String[] args )
    {
        App app = new App();
        app.run();
    }

    public void run() {
        EntityManagerFactory factory = null;
        EntityManager entityManager = null;
        try {
            factory = Persistence.createEntityManagerFactory("PersistenceUnit");
            entityManager = factory.createEntityManager();
            persistPerson(entityManager);
            persistGeek(entityManager);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (entityManager != null)
                entityManager.close();
            if (factory != null)
                factory.close();
        }
    }
    private void persistPerson(EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Person person = new Person();
            person.setFirstName("Asgar");
            person.setLastName("Javadov");
            entityManager.persist(person);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive())
                transaction.rollback();
        }
    }

    private void persistGeek(EntityManager entityManager) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Geek geek = new Geek();
            geek.setFirstName("Asgar");
            geek.setLastName("Javadov");
            geek.setFavouriteProgrammingLanguage("Python");
            entityManager.persist(geek);

            geek = new Geek();
            geek.setFirstName("Yegana");
            geek.setLastName("Abdullayeva");
            geek.setFavouriteProgrammingLanguage("R");
            entityManager.persist(geek);

            geek = new Geek();
            geek.setFirstName("Elchin");
            geek.setLastName("Abdullayev");
            geek.setFavouriteProgrammingLanguage("Java");
            entityManager.persist(geek);
            transaction.commit();
        } catch (Exception e) {

        } finally {
            if (transaction.isActive())
                transaction.rollback();
        }
    }
}
