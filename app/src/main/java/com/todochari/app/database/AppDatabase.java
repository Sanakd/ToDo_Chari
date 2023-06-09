package com.todochari.app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import models.Task;
import models.User;

@Database(entities = {User.class, Task.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "app_database.db";
    public static AppDatabase instance ;
    private static final Object LOCK = new Object();
    public TaskDao taskDao;
    public UserDao userDao;

    public static AppDatabase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,DATABASE_NAME).allowMainThreadQueries().build();

                }
            }
        }
        return instance;
    }



}
