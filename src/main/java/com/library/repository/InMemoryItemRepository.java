package com.library.repository;

import com.library.model.Book;
import com.library.model.DVD;
import com.library.model.LibraryItem;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryItemRepository implements ItemRepository {

    private Map<String, LibraryItem> itemDb = new HashMap<>();
    private static final String FILE_PATH = "library_data.csv";

    // Constructor: Load data immediately when the app starts
    public InMemoryItemRepository() {
        loadData();
    }

    @Override
    public void save(LibraryItem item) {
        itemDb.put(item.getId(), item);
        saveData(); // Save to file every time we add/update something
    }

    @Override
    public LibraryItem findById(String id) {
        return itemDb.get(id);
    }

    @Override
    public List<LibraryItem> findAll() {
        return new ArrayList<>(itemDb.values());
    }

    @Override
    public List<LibraryItem> findByTitle(String title) {
        // (Copy your search logic from the previous step here)
        List<LibraryItem> results = new ArrayList<>();
        for (LibraryItem item : itemDb.values()) {
            if (item.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }

    @Override
    public void delete(String id) {
        itemDb.remove(id);
        saveData(); // Save updates
    }

    // --- FILE HANDLING METHODS ---

    private void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (LibraryItem item : itemDb.values()) {
                StringBuilder line = new StringBuilder();

                // Format: TYPE,ID,TITLE,LOCATION,IS_BORROWED,...
                if (item instanceof Book) {
                    Book b = (Book) item;
                    line.append("BOOK,").append(b.getId()).append(",")
                            .append(b.getTitle()).append(",")
                            .append(b.getLocation()).append(",")
                            .append(b.isBorrowed()).append(",")
                            .append(b.getAuthor());
                } else if (item instanceof DVD) {
                    DVD d = (DVD) item;
                    line.append("DVD,").append(d.getId()).append(",")
                            .append(d.getTitle()).append(",")
                            .append(d.getLocation()).append(",")
                            .append(d.isBorrowed()).append(",")
                            .append(d.getDirector()).append(",")
                            .append(d.getDuration());
                }
                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Could not save data: " + e.getMessage());
        }
    }

    private void loadData() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return; // No file? Nothing to load.

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Simple parsing logic
                String type = parts[0];
                String id = parts[1];
                String title = parts[2];
                String location = parts[3];
                boolean isBorrowed = Boolean.parseBoolean(parts[4]);

                LibraryItem item = null;
                if (type.equals("BOOK")) {
                    String author = parts[5];
                    item = new Book(id, title, author, location);
                } else if (type.equals("DVD")) {
                    String director = parts[5];
                    int duration = Integer.parseInt(parts[6]);
                    item = new DVD(id, title, location, director, duration);
                }

                if (item != null) {
                    item.setBorrowed(isBorrowed);
                    itemDb.put(item.getId(), item);
                }
            }
        } catch (IOException e) {
            System.err.println("Could not load data: " + e.getMessage());
        }
    }
}