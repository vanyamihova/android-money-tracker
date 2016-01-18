package com.megaflashgames.moneynotebook.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;

import com.megaflashgames.moneynotebook.R;
import com.megaflashgames.moneynotebook.annotations.ContentView;
import com.megaflashgames.moneynotebook.ui.custom.CustomToolbar;
import com.megaflashgames.moneynotebook.ui.custom.SlidingMenu;
import com.megaflashgames.moneynotebook.ui.fragments.FragmentBase;
import com.megaflashgames.moneynotebook.ui.fragments.FragmentCar;
import com.megaflashgames.moneynotebook.ui.fragments.FragmentHome;
import com.megaflashgames.moneynotebook.ui.fragments.FragmentProfile;
import com.megaflashgames.moneynotebook.ui.fragments.FragmentSettings;
import com.megaflashgames.moneynotebook.ui.fragments.FragmentTest;
import com.megaflashgames.moneynotebook.ui.model.ScreenSettings;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vanyamihova on 04/05/2015.
 */
@ContentView(R.layout.screen_dashboard)
public class ScreenDashboard extends AppCompatActivity {

    private static final ScreenSettings FIRST_OPENED_FRAGMENT = ScreenSettings.CAR;

    private static final String TAG = ScreenDashboard.class.getSimpleName();

    @Bind(R.id.layout_drawer)
    SlidingPaneLayout mSlidingPanel;
    @Bind(R.id.custom_toolbar)
    CustomToolbar toolbar;
    @Bind(R.id.sliding_menu)
    SlidingMenu slidingMenu;


    private static FragmentManager sFragmentManager;
    private static CustomToolbar sCustomToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_dashboard);
        ButterKnife.bind(this);

        sFragmentManager = getSupportFragmentManager();
        sCustomToolbar = toolbar;

        setActionBar();

        setSlidingMenu();

        displayFragment(FragmentHome.MENU_TAG, false);

    }

    private void setActionBar() {
        toolbar.setOnMenuButtonClickListener(new CustomToolbar.OnMenuButtonClickListener() {
            @Override
            public void onMenuClickListener() {
                toggleSlidingMenu();
            }
        });
        setSupportActionBar(toolbar);
    }

    private void setSlidingMenu() {
        slidingMenu.setActivityDelegate(this);

    }



    public static void displayFragment(ScreenSettings item, boolean addToBackStack) {
        FragmentBase fragment;

        switch (item) {
            case PROFILE:
                fragment = FragmentProfile.newInstance();
                break;
            case HOME: default:
                fragment = FragmentHome.newInstance();
                break;
            case CAR:
                fragment = FragmentCar.newInstance();
                break;
            case SETTINGS:
                fragment = FragmentSettings.newInstance();
                break;
            case TEST:
                fragment = FragmentTest.newInstance();
                break;
        }

        if(sCustomToolbar != null)
            fragment.setCustomToolbar(sCustomToolbar);

        sFragmentManager.beginTransaction()
                .replace(R.id.fragment_content, fragment)
                .commit();
    }


    protected  void openSlidingMenu() { mSlidingPanel.openPane(); }

    protected void closeSlidingMenu() { mSlidingPanel.closePane(); }

    public void toggleSlidingMenu() {
        if(mSlidingPanel.isOpen()) {
            closeSlidingMenu();
        } else {
            openSlidingMenu();
        }
    }



}
