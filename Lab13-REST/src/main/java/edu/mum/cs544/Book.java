package edu.mum.cs544;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank
    @SafeHtml
    private String title;
    @ISBN
    private String ISBN;
    @NotBlank
    @SafeHtml
    private String author;
    @Positive
    private double price;

    public Book() {
    }

    public Book(String title, String isbn, String author, double price) {
        super();
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
