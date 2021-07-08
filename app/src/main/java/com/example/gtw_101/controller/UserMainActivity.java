package com.example.gtw_101.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.gtw_101.R;
import com.example.gtw_101.dao.AchievementDAO;
import com.example.gtw_101.dao.QuestionDAO;
import com.example.gtw_101.model.Question;

public class UserMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        getSupportActionBar().hide();
    }



    /**
     * Create method profileIntent to change to player profile intent
     *
     * @param view storing view
     */
    public void onButtonProfileClick(View view){
        Intent intent = new Intent(this, PlayerProfileActivity.class);
        this.startActivity(intent);
    }

    public void onButtonAchievementClick(View view){
        Intent intent = new Intent(this, AchievementActivity.class);
        this.startActivity(intent);
    }

    public void onButtonPlayGameClick(View view){
        Intent intent = new Intent(this, InGameActivity.class);
        this.startActivity(intent);
    }

}