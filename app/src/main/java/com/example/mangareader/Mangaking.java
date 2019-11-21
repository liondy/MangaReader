package com.example.mangareader;

import android.annotation.SuppressLint;
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
    private FragmentManager fragmentManager;
    private MangaInfo mangaInfo;

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
        this.fragmentManager = this.getChildFragmentManager();
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        this.customBottomBar = new CustomBottomBar(this.context,view.findViewById(R.id.customBottomBar),this);
        this.mangaList = new ArrayList<>();
        this.homeScreen = HomeScreen.createHomeScreen(this.context,this.mangaList,this);
        this.mangaInfo = MangaInfo.createMangaInfo(this.context);
        this.itemSelect(ItemSelector.INFO);
        this.initItems();
        this.customBottomBar.changeBackground(getString(R.color.colorWhite));
        this.customBottomBar.setDefaultBackground(getString(R.color.colorWhite));
        this.customBottomBar.setDefaultTint(getString(R.color.colorBlack));
        this.customBottomBar.changeDividerColor(getString(R.color.colorBlue));
        this.customBottomBar.hideDivider();
        this.customBottomBar.apply(ItemSelector.HOME);
        System.out.println("Home Screen is Added 1: "+homeScreen.isAdded());
        ft.hide(this.mangaInfo).commit();
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
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        System.out.println("Home Screen is Added 2: "+homeScreen.isAdded());
        System.out.println("Manga Info is Added: "+mangaInfo.isAdded());
        System.out.println("Selected ID: "+selectedID);
        switch (selectedID){
            case ItemSelector.HOME:
                if(this.homeScreen.isAdded()){
                    ft.show(this.homeScreen);
                }
                else{
                    System.out.println("masuk sini gak sih");
                    ft.add(R.id.mangaking,this.homeScreen);
                }
                if(this.mangaInfo.isAdded()){
                    ft.hide(this.mangaInfo);
                }
                break;
            case ItemSelector.BOOKMARKS:
                if(this.homeScreen.isAdded()){
                    ft.hide(this.homeScreen);
                }
                if(this.mangaInfo.isAdded()){
                    ft.hide(this.mangaInfo);
                }
                break;
            case ItemSelector.LIKES:
                if(this.homeScreen.isAdded()){
                    ft.hide(this.homeScreen);
                }
                if(this.mangaInfo.isAdded()){
                    ft.hide(this.mangaInfo);
                }
                break;
            case ItemSelector.INFO:
                if(this.mangaInfo.isAdded()){
                    ft.show(mangaInfo);
                }
                else{
                    ft.add(R.id.mangaking,this.mangaInfo);
                    System.out.println("HOII");
                }
                if(this.homeScreen.isAdded()){
                    ft.hide(this.homeScreen);
                }
                break;
        }
        ft.commit();
    }

    @Override
    public void setManga(String image, String title, String rank, String authors, String genres, String status, String summary) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        System.out.println("SET TEXT");
        this.mangaInfo.setText(image,title,rank,authors,genres,status,summary);
        ft.commit();
    }
}
