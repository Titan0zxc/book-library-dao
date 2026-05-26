# Book Library — DAO Pattern

Лабораторная работа №3 — паттерн **DAO (Data Access Object)**.  
Десктопное JavaFX-приложение для управления библиотекой книг.

---

## Содержание

- [Описание](#описание)
- [Функционал](#функционал)
- [Архитектура](#архитектура)
- [Диаграммы](#диаграммы)
- [Запуск](#запуск)
- [Тесты](#тесты)
- [Ветки Git](#ветки-git)
- [Конфигурация](#конфигурация)

---

## Описание

Система управления каталогом книг. Реализует паттерн **DAO** для разделения бизнес-логики и доступа к данным.  
MVP-спринт: источник данных — коллекция в памяти (`ListBookDAO`).

---

## Функционал

| Действие | Описание |
|----------|----------|
| Просмотр | Список всех книг в таблице |
| Добавление | Форма с полями Название / Автор / Год |
| Удаление | Удаление выбранной строки |
| Статус | Переключение «Доступна» ↔ «Выдана» |
| Логирование | Все операции пишутся в `app.log` и в TextArea UI |

---

## Архитектура

Многослойная архитектура **MVC + DAO**:

```
src/main/java/org/example/library/
├── model/
│   └── Book.java               # Сущность — книга
├── dao/
│   ├── BookDAO.java            # Интерфейс доступа к данным
│   └── ListBookDAO.java        # Реализация: коллекция в памяти
├── factory/
│   └── BookFactory.java        # Фабрика DAO по типу источника
├── logger/
│   └── AppLogger.java          # Синглтон-логгер → app.log
├── controller/
│   └── BookController.java     # Бизнес-логика + вызовы логгера
├── HelloController.java        # FXML-контроллер (UI)
└── HelloApplication.java       # Точка входа JavaFX
```

---

## Диаграммы

### Use Case

```plantuml
@startuml
left to right direction
skinparam actorStyle awesome

actor "Библиотекарь" as user

rectangle "Book Library" {
    usecase "Просмотр списка книг" as UC1
    usecase "Добавление книги"    as UC2
    usecase "Удаление книги"      as UC3
    usecase "Изменение статуса"   as UC4
    usecase "Логирование"         as UC5
}

user --> UC1
user --> UC2
user --> UC3
user --> UC4

UC1 .> UC5 : <<include>>
UC2 .> UC5 : <<include>>
UC3 .> UC5 : <<include>>
UC4 .> UC5 : <<include>>
@enduml
```

---

### Диаграмма классов

```plantuml
@startuml
skinparam classAttributeIconSize 0

interface BookDAO {
    + getAll(): List<Book>
    + getById(id: int): Book
    + add(book: Book): void
    + update(book: Book): void
    + delete(id: int): void
}

class Book {
    - id: int
    - title: String
    - author: String
    - year: int
    - status: String
    + getters/setters
}

class ListBookDAO {
    - books: List<Book>
    + getAll(): List<Book>
    + getById(id: int): Book
    + add(book: Book): void
    + update(book: Book): void
    + delete(id: int): void
}

class BookFactory {
    + {static} MEMORY: String
    + {static} create(type: String): BookDAO
}

class BookController {
    - dao: BookDAO
    - logger: AppLogger
    + loadAll(): List<Book>
    + addBook(book: Book): void
    + deleteBook(id: int): void
    + toggleStatus(book: Book): void
    + getNextId(): int
}

class AppLogger {
    - {static} instance: AppLogger
    - {static} getInstance(): AppLogger
    + log(action: String, details: String): void
}

class HelloController {
    - controller: BookController
    + initialize(): void
    + onLoad(): void
    + onAdd(): void
    + onDelete(): void
    + onToggleStatus(): void
}

ListBookDAO   ..|> BookDAO
BookFactory   ..>  BookDAO       : creates
BookController -->  BookDAO      : uses
BookController -->  AppLogger    : uses
HelloController --> BookController : uses
BookController ..>  Book
@enduml
```

---

### Контекстная диаграмма

```plantuml
@startuml
skinparam rectangle {
    BorderColor #4CAF50
    BackgroundColor #E8F5E9
}

actor "Библиотекарь" as user

rectangle "Book Library\n[JavaFX Desktop]" as system

database "Коллекция\nв памяти" as mem
file     "app.log"              as log
file     "config.properties"    as cfg

user   --> system : команды (CRUD)
system --> user   : данные / UI
system --> mem    : чтение / запись
system --> log    : запись событий
cfg    --> system : тип источника данных
@enduml
```

---

### ER-диаграмма

```plantuml
@startuml
entity "BOOK" {
    * id        : INT       <<PK>>
    --
    title       : VARCHAR
    author      : VARCHAR
    year        : INT
    status      : VARCHAR
}

entity "LOG_ENTRY" {
    * id        : INT       <<PK>>
    --
    timestamp   : DATETIME
    action      : VARCHAR
    details     : VARCHAR
}

entity "CONFIG" {
    * key       : VARCHAR   <<PK>>
    --
    value       : VARCHAR
}

BOOK        ||--o{ LOG_ENTRY : triggers
CONFIG      }|--|| BOOK      : "configures source"
@enduml
```

---

## Запуск

### Через Maven

```bash
mvn javafx:run
```

### Через IntelliJ IDEA

Run/Debug Configurations → Main class:

```
org.example.library.HelloApplication
```

---

## Тесты

```bash
mvn test
```

| Тест-класс | Что проверяет |
|-----------|--------------|
| `ListBookDAOTest` | CRUD операции над коллекцией |
| `BookControllerTest` | Логика контроллера с Mockito-моком |

---

## Ветки Git

| Ветка | Содержание |
|-------|-----------|
| `main` | Финальная версия |
| `mvp`  | Коллекция в памяти + рабочий JavaFX UI |

### Рекомендуемые коммиты

```
feat: add Book model
feat: add BookDAO interface
feat: implement ListBookDAO
feat: add BookFactory
feat: add AppLogger singleton
feat: add BookController
feat: add JavaFX UI (HelloController + FXML)
test: add unit tests for DAO and Controller
docs: add README with diagrams
```

---

## Конфигурация

Файл `config.properties` **не включён в репозиторий** (добавлен в `.gitignore`).  
Создайте вручную в корне проекта:

```properties
# Тип источника данных
datasource.type=Память
```
