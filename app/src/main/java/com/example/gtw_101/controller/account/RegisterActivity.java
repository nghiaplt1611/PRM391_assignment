package com.example.gtw_101.controller.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.gtw_101.R;
import com.example.gtw_101.controller.menu.MainActivity;
import com.example.gtw_101.dao.UserDAO;
import com.example.gtw_101.model.Account;
import com.example.gtw_101.utilities.AlertDialogBuilder;
import com.example.gtw_101.utilities.Validation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

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
            MainActivity.mAuth = FirebaseAuth.getInstance();
            MainActivity.db = FirebaseFirestore.getInstance();
            MainActivity.mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Account account = UserDAO.registeredNewAccount(email, fullName, yearOfBirth);
                        DocumentReference documentReference = MainActivity.db.collection("users").document();
                        account.setId(documentReference.getId());
                        documentReference.set(account).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                MainActivity.mAuth.signOut();
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registered successfully! You will be returned to the login page.");
                                builder.setTitle("Notification!");
                                builder.setCancelable(false);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
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

    public void hideEmailError(View view){
        EditText txtEmail = findViewById(R.id.txt_email_register);
        txtEmail.setError(null);
    }

    public void hideFullNameError(View view){
        EditText txtFullName = findViewById(R.id.txt_fullname_register);
        txtFullName.setError(null);
    }

    public void hideYearOfBirthError(View view){
        EditText txtYearOfBirth = findViewById(R.id.txt_yearofbirth_register);
        txtYearOfBirth.setError(null);
    }

    public void hidePasswordError(View view){
        EditText txtPassword = findViewById(R.id.txt_password_register);
        txtPassword.setError(null);
    }

    public void hideConfirmPasswordError(View view){
        EditText txtConfirmPassword = findViewById(R.id.txt_confirm_password_register);
        txtConfirmPassword.setError(null);
    }

    public void returnToLoginActivity(View view){
        this.finish();
    }




}