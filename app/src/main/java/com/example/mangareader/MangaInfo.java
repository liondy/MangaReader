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
    private TextView title;
    private TextView released;
    private TextView rank;
    private TextView author;
    private TextView genre;
    private TextView chapterLength;
    private TextView status;
    private TextView summary;
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

    public void setText(String image, String title, String released, String rank, String authors, String genres, String chapterLength, String status, String summary){
        this.title.setText(title);
        this.released.setText(released);
        this.rank.setText(rank);
        this.author.setText(authors);
        this.genre.setText(genres);
        this.chapterLength.setText(chapterLength);
        this.status.setText(status);
        this.summary.setText(summary);
        if(image.equals("null")){
            Glide.with(this.context).load(R.drawable.backgorund).into(this.poster);
        }
        else{
            Glide.with(context).load("https://cdn.mangaeden.com/mangasimg/200x/"+image).into(this.poster);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.manga_info,container,false);
        this.poster = view.findViewById(R.id.iv_gambar);
        this.title = view.findViewById(R.id.title);
        this.released = view.findViewById(R.id.released_des);
        this.rank = view.findViewById(R.id.rank_des);
        this.author = view.findViewById(R.id.author_des);
        this.genre = view.findViewById(R.id.genre_des);
        this.chapterLength = view.findViewById(R.id.chapter_des);
        this.status = view.findViewById(R.id.status_des);
        this.summary = view.findViewById(R.id.summary_des);

        System.out.println("ADDED MANGA INFO");
        return view;
    }
}
