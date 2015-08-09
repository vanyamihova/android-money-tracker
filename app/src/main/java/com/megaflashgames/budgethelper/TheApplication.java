package com.megaflashgames.budgethelper;

import android.app.Application;

import com.megaflashgames.budgethelper.service.ContextService;

/**
 * Created by vanyamihova on 04/05/2015.
 */
public class TheApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        ContextService.Create(getApplicationContext());

    }
}
