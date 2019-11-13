package com.example.mangareader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements FragmentListener, ItemSelector {

    private FragmentManager fragmentManager;
    private SplashScreen splashScreen;
    private HomeScreen homeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainPresenter presenter = new MainPresenter(this);
        this.splashScreen = SplashScreen.createSplashScreen(this);
        this.homeScreen = HomeScreen.createHomeScreen(this,this);
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
            if(this.homeScreen.isAdded()){
                ft.hide(this.homeScreen);
            }
        }
        else if(page==FragmentListener.HOME_SCREEN){
            if(this.homeScreen.isAdded()){
                ft.show(this.homeScreen);
            }
            else{
                ft.add(R.id.fragment_container,this.homeScreen);
            }
            if(this.splashScreen.isAdded()){
                ft.hide(this.splashScreen);
            }
        }
        ft.commit();
    }

    @Override
    public void itemSelect(int selectedID) {

    }
}
