package com.megaflashgames.moneynotebook.ui.custom;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.megaflashgames.moneynotebook.R;
import com.megaflashgames.moneynotebook.StartScreen;
import com.megaflashgames.moneynotebook.ui.ScreenDashboard;
import com.megaflashgames.moneynotebook.ui.fragments.FragmentCar;
import com.megaflashgames.moneynotebook.ui.fragments.FragmentHome;
import com.megaflashgames.moneynotebook.ui.fragments.FragmentProfile;
import com.megaflashgames.moneynotebook.ui.fragments.FragmentTest;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Vanya Mihova on 11/21/2015.
 */
public class SlidingMenu extends RelativeLayout {

    private ScreenDashboard mActivity;

    public SlidingMenu(Context context) {
        super(context);
        init();
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.layout_sliding_menu, this);
        ButterKnife.bind(this);
    }



    // ACTIVITY DELEGATE
    public void setActivityDelegate(ScreenDashboard delegate) {
        this.mActivity = delegate;
    }

    @OnClick(R.id.image_avatar)
    public void goToProfile() {
        if (mActivity != null)
            mActivity.displayFragment(FragmentProfile.MENU_TAG, false);
    }

    @OnClick(R.id.btn_home)
    public void goHome() {
        if (mActivity != null)
            mActivity.displayFragment(FragmentHome.MENU_TAG, false);
    }

    @OnClick(R.id.btn_car)
    public void goCar() {
        if (mActivity != null)
            mActivity.displayFragment(FragmentCar.MENU_TAG, false);
    }

    @OnClick(R.id.btn_settings)
    public void goSettings() {
        if (mActivity != null)
            mActivity.displayFragment(FragmentTest.MENU_TAG, false);
    }

    @OnClick(R.id.btn_log_out)
    public void logOut() {
        if (mActivity != null) {
            Intent intent = new Intent(mActivity, StartScreen.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mActivity.startActivity(intent);
        }
    }

}
