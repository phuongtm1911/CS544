package edu.mum.cs544;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.*;

public class App {
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("cs544");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Calendar calendar = Calendar.getInstance();
        Product cd = new CD("1989", "Album", "Taylor Swift");
        em.persist(cd);
        Product dvd = new DVD("Titanic", "Movie", "Romance");
        em.persist(dvd);
        Product b = new Book("Stars Shine Down - Sidney Sheldon", "Novel", "Stars Shine Down");
        em.persist(b);
        OrderLine ol1 = new OrderLine(100, cd);
        em.persist(ol1);
        OrderLine ol2 = new OrderLine(50, dvd);
        em.persist(ol2);
        OrderLine ol3 = new OrderLine(5, b);
        em.persist(ol3);
        calendar.set(2019, 5, 1);
        Order o1 = new Order(calendar.getTime());
        o1.addOrderLine(ol1);
        o1.addOrderLine(ol2);
        em.persist(o1);
        calendar.set(2019, 5, 10);
        Order o2 = new Order(calendar.getTime());
        o2.addOrderLine(ol3);
        em.persist(o2);
        Customer c = new Customer("Phoebe", "Tran");
        c.addOrder(o1);
        c.addOrder(o2);

        em.persist(c);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Customer> query = em.createQuery("from Customer", Customer.class);
        List<Customer> customerList = query.getResultList();
        for (Customer customer : customerList) {
            System.out.println("name= " + customer.getFirstname() + " " + customer.getLastname());
            for (Order order : customer.getOrders()) {
                System.out.println("date= " + order.getDate());
                for (OrderLine orderLine : order.getOrderLines()) {
                    if (orderLine.getProduct() instanceof CD) {
                        CD cdp = (CD) orderLine.getProduct();
                        System.out.print("cd= " + cdp.getName() + ", artist= " + cdp.getArtist());
                    }
                    if (orderLine.getProduct() instanceof DVD) {
                        DVD dvdp = (DVD) orderLine.getProduct();
                        System.out.print("dvd= " + dvdp.getName() + ", genre= " + dvdp.getGenre());
                    }
                    if (orderLine.getProduct() instanceof Book) {
                        Book book = (Book) orderLine.getProduct();
                        System.out.print("book= " + book.getTitle());
                    }
                    System.out.println(", quantity= " + orderLine.getQuantity());
                }
            }
        }

        em.getTransaction().commit();
        em.close();
    }
}
