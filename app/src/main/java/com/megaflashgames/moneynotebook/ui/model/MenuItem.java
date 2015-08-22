package com.megaflashgames.moneynotebook.ui.model;

import com.megaflashgames.moneynotebook.R;

/**
 * Created by vanyamihova on 05/05/2015.
 */
public enum MenuItem {
    HOME(R.string.icon_home, R.string.action_home),
    CAR(R.string.icon_car, R.string.action_car),
    SETTINGS(R.string.icon_settings, R.string.action_settings);


    private int resTitle;
    private int icon;

    MenuItem(int icon, int title) {
        this.resTitle = title;
        this.icon = icon;
    }

    public int resTitle() {
        return this.resTitle;
    }

    public int icon() {
        return this.icon;
    }

}
