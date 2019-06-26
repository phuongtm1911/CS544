package edu.mum.cs544;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class App {
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("cs544");

        // Retrieve all students from the database and display their names
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Students> query1 = em.createQuery("from edu.mum.cs544.Students", Students.class);
        List<Students> studentList1 = query1.getResultList();
        for (Students student : studentList1) {
            System.out.println("name= " + student.getName());
        }

        em.getTransaction().commit();
        em.close();

        // Add an extra student to the database
        em = emf.createEntityManager();
        em.getTransaction().begin();

        Students s = new Students(001, "Phoebe", "phoebe@gmail.com", "12345678");
        em.persist(s);

        em.getTransaction().commit();
        em.close();

        // Retrieve all students from the database and display their names
        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Students> query2 = em.createQuery("from edu.mum.cs544.Students", Students.class);
        List<Students> studentList2 = query2.getResultList();
        for (Students student : studentList2) {
            System.out.println("name= " + student.getName());
        }

        em.getTransaction().commit();
        em.close();
    }
}
