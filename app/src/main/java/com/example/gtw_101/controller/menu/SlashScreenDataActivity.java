package com.example.gtw_101.controller.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.gtw_101.R;
import com.example.gtw_101.utilities.LoadData;

public class SlashScreenDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash_screen_data);
        getSupportActionBar().hide();
        new Handler().postDelayed(this::slashScreen,2000);

    }

    /**
     * method is used to make activity_slash_screen_data layout appear 2 seconds
     */
    public void slashScreen(){
        Intent intent = new Intent(this, MainActivity.class);   //Nữa đổi class chỗ này nha
        startActivity(intent);
        finish();
    }
}