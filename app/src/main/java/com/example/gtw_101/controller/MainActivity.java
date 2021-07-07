package com.example.gtw_101.controller;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.gtw_101.R;
import com.example.gtw_101.dao.Test;
import com.example.gtw_101.utilities.DatabaseHandler;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    //public static DatabaseHandler database;
    private FirebaseAuth mAuth;

    /**
     * Override method onCreate to initialize basic login of the activity
     *
     * @param savedInstanceState storing the previous state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //database = new DatabaseHandler(this);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        //mAuth.signOut();
        //Test.test();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            System.out.println(currentUser.getEmail() + currentUser.getDisplayName());
            //currentUser.sendEmailVerification();
//            mAuth.sendPasswordResetEmail(currentUser.getEmail());

            //playGameIntent(findViewById(android.R.id.content).getRootView());
        }
    }

    /**
     * Create method loginIntent to change to login intent
     *
     * @param view storing view
     */
    public void loginIntent(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
    }

    /**
     * Create method playGameIntent to change to play game intent
     *
     * @param view storing view
     */
    public void playGameIntent(View view){
        Intent intent = new Intent(this, InGameActivity.class);
        this.startActivity(intent);
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