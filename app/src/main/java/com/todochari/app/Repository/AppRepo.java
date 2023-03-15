package com.todochari.app.Repository;

import android.content.Context;

import com.todochari.app.database.AppDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import models.Task;
import models.User;

public class AppRepo {
    private AppDatabase appDatabase;
    private Executor executor = Executors.newSingleThreadExecutor();

    public AppRepo(Context context){
        appDatabase = AppDatabase.getInstance(context);
    }

    public void insertUser(User user){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.userDao.insertUser(user);
            }
        });
    }

    public void insertTask(Task task){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.taskDao.insertTask(task);
            }
        });
    }

    public List<User> getUsers(){
        return appDatabase.userDao.getUsers();
    }

    public List<Task> getTasks(int uId){
        return appDatabase.taskDao.getTasks(uId);
    }
}
