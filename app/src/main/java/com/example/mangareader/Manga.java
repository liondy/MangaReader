package com.example.mangareader;

public class Manga {
    private String id;
    private String title;
    private String alias;
    private String category;
    private String status;
    private String image;
    private String rating;

    public Manga(String id, String title, String alias, String category, String status, String rating, String image) {
        this.id = id;
        this.title = title;
        this.alias = alias;
        this.category = category;
        this.status = status;
        this.rating = rating;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getAlias() {
        return alias;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        if(this.status.equals("0")){
            return "Suspended";
        }
        else if(this.status.equals("1")) {
            return "Ongoing";
        }
        else{
            return "Complete";
        }
    }

    public String getRating() {
        return rating;
    }
}
