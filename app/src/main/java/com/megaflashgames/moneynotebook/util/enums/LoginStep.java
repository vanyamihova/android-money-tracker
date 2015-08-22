package com.megaflashgames.moneynotebook.util.enums;

/**
 * Created by VanyaMihova on 8/9/2015.
 */
public enum LoginStep {
    USERNAME(0),
    PASSWORD(1),
    DONE(2);

    LoginStep(int type) { this.type = type; }

    private int type;

    public int getType() { return type; }
}
