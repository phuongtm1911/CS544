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

        Employee e1 = new Employee("Phoebe");
        Employee e2 = new Employee("Phoenix");
        Department d = new Department("IT");
        d.addEmployee(e1);
        d.addEmployee(e2);
        Office o = new Office("NextGen");
        o.addEmployee(e1);
        o.addEmployee(e2);

        em.persist(d);
        em.persist(o);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Department> query1 = em.createQuery("from Department", Department.class);
        List<Department> departmentList = query1.getResultList();
        for (Department department : departmentList) {
            System.out.println("name= " + department.getName());
            for (Employee employee : department.getEmployees()) {
                System.out.println("employee= " + employee.getName());
            }
        }

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        Publisher p = new Publisher("First News");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1992, 9, 1);
        Book b1 = new Book("Stars Shine Down", "0-688-08490-7", "Sidney Sheldon", 2.92, calendar.getTime(), p);
        em.persist(b1);
        calendar.set(1985, 0, 1);
        Book b2 = new Book("If Tomorrow Comes", "0-446-35742-1", "Sidney Sheldon", 3.99, calendar.getTime(), p);
        em.persist(b2);

        calendar.set(2019, 6, 1);
        Reservation r1 = new Reservation(calendar.getTime(), b1);
        calendar.set(2019, 7, 1);
        Reservation r2 = new Reservation(calendar.getTime(), b1);
        Customer c = new Customer("Phoebe");
        c.addReservation(r1);
        c.addReservation(r2);
        em.persist(c);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Book> query2 = em.createQuery("from Book", Book.class);
        List<Book> bookList = query2.getResultList();
        for (Book book : bookList) {
            System.out.println("title= " + book.getTitle() + ", isbn= " + book.getISBN() + ", author= " + book.getAuthor()
                    + ", price= " + book.getPrice() + ", published date= " + book.getPublish_date() + ", publisher= " + book.getPublisher().getName());
        }
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        Student s1 = new Student(1, "Phoebe", "phoebe@gmail.com", "12345678");
        Student s2 = new Student(2, "Phoenix", "phoenix@gmail.com", "12345678");
        Course c1 = new Course("MPP");
        Course c2 = new Course("WAP");
        c1.addStudent(s1);
        c1.addStudent(s2);
        s1.addCourse(c1);
        s1.addCourse(c2);

        em.persist(c1);
        em.persist(c2);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Course> query3 = em.createQuery("from Course", Course.class);
        List<Course> courseList = query3.getResultList();
        for (Course course : courseList) {
            System.out.println("name= " + course.getName());
            for (Student student : course.getStudents()) {
                System.out.println("Student= " + student.getName());
            }
        }

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Customer> query4 = em.createQuery("from Customer", Customer.class);
        List<Customer> customerList = query4.getResultList();
        for (Customer customer : customerList) {
            System.out.println("name= " + customer.getName());
            for (Reservation reservation : customer.getReservations()) {
                System.out.println("Reservation= " + reservation.getDate());
            }
        }

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Reservation> query5 = em.createQuery("from Reservation", Reservation.class);
        List<Reservation> reservationList = query5.getResultList();
        for (Reservation reservation : reservationList) {
            System.out.println("date= " + reservation.getDate() + ", book= " + reservation.getBook().getTitle());
        }

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Office> query6 = em.createQuery("from Office", Office.class);
        List<Office> officeList = query6.getResultList();
        for (Office office : officeList) {
            System.out.println("name= " + office.getName());
            for (Employee employee : office.getEmployees()) {
                System.out.println("employee= " + employee.getName());
            }
        }

        em.getTransaction().commit();
        em.close();
    }
}
