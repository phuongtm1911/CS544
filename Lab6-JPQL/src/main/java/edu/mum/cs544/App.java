package edu.mum.cs544;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import edu.mum.cs544.model.Airline;
import edu.mum.cs544.model.Flight;
import java.text.DateFormat;
import java.util.Locale;

import javax.persistence.*;

public class App {

	private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");
        		
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // a) TODO: Flights leaving USA capacity > 500
        System.out.println("Question A:");
        List<Flight> flights = em.createQuery("from Flight as f join f.airline as a " +
                "where a.capcity > 500", Flight.class).getResultList();
        System.out.printf("%-9s%-31s%-31s\n", "Flight:", "Departs:",
                "Arrives:");
        for (Flight flight : flights) {
            System.out.printf("%-7s  %-12s %7s %8s  %-12s %7s %8s\n",
                    flight.getFlightnr(), flight.getOrigin().getCity(),
                    flight.getDepartureDate(), flight.getDepartureTime(),
                    flight.getDestination().getCity(),
                    flight.getArrivalDate(), flight.getArrivalTime());
        }
        System.out.println();

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        // b) TODO: All airlines that use A380 airplanes
        System.out.println("Question B:");
        List<Airline> airlines = em.createQuery("from Airline as al join al.flights as f, " +
                "join f.airplane ap where ap.model = 'A380'", Airline.class).getResultList();
        System.out.println("Airlines that use A380s:");
        for (Airline airline : airlines) {
            System.out.println(airline.getName());
        }
        System.out.println();

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        // c) TODO: Flights using 747 planes that don't belong to Star Alliance
        System.out.println("Question C:");
        flights = em.createQuery("from Flight as f join f.airplane as ap, join f.airline as al "
                + "where ap.model = '747' and al.name = 'Star Alliance'", Flight.class).getResultList();
        System.out.printf("%-9s%-31s%-31s\n", "Flight:", "Departs:",
                "Arrives:");
        for (Flight flight : flights) {
            System.out.printf("%-7s  %-12s %7s %8s  %-12s %7s %8s\n",
                    flight.getFlightnr(), flight.getOrigin().getCity(),
                    flight.getDepartureDate(), flight.getDepartureTime(),
                    flight.getDestination().getCity(),
                    flight.getArrivalDate(), flight.getArrivalTime());
        }
        System.out.println();

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
                Locale.US);
        DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT,
                Locale.US);

        // d) TODO: All flights leaving before 12pm on 08/07/2009
        System.out.println("Question D:");
        Date date = Date.valueOf("2009-07-08");
        Time time = Time.valueOf("12:00:00");
        TypedQuery<Flight> query = em.createQuery("from Flight as f " +
                "where f.departureDate < :date and f.departureTime < :time", Flight.class);
        query.setParameter("date", date, TemporalType.DATE);
        query.setParameter("time", time, TemporalType.TIME);
        flights = query.getResultList();
        System.out.printf("%-9s%-31s%-31s\n", "Flight:", "Departs:",
                "Arrives:");
        for (Flight flight : flights) {
            System.out.printf("%-7s  %-12s %7s %8s  %-12s %7s %8s\n",
                    flight.getFlightnr(), flight.getOrigin().getCity(),
                    flight.getDepartureDate(), flight.getDepartureTime(),
                    flight.getDestination().getCity(),
                    flight.getArrivalDate(), flight.getArrivalTime());
        }
        System.out.println();
        em.getTransaction().commit();
        em.close();
    }
}
