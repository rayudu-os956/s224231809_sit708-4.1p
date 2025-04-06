package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY dueDate ASC")
    List<Task> getAllTasksSortedByDueDate();

    @Insert
    void insertTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);
    @Query("SELECT * FROM tasks WHERE title = :title LIMIT 1")
    Task getTaskByTitle(String title);
}

