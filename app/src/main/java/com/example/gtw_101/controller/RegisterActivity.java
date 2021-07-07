package com.example.gtw_101.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.gtw_101.R;
import com.example.gtw_101.model.Account;
import com.example.gtw_101.utilities.AlertDialogBuilder;
import com.example.gtw_101.utilities.MD5Hashing;
import com.example.gtw_101.utilities.Validation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
    }

    public void onButtonRegisterClick(View view) {

        EditText txtEmail = findViewById(R.id.txt_email_register);
        EditText txtFullName = findViewById(R.id.txt_fullname_register);
        EditText txtYearOfBirth = findViewById(R.id.txt_yearofbirth_register);
        EditText txtPassword = findViewById(R.id.txt_password_register);
        EditText txtConfirmPassword = findViewById(R.id.txt_confirm_password_register);

        String email = txtEmail.getText().toString();
        String fullName = txtFullName.getText().toString();
        String yearOfBirth = txtYearOfBirth.getText().toString();
        String password = txtPassword.getText().toString();
        String confirmPassword = txtConfirmPassword.getText().toString();

        if (Validation.checkNullData(new String[]{email, fullName, yearOfBirth, password, confirmPassword})){

            if (email.isEmpty()){
                txtEmail.setError("Email cannot be empty! Please input!");
            }

            if (fullName.isEmpty()){
                txtFullName.setError("Full name cannot be empty! Please input!");
            }

            if (yearOfBirth.isEmpty()){
                txtYearOfBirth.setError("Year of birth cannot be empty! Please input!");
            }

            if (password.isEmpty()){
                txtPassword.setError("Password cannot be empty! Please input!");
            }

            if (confirmPassword.isEmpty()){
                txtConfirmPassword.setError("Confirm password cannot be empty! Please input!");
            }
        }
        else if (!Validation.checkRegisterFormat(email, fullName, yearOfBirth, password, confirmPassword)){

            if (!Validation.checkEmailFormat(email)){
                txtEmail.setError("Email must be in correct format. (Ex: thanh@gmail.com)");
            }

            if (!Validation.checkFullNameFormat(fullName)){
                txtFullName.setError("Full name must be in correct format. (Ex: Tri Thanh)");
            }

            if (!Validation.checkYearOfBirth(yearOfBirth)){
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                txtYearOfBirth.setError("Year of birth must be a positive number between 1900 and " + currentYear);
            }

            if (!Validation.checkStrongPassword(password)){
                txtPassword.setError("Your password is not strong enough. It must contain at least 1 uppercase, 1 lowercase letter, 1 digit, and 1 special character.");
            }

            if (!Validation.checkConfirmPassword(password, confirmPassword)){
                txtConfirmPassword.setError("The confirm password did not match.");
            }

        }
        else {
            auth = FirebaseAuth.getInstance();
            db = FirebaseFirestore.getInstance();
            auth.createUserWithEmailAndPassword(email, MD5Hashing.getMD5Hash(password)).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.e("AAA", "***");
                    if (task.isSuccessful()) {
                        Log.e("AAA", "2");
                        Account account = new Account();
                        account.setEmail(email);
                        account.setFullName(fullName);
                        account.setYearOfBirth(Integer.parseInt(yearOfBirth));

                        db.collection("users").document().set(account).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.e("AAA", "3");
                                FirebaseUser user = auth.getCurrentUser();

                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registered successfully! You will be returned to the login page.");
                                builder.setTitle("Notification!");
                                builder.setCancelable(false);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                                // Create the Alert dialog
                                AlertDialog alertDialog = builder.create();

                                // Show the Alert Dialog box
                                alertDialog.show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                AlertDialogBuilder.showAlertDialog("Notification!", "Error occurred while registering!", RegisterActivity.this);
                            }
                        });

                    }
                }
            });
        }
    }

}