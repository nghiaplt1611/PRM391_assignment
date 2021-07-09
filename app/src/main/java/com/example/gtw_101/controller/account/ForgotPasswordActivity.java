package com.example.gtw_101.controller.account;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.gtw_101.R;
import com.example.gtw_101.controller.menu.MainActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();
    }

    public void onButtonContinueClick(View view){
        EditText txtEmail = (EditText) findViewById(R.id.txt_email_forgot_password);
        String email = txtEmail.getText().toString();
        //MainActivity.mAuth.sendPasswordResetEmail()
    }
}