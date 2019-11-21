package com.example.mangareader;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class MangaInfo extends Fragment {
    private static MangaInfo mangaInfo;
    private ImageView poster;
    public TextView title;
    public TextView rank;
    public TextView author;
    public TextView genre;
    public TextView status;
    public TextView summary;
    private Context context;

    public MangaInfo(){
        //require empty public constructor
    }

    public static MangaInfo createMangaInfo(Context context){
        if(mangaInfo == null){
            mangaInfo = new MangaInfo();
            mangaInfo.context = context;
        }
        return mangaInfo;
    }

    public void setText(String image, String title, String rank, String authors, String genres, String status, String summary){
        this.title.setText(title);
        this.rank.setText(rank);
        this.author.setText(authors);
        this.genre.setText(genres);
        this.status.setText(status);
        this.summary.setText(summary);
        Glide.with(context).load("https://cdn.mangaeden.com/mangasimg/200x/"+image).into(this.poster);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.manga_info,container,false);
        this.poster = view.findViewById(R.id.iv_gambar);
        this.title = view.findViewById(R.id.title);
        this.rank = view.findViewById(R.id.rank_des);
        this.author = view.findViewById(R.id.author_des);
        this.genre = view.findViewById(R.id.genre_des);
        this.status = view.findViewById(R.id.status_des);
        this.summary = view.findViewById(R.id.summary_des);

        this.title.setText(this.getArguments().getString("title",""));
        this.rank.setText(this.getArguments().getString("rank",""));
        this.author.setText(this.getArguments().getString("author",""));
        this.genre.setText(this.getArguments().getString("genre",""));
        this.status.setText(this.getArguments().getString("status",""));
        this.summary.setText(this.getArguments().getString("summary",""));
        return view;
    }
}
