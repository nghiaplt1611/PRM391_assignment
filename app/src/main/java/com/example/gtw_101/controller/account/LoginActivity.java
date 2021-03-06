package com.example.gtw_101.controller.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.gtw_101.R;
import com.example.gtw_101.controller.menu.MainActivity;
import com.example.gtw_101.controller.menu.UserMainActivity;
import com.example.gtw_101.dao.UserDAO;
import com.example.gtw_101.utilities.AlertDialogBuilder;
import com.example.gtw_101.utilities.LoadData;
import com.example.gtw_101.utilities.Validation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


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

    public void forgotPasswordIntent(View view){
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        this.startActivity(intent);
    }

    public void backToMainMenuIntent(View view){
        this.finish();
    }

    public void onButtonLoginClick(View view) {
        EditText txtEmail = (EditText) findViewById(R.id.txt_email_login);
        EditText txtPassword = (EditText) findViewById(R.id.txt_password_login);

        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        if (Validation.checkNullData(new String[]{email, password})){
            if (email.isEmpty()){
                txtEmail.setError("Email cannot be empty! Please input!");
            } if (password.isEmpty()) {
               txtPassword.setError("Password cannot be empty! Please input!");
            }

        } else {

            MainActivity.mAuth = FirebaseAuth.getInstance();
            MainActivity.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        MainActivity.user = MainActivity.mAuth.getCurrentUser();

                        if (!UserDAO.checkEmailVerified()){
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("Please verify your email account before playing game!");
                            builder.setTitle("Notification!");
                            builder.setCancelable(false);
                            builder.setPositiveButton("Verify now", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    AlertDialog dialog1 = AlertDialogBuilder.showAlertDialog("Notification!", "An email message has been sent to your email account for verification. Please check it!", LoginActivity.this);
                                    dialog1.show();
                                    MainActivity.user.sendEmailVerification();
                                }
                            }).setNegativeButton("Later", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    MainActivity.mAuth.signOut();
                                }
                            });
                            // Create the Alert dialog
                            AlertDialog alertDialog = builder.create();

                            // Show the Alert Dialog box
                            alertDialog.show();
                        }
                        else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("Login successfully");
                            builder.setTitle("Notification!");
                            builder.setCancelable(false);
                            builder.setPositiveButton("Main menu", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(LoginActivity.this, UserMainActivity.class);
                                    LoadData.loadAllData();
                                    LoadData.loadUserData(MainActivity.mAuth.getCurrentUser());
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            // Create the Alert dialog
                            AlertDialog alertDialog = builder.create();

                            // Show the Alert Dialog box
                            alertDialog.show();
                        }
                    }
                    else {
                        AlertDialog dialog = AlertDialogBuilder.showAlertDialog("Alert!", "Email or password is invalid!!!", LoginActivity.this);
                        dialog.show();
                    }
                }
            });


        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        EditText txtEmail = (EditText) findViewById(R.id.txt_email_login);
        EditText txtPassword = (EditText) findViewById(R.id.txt_password_login);
        txtPassword.setText("");
        txtEmail.setText("");
        txtEmail.setError(null);
        txtPassword.setError(null);

    }

    public void hideEmailError(View view){
        EditText txtEmail = (EditText) findViewById(R.id.txt_email_login);
        txtEmail.setError(null);
    }

    public void hidePasswordError(View view){
        EditText txtPassword = (EditText) findViewById(R.id.txt_password_login);
        txtPassword.setError(null);
    }

}