package com.megaflashgames.moneynotebook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.megaflashgames.moneynotebook.config.Constant;
import com.megaflashgames.moneynotebook.ui.ScreenDashboard;


/**
 * Created by VanyaMihova on 8/6/2015.
 */
public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;

                if(Constant.SKIP_START_SCREEN)
                    intent = new Intent(SplashScreen.this, ScreenDashboard.class);
                else
                    intent = new Intent(SplashScreen.this, StartScreen.class);

                startActivity(intent);
            }
        }, 2000);



    }




}
