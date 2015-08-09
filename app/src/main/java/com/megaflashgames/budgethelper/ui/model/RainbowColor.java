package com.megaflashgames.budgethelper.ui.model;

import com.megaflashgames.budgethelper.R;

/**
 * Created by VanyaMihova on 8/2/2015.
 */
public class RainbowColor {

    public static RainbowColor setPosition(int position) {
        return new RainbowColor(position);
    }

    private RainbowColor(int position) {
        switch (position) {
            case 0: default:
                this.color = R.color.color_1; break;
            case 1:
                this.color = R.color.color_2; break;
            case 2:
                this.color = R.color.color_3; break;
            case 3:
                this.color = R.color.color_4; break;
            case 4:
                this.color = R.color.color_5; break;
            case 5:
                this.color = R.color.color_6; break;
            case 6:
                this.color = R.color.color_7; break;
            case 7:
                this.color = R.color.color_8; break;
            case 8:
                this.color = R.color.color_9; break;
            case 9:
                this.color = R.color.color_10; break;
        }

    }

    private int color;

    public int getColor() {
        return color;
    }
}
