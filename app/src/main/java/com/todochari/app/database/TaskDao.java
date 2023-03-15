package com.todochari.app.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import models.Task;

@Dao
public interface TaskDao {
    @Insert(entity = Task.class,onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Task task);

    @Query("SELECT * FROM Task WHERE userId=:uId")
    List<Task> getTasks(int uId);

    @Query("SELECT * FROM Task WHERE id=:taskId AND userId=:uId")
    Task getTask(int taskId, int uId);

}
