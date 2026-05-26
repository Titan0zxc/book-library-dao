package org.example.library.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppLogger {

    private static final String LOG_FILE = "app.log";
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static AppLogger instance;

    private AppLogger() {}

    public static AppLogger getInstance() {
        if (instance == null) {
            instance = new AppLogger();
        }
        return instance;
    }

    public void log(String action, String details) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String entry = String.format("[%s] %s — %s", timestamp, action, details);

        // Вывод в консоль
        System.out.println(entry);

        // Запись в файл
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println(entry);
        } catch (IOException e) {
            System.err.println("Ошибка записи лога: " + e.getMessage());
        }
    }
}
