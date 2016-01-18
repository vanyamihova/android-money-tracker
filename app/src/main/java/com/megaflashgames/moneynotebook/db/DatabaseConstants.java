package com.megaflashgames.moneynotebook.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Vanya Mihova on 11/21/2015.
 */
@Database(name = DatabaseConstants.NAME, version = DatabaseConstants.VERSION)
public class DatabaseConstants {

    public static final String NAME = "Money";

    public static final int VERSION = 1;

}
