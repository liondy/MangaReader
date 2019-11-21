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

public class MangaInfo extends Fragment {
    private TextView title;
    private ImageView poster;
    private TextView genres;
    private TextView status;
    private TextView summary;
    private Manga manga;
    private Context context;

    public MangaInfo(Context context){
        this.context = context;
    }

    public void setManga(Manga manga){
        this.manga = manga;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.manga_info,container,false);
        this.title = view.findViewById(R.id.title);
        this.poster = view.findViewById(R.id.iv_gambar);
        this.genres = view.findViewById(R.id.genre_des);
        this.status = view.findViewById(R.id.status_des);
        this.summary = view.findViewById(R.id.summary_des);

        if(this.manga!=null){
            this.title.setText(this.manga.getTitle());
            this.genres.setText(this.manga.getCategory());
            this.status.setText(this.manga.getStatus());
        }

        return view;
    }

}
