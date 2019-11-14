package com.example.mangareader;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

public class HomeScreen extends Fragment {
    private static HomeScreen homeScreen;
    private Context context;
    private ListView listManga;
    private static final String JSON_URL = "https://www.mangaeden.com/api/list/0/";
    private ArrayList<Manga> mangaList;

    public HomeScreen(){

    }

    public static HomeScreen createHomeScreen(Context context, ArrayList<Manga> mangaList){
        if(homeScreen==null){
            homeScreen = new HomeScreen();
            homeScreen.context = context;
            homeScreen.mangaList = mangaList;
        }
        return homeScreen;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.home_screen,container,false);
        this.listManga = (ListView) view.findViewById(R.id.listManga);

        return view;
    }

    private void loadPlayer() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray mangalist = obj.getJSONArray("result");

                            for (int i = 0; i < mangalist.length(); i++) {

                                JSONObject playerObject = mangalist.getJSONObject(i);


                                Manga manga = new Manga(playerObject.getString("i"),
                                        playerObject.getString("t"),
                                        playerObject.getString("im"));

                                mangaList.add(manga);
                            }

                            ListViewAdapter adapter = new ListViewAdapter(playerItemList, getApplicationContext());

                            listView.setAdapter(adapter);

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
}
