package com.megaflashgames.moneynotebook.db.model;

import com.megaflashgames.moneynotebook.db.DatabaseConstants;
import com.megaflashgames.moneynotebook.util.enums.CarSpendType;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Vanya Mihova on 11/21/2015.
 */
@Table(databaseName = DatabaseConstants.NAME)
public class Car extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public double total;
    @Column
    public int kilometers;
    @Column
    public long crateAt;
    @Column
    public long modifyAt;
    @Column
    public CarSpendType type;

    public void saveData() {
        if(id > 0)
            update();
        else
            save();
    }
}
