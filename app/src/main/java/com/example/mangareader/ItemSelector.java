package com.example.mangareader;

public interface ItemSelector {
    public static final int BOOKMARK = 0;
    public static final int LIKES = 1;
    public static final int SEARCH = 2;
    public static final int PROFILE = 3;
    void itemSelect(int selectedID);
}
