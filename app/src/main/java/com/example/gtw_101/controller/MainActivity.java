package com.example.gtw_101.controller;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gtw_101.R;
import com.example.gtw_101.dao.QuestionDAO;
import com.example.gtw_101.model.Question;
import com.example.gtw_101.utilities.LoadData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    //public static DatabaseHandler database;
    public static FirebaseAuth mAuth;
    public static FirebaseFirestore db;
    public static FirebaseUser user;


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
        LoadData.loadAllData();
        //mAuth.signOut();
        //Test.test();
    }

    @Override
    protected void onStart() {
        super.onStart();
        user = mAuth.getCurrentUser();
        if (user != null){
            //currentUser.sendEmailVerification();
//            mAuth.sendPasswordResetEmail(currentUser.getEmail());

            userMainMenuIntent(findViewById(android.R.id.content).getRootView());
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

    public void userMainMenuIntent(View view){
        Intent intent = new Intent(this, UserMainActivity.class);
        this.startActivity(intent);
        this.finish();
    }

}