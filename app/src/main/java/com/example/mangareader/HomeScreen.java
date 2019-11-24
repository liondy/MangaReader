package com.example.mangareader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private ProgressBar progressBar;

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
        this.progressBar = view.findViewById(R.id.progressBar1);
        this.progressBar.setVisibility(View.VISIBLE);
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
                    @SuppressLint("ShowToast")
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                        alertDialog.setTitle("No Internet Found");
                        alertDialog.setMessage("Uh-oh! It seems that you're in offline mode! Try to check your Internet Connection!");
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "RECONNECT", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                loadManga();
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
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        InputMethodManager imm = (InputMethodManager) this.context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        final Manga manga = this.mangaList.get(i);
        final String mangaId = manga.getId();
        String url = "https://www.mangaeden.com/api/manga/"+mangaId+"/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    String description = obj.getString("description");
                    String author = obj.getString("author");
                    String chapterLength = obj.getString("chapters_len");
                    int panjang = Integer.parseInt(chapterLength);
                    String released = obj.getString("released");
                    JSONArray chapters = obj.getJSONArray("chapters");
                    String[] arr = new String[panjang];
                    ArrayList<Chapter> chapterArrayList = new ArrayList<>();
                    for(int i = 0; i < panjang; i++){
                        arr[i] = chapters.getString(i);
                        String[] chapterInfo = new String[arr[i].split(",").length];
                        for(int j = 0; j < chapterInfo.length; j++){
                            chapterInfo[j] = arr[i].split(",")[j];
                        }
                        Chapter chapter = new Chapter(chapterInfo[0],chapterInfo[2],chapterInfo[3]);
                        chapterArrayList.add(chapter);
                    }
                    manga.setAuthor(author);
                    manga.setSummary(description);
                    manga.setChapter_len(chapterLength);
                    manga.setReleased(released);
                    manga.setChapterList(chapterArrayList);
                    itemSelector.setManga(manga);

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
        RequestQueue requestQueue = Volley.newRequestQueue(this.context);
        requestQueue.add(stringRequest);
        this.itemSelector.itemSelect(ItemSelector.INFO,false);
        itemSelector.setManga(manga);
        this.editSearch.setText("");
    }
}
