package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;

import java.util.List;

public class LibraryService {
    private final BookRepository bookRepository;

    public LibraryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String id, String title, String author) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be empty");
        }

        Book newBook = new Book(id, title, author);
        bookRepository.save(newBook);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void borrowBook(String id) {
        Book book = bookRepository.findById(id);

        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }

        if (book.isBorrowed()) {
            throw new IllegalArgumentException("Book is not available");
        }

        book.setBorrowed(true);
        bookRepository.save(book);
        System.out.println("Success! you borrowed: " + book.getTitle());
    }

    public void returnBook (String id) {
        Book book = bookRepository.findById(id);

        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }

        book.setBorrowed(false);

        bookRepository.save(book);
        System.out.println("Success! you returned: " + book.getTitle());
    }
}
