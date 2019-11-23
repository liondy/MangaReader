package com.example.mangareader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MangaInfo extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private static MangaInfo mangaInfo;
    private Manga manga;
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
    private NonScrollListView listChapter;
    private ImageButton btn_bookmark;
    private ImageButton btn_likes;
    private boolean bookmark_active;
    private boolean like_active;

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

    public void setText(Manga manga){
        this.manga = manga;
        ColorFilter filter;
        if(!this.manga.isBookmarked()){
            filter = new PorterDuffColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
        }
        else{
            filter = new PorterDuffColorFilter(getResources().getColor(R.color.colorItem2Tint), PorterDuff.Mode.SRC_IN);
        }
        this.btn_bookmark.setColorFilter(filter);
        if(!this.manga.isLiked()){
            filter = new PorterDuffColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
        }
        else{
            filter = new PorterDuffColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        }
        this.btn_likes.setColorFilter(filter);
        this.title.setText(manga.getTitle());
        this.released.setText(manga.getReleased());
        this.rank.setText(manga.getRating());
        this.author.setText(manga.getAuthor());
        this.genre.setText(manga.getCategory());
        this.chapterLength.setText(manga.getChapter_len());
        this.status.setText(manga.getStatus());
        this.summary.setText(manga.getSummary());
        if(manga.getImage().equals("null")){
            Glide.with(this.context).load(R.drawable.backgorund).into(this.poster);
        }
        else{
            Glide.with(context).load("https://cdn.mangaeden.com/mangasimg/200x/"+manga.getImage()).into(this.poster);
        }
        ChapterAdapter adapter = new ChapterAdapter(manga.getChapterList(),this.context);
        this.listChapter.setAdapter(adapter);
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
        this.btn_bookmark = view.findViewById(R.id.btn_bookmark);
        this.btn_likes = view.findViewById(R.id.btn_likes);
        listChapter = (NonScrollListView) view.findViewById(R.id.listChapter);

        listChapter.setOnItemClickListener(this);
        this.btn_bookmark.setOnClickListener(this);
        this.btn_likes.setOnClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        if(view.getId()==this.btn_bookmark.getId()){
            ColorFilter filter;
            if(this.manga.isBookmarked()){
                filter = new PorterDuffColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
            }
            else{
                filter = new PorterDuffColorFilter(getResources().getColor(R.color.colorItem2Tint), PorterDuff.Mode.SRC_IN);
            }
            this.manga.setBookmarked();
            this.btn_bookmark.setColorFilter(filter);
        }
        else if(view.getId()==this.btn_likes.getId()){
            ColorFilter filter;
            if(this.manga.isLiked()){
                filter = new PorterDuffColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
            }
            else{
                filter = new PorterDuffColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
            }
            this.manga.like();
            this.btn_likes.setColorFilter(filter);
        }
    }
}
