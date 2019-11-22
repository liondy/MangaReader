package com.example.mangareader;

public class Chapter {

    private int number;
    private int title;
    private int id;

    public Chapter(int number, int title, int id) {
        this.number = number;
        this.title = title;
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public int getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }
}
