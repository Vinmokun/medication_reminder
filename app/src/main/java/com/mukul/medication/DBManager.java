package com.mukul.medication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseDriver dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseDriver(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String desc, String dose, String time, String units, String frequency) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseDriver.SUBJECT, name);
        contentValue.put(DatabaseDriver.DESC, desc);
        contentValue.put(DatabaseDriver.DOSE, dose);
        contentValue.put(DatabaseDriver.TIME, time);
        contentValue.put(DatabaseDriver.UNITS, units);
        contentValue.put(DatabaseDriver.FREQUENCY, frequency);

        database.insert(DatabaseDriver.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseDriver._ID, DatabaseDriver.SUBJECT, DatabaseDriver.DESC, DatabaseDriver.DOSE, DatabaseDriver.TIME, DatabaseDriver.UNITS, DatabaseDriver.FREQUENCY };
        Cursor cursor = database.query(DatabaseDriver.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String desc, String dose, String time, String units, String frequency) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseDriver.SUBJECT, name);
        contentValues.put(DatabaseDriver.DESC, desc);
        contentValues.put(DatabaseDriver.DOSE, dose);
        contentValues.put(DatabaseDriver.TIME, time);
        contentValues.put(DatabaseDriver.UNITS, units);
        contentValues.put(DatabaseDriver.FREQUENCY, frequency);

        int i = database.update(DatabaseDriver.TABLE_NAME, contentValues, DatabaseDriver._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseDriver.TABLE_NAME, DatabaseDriver._ID + "=" + _id, null);
    }

}
