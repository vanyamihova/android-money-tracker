package com.megaflashgames.budgethelper.ui.fragments;

import android.os.Bundle;
import android.view.View;

import com.megaflashgames.budgethelper.R;
import com.megaflashgames.budgethelper.annotations.ContentView;

/**
 * Created by vanyamihova on 05/05/2015.
 */
@ContentView(R.layout.fragment_settings)
public class FragmentSettings extends FragmentBase {

    public static final String FRAGMENT_TAG = FragmentSettings.class.getSimpleName();


    public static FragmentBase newInstance() {
        FragmentBase fragment = new FragmentSettings();

        return fragment;
    }

    @Override
    public void onViewCreated(View container, Bundle savedInstanceState) {
        super.onViewCreated(container, savedInstanceState);

        mNavigation = this.getFragmentNavigation();
        mNavigation.resetNavigationInRight();
    }

}