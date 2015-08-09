package com.megaflashgames.budgethelper.service;

import android.content.Context;

import com.megaflashgames.budgethelper.interfaces.IContextProvider;

/**
 * Created by vanyamihova on 04/05/2015.
 */
public class ContextService implements IContextProvider {

    private static ContextService Instance;

    private Context mApplicationContext;

    public static ContextService GetInstance() {
        if(Instance == null) {
            throw new RuntimeException("Before use ContextService you should init him");
        }
        return Instance;
    }

    public static void Create(Context context) {
        if(Instance == null) {
            Instance = new ContextService(context.getApplicationContext());
        }
    }

    private ContextService(Context context) {
        this.mApplicationContext = context;
    }

    @Override
    public Context inject() {
        return getContext();
    }

    public Context getContext() {
        return mApplicationContext;
    }
}
