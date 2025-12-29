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

        // 1. Create the Storage (Repository)
        ItemRepository repository = new InMemoryItemRepository();

        // 2. Create the Logic (Service) and inject Storage
        LibraryService service = new LibraryService(repository);

        // 3. Create the UI
        ConsoleUI ui = new ConsoleUI();

        // 4. Create a Dummy User for testing
        User user = new User("U001", "Java Student");

        // 5. SEED DATA
        repository.save(new Book("B001", "The Great Gatsby", "F. Scott Fitzgerald", "Shelf A"));
        repository.save(new Book("B002", "Java Programming", "John Doe", "Shelf B"));
        repository.save(new DVD("D001", "Inception", "Shelf C", "Christopher Nolan", 148));

        // 6. Create Controller and inject Service, UI, and User
        LibraryController controller = new LibraryController(service, ui, user);

        // 7. START THE APP
        controller.start();
    }
}