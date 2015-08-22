package com.megaflashgames.moneynotebook.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.util.Log;

import com.megaflashgames.moneynotebook.db.model.BaseDbObject;
import com.megaflashgames.moneynotebook.db.model.Column;
import com.megaflashgames.moneynotebook.db.model.SearchCriteria;
import com.megaflashgames.moneynotebook.db.model.Table;
import com.megaflashgames.moneynotebook.service.ContextService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DaoService {

    private static final String TAG = DaoService.class.getSimpleName();

    private static DaoService Instance;

    private DatabaseManager mDatabaseManager;

    public static DaoService GetInstance() {
        Log.d(TAG, "DaoService GetInstance()");
        if (Instance == null) {
            Instance = new DaoService();
            Log.d(TAG, "DaoService is created");
        }

        return Instance;
    }

    private DaoService() {
        mDatabaseManager = new DatabaseManager(ContextService.GetInstance().inject());
    }

    public <T extends BaseDbObject> T get(long id, Class<T> clazz) {
        Cursor c = mDatabaseManager.get(getTableName(clazz), createColumns(clazz), id);
        T t = null;
        try {
            if (c.moveToFirst()) {
                t = createObjectFromCursor(c, clazz);
            }
        } catch (Exception e) {
            Log.w(TAG, String.format("Exception while get object from db with id %d. With exception %s", id, e), e);
        }
        c.close();
        return t;
    }

    public <T extends BaseDbObject> List<T> getAll(Class<T> clazz, SearchCriteria criteria) {
        List<T> all = new ArrayList<T>();
        Cursor c = null;

        if (criteria != null) {
            c = mDatabaseManager.getAllByCriteria(getTableName(clazz), createColumns(clazz), criteria);
        } else {
            c = mDatabaseManager.getAll(getTableName(clazz), createColumns(clazz));
        }

        try {
            while (c.moveToNext()) {
                all.add(createObjectFromCursor(c, clazz));
            }
        } catch (Exception e) {
            Log.w(TAG, String.format("Exception while get all object from db. With exception %s", e), e);
        }
        c.close();
        return all;
    }


    public <T extends BaseDbObject> List<T> getAllByType(Class<T> clazz, @NonNull String type) {
        List<T> all = new ArrayList<T>();
        Cursor c = null;

        c = mDatabaseManager.getAllByType(getTableName(clazz), createColumns(clazz), type);

        try {
            while (c.moveToNext()) {
                all.add(createObjectFromCursor(c, clazz));
            }
        } catch (Exception e) {
            Log.w(TAG, String.format("Exception while get all object from db. With exception %s", e), e);
        }
        c.close();
        return all;
    }

    public <T extends BaseDbObject> T save(T record) {
        String table = getTableName(record.getClass());
        ContentValues values = createContentValues(record);
        if (record.hasId()) {
            mDatabaseManager.update(table, values, record.getId());
        } else {
            long id = mDatabaseManager.insert(table, values);
            if (id >= 0) {
                record.setId(id);
            }
        }

        return record;
    }

    public <T extends BaseDbObject> boolean delete(T record) {
        String table = getTableName(record.getClass());
        if (record.hasId()) {
            mDatabaseManager.delete(table, record.getId());
            return true;
        }

        return false;
    }

    public <T extends BaseDbObject> void deleteAllRows(Class<T> clazz) {
        String table = getTableName(clazz);
        mDatabaseManager.deleteAllRows(table);
    }

    public <T extends BaseDbObject> void deleteAllRowsByType(Class<T> clazz, String type) {
        String table = getTableName(clazz);
        mDatabaseManager.deleteAllRowsByType(table, type);
    }


    private <T extends BaseDbObject> ContentValues createContentValues(T record) {
        Log.d(TAG, String.format("Create ContetnValues from record %s", record.toString()));
        ContentValues values = new ContentValues();
        try {
            Field[] fields = record.getClass().getDeclaredFields();
            for (Field f : fields) {
                if (f.isAnnotationPresent(Column.class)) {
                    Column c = f.getAnnotation(Column.class);
                    f.setAccessible(true);
                    Object v = f.get(record);
                    if (v != null) {
                        values.put(c.value(), v.toString());
                    }
                }
            }
        } catch (Exception e) {
            Log.w(TAG, String.format("Exception while create content view for record %s. With exception %s", record.toString(), e), e);
        }

        return values;
    }

    private <T extends BaseDbObject> String[] createColumns(Class<T> clazz) {
        Log.d(TAG, String.format("Create columns from record %s", clazz.toString()));
        List<String> columns = new ArrayList<String>();
        columns.add(DatabaseHelper.COMMON_ID);
        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                if (f.isAnnotationPresent(Column.class)) {
                    Column c = f.getAnnotation(Column.class);
                    columns.add(c.value());
                }
            }
        } catch (Exception e) {
            Log.w(TAG, String.format("Exception while create columns for record %s. With exception %s", clazz.toString(), e), e);
        }

        return columns.toArray(new String[columns.size()]);
    }

    private <T extends BaseDbObject> String getTableName(Class<T> clazz) {
        if (clazz.isAnnotationPresent(Table.class)) {
            Table t = clazz.getAnnotation(Table.class);
            return t.value();
        }

        return null;
    }

    private <T extends BaseDbObject> T createObjectFromCursor(Cursor cursor, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        Log.d(TAG, String.format("Create object from cursor db"));

        T t = clazz.newInstance();
        t.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COMMON_ID)));

        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                if (f.isAnnotationPresent(Column.class)) {
                    Column c = f.getAnnotation(Column.class);
                    f.setAccessible(true);
                    handleFieldType(f, t, c.value(), cursor);
                }
            }
        } catch (Exception e) {
            Log.w(TAG, String.format("Exception while create object from cursor db. With exception %s", e), e);
        }

        return t;
    }

    private void handleFieldType(Field field, Object object, String column, Cursor cursor) throws IllegalAccessException, IllegalArgumentException {
        if (field.getType().getName().equalsIgnoreCase(String.class.getName())) {
            field.set(object, cursor.getString(cursor.getColumnIndex(column)));
        } else if (field.getType().getName().equalsIgnoreCase(int.class.getName())) {
            field.set(object, cursor.getInt(cursor.getColumnIndex(column)));
        } else if (field.getType().getName().equalsIgnoreCase(Integer.class.getName())) {
            field.set(object, cursor.getInt(cursor.getColumnIndex(column)));
        } else if (field.getType().getName().equalsIgnoreCase(double.class.getName())) {
            field.set(object, cursor.getDouble(cursor.getColumnIndex(column)));
        } else if (field.getType().getName().equalsIgnoreCase(Double.class.getName())) {
            field.set(object, cursor.getDouble(cursor.getColumnIndex(column)));
        } else if (field.getType().getName().equalsIgnoreCase(float.class.getName())) {
            field.set(object, cursor.getFloat(cursor.getColumnIndex(column)));
        } else if (field.getType().getName().equalsIgnoreCase(Float.class.getName())) {
            field.set(object, cursor.getFloat(cursor.getColumnIndex(column)));
        } else if (field.getType().getName().equalsIgnoreCase(short.class.getName())) {
            field.set(object, cursor.getShort(cursor.getColumnIndex(column)));
        } else if (field.getType().getName().equalsIgnoreCase(Short.class.getName())) {
            field.set(object, cursor.getShort(cursor.getColumnIndex(column)));
        } else if (field.getType().getName().equalsIgnoreCase(long.class.getName())) {
            field.set(object, cursor.getLong(cursor.getColumnIndex(column)));
        } else if (field.getType().getName().equalsIgnoreCase(Long.class.getName())) {
            field.set(object, cursor.getLong(cursor.getColumnIndex(column)));
        }
    }

}
