package com.example.robinjava;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "App2";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Nationaliteter";
    private static final String COLUMN_NAME = "Nationalitet";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<String> getSpinnerOptions() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> options = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_NAME + " FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                String option = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                options.add(option);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return options;
    }
}
