package com.example.gtw_101.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gtw_101.R;

public class PlayerProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);
        getSupportActionBar().hide();
    }
}