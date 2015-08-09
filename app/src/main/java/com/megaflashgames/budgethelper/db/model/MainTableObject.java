package com.megaflashgames.budgethelper.db.model;

import com.megaflashgames.budgethelper.db.DatabaseHelper;

/**
 * Created by Cec on 5/24/15.
 */
@Table(DatabaseHelper.TABLE_MAIN)
public class MainTableObject extends BaseDbObject {
    @Column(DatabaseHelper.MAIN_OBJECT)
    private String jsonObject;
    @Column(DatabaseHelper.MAIN_TYPE)
    private String type;

    public String getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(String jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MainTableObject{" +
                "jsonObject='" + jsonObject + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
