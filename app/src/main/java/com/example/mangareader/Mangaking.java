package com.example.mangareader;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class Mangaking extends Fragment implements ItemSelector {
    private static Mangaking mangaking;
    private CustomBottomBar customBottomBar;
    private Context context;
    private ListView lstManga;
    private ArrayList<Manga> mangaList;
    private HomeScreen homeScreen;

    public Mangaking(){
        //require empty public constructor
    }

    public static Mangaking createMangaApp(Context context){
        if(mangaking==null){
            mangaking = new Mangaking();
            mangaking.context = context;
        }
        return mangaking;
    }

    @SuppressLint("ResourceType")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.mangaking,container,false);
        this.customBottomBar = new CustomBottomBar(this.context,view.findViewById(R.id.customBottomBar),this);
        this.mangaList = new ArrayList<>();
        this.homeScreen = HomeScreen.createHomeScreen(this.context,this.mangaList);
        this.initItems();
        this.customBottomBar.changeBackground(getString(R.color.colorWhite));
        this.customBottomBar.setDefaultBackground(getString(R.color.colorWhite));
        this.customBottomBar.setDefaultTint(getString(R.color.colorBlack));
        this.customBottomBar.changeDividerColor(getString(R.color.colorBlue));
        this.customBottomBar.hideDivider();
        this.customBottomBar.apply(ItemSelector.HOME);
        System.out.println("Home Screen is Added: "+homeScreen.isAdded());

        return view;
    }

    @SuppressLint("ResourceType")
    private void initItems(){
        CustomBottomItem home = new CustomBottomItem(ItemSelector.HOME,R.drawable.home_icons,getString(R.string.item_0),getString(R.color.colorYellow),getString(R.color.colorItem1Tint));
        CustomBottomItem bookmarks = new CustomBottomItem(ItemSelector.BOOKMARKS, R.drawable.ic_bookmarks, getString(R.string.item_1), getString(R.color.colorLightYeloow), getString(R.color.colorItem2Tint));
        CustomBottomItem likes = new CustomBottomItem(ItemSelector.LIKES, R.drawable.ic_likes, getString(R.string.item_2), getString(R.color.colorItem2Tint), getString(R.color.colorItem3Tint));

        this.customBottomBar.addItem(home);
        this.customBottomBar.addItem(bookmarks);
        this.customBottomBar.addItem(likes);
    }

    @Override
    public void itemSelect(int selectedID) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        System.out.println("Home Screen is Added: "+homeScreen.isAdded());
        System.out.println("Selected ID: "+selectedID);
        switch (selectedID){
            case ItemSelector.HOME:
                //todo do something, when Bookmark is selected
                if(this.homeScreen.isAdded()){
                    ft.show(this.homeScreen);
                }
                else{
                    System.out.println("masuk sini gak sih");
                    ft.add(R.id.mangaking,this.homeScreen);
                }
                break;
            case ItemSelector.BOOKMARKS:
                //todo do something, when Likes is selected
                if(this.homeScreen.isAdded()){
                    ft.hide(this.homeScreen);
                }
                break;
            case ItemSelector.LIKES:
                //todo do something, when Search is selected
                if(this.homeScreen.isAdded()){
                    ft.hide(this.homeScreen);
                }
                break;
        }
        ft.commit();
    }
}
