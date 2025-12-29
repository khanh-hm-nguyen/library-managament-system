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
}