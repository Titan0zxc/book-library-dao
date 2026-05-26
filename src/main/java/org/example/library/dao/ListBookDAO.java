package org.example.library.dao;

import org.example.library.model.Book;
import java.util.ArrayList;
import java.util.List;

public class ListBookDAO implements BookDAO {

    private final List<Book> books = new ArrayList<>();

    public ListBookDAO() {
        // Начальные тестовые данные
        books.add(new Book(1, "Мастер и Маргарита",    "Булгаков",   1967, "Доступна"));
        books.add(new Book(2, "Преступление и наказание", "Достоевский", 1866, "Выдана"));
        books.add(new Book(3, "Война и мир",            "Толстой",    1869, "Доступна"));
        books.add(new Book(4, "1984",                   "Оруэлл",     1949, "Доступна"));
        books.add(new Book(5, "Процесс",                "Кафка",      1925, "Выдана"));
    }

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(books);
    }

    @Override
    public Book getById(int id) {
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void add(Book book) {
        books.add(book);
    }

    @Override
    public void update(Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == book.getId()) {
                books.set(i, book);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        books.removeIf(b -> b.getId() == id);
    }
}
