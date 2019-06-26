package edu.mum.main;

import java.util.Calendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.domain.User;
import edu.mum.service.UserService;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("context/applicationContext.xml");
        UserService userService = (UserService) ctx.getBean("userServiceImpl");

        // Add a user
        Calendar c = Calendar.getInstance();
        c.set(2019, 5, 1);
        User user1 = new User("John", "Doe", "johndoe@gmail.com", 4, false, 1, c.getTime());
        userService.save(user1);

        // Lookup the user by email and print the user
        User foundUser1 = userService.findByEmail("johndoe@gmail.com");
        System.out.println("   ********** User **********   ");
        System.out.println("User Name: " + foundUser1.getFirstName() + " " + foundUser1.getLastName());

        // Create an entity
        c.set(2019, 5, 10);
        User user2 = new User("Phoebe", "Tran", "phoebe@gmail.com", 5, false, 3, c.getTime());
        userService.save(user2);
        User foundUser2 = userService.findByEmail("phoebe@gmail.com");
        System.out.println("Email: " + foundUser2.getEmail());

        // Update entity
        user2.setEmail("phoebetran@gmail.com");
        userService.update(user2);
        User foundUpdatedUser2 = userService.findByEmail("phoebetran@gmail.com");
        System.out.println("Email: " + foundUpdatedUser2.getEmail());

//        // Try updating the entity with the “unmanaged” version of the entity. Expected result: StaleObjectStateException
//        userService.detach(user2);
//        user2.setEmail("phoebe@gmail.com");
//        userService.update(user2);

        // Refresh
        c.set(2019, 5, 20);
        User user3 = new User("Phoenix", "Tran", "phoenix@gmail.com", 5, false, 3, c.getTime());
        userService.refresh(user3, "phoenixtran@gmail.com");

        // Flush
        c.set(2019, 5, 01);
        User user4 = new User("Phuong", "Tran", "phuong@gmail.com", 5, false, 3, c.getTime());
        userService.flush(user4, "phuong@gmail.com");

    }
  
}