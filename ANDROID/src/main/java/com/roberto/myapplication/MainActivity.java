package com.roberto.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roberto.myapplication.conection.ApacheConection;
import com.roberto.myapplication.controller.AsyncTaskController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AsyncTaskController asyncTaskController = new AsyncTaskController();
        asyncTaskController.execute("");
    }
}
