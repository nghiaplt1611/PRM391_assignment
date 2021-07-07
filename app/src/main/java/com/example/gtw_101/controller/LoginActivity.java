package com.example.gtw_101.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.gtw_101.R;
import com.example.gtw_101.model.Account;
import com.example.gtw_101.utilities.AlertDialogBuilder;
import com.example.gtw_101.utilities.MD5Hashing;
import com.example.gtw_101.utilities.Validation;


public class LoginActivity extends AppCompatActivity {

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
        EditText txtUsername = (EditText) findViewById(R.id.txt_email_login);
        EditText txtPassword = (EditText) findViewById(R.id.txt_password_login);

        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        if (Validation.checkNullData(new String[]{username, password})){
            if (username.isEmpty()){
                txtUsername.setError("Username cannot be empty! Please input!");
            } if (password.isEmpty()) {
               txtPassword.setError("Password cannot be empty! Please input!");
            }

        } else {
            Account account = MainActivity.database.login(username.trim(), MD5Hashing.getMD5Hash(password));
            if (account == null){
                AlertDialogBuilder.showAlertDialog("Alert!!!", "Username or password is invalid!", this);
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Login successfully! Have fun with the game!");

                // Set Alert Title
                builder.setTitle("Welcome back, " + account.getFullName());

                // Set Cancelable false
                // for when the user clicks on the outside
                // the Dialog Box then it will remain show
                builder.setCancelable(false);

                // Set the positive button with yes name
                // OnClickListener method is use of
                // DialogInterface interface.

                builder.setPositiveButton(
                                "Play Game",
                        (dialog, which) -> {
                            dialog.cancel();
                            Intent intent = new Intent(this, InGameActivity.class);
                            this.startActivity(intent);
                            this.finish();
                        });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();

                // Show the Alert Dialog box
                alertDialog.show();
            }
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