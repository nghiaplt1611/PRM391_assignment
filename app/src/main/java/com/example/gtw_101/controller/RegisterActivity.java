package com.example.gtw_101.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gtw_101.R;
import com.example.gtw_101.model.Account;
import com.example.gtw_101.utilities.AlertDialogBuilder;
import com.example.gtw_101.utilities.MD5Hashing;
import com.example.gtw_101.utilities.Validation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
    }

    public void onButtonRegisterClick(View view) {
        EditText txtFullName = (EditText) findViewById(R.id.txt_full_name);
        EditText txtUsername = (EditText) findViewById(R.id.txt_username);
        EditText txtPassword = (EditText) findViewById(R.id.txt_password);
        EditText txtConfirmPassword = (EditText) findViewById(R.id.txt_confirm_password);


        String fullName = txtFullName.getText().toString();
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        String confirmPassword = txtConfirmPassword.getText().toString();

        if (Validation.checkNullData(new String[]{fullName, username, password, confirmPassword})){

            if (fullName.isEmpty()){
                txtFullName.setError("Full name cannot be empty! Please input!");
            }

            if (username.isEmpty()){
                txtUsername.setError("Username cannot be empty! Please input!");
            }

            if (password.isEmpty()){
                txtPassword.setError("Password cannot be empty! Please input!");
            }

            if (confirmPassword.isEmpty()){
                txtConfirmPassword.setError("Confirm password cannot be empty! Please input!");
            }

        }
        if (!(Validation.checkNameFormat(fullName)
                && Validation.checkUsernameFormat(username)
                && Validation.checkPasswordFormat(password)
                && Validation.checkConfirmPassword(password, confirmPassword))) {
            if (!Validation.checkNameFormat(fullName)) {
                txtFullName.setError("Wrong format! Full name can only contain lowercase, uppercase (non-ascii characters) letters and space.");
            }

            if (!Validation.checkUsernameFormat(username)) {
                txtUsername.setError("Wrong format! Username must have at least 6 characters (non-ascii characters) and cannot contain space.");
            }

            if (!Validation.checkPasswordFormat(password)) {
                txtPassword.setError("Wrong format! Password must have at least 6 characters, including letters (non-ascii characters) and numbers.");
            }

            if (!Validation.checkConfirmPassword(password, confirmPassword)) {
                txtConfirmPassword.setError("The password and the confirm password did not match!");
            }
        } else if (MainActivity.database.checkExistedAccount(username)){
            AlertDialogBuilder.showAlertDialog("Alert!!!", "This username has already existed in the database!\nPlease consider changing another one.", this);
        } else {
            Account account = new Account();
            account.setFullName(fullName.trim());
            account.setUsername(username.trim());
            account.setPassword(MD5Hashing.getMD5Hash(password));
            MainActivity.database.addNewAccount(account);


            ////////////////////////////////////////
            /**
             * Test sign up in firebase
             */

            String email = username;
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                }
            });


            ////////////////////////////////////////

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("The account has been registered successfully.\nYou will be redirected to login page.");

            // Set Alert Title
            builder.setTitle("Notification!");

            // Set Cancelable false
            // for when the user clicks on the outside
            // the Dialog Box then it will remain show
            builder.setCancelable(false);

            // Set the positive button with yes name
            // OnClickListener method is use of
            // DialogInterface interface.

            builder.setPositiveButton(
                    "OK",
                    (dialog, which) -> {
                        dialog.cancel();
                        this.finish();
                    });

            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();

            // Show the Alert Dialog box
            alertDialog.show();

        }
    }

}