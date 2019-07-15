package edu.mum.cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Client implements CommandLineRunner {
    @Autowired
    private BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        Book b = bookService.get(1);
        bookService.add(new Book("If Tomorrow Comes", "978-0-306-40615-7", "Sidney Sheldon", 2.92));
        System.out.println(bookService.getAll());
        b.setTitle("Nothing Lasts Forever");
        b.setIsbn("978-0-306-40615-7");
        bookService.update(b);
        System.out.println(bookService.getAll());
        bookService.delete(3);
        System.out.println(bookService.getAll());
        b = bookService.getAll().get(0);
        System.out.println(b.getTitle());
    }
}
