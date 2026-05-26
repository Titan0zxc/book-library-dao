package org.example.library.factory;

import org.example.library.dao.BookDAO;
import org.example.library.dao.ListBookDAO;

public class BookFactory {

    public static final String MEMORY = "Память";

    public static BookDAO create(String type) {
        if (MEMORY.equalsIgnoreCase(type)) {
            return new ListBookDAO();
        }
        throw new IllegalArgumentException("Неизвестный источник данных: " + type);
    }
}
