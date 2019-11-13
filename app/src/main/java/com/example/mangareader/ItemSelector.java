package com.example.mangareader;

public interface ItemSelector {
    public static final int HOME = 0;
    public static final int BOOKMARKS = 1;
    public static final int LIKES = 2;
    public final int ITEM_LIMIT = 4;
    void itemSelect(int selectedID);
}
