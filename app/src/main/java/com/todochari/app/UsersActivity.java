package com.todochari.app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.todochari.app.adapter.UsersAdapter;
import com.todochari.app.databinding.UsersBinding;
import com.todochari.app.viewmodel.UserViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import models.User;

public class UsersActivity extends AppCompatActivity {
    private UsersBinding binding;
    private UserViewModel userViewModel;
    private UsersAdapter usersAdapter;
    ArrayList<User> usersList;
    Handler mainHandler;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.usersRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        usersAdapter = new UsersAdapter();
        binding.usersRecyclerView.setAdapter(usersAdapter);
        new fetchData().start();

        //userViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(UserViewModel.class);

        usersAdapter.setUsers(usersList);
    }

    class fetchData extends Thread{

        public final String BASE_URL = "https://jsonplaceholder.typicode.com/";
        public final String USERS_END_POINT = "users/";
        public final String TASKS_END_POINT = "todos";

        String data = "";

        @Override
        public void run() {
            super.run();
            try {
                URL users_url = new URL(BASE_URL+USERS_END_POINT);
                URL tasks_url = new URL(BASE_URL+TASKS_END_POINT);

                HttpURLConnection httpURLConnection = (HttpURLConnection) users_url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while((line = bufferedReader.readLine())!= null){
                    data = data+line;
                }

                if(!data.isEmpty()){
                    JSONArray jsonArray = new JSONArray(data);

                    usersList.clear();
                    for(int i = 0; i< jsonArray.length(); i++ ){
                        User user = new User();

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        user.setId(jsonObject.getInt("id"));
                        user.setName(jsonObject.getString("name"));
                        user.setUsername(jsonObject.getString("username"));
                        user.setEmail(jsonObject.getString("email"));

                        usersList.add(user);

                    }

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                    usersAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}
