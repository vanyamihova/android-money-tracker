package com.megaflashgames.moneynotebook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.megaflashgames.moneynotebook.annotations.ContentView;
import com.megaflashgames.moneynotebook.ui.ScreenBase;

/**
 * Created by VanyaMihova on 8/6/2015.
 */
@ContentView(R.layout.start_screen)
public class SplashScreen extends ScreenBase {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        enableInjector(true);
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, StartScreen.class);
                startActivity(intent);
            }
        }, 2000);



    }




}
