package com.library.repository;

import com.library.model.LibraryItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryItemRepository implements ItemRepository {

    private Map<String, LibraryItem> itemDb = new HashMap<>();


    @Override
    public void save(LibraryItem item) {

    }

    @Override
    public LibraryItem findById(String id) {
        return null;
    }

    @Override
    public List<LibraryItem> findAll() {
        return List.of();
    }

    @Override
    public void delete(String id) {

    }
}
