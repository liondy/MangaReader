package com.example.mangareader;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Locale;

public class HomeScreen extends Fragment implements AdapterView.OnItemClickListener {
    private static HomeScreen homeScreen;
    private Context context;
    private ListView listManga;
    private static final String JSON_URL = "https://www.mangaeden.com/api/list/0/";
    private ArrayList<Manga> mangaList;
    private ItemSelector itemSelector;
    private EditText editSearch;
    private ListViewAdapter adapter;

    public HomeScreen(){

    }

    public static HomeScreen createHomeScreen(Context context, ArrayList<Manga> mangaList, Mangaking mangaking){
        if(homeScreen==null){
            homeScreen = new HomeScreen();
            homeScreen.context = context;
            homeScreen.mangaList = (ArrayList<Manga>) mangaList.clone();
            homeScreen.itemSelector = mangaking;
        }
        return homeScreen;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.home_screen,container,false);
        this.listManga = (ListView) view.findViewById(R.id.listManga);
        this.listManga.setOnItemClickListener(this);
        this.loadManga();
        this.editSearch = (EditText) view.findViewById(R.id.editSearch);

        this.editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editSearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }
        });
        return view;
    }

    private void loadManga() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray mangalist = obj.getJSONArray("manga");

                            for (int i = 0; i < mangalist.length(); i++) {

                                JSONObject mangaReader = mangalist.getJSONObject(i);


                                Manga manga = new Manga(mangaReader.getString("i"),
                                        mangaReader.getString("t"),
                                        mangaReader.getString("a"),
                                        mangaReader.getString("c"),
                                        mangaReader.getString("s"),
                                        mangaReader.getString("h"),
                                        mangaReader.getString("im"));

                                mangaList.add(manga);
                            }

                            adapter = new ListViewAdapter(mangaList, context);

                            listManga.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("error");
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this.context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        InputMethodManager imm = (InputMethodManager) this.context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        final Manga manga = this.mangaList.get(i);
        String mangaId = manga.getId();
        String url = "https://www.mangaeden.com/api/manga/"+mangaId+"/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("sini dulu");
                    JSONObject obj = new JSONObject(response);
                    String description = obj.getString("description");
                    String author = obj.getString("author");
                    String chapterLength = obj.getString("chapters_len");
                    String released = obj.getString("released");
                    manga.setAuthor(author);
                    manga.setSummary(description);
                    manga.setChapter_len(chapterLength);
                    manga.setReleased(released);
                    itemSelector.setManga(manga.getImage(),manga.getTitle(),manga.getReleased(),manga.getRating(),manga.getAuthor(),manga.getCategory(),manga.getChapter_len(),manga.getStatus(),manga.getSummary());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error");
            }
        });
        System.out.println("masuk");
        RequestQueue requestQueue = Volley.newRequestQueue(this.context);
        requestQueue.add(stringRequest);
        System.out.println("sini ajah");
        this.itemSelector.itemSelect(ItemSelector.INFO);
        itemSelector.setManga(manga.getImage(),manga.getTitle(),manga.getReleased(),manga.getRating(),manga.getAuthor(),manga.getCategory(),manga.getChapter_len(),manga.getStatus(),manga.getSummary());
        this.editSearch.setText("");
    }
}
