package com.library.model;

public class Book extends LibraryItem {

   private String author;

    public Book(String id, String title, String author,String location) {
        super(id, title, location);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        // super.toString() gets the ID, Title, Location from the parent
        return super.toString() + " Book{" +
                "author='" + author + '\'' +
                '}';
    }
}
