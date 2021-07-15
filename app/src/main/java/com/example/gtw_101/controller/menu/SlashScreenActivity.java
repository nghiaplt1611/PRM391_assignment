package com.example.gtw_101.controller.menu;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.gtw_101.R;
import com.example.gtw_101.controller.menu.MainActivity;
import com.example.gtw_101.utilities.DatabaseHandler;
import com.example.gtw_101.utilities.LoadData;

public class SlashScreenActivity extends AppCompatActivity {

    public static DatabaseHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = new DatabaseHandler(this);
        setContentView(R.layout.activity_slash_screen);
        getSupportActionBar().hide();
        LoadData.loadAllData();
        new Handler().postDelayed(this::slashScreen,2000);
    }

    /**
     * method is used to make activity_slash_screen layout appear 2 seconds whenever the app start
      */
    public void slashScreen(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}