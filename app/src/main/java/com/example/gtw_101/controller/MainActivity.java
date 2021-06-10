package com.example.gtw_101.controller;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gtw_101.R;
import com.example.gtw_101.utilities.DatabaseHandler;

public class MainActivity extends AppCompatActivity {

    public static DatabaseHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = new DatabaseHandler(this);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }


    public void loginIntent(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
    }

    public void playGameIntent(View view){
        Intent intent = new Intent(this, InGameActivity.class);
        this.startActivity(intent);
    }
}