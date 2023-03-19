package org.example.collections;

public class Film {
    private String title;
    private int year;

    public Film(){
    }

    public Film (String title, int year) {
        this.title = title;
        this.year = year;
    }

    public int getYear () { return this.year; }
    public String getTitle () { return this.title; }

    @Override
    public String toString () {
        return "Film {" +
                "title='" + title + '\'' +
                ", year=" + year +
                " }";
    }
}
