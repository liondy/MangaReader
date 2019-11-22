package com.example.mangareader;

public interface ItemSelector {
    public static final int HOME = 0;
    public static final int BOOKMARKS = 1;
    public static final int LIKES = 2;
    public static final int INFO = 4;
    public final int ITEM_LIMIT = 4;
    void itemSelect(int selectedID);
    void setManga(String image, String title, String released, String rank, String authors, String genres, String chapterLength, String status, String summary);
}
