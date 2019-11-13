package com.example.mangareader;

import android.annotation.SuppressLint;
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

    @SuppressLint("ResourceType")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.home_screen,container,false);
        this.customBottomBar = new CustomBottomBar(this.context,view.findViewById(R.id.customBottomBar),this.itemSelectorInterface);
        this.initItems();
        this.customBottomBar.changeBackground(getString(R.color.colorWhite));
        this.customBottomBar.setDefaultBackground(getString(R.color.colorWhite));
        this.customBottomBar.setDefaultTint(getString(R.color.colorBlack));
        this.customBottomBar.changeDividerColor(getString(R.color.colorBlue));
        this.customBottomBar.hideDivider();
        this.customBottomBar.apply(ItemSelector.HOME);
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
}
