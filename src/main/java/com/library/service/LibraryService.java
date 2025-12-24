package com.library.service;

import com.library.model.Book;
import com.library.model.User;
import com.library.repository.BookRepository;

import java.util.ArrayList;
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

    public void borrowBook(String id, User user) {
        Book book = bookRepository.findById(id);

        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }

        if (book.isBorrowed()) {
            throw new IllegalStateException("Book is not available");
        }

        if (!user.canBorrow()) {
            throw new IllegalStateException("User " + user.getName() + " has reached the limit of 3 books.");
        }

        book.setBorrowed(true);
        user.addBook(book);
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

    public List<Book> searchBooks (String query) {
        List<Book> results = new ArrayList<>();

        List<Book> allBooks = bookRepository.findAll();

        for (Book book : allBooks) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(query.toLowerCase()) ) {
                results.add(book);
            }
        }

        return results;

    }


}
