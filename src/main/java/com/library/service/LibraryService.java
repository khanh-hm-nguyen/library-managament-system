package com.library.service;

import com.library.model.LibraryItem;
import com.library.model.User;
import com.library.repository.ItemRepository;
import com.library.exception.LibraryException;

import java.util.List;

public class LibraryService {

    // The service needs the repository to talk to the database
    private ItemRepository itemRepository;

    // Constructor Injection: We pass the repository in when we start the service
    public LibraryService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // 1. Borrowing Logic
    public void borrowItem(String itemId, User user) throws LibraryException {
        LibraryItem item = itemRepository.findById(itemId);

        if (item == null) {
            throw new LibraryException("Item not found with ID: " + itemId);
        }

        if (item.isBorrowed()) {
            throw new LibraryException("Item is already borrowed.");
        }

        if (!user.canBorrow()) {
            throw new LibraryException("User has reached borrowing limit.");
        }

        item.setBorrowed(true);
        user.addItem(item);
        itemRepository.save(item);

    }

    // 2. Returning Logic
    public void returnItem(String itemId, User user) {
        LibraryItem item = itemRepository.findById(itemId);

        if (item == null) {
            System.out.println("Error: Item not found.");
            return;
        }

        item.setBorrowed(false); // Mark as available
        user.removeItem(item);   // Remove from user's list

        itemRepository.save(item); // Update database

        System.out.println("Success: Returned '" + item.getTitle() + "'");
    }

    public List<LibraryItem> getAllItems() { return itemRepository.findAll(); }

    public List<LibraryItem> searchByTitle(String title) {
        return itemRepository.findByTitle(title);
    }
}