package com.example.mangareader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<ChapterPages> chapterPagesList;
    private int i;

    public ImageAdapter(ArrayList<ChapterPages> chapterPagesList, Context context, int i){
        this.chapterPagesList = chapterPagesList;
        this.context = context;
        this.i = i;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View itemView = layoutInflater.inflate(R.layout.pager_item,container,false);
        PhotoView photoView = (PhotoView) itemView.findViewById(R.id.image);
        String imgUrl = "https://cdn.mangaeden.com/mangasimg/";
        if(position+this.i<this.chapterPagesList.size()){
            imgUrl += this.chapterPagesList.get(position+this.i).getImage();
        }
        Picasso.get().load(imgUrl).into(photoView);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
