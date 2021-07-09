package com.example.gtw_101.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gtw_101.R;

public class PlayerProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);
        getSupportActionBar().hide();
    }

    public void onButtonLogoutClick(View view){
        MainActivity.mAuth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    public void onButtonChangePass(View view){
        Intent intent = new Intent(this, ChangePasswordActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    public void onButtonEditProfile(View view){
        Intent intent = new Intent(this, EditProfilePlayerActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    public void onButtonChangeAvatar(View view){
        Intent intent = new Intent(this, ChangeAvatarActivity.class);
        this.startActivity(intent);
        this.finish();
    }

}