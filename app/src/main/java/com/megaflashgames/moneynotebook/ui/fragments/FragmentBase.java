package com.megaflashgames.moneynotebook.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.megaflashgames.moneynotebook.ui.custom.CustomToolbar;


/**
 * Created by vanyamihova on 04/05/2015.
 */
public class FragmentBase extends Fragment {

    private CustomToolbar mCustomToolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        if(mCustomToolbar != null)
            customizeToolbar(mCustomToolbar);

    }

    protected void customizeToolbar(CustomToolbar toolbar) { }

    public void setCustomToolbar(CustomToolbar toolbar) {
        mCustomToolbar = toolbar;
        mCustomToolbar.setDefaultSettings();
    }


}
