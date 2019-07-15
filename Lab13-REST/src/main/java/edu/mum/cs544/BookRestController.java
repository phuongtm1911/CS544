package edu.mum.cs544;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BookRestController {
    @Resource
    private BookService bookService;

    @GetMapping(value = "/books", produces = "application/json")
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping(value = "/books/{id}", produces = "application/json")
    public Book get(@PathVariable int id) {
        return bookService.get(id);
    }

    @PostMapping(value = "/books")
    public RedirectView add(@RequestBody Book book) {
        int id = bookService.add(book);
        return new RedirectView("/books/" + id);
    }

    @PutMapping(value = "/books/{id}")
    public void update(@PathVariable int id, @RequestBody Book book) {
        if (id != book.getId()) {
            throw new IllegalArgumentException();
        }
        bookService.update(book);
    }

    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable int id) {
        bookService.delete(id);
    }
}
