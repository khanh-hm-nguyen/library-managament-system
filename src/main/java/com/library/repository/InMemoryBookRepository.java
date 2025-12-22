package com.library.repository;

import com.library.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryBookRepository implements BookRepository {

    private Map<String, Book> bookDb = new HashMap<>();

    @Override
    public void save(Book book) {
        bookDb.put(book.getId(), book);
    }

    @Override
    public Book findById(String id) {
        return bookDb.get(id);
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(bookDb.values());
    }

    @Override
    public void delete(String id) {
        bookDb.remove(id);
    }
}
