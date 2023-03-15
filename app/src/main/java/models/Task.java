package models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = ForeignKey.CASCADE)})
public class Task {
    @PrimaryKey
    int id;
    String title;
    String completed;
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
