package com.todochari.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Intent intent;
    Integer count =1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = this.findViewById(R.id.progressBar);
        intent = new Intent(this.getApplicationContext(), UsersActivity.class);

        progressBar.setProgress(0);
        new AsyncProgressBar().execute(10);


    }


    private class AsyncProgressBar extends AsyncTask<Integer, Integer, String>{


        @Override
        protected String doInBackground(Integer... params) {
            for (; count <= params[0]; count++) {
                try {
                    Thread.sleep(1000);
                    publishProgress(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Task Completed.";

        }

        @Override
        protected void onPostExecute(String s) {
            try {
                this.finalize();
                startActivity(intent);
            } catch (Throwable e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            progressBar.setProgress(progress[0]);
        }




    }
}