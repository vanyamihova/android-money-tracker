package com.megaflashgames.budgethelper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.megaflashgames.budgethelper.annotations.ContentView;
import com.megaflashgames.budgethelper.annotations.InjectView;
import com.megaflashgames.budgethelper.ui.ScreenBase;
import com.megaflashgames.budgethelper.ui.ScreenDashboard;

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
