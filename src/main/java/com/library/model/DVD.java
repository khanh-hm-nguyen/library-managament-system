package com.library.model;

public class DVD extends LibraryItem {
   private String director;
   private int duration;

    public DVD(String id, String title, String location, String director, int duration) {
        super(id, title, location);
        this.director = director;
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return super.toString() + " DVD{" +
                "director='" + director + '\'' +
                ", duration=" + duration +
                '}';
    }
}
