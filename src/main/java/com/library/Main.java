package com.library; // Usually outside the subfolders

import com.library.controller.LibraryController;
import com.library.model.Book;
import com.library.model.DVD;
import com.library.model.User;
import com.library.repository.InMemoryItemRepository;
import com.library.repository.ItemRepository;
import com.library.service.LibraryService;
import com.library.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {

        // Create the Storage (Repository)
        ItemRepository repository = new InMemoryItemRepository();

        // Create the Logic (Service) and inject Storage
        LibraryService service = new LibraryService(repository);

        // Create the UI
        ConsoleUI ui = new ConsoleUI();

        // Create a Dummy User for testing
        User user = new User("U001", "Java Student");

        // Create Controller and inject Service, UI, and User
        LibraryController controller = new LibraryController(service, ui, user);

        // START THE APP
        controller.start();
    }
}