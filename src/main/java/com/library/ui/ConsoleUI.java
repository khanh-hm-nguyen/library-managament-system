package com.library.ui;

import java.util.Scanner;

public class ConsoleUI {

    private Scanner scanner;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
    }

    // 1. Show the Main Menu
    public void showMainMenu() {
        System.out.println("\n=== LIBRARY SYSTEM ===");
        System.out.println("1. List All Items");
        System.out.println("2. Search Item");
        System.out.println("3. Borrow Item");
        System.out.println("4. Return Item");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    // 2. Generic method to ask the user for text
    public String askForInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    // 3. Display messages nicely
    public void showMessage(String message) {
        System.out.println(">> " + message);
    }

    // 4. Close the scanner when app stops
    public void close() {
        scanner.close();
    }
}