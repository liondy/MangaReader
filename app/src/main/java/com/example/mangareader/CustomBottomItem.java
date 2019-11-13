package com.example.mangareader;

public class CustomBottomItem {

    private int itemId;
    private int itemIconId;
    private String itemTitle;
    private String itemBackgroundColor;
    private String itemTintColor;
    private boolean isOpen;

    public CustomBottomItem(int itemId, int itemIconId, String itemTitle, String itemBackgroundColor, String itemTintColor) {
        this.itemId = itemId;
        this.itemIconId = itemIconId;
        this.itemTitle = itemTitle;
        this.itemBackgroundColor = itemBackgroundColor;
        this.itemTintColor = itemTintColor;
        this.isOpen = false;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemIconId() {
        return itemIconId;
    }

    public void setItemIconId(int itemIconId) {
        this.itemIconId = itemIconId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemBackgroundColor() {
        return itemBackgroundColor;
    }

    public void setItemBackgroundColor(String itemBackgroundColor) {
        this.itemBackgroundColor = itemBackgroundColor;
    }

    public String getItemTintColor() {
        return itemTintColor;
    }

    public void setItemTintColor(String itemTintColor) {
        this.itemTintColor = itemTintColor;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
