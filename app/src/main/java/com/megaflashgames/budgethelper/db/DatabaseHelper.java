package com.megaflashgames.budgethelper.db;


import com.megaflashgames.budgethelper.model.Car;
import com.megaflashgames.budgethelper.model.Home;

/**
 * Created by vmihova on 5/16/15.
 */
public class DatabaseHelper {

    public static final int DATABASE_VERSION = 29; // CREATE_TABLE_WEATHER
    public static final String DATABASE_NAME = "bfantastic";

    public static final String COMMON_ID = "ID";

    // settings
    public static final String TABLE_SETTINGS = "Settings";
    public static final String SETTINGS_OPTION = "SettingsOption";
    public static final String SETTINGS_VALUE = "SeetingsValue";

    public static final String CREATE_TABLE_SETTINGS = String.format(
            "CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)",
            TABLE_SETTINGS, COMMON_ID, SETTINGS_OPTION, SETTINGS_VALUE);

    // main table
    public static final String TABLE_MAIN = "Main";
    public static final String MAIN_TYPE = "MainJsonType";
    public static final String MAIN_OBJECT = "MainJsonObject";

    public static final String CREATE_MAIN_TABLE = String.format(
            "CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)",
            TABLE_MAIN, COMMON_ID, MAIN_TYPE, MAIN_OBJECT);

    // history table
    public static final String TABLE_HISTORY = "History";
    public static final String HISTORY_DATE = "HistoryDate";
    public static final String HISTORY_OBJECT = "HistoryJsonObject";

    public static final String CREATE_HISTORY_TABLE = String.format(
            "CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s REAL, %s TEXT)",
            TABLE_HISTORY, COMMON_ID, HISTORY_DATE, HISTORY_OBJECT);

    // attachment table
    public static final String TABLE_ATTACHMENT = "Attachment";
    public static final String ATTACHMENT_OBJECT = "AttachmentJsonObject";
    public static final String ATTACHMENT_TYPE = "AttachmentType";
    public static final String ATTACHMENT_STATUS = "AttachmentStatus";
    public static final String CREATE_ATTACHMENTS_TABLE = String.format(
            "CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s INTEGER)",
            TABLE_ATTACHMENT, COMMON_ID, ATTACHMENT_TYPE, ATTACHMENT_OBJECT, ATTACHMENT_STATUS);


    public enum MainTableTypes {
        CAR(Car.class.getSimpleName()),
        HOME(Home.class.getSimpleName());

        private String name;

        MainTableTypes(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }


}