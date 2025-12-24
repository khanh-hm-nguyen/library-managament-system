package com.library.model;

public class DVD extends LibraryItem {
    private String duration;

    public DVD(String id, String title, String location, String duration) {
        super(id, title, location);
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "DVD{" +
                "duration='" + duration + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
