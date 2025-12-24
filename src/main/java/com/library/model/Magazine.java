package com.library.model;

public class Magazine extends LibraryItem {
    private String type;

    public Magazine(String id, String title, String location, String type) {
        super(id, title, location);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "type='" + type + '\'' +
                '}';
    }
}
