package org.example.library.dao;

import org.example.library.model.Book;
import java.util.List;

public interface BookDAO {
    List<Book> getAll();
    Book getById(int id);
    void add(Book book);
    void update(Book book);
    void delete(int id);
}
