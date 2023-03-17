package com.todochari.app;

import static java.sql.DriverManager.println;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.todochari.app.adapter.TasksAdapter;
import com.todochari.app.databinding.TasksBinding;
import com.todochari.app.viewmodel.TaskViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import models.Task;
import models.User;

public class TasksActivity extends AppCompatActivity {
    private TasksBinding binding;
    //private TaskViewModel taskViewModel;
    private TasksAdapter tasksAdapter;
    private int userId;
    ArrayList<Task> tasksList;
    Handler mainHandler;
    ProgressDialog progressDialog;
    public static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TasksBinding.inflate(getLayoutInflater());
        tasksAdapter = new TasksAdapter();
        progressDialog = new ProgressDialog(this);
        tasksList = new ArrayList<Task>();
        mainHandler = new Handler();
        intent = new Intent(this.getApplicationContext(), UsersActivity.class);
        userId = 1;

        setContentView(binding.getRoot());
        binding.tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.tasksRecyclerView.setAdapter(tasksAdapter);
        //taskViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(TaskViewModel.class);
        new fetchData().start();
        tasksAdapter.setTasks(tasksList);
    }

    class fetchData extends Thread{

        public final String BASE_URL = "https://jsonplaceholder.typicode.com/";
        //public final String USERS_END_POINT = "users/";
        public final String TASKS_END_POINT = "todos?userId=1";

        String data = "";

        @Override
        public void run() {
            super.run();
            try {
                //URL users_url = new URL(BASE_URL+USERS_END_POINT);
                URL tasks_url = new URL(BASE_URL+TASKS_END_POINT);

                HttpURLConnection httpURLConnection = (HttpURLConnection) tasks_url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while((line = bufferedReader.readLine())!= null){
                    data = data+line;
                }

                if(!data.isEmpty()){
                    JSONArray jsonArray = new JSONArray(data);
                    println("__________________________________________________________________");
                    println(jsonArray.toString());
                    println("__________________________________________________________________");

                    tasksList.clear();
                    for(int i = 0; i< jsonArray.length(); i++ ){
                        Task task = new Task();

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        task.setId(jsonObject.getInt("id"));

                        task.setTitle(jsonObject.getString("title"));

                        task.setCompleted(jsonObject.getBoolean("completed"));

                        task.setUserId(1);

                        tasksList.add(task);

                    }

                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            mainHandler.post(new Runnable() {

                @Override
                public void run() {
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                    tasksAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    //TODO:use bundle or intent to get userId
}
