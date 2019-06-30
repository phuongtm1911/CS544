package edu.mum.cs544;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("cs544");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Laptop l1 = new Laptop("Thinkpad");
        Laptop l2 = new Laptop("Dell");
        Employee e = new Employee("Phoebe");
        e.addLaptop(l1);
        e.addLaptop(l2);

        em.persist(e);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Employee> query1 = em.createQuery("from Employee", Employee.class);
        List<Employee> employeeList = query1.getResultList();
        for (Employee employee : employeeList) {
            System.out.println("name= " + employee.getName());
            for (Laptop laptop : employee.getLaptops()) {
                System.out.println("laptop= " + laptop.getName());
            }
        }

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        Passenger p1 = new Passenger("Phoebe");
        Passenger p2 = new Passenger("Phoenix");
        Flight f = new Flight("AA1234");
        f.addPassenger(p1);
        f.addPassenger(p2);

        em.persist(f);

        TypedQuery<Flight> query2 = em.createQuery("from Flight", Flight.class);
        List<Flight> flightList = query2.getResultList();
        for (Flight flight : flightList) {
            System.out.println("name= " + flight.getName());
            for (Passenger passenger : flight.getPassengers()) {
                System.out.println("passenger= " + passenger.getName());
            }
        }

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        Student s1 = new Student(1, "Phoebe", "phoebe@gmail.com", "12345678");
        Student s2 = new Student(2, "Phoenix", "phoenix@gmail.com", "12345678");
        School s = new School("MUM");
        s.addStudent(s1);
        s.addStudent(s2);

        em.persist(s);

        TypedQuery<School> query3 = em.createQuery("from School", School.class);
        List<School> schoolList = query3.getResultList();
        for (School school : schoolList) {
            System.out.println("name= " + school.getName());
            Set set = school.getStudents().entrySet();
            Iterator i = set.iterator();
            while (i.hasNext()) {
                Map.Entry entry = (Map.Entry) i.next();
                Student student = (Student) entry.getValue();
                System.out.println("Student: " + student.getName());
            }
        }

        em.getTransaction().commit();
        em.close();
    }
}
