package com.example.mangareader;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MangaPages extends Fragment implements AdapterView.OnItemClickListener {
    public static MangaPages mangaPages;
    private Context context;
    private ListView pages;
    private ArrayList<ChapterPages> chapterPages;

    public MangaPages(){
        //require empty public constructor
    }

    public static MangaPages createMangaPages(Context context){
        if(mangaPages == null){
            mangaPages = new MangaPages();
            mangaPages.context = context;
        }
        return mangaPages;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.chapter_pages,container,false);
        this.pages = view.findViewById(R.id.list_pages);
        this.pages.setOnItemClickListener(this);
        return view;
    }

    public void setPages(ArrayList<ChapterPages> pages){
        this.chapterPages = pages;
        ChapterPagesAdapter adapter = new ChapterPagesAdapter(this.chapterPages,context);
        this.pages.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder zoom = new AlertDialog.Builder(context);
        View v = getLayoutInflater().inflate(R.layout.dialog_custom_layout,null);
        ViewPager viewPager = v.findViewById(R.id.imageView);
        ImageAdapter adapter = new ImageAdapter(chapterPages,context,i);
        viewPager.setAdapter(adapter);
        zoom.setView(v);
        AlertDialog dialog = zoom.create();
        dialog.show();
    }
}
