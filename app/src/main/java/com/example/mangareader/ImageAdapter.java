package com.example.mangareader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<ChapterPages> chapterPagesList;
    private int i;
    private boolean flag = false;

    public ImageAdapter(ArrayList<ChapterPages> chapterPagesList, Context context, int i){
        this.chapterPagesList = chapterPagesList;
        this.context = context;
        this.i = i;
    }

    @Override
    public int getCount() {
        return this.chapterPagesList.size()-this.i;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        String imgUrl = "https://cdn.mangaeden.com/mangasimg/";
        if(position+this.i<this.chapterPagesList.size()){
            imgUrl += this.chapterPagesList.get(position+this.i).getImage();
        }
        Picasso.get().load(imgUrl).into(imageView);
        container.addView(imageView,0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }
}
