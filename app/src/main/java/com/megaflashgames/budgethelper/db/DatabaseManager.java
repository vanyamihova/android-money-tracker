package com.megaflashgames.budgethelper.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.megaflashgames.budgethelper.db.model.SearchCriteria;

class DatabaseManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = DatabaseHelper.DATABASE_NAME;
    public static final int DATABASE_VERSION = DatabaseHelper.DATABASE_VERSION;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseHelper.CREATE_MAIN_TABLE);
        db.execSQL(DatabaseHelper.CREATE_HISTORY_TABLE);
        db.execSQL(DatabaseHelper.CREATE_ATTACHMENTS_TABLE);
        db.execSQL(DatabaseHelper.CREATE_TABLE_SETTINGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: to be implemented !!! by Cec
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    public long insert(String tableName, ContentValues values) {
        SQLiteDatabase db = getReadableDatabase();
        return db.insert(tableName, null, values);
    }

    public void update(String tableName, ContentValues values, long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(tableName, values, " id = ? ", new String[]{String.valueOf(id)});
    }

    public Cursor get(String table, String[] columns, long id) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(table, columns, " id = ? ", new String[]{String.valueOf(id)}, null, null, null);
    }

    public Cursor getAll(String table, String[] columns) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(table, columns, null, null, null, null, null);
    }

    public Cursor getAllByCriteria(String table, String[] columns, SearchCriteria criteria) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(table, columns, criteria.getWhereClause(), criteria.getWhereArgs(), null, null, null);
    }

    public Cursor getAllByType(String table, String[] columns, String type) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(table, columns, DatabaseHelper.MAIN_TYPE + " = ? ", new String[]{type}, null, null, null);
    }

    public int delete(String table, long id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(table, " id = ? ", new String[]{String.valueOf(id)});
    }

    public int deleteAllRows(String table) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(table, null, null);
    }

    public int deleteAllRowsByType(String table, String type) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(table, DatabaseHelper.MAIN_TYPE + " = ?", new String[]{type});
    }
}
