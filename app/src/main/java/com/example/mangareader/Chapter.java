package com.example.mangareader;

public class Chapter {

    private String number;
    private String title;
    private String id;

    public Chapter(String number, String title, String id) {
        this.number = number;
        this.title = title;
        this.id = id;
    }

    public String getNumber() {
        String num = "";
        for (int i = 0 ; i < this.number.length() ; i++){
            if(this.number.charAt(i)=='['||this.number.charAt(i)==']'){
                continue;
            }
            num+=this.number.charAt(i);
        }
        return num;
    }

    public String getTitle() {
        String title = "";
        for (int i = 0 ; i < this.title.length() ; i++){
            if(this.title.charAt(i)=='['||this.title.charAt(i)==']'){
                continue;
            }
            title+=this.title.charAt(i);
        }
        return title;
    }

    public String getId() {
        String id = "";
        for (int i = 0 ; i < this.id.length() ; i++){
            if(this.id.charAt(i)=='['||this.id.charAt(i)==']'){
                continue;
            }
            id+=this.number.charAt(i);
        }
        return id;
    }
}
