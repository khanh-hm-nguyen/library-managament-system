package com.library.model;
import java.util.Objects;

public abstract class LibraryItem {
    private String id;
    private String title;
    private String location;
    private boolean isBorrowed;

    public LibraryItem(String id, String title, String location) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.isBorrowed = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryItem that = (LibraryItem) o;
        // only check ID, because ID should be unique
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "LibraryItem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", isBorrowed=" + isBorrowed +
                '}';
    }
}
