package com.example.mangareader;

import java.util.ArrayList;

public class Manga {
    private String id;
    private String title;
    private String alias;
    private String category;
    private String status;
    private String image;
    private String rating;
    private String summary;
    private String author;
    private String chapter_len;
    private String released;
    private ArrayList<Chapter> chapterList;

    public Manga(String id, String title, String alias, String category, String status, String rating, String image) {
        this.id = id;
        this.title = title;
        this.alias = alias;
        this.category = category;
        this.status = status;
        this.rating = rating;
        this.image = image;
        this.summary = "";
        this.author = "";
        this.chapter_len = "";
        this.released = "";
        this.chapterList = new ArrayList();
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
        if(this.category.equals("[]")){
            return "-";
        }
        else{
            String category="";
            for (int i = 0 ; i < this.category.length() ; i++){
                if(this.category.charAt(i)=='['||this.category.charAt(i)==']'||this.category.charAt(i)=='"'){
                    continue;
                }
                else if(this.category.charAt(i)==','){
                    category+=this.category.charAt(i)+" ";
                }
                else{
                    category+=this.category.charAt(i);
                }
            }
            return category;
        }
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

    public void setSummary(String summary){
        this.summary = summary;
    }

    public String getSummary(){
        if(summary.equals("")){
            return "-";
        }
        return summary;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        if(this.author.equals("")){
            return "-";
        }
        return this.author;
    }

    public String getChapter_len() {
        return chapter_len;
    }

    public void setChapter_len(String chapter_len) {
        this.chapter_len = chapter_len;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public ArrayList<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(ArrayList<Chapter> chapterList) {
        this.chapterList = chapterList;
    }
}
