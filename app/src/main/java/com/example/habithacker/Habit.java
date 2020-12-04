package com.example.habithacker;

public class Habit {
    private int id;
    private String name;
    private String description;
    private String frequency;
    private String progress;

    public static final String TABLE_NAME = "habit";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESC = "description";
    public static final String COLUMN_FREQ = "frequency";
    public static final String COLUMN_PROG = "progress";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_DESC + " TEXT,"
                    + COLUMN_FREQ + " TEXT,"
                    + COLUMN_PROG + " TEXT"
                    + ")";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Habit(){

    }

    public Habit(String name, String description, String frequency, String progress){
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.progress = progress;
    }



}
