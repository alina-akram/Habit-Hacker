package com.example.habithacker;

public class Habit {
    private String name;
    private String description;
    private String frequency;
    private String progress;

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

    public Habit(String name, String description, String frequency, String progress){
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.progress = progress;
    }
}
