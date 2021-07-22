package com.example.gtw_101.controller.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gtw_101.R;
import com.example.gtw_101.dao.LeaderboardDAO;
import com.example.gtw_101.dao.UserDAO;
import com.example.gtw_101.model.Account;
import com.example.gtw_101.utilities.GetAvatarResource;

public class LeaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader);
        getSupportActionBar().hide();
        showData();
    }

    public void showData(){
        for (int i = 0; i < LeaderboardDAO.listTop3Ranking.size(); i++){
            Account a = LeaderboardDAO.listTop3Ranking.get(i);
            displayEachData(a, i);
            Log.e("AAAAAAAAAA", "AAAAAAAAA");
        }

        displayEachData(UserDAO.account, 3);

    }

    public void displayEachData(Account a, int position){
        switch(position){
            case 0:
                ImageView img1 = findViewById(R.id.img_top1);
                TextView name1 = findViewById(R.id.txt_top1_name);
                TextView score1 = findViewById(R.id.txt_top1_point);

                img1.setImageResource(GetAvatarResource.getAvatarImageID(a.getAvatar()));
                name1.setText(a.getFullName());
                score1.setText(String.valueOf(a.getScore()));
                break;
            case 1:
                ImageView img2 = findViewById(R.id.img_top2);
                TextView name2 = findViewById(R.id.txt_top2_name);
                TextView score2 = findViewById(R.id.txt_top2_point);

                img2.setImageResource(GetAvatarResource.getAvatarImageID(a.getAvatar()));
                name2.setText(a.getFullName());
                score2.setText(String.valueOf(a.getScore()));
                break;
            case 2:
                ImageView img3 = findViewById(R.id.img_top3);
                TextView name3 = findViewById(R.id.txt_top3_name);
                TextView score3 = findViewById(R.id.txt_top3_point);

                img3.setImageResource(GetAvatarResource.getAvatarImageID(a.getAvatar()));
                name3.setText(a.getFullName());
                score3.setText(String.valueOf(a.getScore()));
                break;
            default:
                ImageView img = findViewById(R.id.img_top_you);
                TextView name = findViewById(R.id.txt_top_you_name);
                TextView score = findViewById(R.id.txt_your_point);

                img.setImageResource(GetAvatarResource.getAvatarImageID(a.getAvatar()));
                name.setText(a.getFullName());
                score.setText(String.valueOf(a.getScore()));
                break;

        }
    }

    public void returnToUserMenuIntent(View view){
        this.finish();
    }
}