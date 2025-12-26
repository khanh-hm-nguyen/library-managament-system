package com.library.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String id;
    private String name;

    private List<LibraryItem> borrowedItems;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        // Initialize the list here to avoid NullPointerException
        this.borrowedItems = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Updated Getters/Setters to handle LibraryItem
    public List<LibraryItem> getBorrowedItems() {
        return borrowedItems;
    }

    public void setBorrowedItems(List<LibraryItem> borrowedItems) {
        this.borrowedItems = borrowedItems;
    }

    public boolean canBorrow() {
            // checking the list size
        return borrowedItems.size() < 3;
    }

    public void addItem(LibraryItem item) {
        borrowedItems.add(item);
    }

    public void removeItem(LibraryItem item) {
        borrowedItems.remove(item);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", borrowedItems=" + borrowedItems +
                '}';
    }
}