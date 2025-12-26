package com.library.service;

import com.library.model.Book;
import com.library.model.DVD;
import com.library.model.LibraryItem;
import com.library.model.User;
import com.library.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private final ItemRepository itemRepository;

    public LibraryService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Generic method to add ANY item
    public void addItem(LibraryItem item) {
        itemRepository.save(item);
    }


    public void borrowItem(String id, User user) {
        LibraryItem item = itemRepository.findById(id);

        if (item == null) {
            throw new IllegalArgumentException("Item not found");
        }

        if (item.isBorrowed()) {
            throw new IllegalStateException("Item is not available");
        }


        item.setBorrowed(true);

        itemRepository.save(item);

        System.out.println("Success! You borrowed: " + item.getTitle());
    }

    public List<LibraryItem> getAllItems() {
        return itemRepository.findAll();
    }

    public List<LibraryItem> searchItems(String query) {
        List<LibraryItem> results = new ArrayList<>();

        // Convert query to lowercase once to save processing
        String lowerCaseQuery = query.toLowerCase();

        List<LibraryItem> allItems = itemRepository.findAll();

        for (LibraryItem item : allItems) {
            boolean matches = false;

            // 1. Check the Title (Shared by all items)
            if (item.getTitle().toLowerCase().contains(lowerCaseQuery)) {
                matches = true;
            }

            // 2. If it's a BOOK, check the Author
            else if (item instanceof Book book) {
                if (book.getAuthor().toLowerCase().contains(lowerCaseQuery)) {
                    matches = true;
                }
            }

            // 3. If it's a DVD, check the Director
            else if (item instanceof DVD dvd) {
                if (dvd.getDirector().toLowerCase().contains(lowerCaseQuery)) {
                    matches = true;
                }
            }

            // If any condition matched, add to results
            if (matches) {
                results.add(item);
            }
        }

        return results;
    }
}