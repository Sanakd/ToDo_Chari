package com.todochari.app.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.todochari.app.R;
import com.todochari.app.databinding.UserCardBinding;

import java.util.List;

import models.Task;
import models.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    List<User> userList;

    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        UserCardBinding userCardBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.user_card, parent, false);

        return new UsersAdapter.ViewHolder(userCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {
        if(userList!=null){
            User user = userList.get(position);
            holder.userCardBinding.setUser(user);
        }
    }

    @Override
    public int getItemCount() {
        if(userList != null)
            return userList.size();
        else
            return 0;
    }

    public void setUsers(List<User> users){
        userList = users;
        notifyDataSetChanged();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{

        UserCardBinding userCardBinding;


        public ViewHolder(@NonNull UserCardBinding userCardBinding) {
            super(userCardBinding.getRoot());
            this.userCardBinding = userCardBinding;
        }

    }
}
