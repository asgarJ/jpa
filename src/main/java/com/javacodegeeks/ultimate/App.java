package com.javacodegeeks.ultimate;

import com.javacodegeeks.ultimate.entities.Geek;
import com.javacodegeeks.ultimate.entities.Person;
import com.javacodegeeks.ultimate.entities.Project;

import javax.persistence.*;
import java.util.List;
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
            inheritance(entityManager);
            relationships(entityManager);
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
            if (transaction.isActive())
                transaction.rollback();
        }
    }

    private void inheritance(EntityManager entityManager) {
        try {
            TypedQuery<Person> query = entityManager.createQuery("from Person", Person.class);
            List<Person> persons = query.getResultList();
            for (Person person: persons) {
                StringBuilder sb = new StringBuilder();
                sb.append(person.getFirstName()).append(" ").append(person.getLastName());
                if (person instanceof Geek) {
                    sb.append(" ").append(((Geek) person).getFavouriteProgrammingLanguage());
                }
                LOGGER.info(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void relationships(EntityManager entityManager) {
        List<Geek> resultList = entityManager.createQuery("from Geek g where g.favouriteProgrammingLanguage = :fpl")
                .setParameter("fpl", "Java").getResultList();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Project project = new Project();
            project.setTitle("Java Project");
            for (Geek geek: resultList) {
                project.getGeeks().add(geek);
                geek.getProjects().add(project);
            }
            entityManager.persist(project);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive())
                transaction.rollback();
            e.printStackTrace();
        }
    }
}
