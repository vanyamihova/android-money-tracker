package com.megaflashgames.budgethelper.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.megaflashgames.budgethelper.R;
import com.megaflashgames.budgethelper.annotations.ContentView;
import com.megaflashgames.budgethelper.annotations.InjectView;
import com.megaflashgames.budgethelper.db.DatabaseService;
import com.megaflashgames.budgethelper.model.Car;
import com.megaflashgames.budgethelper.ui.adapter.SlidingMenuAdapter;
import com.megaflashgames.budgethelper.ui.fragments.FragmentBase;
import com.megaflashgames.budgethelper.ui.fragments.FragmentHome;
import com.megaflashgames.budgethelper.ui.fragments.FragmentNavigation;
import com.megaflashgames.budgethelper.ui.fragments.FragmentCar;
import com.megaflashgames.budgethelper.ui.fragments.FragmentSettings;
import com.megaflashgames.budgethelper.ui.model.MenuItem;
import com.megaflashgames.budgethelper.ui.model.RainbowColor;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vanyamihova on 04/05/2015.
 */
@ContentView(R.layout.screen_dashboard)
public class ScreenDashboard extends ScreenBase implements FragmentNavigation.OnFragmentNavigationListener {

    private static final MenuItem FIRST_OPENED_FRAGMENT = MenuItem.CAR;

    private static final String TAG = ScreenDashboard.class.getSimpleName();

    @InjectView(R.id.layout_drawer)
    private SlidingPaneLayout mSlidingPanel;

    @InjectView(R.id.listview_menu)
    private ListView mListMenu;
    @InjectView(R.id.connectingLine)
    View connectingLine;

    // Fragments
    private FragmentBase mCurrentFragment;
    private FragmentNavigation mNavigation;

    // Adapters
    private SlidingMenuAdapter mSlidingMenuAdapter;

    private AdapterView.OnItemClickListener mOnMenuItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            onMenuItemClick((MenuItem) parent.getItemAtPosition(position));
            closeSlidingMenu();
        }
    };

    private FragmentBase.OnFragmentAction mOnFragmentAction = new FragmentBase.OnFragmentAction() {
        @Override
        public void onDisplayFragment(String fragmentTag, boolean addToBackStack, Object... data) {
            displayFragment(fragmentTag,addToBackStack,data);
        }
        @Override
        public void onFragmentVisibilityChange(FragmentBase fragment, boolean goToVisible) { }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        enableInjector(true);
        super.onCreate(savedInstanceState);

        // Add navigations
        mNavigation = FragmentNavigation.newInstance();
        mNavigation.setOnFragmentNavigationListener(this);
        getFragmentManager().beginTransaction().replace(R.id.fragment_navigation, mNavigation, FragmentNavigation.FRAGMENT_TAG).commit();
        // Add sliding menu
        mSlidingMenuAdapter = new SlidingMenuAdapter(this, getLayoutInflater(), Arrays.asList(MenuItem.values()));
        mListMenu.setAdapter(mSlidingMenuAdapter);
        mListMenu.setOnItemClickListener(mOnMenuItemClickListener);

//        // exit
//        mHandler = new Handler(Looper.getMainLooper());
//        mExitToast = Toast.makeText(this, getString(R.string.exitText), Toast.LENGTH_SHORT);

        onMenuItemClick(FIRST_OPENED_FRAGMENT);
    }

    private void onMenuItemClick(MenuItem item) {
        setColorTheme(Arrays.asList(MenuItem.values()).indexOf(item));
        switch(item) {
            case SETTINGS:
                displayFragment(FragmentSettings.FRAGMENT_TAG, false);
                break;
            case CAR:
                displayFragment(FragmentCar.FRAGMENT_TAG, false);
                break;
            case HOME:
                displayFragment(FragmentHome.FRAGMENT_TAG, false);
                break;
        }
    }

    protected boolean isSlidingMenuOpen() {
        return mSlidingPanel.isOpen();
    }

    protected  void openSlidingMenu() { mSlidingPanel.openPane(); }

    protected void closeSlidingMenu() { mSlidingPanel.closePane(); }

    public void toggleSlidingMenu() {
        if(isSlidingMenuOpen()) {
            closeSlidingMenu();
        } else {
            openSlidingMenu();
        }
    }

    @Override
    protected FragmentBase onNewFragment(FragmentBase fragment, String fragmentTag, Object... data) {
        if(fragment == null) {
            if (fragmentTag.equalsIgnoreCase(FragmentSettings.FRAGMENT_TAG)) {
                fragment = FragmentSettings.newInstance();
            } else if (fragmentTag.equalsIgnoreCase(FragmentCar.FRAGMENT_TAG)) {
                fragment = FragmentCar.newInstance();
            } else if (fragmentTag.equalsIgnoreCase(FragmentHome.FRAGMENT_TAG)) {
                fragment = FragmentHome.newInstance();
            }
            fragment.setFragmentNavigation(mNavigation);
        }

        mCurrentFragment = fragment;
        return mCurrentFragment;
    }

    @Override
    protected FragmentBase.OnFragmentAction onFragmentAction() { return mOnFragmentAction; }


    @Override
    public void onSlidingMenuToggle() {
        toggleSlidingMenu();
    }


    private void setColorTheme(int position) {
        int colorOfPosition = ScreenDashboard.this.getResources().getColor(RainbowColor.setPosition(position).getColor());

        connectingLine.setBackgroundColor(colorOfPosition);
        mNavigation.setBackgroundOnNavigation(colorOfPosition);
    }

}
