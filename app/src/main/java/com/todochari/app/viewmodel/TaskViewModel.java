package com.todochari.app.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.todochari.app.Repository.AppRepo;

import java.util.List;

import models.Task;

public class TaskViewModel extends AndroidViewModel {

    private AppRepo appRepo;

    public TaskViewModel(@NonNull Application application) {
        super(application);

        appRepo = new AppRepo(application);
    }

    public void insertTask(Task task){
        appRepo.insertTask(task);
    }

    public List<Task> getTasks(int uId){
        return appRepo.getTasks(uId);
    }
}
