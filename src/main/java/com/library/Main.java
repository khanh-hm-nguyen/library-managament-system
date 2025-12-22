package com.library;

import com.library.model.Book;
import com.library.repository.InMemoryBookRepository;
import com.library.service.LibraryService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        InMemoryBookRepository repo = new InMemoryBookRepository();

        LibraryService service = new LibraryService(repo);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Library System!");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add a Book");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. View All Books");
            System.out.println("5. Exit");
            System.out.print("> ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1": {
                        System.out.println("Enter Book ID: ");
                        String id = scanner.nextLine();
                        System.out.println("Enter Book Title: ");
                        String title = scanner.nextLine();
                        System.out.println("Enter Book Author: ");
                        String author = scanner.nextLine();
                        service.addBook(id, title, author);

                        System.out.println("Booked added successfully");
                        break;
                    }

                    case "2": {
                        System.out.println("Enter Book ID to borrow: ");
                        String borrowId = scanner.nextLine();
                        service.borrowBook(borrowId);

                        System.out.println("Booked borrowed successfully");
                        break;
                    }

                    case "3": {
                        System.out.println("Enter Book ID to return: ");
                        String id = scanner.nextLine();
                        service.returnBook(id);

                        System.out.println("Booked returned successfully");
                        break;
                    }

                    case "4": {
                        System.out.println("Library Inventory");
                        for (Book book : service.getAllBooks()) {
                            System.out.println(book);
                        }
                        break;
                    }

                    case "5": {
                        System.out.println("Thannk You");
                        scanner.close();
                        return;
                    }

                    default: {
                        System.out.println("Invalid option. Please try again");
                    }

                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }
}
