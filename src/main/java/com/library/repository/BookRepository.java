package com.library.repository;

import com.library.model.Book;

import java.util.List;

public interface BookRepository {
    void save(Book book);
    Book findById(String id);
    List<Book> findAll();
    void delete(String id);
}
