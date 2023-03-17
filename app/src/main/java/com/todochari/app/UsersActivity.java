package com.todochari.app;

import static java.sql.DriverManager.println;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.net.URL;
import java.util.ArrayList;

import models.User;

public class UsersActivity extends AppCompatActivity {
    private UsersBinding binding;
    private UsersAdapter usersAdapter;
    ArrayList<User> usersList;
    Handler mainHandler;
    ProgressDialog progressDialog;
    public static Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UsersBinding.inflate(getLayoutInflater());
        usersAdapter = new UsersAdapter();
        progressDialog = new ProgressDialog(this);
        usersList = new ArrayList<User>();
        mainHandler = new Handler();
        intent = new Intent(this.getApplicationContext(), TasksActivity.class);


        setContentView(binding.getRoot());
        binding.usersRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        //userViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(UserViewModel.class);
        binding.usersRecyclerView.setAdapter(usersAdapter);
        new fetchData().start();
        usersAdapter.setUsers(usersList);


    }


    @Override
    protected void onPostResume() {
        super.onPostResume();

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cardView;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            cardView = (CardView) itemLayoutView.findViewById(R.id.userCardView);
        }

        @Override
        public void onClick(View v) {
            int userId = 1;
            intent.putExtra("userId", userId);
            startActivity(intent);
            Log.d("click", "Ouach! these are their tasks");

        }
    }


    class fetchData extends Thread {

        public final String BASE_URL = "https://jsonplaceholder.typicode.com/";
        public final String USERS_END_POINT = "users/";
        //public final String TASKS_END_POINT = "todos";

        String data = "";

        @Override
        public void run() {
            super.run();
            try {
                URL users_url = new URL(BASE_URL + USERS_END_POINT);
                //URL tasks_url = new URL(BASE_URL+TASKS_END_POINT);

                HttpURLConnection httpURLConnection = (HttpURLConnection) users_url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    data = data + line;
                }

                if (!data.isEmpty()) {
                    JSONArray jsonArray = new JSONArray(data);
                    println(jsonArray.toString());
                    usersList.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        User user = new User();

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        user.setId(jsonObject.getInt("id"));
                        user.setName(jsonObject.getString("name"));
                        user.setUsername(jsonObject.getString("username"));
                        user.setEmail(jsonObject.getString("email"));

                        usersList.add(user);

                    }

                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            mainHandler.post(new Runnable() {

                @Override
                public void run() {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    usersAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    //TODO: replace view holder to UsersAdapter to rich recycler view item clicked

}
