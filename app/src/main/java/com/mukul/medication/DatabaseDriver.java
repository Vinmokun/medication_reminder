package com.mukul.medication;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseDriver extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "MEDICATION";

    // Table columns
    public static final String _ID = "_id";
    public static final String SUBJECT = "subject";
    public static final String DESC = "description";
    public static final String DOSE = "dose";
    public static final String TIME = "time";
    public static final String UNITS = "units";
    public static final String FREQUENCY = "frequency";


    // Database Information
    static final String DB_NAME = "MEDS.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table "
            + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SUBJECT + " TEXT NOT NULL, "
            + DESC + " TEXT, "
            + DOSE + " TEXT, "
            + TIME + " TEXT, "
            + UNITS + " TEXT, "
            + FREQUENCY + " TEXT);";

    public DatabaseDriver(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
