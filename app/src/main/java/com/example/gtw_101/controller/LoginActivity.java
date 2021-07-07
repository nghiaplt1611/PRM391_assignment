package com.example.gtw_101.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.gtw_101.R;
import com.example.gtw_101.utilities.AlertDialogBuilder;
import com.example.gtw_101.utilities.Validation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().hide();
    }

    public void registerIntent(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        this.startActivity(intent);
    }

    public void onButtonLoginClick(View view) {
        EditText txtEmail = (EditText) findViewById(R.id.txt_email_login);
        EditText txtPassword = (EditText) findViewById(R.id.txt_password_login);

        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if (Validation.checkNullData(new String[]{email, password})){
            if (email.isEmpty()){
                txtEmail.setError("Email cannot be empty! Please input!");
            } if (password.isEmpty()) {
               txtPassword.setError("Password cannot be empty! Please input!");
            }

        } else {

            auth = FirebaseAuth.getInstance();
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser user = auth.getCurrentUser();
                        AlertDialogBuilder.showAlertDialog("Notification!", "Login successfully!", LoginActivity.this);
                    }
                    else {
                        AlertDialogBuilder.showAlertDialog("Notification!", "Email or password is invalid!!!", LoginActivity.this);
                    }
                }
            });


        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        EditText txtUsername = (EditText) findViewById(R.id.txt_email_login);
        EditText txtPassword = (EditText) findViewById(R.id.txt_password_login);
        txtPassword.setText("");
        txtUsername.setText("");
    }
}