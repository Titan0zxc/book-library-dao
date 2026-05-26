package org.example.library.controller;

import org.example.library.dao.BookDAO;
import org.example.library.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookControllerTest {

    private BookDAO mockDao;
    private BookController controller;

    @BeforeEach
    void setUp() {
        mockDao = Mockito.mock(BookDAO.class);
        when(mockDao.getAll()).thenReturn(List.of(
                new Book(1, "Тест", "Автор", 2024, "Доступна"),
                new Book(2, "Другая", "Автор2", 2020, "Выдана")
        ));
        controller = new BookController(mockDao);
    }

    @Test
    void testLoadAll_CallsGetAll() {
        List<Book> books = controller.loadAll();
        verify(mockDao, times(2)).getAll(); // 1 в конструкторе getNextId нет, 1 в loadAll
        assertEquals(2, books.size());
    }

    @Test
    void testAddBook_CallsAdd() {
        Book newBook = new Book(3, "Новая", "Новый", 2025, "Доступна");
        controller.addBook(newBook);
        verify(mockDao).add(newBook);
    }

    @Test
    void testDeleteBook_CallsDelete() {
        when(mockDao.getById(1)).thenReturn(new Book(1, "Тест", "Автор", 2024, "Доступна"));
        controller.deleteBook(1);
        verify(mockDao).delete(1);
    }

    @Test
    void testToggleStatus_FromDostupna_ToVidana() {
        Book book = new Book(1, "Тест", "Автор", 2024, "Доступна");
        controller.toggleStatus(book);
        assertEquals("Выдана", book.getStatus());
        verify(mockDao).update(book);
    }

    @Test
    void testToggleStatus_FromVidana_ToDostupna() {
        Book book = new Book(2, "Другая", "Автор2", 2020, "Выдана");
        controller.toggleStatus(book);
        assertEquals("Доступна", book.getStatus());
    }
}
