package com.megaflashgames.moneynotebook.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.megaflashgames.moneynotebook.R;
import com.megaflashgames.moneynotebook.ui.model.ScreenSettings;

/**
 * Created by Vanya Mihova on 11/21/2015.
 */
public class FragmentTest extends FragmentBase {

    public static final ScreenSettings MENU_TAG = ScreenSettings.TEST;
    public static final String FRAGMENT_TAG = FragmentTest.class.getSimpleName();


    public static FragmentBase newInstance() {
        FragmentBase fragment = new FragmentTest();

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "FragmentTest", Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.activity_main, container, false);
    }
}
