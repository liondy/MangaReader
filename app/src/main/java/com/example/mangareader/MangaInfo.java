package com.example.mangareader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class MangaInfo extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private static MangaInfo mangaInfo;
    private ItemSelector itemSelector;
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
    public ProgressBar progressBar;

    public MangaInfo(){
        //require empty public constructor
    }

    public static MangaInfo createMangaInfo(Context context, Mangaking mangaking){
        if(mangaInfo == null){
            mangaInfo = new MangaInfo();
            mangaInfo.context = context;
            mangaInfo.itemSelector = mangaking;
        }
        return mangaInfo;
    }

    public void loading(){
        this.progressBar.setVisibility(View.VISIBLE);
    }

    public void finishLoading(){
        this.progressBar.setVisibility(View.GONE);
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
        this.listChapter = (NonScrollListView) view.findViewById(R.id.listChapter);
        this.progressBar = view.findViewById(R.id.progressBar2);

        this.listChapter.setOnItemClickListener(this);
        this.btn_bookmark.setOnClickListener(this);
        this.btn_likes.setOnClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        this.itemSelector.itemSelect(ItemSelector.PAGES,false);
        this.loadChapter(i);
        this.itemSelector.loadPages();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        Toast toast;
        CharSequence text = "";
        int duration = Toast.LENGTH_SHORT;
        if(view.getId()==this.btn_bookmark.getId()){
            ColorFilter filter;
            if(this.manga.isBookmarked()){
                filter = new PorterDuffColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
                text = "Remove "+this.manga.getTitle()+" from bookmark list";
            }
            else{
                filter = new PorterDuffColorFilter(getResources().getColor(R.color.colorItem2Tint), PorterDuff.Mode.SRC_IN);
                text = "Add "+this.manga.getTitle()+" to bookmark list";
            }
            this.manga.setBookmarked();
            this.btn_bookmark.setColorFilter(filter);
        }
        else if(view.getId()==this.btn_likes.getId()){
            ColorFilter filter;
            if(this.manga.isLiked()){
                filter = new PorterDuffColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
                text = "Unlike "+this.manga.getTitle();
            }
            else{
                filter = new PorterDuffColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
                text = "Thank you for liking "+this.manga.getTitle();
            }
            this.manga.like();
            this.btn_likes.setColorFilter(filter);
        }
        toast = Toast.makeText(context,text,duration);
        toast.show();
    }

    public void loadChapter(final int position){
        String chapterID = this.manga.getChapterList().get(position).getId();
        String url = "https://www.mangaeden.com/api/chapter/"+chapterID+"/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray pages = obj.getJSONArray("images");
                    String[] arr = new String[pages.length()];
                    ArrayList<ChapterPages> chapterPagesArrayList = new ArrayList<>();
                    for(int i = 0; i < arr.length; i++){
                        arr[i] = pages.getString(i);
                        String[] pagesImage = new String[arr[i].split(",").length];
                        for(int j = 0; j < pagesImage.length; j++){
                            pagesImage[j] = arr[i].split(",")[j];
                        }
                        ChapterPages chapterPages = new ChapterPages(pagesImage[0],pagesImage[1]);
                        chapterPagesArrayList.add(chapterPages);
                    }
                    Collections.reverse(chapterPagesArrayList);
                    itemSelector.setPages(chapterPagesArrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("No Internet Found");
                alertDialog.setMessage("Uh-oh! It seems that you're in offline mode! Try to check your Internet Connection!");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "RECONNECT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        loadChapter(position);
                        progressBar.setVisibility(View.VISIBLE);
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CLOSE APP", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        System.exit(0);
                    }
                });
                alertDialog.show();
                Toast.makeText(context,"No Internet Found",Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this.context);
        requestQueue.add(stringRequest);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                itemSelector.finishPages();
            }
        });
    }
}
