package edu.mum.cs544;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class BookService {
    @Resource
    private IBookDao bookDao;

    public List<Book> getAll() {
        return bookDao.findAll();
    }

    public void add(Book book) {
        bookDao.save(book);
    }

    public Book get(int id) {
        return (Book) bookDao.getOne(id);
//        return (Book) bookDao.findById(id).get();
    }

    public void update(Book book) {
        bookDao.save(book);
    }

    public void delete(int id) {
        bookDao.deleteById(id);
    }
}
