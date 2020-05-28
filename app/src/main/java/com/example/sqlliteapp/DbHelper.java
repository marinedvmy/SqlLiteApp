package com.example.sqlliteapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="SQLite.db";
    public static final String TABLE_NAME="SQLite";

    //COLUMNS

    public static final String COLUMNS_1="ID";
    public static final String COLUMNS_2="first_name";
    public static final String COLUMNS_3="address";
    public static final String COLUMNS_4="phone_number";
    public static final String COLUMNS_5="email";
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, first_name TEXT, address TEXT, phone_number TEXT, email TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

}
