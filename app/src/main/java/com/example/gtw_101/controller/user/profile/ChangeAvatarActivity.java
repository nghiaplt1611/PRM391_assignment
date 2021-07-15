package com.example.gtw_101.controller.user.profile;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gtw_101.R;
import com.example.gtw_101.dao.UserDAO;
import com.example.gtw_101.utilities.GetAvatarResource;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class ChangeAvatarActivity extends AppCompatActivity {

    int previousChoice;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_avatar);
        getSupportActionBar().hide();
        previousChoice = UserDAO.account.getAvatar();
        loadChosenAvatar(previousChoice);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void loadChosenAvatar(int avatar){
        Button btn = (Button) findViewById(GetAvatarResource.getButtonAvatarID(avatar));
        btn.setForeground(getResources().getDrawable(R.drawable.choose_avatar_foreground));
    }

    public void returnToUserProfileIntent(View view){
        UserDAO.account.setAvatar(previousChoice);
        this.finish();
        UserDAO.updateAvatar(UserDAO.account.getId(), previousChoice);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void handleAvatarSelection(int choice){
        if (choice != previousChoice){
            Button btn = (Button) findViewById(GetAvatarResource.getButtonAvatarID(choice));
            btn.setForeground(getResources().getDrawable(R.drawable.choose_avatar_foreground));

            btn = (Button) findViewById(GetAvatarResource.getButtonAvatarID(previousChoice));
            btn.setForeground(null);
            previousChoice = choice;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onFirstAvatarClick(View view){
        handleAvatarSelection(1);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onSecondAvatarClick(View view){
        handleAvatarSelection(2);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onThirdAvatarClick(View view){
        handleAvatarSelection(3);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onFourthAvatarClick(View view){
        handleAvatarSelection(4);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onFifthAvatarClick(View view){
        handleAvatarSelection(5);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onSixthAvatarClick(View view){
        handleAvatarSelection(6);
    }
}