package com.library.model;

public class Book {

    private String id;
    private String title;
    private String author;
    private boolean isBorrowed;

    // constructor
    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = isBorrowed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean status) {
        this.isBorrowed = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isBorrowed=" + isBorrowed +
                '}';
    }
}
