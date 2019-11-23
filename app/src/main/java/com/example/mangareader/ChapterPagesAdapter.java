package com.example.mangareader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ChapterPagesAdapter extends ArrayAdapter<ChapterPages> {

    private ArrayList<ChapterPages> chapterPagesList;
    private Context context;

    public ChapterPagesAdapter(ArrayList<ChapterPages> chapterList, Context context) {
        super(context, R.layout.chapter_pages, chapterList);
        this.chapterPagesList = chapterList;
        this.context = context;
    }

    @Override
    public View getView(final int id, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        @SuppressLint("ViewHolder") View listViewItem = inflater.inflate(R.layout.item_pages, null, true);

        ChapterPages chapterPages = chapterPagesList.get(id);

        ImageView gambar = listViewItem.findViewById(R.id.iv_pages);
        Glide.with(context).load("https://cdn.mangaeden.com/mangasimg/"+chapterPages.getImage()).into(gambar);
        return listViewItem;
    }
}
