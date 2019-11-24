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
import java.util.Stack;

public class Mangaking extends Fragment implements ItemSelector {
    private static Mangaking mangaking;
    private CustomBottomBar customBottomBar;
    private Context context;
    private ListView lstManga;
    private ArrayList<Manga> mangaList;
    private HomeScreen homeScreen;
    private MangaInfo mangaInfo;
    private MangaPages mangaPages;
    private FragmentManager fragmentManager;
    private int lastState;
    private MainPresenter mainPresenter;
    private Stack<Integer> state;

    public Mangaking(){
        //require empty public constructor
    }

    public static Mangaking createMangaApp(Context context, MainPresenter mainPresenter){
        if(mangaking==null){
            mangaking = new Mangaking();
            mangaking.context = context;
            mangaking.state = new Stack();
            mangaking.mainPresenter = mainPresenter;
            mangaking.mainPresenter.setStack(mangaking.state);
            mangaking.mainPresenter.itemSelector = mangaking;
        }
        return mangaking;
    }

    @SuppressLint("ResourceType")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.mangaking,container,false);
        this.lastState = ItemSelector.HOME;
        this.fragmentManager = this.getChildFragmentManager();
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        this.customBottomBar = new CustomBottomBar(this.context,view.findViewById(R.id.customBottomBar),this);
        this.mangaList = new ArrayList<>();
        this.homeScreen = HomeScreen.createHomeScreen(this.context,this.mangaList,this);
        this.mangaInfo = MangaInfo.createMangaInfo(this.context,this);
        this.mangaPages = MangaPages.createMangaPages(this.context);
        this.itemSelect(ItemSelector.INFO,false);
        this.itemSelect(ItemSelector.PAGES,false);
        this.initItems();
        lastState = ItemSelector.HOME;
        mainPresenter.stack.clear();
        this.customBottomBar.changeBackground(getString(R.color.colorWhite));
        this.customBottomBar.setDefaultBackground(getString(R.color.colorWhite));
        this.customBottomBar.setDefaultTint(getString(R.color.colorBlack));
        this.customBottomBar.changeDividerColor(getString(R.color.colorBlue));
        this.customBottomBar.hideDivider();
        this.customBottomBar.apply(ItemSelector.HOME);
        ft.hide(this.mangaInfo);
        ft.hide(this.mangaPages);
        ft.commit();
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
    public void itemSelect(int selectedID,boolean ispop) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(this.lastState!=selectedID && !ispop){
            this.state.push(this.lastState);
        }
        this.lastState = selectedID;
        ItemAdapter id = (ItemAdapter) customBottomBar.getCustom_recycler_view().getAdapter();
        switch (selectedID){
            case ItemSelector.HOME:
                if(this.homeScreen.isAdded()){
                    ft.show(this.homeScreen);
                }
                else{
                    ft.add(R.id.mangaking,this.homeScreen);
                }
                if(this.mangaInfo.isAdded()){
                    ft.hide(this.mangaInfo);
                }
                if(this.mangaPages.isAdded()){
                    ft.hide(this.mangaPages);
                }
                if(id != null){
                    id.forceChange(0);
                }
                break;
            case ItemSelector.BOOKMARKS:
                if(this.homeScreen.isAdded()){
                    ft.hide(this.homeScreen);
                }
                if(this.mangaInfo.isAdded()){
                    ft.hide(this.mangaInfo);
                }
                if(this.mangaPages.isAdded()){
                    ft.hide(this.mangaPages);
                }
                if(id != null){
                    id.forceChange(1);
                }
                break;
            case ItemSelector.LIKES:
                if(this.homeScreen.isAdded()){
                    ft.hide(this.homeScreen);
                }
                if(this.mangaInfo.isAdded()){
                    ft.hide(this.mangaInfo);
                }
                if(this.mangaPages.isAdded()){
                    ft.hide(this.mangaPages);
                }
                if(id != null){
                    id.forceChange(2);
                }
                break;
            case ItemSelector.PAGES:
                if(this.mangaPages.isAdded()){
                    ft.show(this.mangaPages);
                }
                else{
                    ft.add(R.id.mangaking,this.mangaPages);
                }
                if(this.homeScreen.isAdded()){
                    ft.hide(this.homeScreen);
                }
                if(this.mangaInfo.isAdded()){
                    ft.hide(this.mangaInfo);
                }
                if(id != null){
                    id.forceChange(0);
                }
                break;
            case ItemSelector.INFO:
                if(this.mangaInfo.isAdded()){
                    ft.show(mangaInfo);
                }
                else{
                    ft.add(R.id.mangaking,this.mangaInfo);
                }
                if(this.homeScreen.isAdded()){
                    ft.hide(this.homeScreen);
                }
                if(this.mangaPages.isAdded()){
                    ft.hide(this.mangaPages);
                }
                if(id != null){
                    id.forceChange(0);
                }
                break;
        }
        ft.commit();
    }

    @Override
    public void setManga(Manga manga) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        this.mangaInfo.setText(manga);
        ft.commit();
    }

    @Override
    public void setPages(ArrayList<ChapterPages> pages) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        this.mangaPages.setPages(pages);
        ft.commit();
    }
}
