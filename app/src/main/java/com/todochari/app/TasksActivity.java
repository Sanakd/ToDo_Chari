package com.todochari.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.todochari.app.adapter.TasksAdapter;
import com.todochari.app.databinding.TasksBinding;
import com.todochari.app.viewmodel.TaskViewModel;

public class TasksActivity extends AppCompatActivity {
    private TasksBinding binding;
    private TaskViewModel taskViewModel;
    private TasksAdapter tasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TasksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new TasksAdapter();
        binding.tasksRecyclerView.setAdapter(tasksAdapter);

        taskViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(TaskViewModel.class);
        tasksAdapter.setTasks(taskViewModel.getTasks(1));
    }
}
