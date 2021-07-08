package com.example.gtw_101.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gtw_101.R;
import com.example.gtw_101.fragment.HowToPlayFirstFragment;
import com.example.gtw_101.fragment.HowToPlaySecondFragment;

public class HowToPlayActivity extends AppCompatActivity {

    Button btnNext = findViewById(R.id.btn_next_h2p);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

    }

    public void nextButton(View view){
        if (btnNext.getText().toString().equals("HOME")) {
            FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.fragment_h2p_1, new HowToPlaySecondFragment());
            btnNext.setText("Home");
        }else {

        }
    }

    public void backButton(View view){
        if (btnNext.getText().toString().equals("HOME")){
            FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.fragment_h2p_2, new HowToPlayFirstFragment());
            btnNext.setText("Home");
        }
    }
}