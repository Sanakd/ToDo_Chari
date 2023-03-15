package com.todochari.app.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import models.User;

@Dao
public interface UserDao {

    @Insert(entity = User.class,onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("SELECT name, username, email FROM user WHERE id=:userId")
    User getUser(int userId);

    @Query("SELECT * FROM user")
    List<User> getUsers();
}
