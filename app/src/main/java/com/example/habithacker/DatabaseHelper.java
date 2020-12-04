package com.example.habithacker;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "notes_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Habit.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Habit.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insert(Habit habit) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Habit.COLUMN_NAME, habit.getName());
        values.put(Habit.COLUMN_DESC, habit.getDescription());
        values.put(Habit.COLUMN_FREQ, habit.getFrequency());
        values.put(Habit.COLUMN_PROG, habit.getProgress());

        // insert row
        long id = db.insert(Habit.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public ArrayList<Habit> getAllHabit() {
        ArrayList<Habit> habits = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Habit.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Habit habit = new Habit();
                habit.setId(cursor.getInt(cursor.getColumnIndex(Habit.COLUMN_ID)));
                habit.setName(cursor.getString(cursor.getColumnIndex(Habit.COLUMN_NAME)));
                habit.setDescription(cursor.getString(cursor.getColumnIndex(Habit.COLUMN_DESC)));
                habit.setFrequency(cursor.getString(cursor.getColumnIndex(Habit.COLUMN_FREQ)));
                habit.setProgress(cursor.getString(cursor.getColumnIndex(Habit.COLUMN_PROG)));

                habits.add(habit);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return habits;
    }

    public int updateHabit(Habit habit, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Habit.COLUMN_NAME, habit.getName());
        values.put(Habit.COLUMN_DESC, habit.getDescription());
        values.put(Habit.COLUMN_FREQ, habit.getFrequency());
        values.put(Habit.COLUMN_PROG, habit.getProgress());

        // updating row
        return db.update(Habit.TABLE_NAME, values, Habit.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteHabit(Habit habit) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Habit.TABLE_NAME, Habit.COLUMN_ID + " = ?",
                new String[]{String.valueOf(habit.getId())});
        db.close();
    }
}