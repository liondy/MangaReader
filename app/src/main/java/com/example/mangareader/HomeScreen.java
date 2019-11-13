package com.example.mangareader;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class HomeScreen extends Fragment {
    private static HomeScreen home_screen;
    private CustomBottomBar customBottomBar;
    private ItemSelector itemSelectorInterface;
    private Context context;

    public HomeScreen(){
        //require empty public constructor
    }

    public static HomeScreen createHomeScreen(Context context, ItemSelector itemSelector){
        if(home_screen==null){
            home_screen = new HomeScreen();
            home_screen.context = context;
            home_screen.itemSelectorInterface = itemSelector;
        }
        return home_screen;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.home_screen,container,false);
        this.customBottomBar = new CustomBottomBar(this.context,view.findViewById(R.id.customBottomBar),this.itemSelectorInterface);
        return view;
    }
}
