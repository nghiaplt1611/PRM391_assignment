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

    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
        btnNext = findViewById(R.id.btn_next_h2p);
    }

    public void nextButton(View view){
        if (btnNext.getText().toString().equals("NEXT")) {
            FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.fragment_h2p_1, new HowToPlaySecondFragment());
            fm.commit();
            btnNext.setText("HOME");
        }
        else {
            this.finish();
        }
    }

    public void backButton(View view){
        if (btnNext.getText().toString().equals("HOME")){
            FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.fragment_h2p_2, new HowToPlayFirstFragment());
            fm.commit();
            btnNext.setText("NEXT");
        }
        else {
            this.finish();
        }
    }
}