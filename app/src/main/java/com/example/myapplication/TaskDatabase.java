package com.example.myapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Task.class}, version = 1, exportSchema = false)

public abstract class TaskDatabase extends RoomDatabase {
    private static TaskDatabase instance;

    public abstract TaskDao taskDao();

    public static synchronized TaskDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            TaskDatabase.class, "task_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
