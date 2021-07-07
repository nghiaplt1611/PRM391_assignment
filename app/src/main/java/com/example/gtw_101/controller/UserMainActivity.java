package com.example.gtw_101.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gtw_101.R;

public class UserMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
    }
    /**
     * Create method profileIntent to change to player profile intent
     *
     * @param view storing view
     */
    public void profileIntent(View view){
        Intent intent = new Intent(this, PlayerProfileActivity.class);
        this.startActivity(intent);
    }
}