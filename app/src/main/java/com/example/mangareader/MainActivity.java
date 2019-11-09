package com.example.mangareader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import github.com.st235.lib_expandablebottombar.ExpandableBottomBar;
import github.com.st235.lib_expandablebottombar.ExpandableBottomBarMenuItem;

public class MainActivity extends AppCompatActivity implements FragmentListener {

    private FragmentManager fragmentManager;
    private SplashScreen splashScreen;
    private ExpandableBottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainPresenter presenter = new MainPresenter(this);
        this.bottomBar = findViewById(R.id.expandable_bottom_bar);
        this.addItem();
        this.splashScreen = SplashScreen.createSplashScreen();
        this.fragmentManager=this.getSupportFragmentManager();
        this.showPage(FragmentListener.SPLASH_SCREEN);
    }

    protected void addItem(){
        this.bottomBar.addItems(
                ExpandableBottomBarMenuItem.Builder(this)
                        .addItem(R.id.icon_home, R.drawable.ic_bug, R.string.text, Color.GRAY)
                        .addItem(R.id.icon_go, R.drawable.ic_gift, R.string.text2, 0xFFFF77A9)
                        .addItem(R.id.icon_left, R.drawable.ic_one, R.string.text3, 0xFF58A5F0)
                        .addItem(R.id.icon_right, R.drawable.ic_two, R.string.text4, 0xFFBE9C91)
                        .build()
        );
    }

    @Override
    public void showPage(int page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page==FragmentListener.SPLASH_SCREEN){
            if(this.splashScreen.isAdded()){
                System.out.println("aaaaaaaaaaaaaaaa");
                ft.show(this.splashScreen);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("bbbbbbbbbbbbbbbbbbbbb");
                ft.add(R.id.fragment_container,this.splashScreen);
            }
        }
        ft.commit();
    }
}
