package org.example.library.dao;

import org.example.library.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListBookDAOTest {

    private ListBookDAO dao;

    @BeforeEach
    void setUp() {
        dao = new ListBookDAO();
    }

    @Test
    void testGetAll_ReturnsNonEmptyList() {
        List<Book> books = dao.getAll();
        assertFalse(books.isEmpty(), "Список книг не должен быть пустым");
    }

    @Test
    void testAdd_IncreasesSize() {
        int sizeBefore = dao.getAll().size();
        dao.add(new Book(99, "Тест", "Автор", 2024, "Доступна"));
        assertEquals(sizeBefore + 1, dao.getAll().size());
    }

    @Test
    void testGetById_ReturnsCorrectBook() {
        Book book = dao.getById(1);
        assertNotNull(book);
        assertEquals(1, book.getId());
    }

    @Test
    void testDelete_RemovesBook() {
        int sizeBefore = dao.getAll().size();
        dao.delete(1);
        assertEquals(sizeBefore - 1, dao.getAll().size());
        assertNull(dao.getById(1));
    }

    @Test
    void testUpdate_ChangesStatus() {
        Book book = dao.getById(1);
        assertNotNull(book);
        book.setStatus("Выдана");
        dao.update(book);
        assertEquals("Выдана", dao.getById(1).getStatus());
    }
}
