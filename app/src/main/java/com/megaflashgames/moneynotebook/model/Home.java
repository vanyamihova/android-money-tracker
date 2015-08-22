package com.megaflashgames.moneynotebook.model;

import com.megaflashgames.moneynotebook.db.model.BaseDbObject;

/**
 * Created by VanyaMihova on 8/2/2015.
 */
public class Home extends BaseDbObject {

    private String description;
    private double spentMoney;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(double spentMoney) {
        this.spentMoney = spentMoney;
    }

    @Override
    public String toString() {
        return "Home {" +
                "description: " + description +
                ", spentMoney: " + spentMoney +
                "}";
    }

}
