package com.example.mangareader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ChapterAdapter extends ArrayAdapter<Chapter> {

    private ArrayList<Chapter> chapterList;

    private Context context;

    public ChapterAdapter(ArrayList<Chapter> chapterList, Context context) {
        super(context, R.layout.manga_info, chapterList);
        this.chapterList = chapterList;
        this.context = context;
    }

    @Override
    public View getView(final int id, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        @SuppressLint("ViewHolder") View listViewItem = inflater.inflate(R.layout.chapter_list, null, true);

        TextView chapterNumber = listViewItem.findViewById(R.id.chapter_number);
        TextView chapterTitle = listViewItem.findViewById(R.id.chapter_title);

        Chapter chapter = chapterList.get(id);

        chapterNumber.setText(" "+chapter.getNumber());
        chapterTitle.setText(chapter.getTitle());
        return listViewItem;
    }

}
