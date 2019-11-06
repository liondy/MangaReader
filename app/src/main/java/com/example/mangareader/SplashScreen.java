package com.example.mangareader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class SplashScreen extends Fragment {
    private static SplashScreen splash_screen;

    public SplashScreen(){
        //require empty public constructor
    }

    public static SplashScreen createSplashScreen(){
        if(splash_screen==null){
            splash_screen = new SplashScreen();
        }
        return splash_screen;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.splash_screen,container,false);
        return view;
    }
}
