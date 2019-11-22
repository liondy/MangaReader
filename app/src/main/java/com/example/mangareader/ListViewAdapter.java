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
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends ArrayAdapter<Manga> {

    private ArrayList<Manga> mangaList;
    private ArrayList<Manga> arraylist;

    private Context context;

    public ListViewAdapter(ArrayList<Manga> mangaList, Context context) {
        super(context, R.layout.home_screen, mangaList);
        this.arraylist = new ArrayList<>();
        this.mangaList = mangaList;
        this.arraylist.addAll(mangaList);
        this.context = context;
    }

    @Override
    public View getView(final int id, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        @SuppressLint("ViewHolder") View listViewItem = inflater.inflate(R.layout.list_manga, null, true);

        TextView textViewTitle = listViewItem.findViewById(R.id.textViewName);
        TextView textViewAlias = listViewItem.findViewById(R.id.textViewAlias);
        TextView textViewCategory = listViewItem.findViewById(R.id.textViewCategory);
        TextView textViewStatus = listViewItem.findViewById(R.id.textViewStatus);
        TextView textViewRating = listViewItem.findViewById(R.id.textViewHits);
        ImageView imgView = listViewItem.findViewById(R.id.Poster);


        Manga mangaItem = mangaList.get(id);

        textViewTitle.setText(mangaItem.getTitle());
        textViewAlias.setText(mangaItem.getAlias());
        textViewCategory.setText(mangaItem.getCategory());
        textViewStatus.setText(mangaItem.getStatus());
        textViewRating.setText(mangaItem.getRating());

        if(mangaItem.getImage().equals("null")){
            Glide.with(context).load(R.drawable.placeholder).into(imgView);
        }
        else{
            Glide.with(context).load("https://cdn.mangaeden.com/mangasimg/200x/"+mangaItem.getImage()).into(imgView);
        }
        return listViewItem;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        this.mangaList.clear();
        if (charText.length() == 0) {
            this.mangaList.addAll(this.arraylist);
        }
        else {
            for (Manga manga : this.arraylist) {
                if (manga.getTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                    this.mangaList.add(manga);
                }
            }
        }
        notifyDataSetChanged();
    }
}
