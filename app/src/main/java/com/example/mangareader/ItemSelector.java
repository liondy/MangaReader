package com.example.mangareader;

import java.util.ArrayList;

public interface ItemSelector {
    public static final int HOME = 0;
    public static final int BOOKMARKS = 1;
    public static final int LIKES = 2;
    public static final int PAGES = 3;
    public static final int INFO = 4;
    public final int ITEM_LIMIT = 4;
    void itemSelect(int selectedID,boolean ispop);
    void setManga(Manga manga);
    void setPages(ArrayList<ChapterPages> pages);
    void loadInfo();
    void finishInfo();
    void loadPages();
    void finishPages();
}
