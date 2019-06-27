package edu.mum.cs544;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

public class AppBook {
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("cs544");

        // Create 3 books save them to the database
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Calendar c = Calendar.getInstance();
        c.set(1992, 9, 1);
        Book book1 = new Book("Stars Shine Down", "0-688-08490-7", "Sidney Sheldon", 2.92, c.getTime());
        em.persist(book1);
        c.set(1985, 0, 1);
        Book book2 = new Book("If Tomorrow Comes", "0-446-35742-1", "Sidney Sheldon", 3.99, c.getTime());
        em.persist(book2);
        c.set(2015, 9, 6);
        Book book3 = new Book("Chicken Soup for the Soul", "1611599520", "Jack Canfield", 9.99, c.getTime());
        em.persist(book3);

        em.getTransaction().commit();
        em.close();

        // Retrieve all books and output them to the console
        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Book> query1 = em.createQuery("from Book", Book.class);
        List<Book> bookList1 = query1.getResultList();

        for (Book book : bookList1) {
            System.out.println("title= " + book.getTitle() + ", isbn= " + book.getISBN() + ", author= " + book.getAuthor()
                + ", price= " + book.getPrice() + ", published date= " + book.getPublish_date());
        }

        em.getTransaction().commit();
        em.close();

        // Change the title and price of one book & delete a different book
        em = emf.createEntityManager();
        em.getTransaction().begin();

        Book updatedBook = em.find(Book.class, 3);
        updatedBook.setTitle("Chicken Soup for the Soul - Think Possible");
        updatedBook.setPrice(14.38);

        Book removedBook = em.find(Book.class, 2);
        em.remove(removedBook);

        em.getTransaction().commit();
        em.close();

        // Retrieve all books and output them to the console
        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Book> query2 = em.createQuery("from Book", Book.class);
        List<Book> bookList2 = query2.getResultList();

        for (Book book : bookList2) {
            System.out.println("title= " + book.getTitle() + ", isbn= " + book.getISBN() + ", author= " + book.getAuthor()
                    + ", price= " + book.getPrice() + ", published date= " + book.getPublish_date());
        }

        em.getTransaction().commit();
        em.close();
    }
}
