package com.library.controller;

import com.library.model.LibraryItem;
import com.library.model.User;
import com.library.service.LibraryService;
import com.library.ui.ConsoleUI;
import java.util.List;

public class LibraryController {

    private LibraryService libraryService;
    private ConsoleUI ui;
    private User currentUser; // The person currently using the app

    // pass in the dependencies so the controller can use them
    public LibraryController(LibraryService libraryService, ConsoleUI ui, User currentUser) {
        this.libraryService = libraryService;
        this.ui = ui;
        this.currentUser = currentUser;
    }

    public void start() {
        boolean running = true;

        ui.showMessage("Welcome, " + currentUser.getName() + "!");

        while (running) {
            ui.showMainMenu();
            String choice = ui.askForInput(""); // Empty prompt because menu already shows it

            switch (choice) {
                case "1":
                    listAllItems();
                    break;
                case "2":
                    borrowWorkflow();
                    break;
                case "3":
                    returnWorkflow();
                    break;
                case "4":
                    running = false;
                    ui.showMessage("Goodbye!");
                    break;
                default:
                    ui.showMessage("Invalid option. Please try again.");
            }
        }
        ui.close();
    }

    private void listAllItems() {
        ui.showMessage("--- Library Catalog ---");

        // 1. Get the list from the service
        List<LibraryItem> items = libraryService.getAllItems();

        // 2. Check if the library is empty
        if (items.isEmpty()) {
            ui.showMessage("No items found in the library.");
        } else {
            // 3. Loop through and print each item
            for (LibraryItem item : items) {
                ui.showMessage(item.toString());
            }
        }
    }

    private void borrowWorkflow() {
        String itemId = ui.askForInput("Enter Item ID to borrow");
        libraryService.borrowItem(itemId, currentUser);
    }

    private void returnWorkflow() {
        String itemId = ui.askForInput("Enter Item ID to return");
        libraryService.returnItem(itemId, currentUser);
    }
}