package com.todochari.app.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.todochari.app.Repository.AppRepo;

import java.util.List;

import models.User;

public class UserViewModel extends AndroidViewModel {

    private AppRepo appRepo ;

    public UserViewModel(@NonNull Application application) {
        super(application);

        appRepo = new AppRepo(application);
    }

    public void insertUser(User user){
        appRepo.insertUser(user);
    }

    public List<User> getUsers(){
        return appRepo.getUsers();
    }
}
