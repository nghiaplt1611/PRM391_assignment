package com.example.gtw_101.controller.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.gtw_101.R;
import com.example.gtw_101.controller.user.AchievementActivity;
import com.example.gtw_101.controller.user.InGameActivity;
import com.example.gtw_101.controller.user.LeaderActivity;
import com.example.gtw_101.controller.user.PlayerProfileActivity;
import com.example.gtw_101.dao.LeaderboardDAO;
import com.example.gtw_101.dao.QuestionDAO;
import com.example.gtw_101.dao.UserDAO;
import com.example.gtw_101.utilities.CustomPopupCongrats;
import com.example.gtw_101.utilities.LoadData;

public class UserMainActivity extends AppCompatActivity {

    Dialog congratDiag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        getSupportActionBar().hide();
        LoadData.loadQuestion();
        LoadData.loadLeaderboard();
        LoadData.loadAllData();
    }



    /**
     * Create method profileIntent to change to player profile intent
     *
     * @param view storing view
     */
    public void onButtonProfileClick(View view){
        Intent intent = new Intent(this, PlayerProfileActivity.class);
        this.startActivity(intent);
    }

    public void onButtonAchievementClick(View view){
        Intent intent = new Intent(this, AchievementActivity.class);
        this.startActivity(intent);
    }

    public void onButtonPlayGameClick(View view){
        if (UserDAO.account.isFinishedGame()){
            congratDiag = CustomPopupCongrats.showFinishedGameDialog(this, UserDAO.account.getScore());
            congratDiag.show();
        }
        else {
            Intent intent = new Intent(this, InGameActivity.class);
            this.startActivity(intent);
        }

    }

    public void howToPlayIntent(View view){
        Intent intent = new Intent(this, HowToPlayActivity.class);
        this.startActivity(intent);
    }

    public void onButtonLeaderBoardClick(View view){
        Intent intent = new Intent(this, LeaderActivity.class);
        this.startActivity(intent);
    }

    public void returnToUserMainMenuIntent(View view){
        congratDiag.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoadData.loadLeaderboard();
    }


}