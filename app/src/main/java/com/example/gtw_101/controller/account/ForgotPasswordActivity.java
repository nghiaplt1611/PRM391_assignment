package com.example.gtw_101.controller.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.gtw_101.R;
import com.example.gtw_101.controller.menu.MainActivity;
import com.example.gtw_101.dao.UserDAO;
import com.example.gtw_101.utilities.AlertDialogBuilder;
import com.example.gtw_101.utilities.Validation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

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
        if (email.isEmpty()){
            txtEmail.setError("Email cannot be empty! Please input!");
        }
        else if (!Validation.checkEmailFormat(email)){
            AlertDialog dialog = AlertDialogBuilder.showAlertDialog("Alert!", "This email is not in correct format. Please check!", this);
            dialog.show();
        }
        else {
            MainActivity.mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPasswordActivity.this);
                        builder.setMessage("An email has been sent to " + email + " for verification. Please check it!");
                        builder.setTitle("Notification!");
                        builder.setCancelable(false);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                finish();
                            }
                        });
                        // Create the Alert dialog
                        AlertDialog alertDialog = builder.create();

                        // Show the Alert Dialog box
                        alertDialog.show();
                    }
                    else {
                        AlertDialog dialog = AlertDialogBuilder.showAlertDialog("Alert!", "This email account doesn't exist in the database. Please check!", ForgotPasswordActivity.this);
                        dialog.show();
                    }
                }
            });
        }
    }

    public void hideEmailError(View view){
        EditText txtEmail = findViewById(R.id.txt_email_forgot_password);
        txtEmail.setError(null);
    }

    public void returnToLoginIntent(View view){
        this.finish();
    }
}