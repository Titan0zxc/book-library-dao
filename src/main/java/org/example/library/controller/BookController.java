package org.example.library.controller;

import org.example.library.dao.BookDAO;
import org.example.library.logger.AppLogger;
import org.example.library.model.Book;

import java.util.List;

public class BookController {

    private final BookDAO dao;
    private final AppLogger logger = AppLogger.getInstance();

    public BookController(BookDAO dao) {
        this.dao = dao;
        logger.log("СТАРТ", "Контроллер инициализирован, источник: " + dao.getClass().getSimpleName());
    }

    public List<Book> loadAll() {
        List<Book> books = dao.getAll();
        logger.log("ЗАГРУЗКА", "Загружено книг: " + books.size());
        return books;
    }

    public void addBook(Book book) {
        dao.add(book);
        logger.log("ДОБАВЛЕНИЕ", "Книга: \"" + book.getTitle() + "\" id=" + book.getId());
    }

    public void deleteBook(int id) {
        Book book = dao.getById(id);
        dao.delete(id);
        String title = (book != null) ? book.getTitle() : "id=" + id;
        logger.log("УДАЛЕНИЕ", "Книга: \"" + title + "\"");
    }

    public void toggleStatus(Book book) {
        String newStatus = "Доступна".equals(book.getStatus()) ? "Выдана" : "Доступна";
        book.setStatus(newStatus);
        dao.update(book);
        logger.log("СТАТУС", "Книга: \"" + book.getTitle() + "\" → " + newStatus);
    }

    public int getNextId() {
        return dao.getAll().stream()
                .mapToInt(Book::getId)
                .max()
                .orElse(0) + 1;
    }
}
