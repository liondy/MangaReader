package com.example.mangareader;

public interface FragmentListener {
    public static final int SPLASH_SCREEN = 1;
    public static final int HOME_SCREEN = 2;
    void showPage(int page);
}
