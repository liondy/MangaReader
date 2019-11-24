package com.example.mangareader;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class MangaPages extends Fragment {
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
        return view;
    }

    public void setPages(ArrayList<ChapterPages> pages){
        this.chapterPages = pages;
        ChapterPagesAdapter adapter = new ChapterPagesAdapter(this.chapterPages,context);
        this.pages.setAdapter(adapter);
    }
}
