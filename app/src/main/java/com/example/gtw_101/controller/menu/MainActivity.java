package com.example.gtw_101.controller.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;


import com.example.gtw_101.R;
import com.example.gtw_101.controller.account.ForgotPasswordActivity;
import com.example.gtw_101.controller.user.InGameActivity;
import com.example.gtw_101.controller.account.LoginActivity;
import com.example.gtw_101.utilities.AlertDialogBuilder;
import com.example.gtw_101.utilities.CheckNetworkConnection;
import com.example.gtw_101.utilities.DatabaseHandler;
import com.example.gtw_101.utilities.LoadData;
import com.example.gtw_101.utilities.LoadingPopup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    public static FirebaseAuth mAuth;
    public static FirebaseFirestore db;
    public static FirebaseUser user;
    public static Context context;
    AlertDialog dialog;
    Dialog loadingDiag;

    private SwitchCompat bSwitch;


    /**
     * Override method onCreate to initialize basic login of the activity
     *
     * @param savedInstanceState storing the previous state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        bSwitch = findViewById(R.id.btn_languague_menu);
        bSwitch.setOnCheckedChangeListener(this);
        context = getApplicationContext();
        checkConnection();

    }

    @Override
    protected void onStart() {
        super.onStart();
        user = mAuth.getCurrentUser();
        if (user != null){
            LoadData.loadUserData(user);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Welcome back! Have fun with Guess The Word!!!");
            builder.setTitle("Automatic logged in!");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    loadingDiag.cancel();
                    finish();
                    userMainMenuIntent(findViewById(android.R.id.content).getRootView());
                }
            });
            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();

            // Show the Alert Dialog box
            alertDialog.show();
        }
        else {
            LoadData.loadGuestData();
        }
    }

    /**
     * Create method loginIntent to change to login intent
     *
     * @param view storing view
     */
    public void loginIntent(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
    }

    public void loadQuestionAlertDialog(View view){
        LoadData.loadQuestion();
        dialog = AlertDialogBuilder.showAlertDialog("Notification!", "Please wait a little bit for loading questions...", this);
//        dialog.show();
        loadingDiag = LoadingPopup.loadingDialog(this);
        loadingDiag.show();
        new Handler().postDelayed(this::playGameIntent,2000);

    }

    public void playGameIntent(){
//        dialog.cancel();
        loadingDiag.cancel();
        Intent intent = new Intent(this, InGameActivity.class);
        this.startActivity(intent);
    }

    public void userMainMenuIntent(View view){
        Intent intent = new Intent(this, UserMainActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    public void howToPlayIntent(View view){
        Intent intent = new Intent(this, HowToPlayActivity.class);
        this.startActivity(intent);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            // Nữa để code xử lí đổi qua Tiếng Việt vào đây
        }
    }

    public void checkConnection(){
        if (!CheckNetworkConnection.isConnected()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please check your Internet connection before playing game!");
            builder.setTitle("Alert!");
            builder.setCancelable(false);
            builder.setPositiveButton("Reload", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(MainActivity.this, SlashScreenActivity.class);
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

}