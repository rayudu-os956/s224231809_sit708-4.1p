package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String description;
    public String dueDate;

    public Task(String title, String description, String dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }
}

