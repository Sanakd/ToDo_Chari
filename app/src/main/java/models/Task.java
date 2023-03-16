package models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Task {

    @PrimaryKey
    int id;
    String title;
    String completed;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        if(completed == true)
        this.completed = "completed";
        else
            this.completed = "progress";
    }
}
