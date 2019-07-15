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
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date publishedDate;

    public Book() {
    }

    public Book(String title, String ISBN, String author, double price, Date publishedDate) {
        super();
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.price = price;
        this.publishedDate = publishedDate;
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

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(book.price, price) == 0 &&
                title.equals(book.title) &&
                ISBN.equals(book.ISBN) &&
                author.equals(book.author) &&
                publishedDate.equals(book.publishedDate);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((ISBN == null) ? 0 : ISBN.hashCode());
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = (int) (prime * result + price);
        result = prime * result + ((publishedDate == null) ? 0 : publishedDate.hashCode());
        return result;
    }

}
