package com.library.repository;

import com.library.model.LibraryItem;
import java.util.ArrayList; // Import this!
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryItemRepository implements ItemRepository {

    private Map<String, LibraryItem> itemDb = new HashMap<>();

    @Override
    public void save(LibraryItem item) {
        itemDb.put(item.getId(), item);
    }

    @Override
    public LibraryItem findById(String id) {
        return itemDb.get(id);
    }


    @Override
    public List<LibraryItem> findAll() {
        // convert into a List.
        return new ArrayList<>(itemDb.values());
    }

    @Override
    public void delete(String id) {
        // Map.remove(Key) deletes the entry
        itemDb.remove(id);
    }

    @Override
    public List<LibraryItem> findByTitle(String title) {
        List<LibraryItem> results = new ArrayList<>();

        // Loop through all items in the database
        for (LibraryItem item : itemDb.values()) {
            // Check if the item title contains the search text (Case Insensitive)
            if (item.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }
}