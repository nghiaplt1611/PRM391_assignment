package com.example.gtw_101.controller.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.gtw_101.R;

public class LeaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader);
        getSupportActionBar().hide();
    }

    public void returnToUserMenuIntent(View view){
        this.finish();
    }
}