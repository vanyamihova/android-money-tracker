package com.megaflashgames.moneynotebook.db.model;

import com.megaflashgames.moneynotebook.db.DatabaseHelper;

/**
 * Created by Cec on 5/24/15.
 */
@Table(DatabaseHelper.TABLE_HISTORY)
public class HistoryTableObject extends BaseDbObject {
    @Column(DatabaseHelper.HISTORY_DATE)
    private long date;
    @Column(DatabaseHelper.HISTORY_OBJECT)
    private String jsonObject;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(String jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public String toString() {
        return "HistoryTableObject{" +
                "date=" + date +
                ", jsonObject='" + jsonObject + '\'' +
                '}';
    }
}
