# Book Library — DAO Pattern

Лабораторная работа № 3 — паттерн DAO (Data Access Object).

## Описание

Десктопное JavaFX-приложение для управления библиотекой книг.  
MVP-спринт: единственный источник данных — коллекция в памяти (`ListBookDAO`).

## Функционал

- Просмотр списка книг в таблице
- Добавление новой книги
- Удаление выбранной книги
- Смена статуса книги (Доступна ↔ Выдана)
- Логирование всех операций в `app.log` и в окне приложения

## Архитектура

```
MVC + DAO
├── model/      Book.java
├── dao/        BookDAO (interface), ListBookDAO
├── factory/    BookFactory
├── logger/     AppLogger (Singleton)
├── controller/ BookController
└── HelloController.java (FXML)
```

## Запуск

```bash
mvn javafx:run
```

## Тесты

```bash
mvn test
```

## Ветки Git

| Ветка | Содержание |
|-------|-----------|
| `main` | финальная версия |
| `mvp`  | коллекция в памяти + рабочий интерфейс |

## Конфигурация

Файл `config.properties` — не включён в репозиторий (см. `.gitignore`).  
Создайте его вручную:

```properties
datasource.type=Память
```
