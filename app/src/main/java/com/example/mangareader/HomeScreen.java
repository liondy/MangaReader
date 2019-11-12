package com.example.mangareader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class HomeScreen extends Fragment {
    private static HomeScreen home_screen;

    public HomeScreen(){
        //require empty public constructor
    }

    public static HomeScreen createHomeScreen(){
        if(home_screen==null){
            home_screen = new HomeScreen();
        }
        return home_screen;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.home_screen,container,false);
        return view;
    }
}
