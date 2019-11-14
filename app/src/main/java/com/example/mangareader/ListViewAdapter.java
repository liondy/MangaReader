package com.example.mangareader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Manga> {

    private List<Manga> mangaList;

    private Context context;

    public ListViewAdapter(List<Manga> mangaList, Context context) {
        super(context, R.layout.home_screen, mangaList);
        this.mangaList = mangaList;
        this.context = context;
    }



    @Override
    public View getView(final int id, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.home_screen, null, true);

        TextView textViewNo = listViewItem.findViewById(R.id.textViewNo);
        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        TextView textViewPosition = listViewItem.findViewById(R.id.textViewPosition);
        TextView textViewBirth_date = listViewItem.findViewById(R.id.textViewBirthDate);
        ImageView imgVIew = listViewItem.findViewById(R.id.Poster);


        Manga playerItem = mangaList.get(id);

        textViewNo.setText(playerItem.getId());
        textViewName.setText(playerItem.getTitle());

        Glide.with(context).load(playerItem.getImage()).into(imgVIew);

        return listViewItem;
    }
}
