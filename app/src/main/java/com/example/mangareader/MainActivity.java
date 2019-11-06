package com.example.mangareader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements FragmentListener {

    private FragmentManager fragmentManager;
    private SplashScreen splashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainPresenter presenter = new MainPresenter(this);
        this.splashScreen = SplashScreen.createSplashScreen();
        this.fragmentManager=this.getSupportFragmentManager();
        this.showPage(FragmentListener.SPLASH_SCREEN);
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
