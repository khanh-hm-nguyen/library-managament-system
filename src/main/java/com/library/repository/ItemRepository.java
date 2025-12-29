package com.library.repository;

import com.library.model.LibraryItem;

import java.util.List;

public interface ItemRepository {
    void save(LibraryItem item);
    LibraryItem findById(String id);
    List<LibraryItem> findAll();
    void delete(String id);

    List<LibraryItem> findByTitle(String title);
}
