package edu.mum.cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BookService {
    @Autowired
    private RestTemplate restTemplate;
    private final String bookUrl = "http://localhost:8080/books/{id}";
    private final String booksUrl = "http://localhost:8080/books";

    public List<Book> getAll() {
        ResponseEntity<List<Book>> response = restTemplate.exchange(booksUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() {
        });
        return response.getBody();
    }

    public int add(Book book) {
        URI uri = restTemplate.postForLocation(booksUrl, book);
        if (uri == null) {
            return 0;
        }
        Matcher m = Pattern.compile(".*/books/(\\d+)").matcher(uri.getPath());
        m.matches();
        return Integer.parseInt(m.group(1));
    }

    public Book get(int id) {
        return restTemplate.getForObject(bookUrl, Book.class, id);
    }

    public void update(Book book) {
        System.out.println(book);
        restTemplate.put(bookUrl, book, book.getId());
    }

    public void delete(int id) {
        restTemplate.delete(bookUrl, id);
    }
}
