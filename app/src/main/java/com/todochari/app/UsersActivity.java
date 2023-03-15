package com.todochari.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.todochari.app.adapter.UsersAdapter;
import com.todochari.app.databinding.UsersBinding;
import com.todochari.app.viewmodel.UserViewModel;

public class UsersActivity extends AppCompatActivity {
    private UsersBinding binding;
    private UserViewModel userViewModel;
    private UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.usersRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        usersAdapter = new UsersAdapter();
        binding.usersRecyclerView.setAdapter(usersAdapter);

        userViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(UserViewModel.class);
        usersAdapter.setUsers(userViewModel.getUsers());
    }
}
