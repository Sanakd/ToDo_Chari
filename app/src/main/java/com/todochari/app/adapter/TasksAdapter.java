package com.todochari.app.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.todochari.app.R;
import com.todochari.app.databinding.TaskTileBinding;

import java.util.List;

import models.Task;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {

    List<Task> taskList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        TaskTileBinding taskTileBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.task_tile, parent, false);

        return new ViewHolder(taskTileBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(taskList!=null){
            Task task = taskList.get(position);
            holder.taskTileBinding.setTask(task);
        }
    }

    @Override
    public int getItemCount() {
        if(taskList != null)
            return taskList.size();
        else
            return 0;
    }

    public void setTasks(List<Task> tasks){
        taskList = tasks;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TaskTileBinding taskTileBinding;

        public ViewHolder(@NonNull TaskTileBinding taskTileBinding) {
            super(taskTileBinding.getRoot());
            this.taskTileBinding = taskTileBinding;
        }
    }
}
