package com.example.mangareader;

public class ChapterPages {
    String numberPage;
    String image;

    public ChapterPages(String numberPage, String image) {
        this.numberPage = numberPage;
        this.image = image;
    }


    public String getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(String numberPage) {
        this.numberPage = numberPage;
    }

    public String getImage() {
        String img = "";
        for (int i = 0 ; i < this.image.length() ; i++){
            if(this.image.charAt(i)=='"'){
                continue;
            }
            img+=this.image.charAt(i);
        }
        return img;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
