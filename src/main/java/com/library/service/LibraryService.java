package com.library.service;

import com.library.model.LibraryItem;
import com.library.model.User;
import com.library.repository.ItemRepository;

public class LibraryService {

    // The service needs the repository to talk to the database
    private ItemRepository itemRepository;

    // Constructor Injection: We pass the repository in when we start the service
    public LibraryService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // 1. Borrowing Logic
    public void borrowItem(String itemId, User user) {
        // Step A: Find the item
        LibraryItem item = itemRepository.findById(itemId);

        // Step B: Validation Checks
        if (item == null) {
            System.out.println("Error: Item not found with ID: " + itemId);
            return;
        }

        if (item.isBorrowed()) {
            System.out.println("Error: Item is already borrowed by someone else.");
            return;
        }

        if (!user.canBorrow()) {
            System.out.println("Error: User has reached their borrowing limit (Max 3).");
            return;
        }

        // Step C: If we pass all checks, perform the action
        item.setBorrowed(true); // Mark item as taken
        user.addItem(item);     // Add to user's personal list

        // Step D: SAVE the changes back to the repository
        itemRepository.save(item);

        System.out.println("Success: " + user.getName() + " borrowed '" + item.getTitle() + "'");
    }

    // 2. Returning Logic
    public void returnItem(String itemId, User user) {
        LibraryItem item = itemRepository.findById(itemId);

        if (item == null) {
            System.out.println("Error: Item not found.");
            return;
        }

        // You might want to check if THIS specific user actually has the book,
        // but for now, we will keep it simple.

        item.setBorrowed(false); // Mark as available
        user.removeItem(item);   // Remove from user's list

        itemRepository.save(item); // Update database

        System.out.println("Success: Returned '" + item.getTitle() + "'");
    }
}