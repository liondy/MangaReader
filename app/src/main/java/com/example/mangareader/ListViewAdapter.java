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
        if(mangaItem.getCategory().equals("[]")){
            textViewCategory.setText("-");
        }
        else{
            String category="";
            for (int i = 0 ; i<mangaItem.getCategory().length() ; i++){
                if(mangaItem.getCategory().charAt(i)=='[' || mangaItem.getCategory().charAt(i)==']' || mangaItem.getCategory().charAt(i)=='"'){
                    continue;
                }
                else if(mangaItem.getCategory().charAt(i)==','){
                    category+=mangaItem.getCategory().charAt(i)+" ";
                }
                else{
                    category+=mangaItem.getCategory().charAt(i);
                }
            }
            textViewCategory.setText(category);
        }
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
}
