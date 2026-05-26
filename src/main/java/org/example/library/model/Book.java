package org.example.library.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private String status;

    public Book(int id, String title, String author, int year, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.status = status;
    }

    public int getId()          { return id; }
    public String getTitle()    { return title; }
    public String getAuthor()   { return author; }
    public int getYear()        { return year; }
    public String getStatus()   { return status; }

    public void setId(int id)           { this.id = id; }
    public void setTitle(String title)  { this.title = title; }
    public void setAuthor(String author){ this.author = author; }
    public void setYear(int year)       { this.year = year; }
    public void setStatus(String status){ this.status = status; }
}
