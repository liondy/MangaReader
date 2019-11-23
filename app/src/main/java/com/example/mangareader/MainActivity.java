package com.example.mangareader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements FragmentListener {

    private FragmentManager fragmentManager;
    private SplashScreen splashScreen;
    private Mangaking mangaking;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.presenter = new MainPresenter(this);
        this.splashScreen = SplashScreen.createSplashScreen(this);
        this.mangaking = Mangaking.createMangaApp(this,this.presenter);
        this.fragmentManager=this.getSupportFragmentManager();
        this.showPage(FragmentListener.SPLASH_SCREEN);
    }

    @Override
    public void showPage(int page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page==FragmentListener.SPLASH_SCREEN){
            if(this.splashScreen.isAdded()){
                ft.show(this.splashScreen);
            }
            else{
                ft.add(R.id.fragment_container,this.splashScreen);
            }
            if(this.mangaking.isAdded()){
                ft.hide(this.mangaking);
            }
        }
        else if(page==FragmentListener.MANGAKING){
            if(this.mangaking.isAdded()){
                ft.show(this.mangaking);
            }
            else{
                ft.add(R.id.fragment_container,this.mangaking);
            }
            if(this.splashScreen.isAdded()){
                ft.hide(this.splashScreen);
            }
        }
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if(this.presenter.stack!=null && !this.presenter.stack.isEmpty()){
            this.presenter.itemSelector.itemSelect(this.presenter.stack.pop(),true);
        }
        else{
            moveTaskToBack(true);
        }
    }
}
