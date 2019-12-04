package com.example.mangareader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<Manga> mangaList;
    private ArrayList<Manga> arraylist;

    private Context context;

    public ListViewAdapter(ArrayList<Manga> mangaList, Context context) {
        this.arraylist = new ArrayList<>();
        this.mangaList = mangaList;
        this.arraylist.addAll(mangaList);
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.mangaList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.mangaList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        Manga mangaItem = mangaList.get(i);
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_manga,parent,false);
            TextView textViewTitle = convertView.findViewById(R.id.textViewName);
            TextView textViewAlias = convertView.findViewById(R.id.textViewAlias);
            TextView textViewCategory = convertView.findViewById(R.id.textViewCategory);
            TextView textViewStatus = convertView.findViewById(R.id.textViewStatus);
            TextView textViewRating = convertView.findViewById(R.id.textViewHits);
            ImageView imgView = convertView.findViewById(R.id.Poster);

            textViewTitle.setText(mangaItem.getTitle()+"");
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

            ViewHolder vh = new ViewHolder(textViewTitle,textViewAlias,textViewCategory,textViewStatus,textViewRating,imgView);
            convertView.setTag(vh);
        }
        else{
            ViewHolder vh = (ViewHolder) convertView.getTag();
            vh.title.setText(mangaItem.getTitle());
            vh.alias.setText(mangaItem.getAlias());
            vh.category.setText(mangaItem.getCategory());
            vh.status.setText(mangaItem.getStatus());
            vh.rating.setText(mangaItem.getRating());

            if(mangaItem.getImage().equals("null")){
                Glide.with(context).load(R.drawable.placeholder).into(vh.poster);
            }
            else{
                Glide.with(context).load("https://cdn.mangaeden.com/mangasimg/200x/"+mangaItem.getImage()).into(vh.poster);
            }
        }
        return convertView;
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

    private class ViewHolder{
        protected TextView title;
        protected TextView alias;
        protected TextView category;
        protected TextView status;
        protected TextView rating;
        protected ImageView poster;

        public ViewHolder(TextView title, TextView alias, TextView category, TextView status, TextView rating, ImageView poster) {
            this.title = title;
            this.alias = alias;
            this.category = category;
            this.status = status;
            this.rating = rating;
            this.poster = poster;
        }
    }
}
