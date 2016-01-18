package com.megaflashgames.moneynotebook.ui.fragments;

import android.os.Bundle;
import android.view.View;

import com.megaflashgames.moneynotebook.R;
import com.megaflashgames.moneynotebook.annotations.ContentView;
import com.megaflashgames.moneynotebook.ui.model.ScreenSettings;

/**
 * Created by vanyamihova on 05/05/2015.
 */
@ContentView(R.layout.fragment_profile)
public class FragmentProfile extends FragmentBase {


    public static final ScreenSettings MENU_TAG = ScreenSettings.PROFILE;
    public static final String FRAGMENT_TAG = FragmentProfile.class.getSimpleName();


    public static FragmentBase newInstance() {
        FragmentBase fragment = new FragmentProfile();

        return fragment;
    }

    @Override
    public void onViewCreated(View container, Bundle savedInstanceState) {
        super.onViewCreated(container, savedInstanceState);

//        mNavigation = this.getFragmentNavigation();
//        mNavigation.resetNavigationInRight();
    }

}