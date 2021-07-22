package com.example.gtw_101.controller.user;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gtw_101.R;
import com.example.gtw_101.dao.AchievementDAO;
import com.example.gtw_101.dao.UserDAO;
import com.example.gtw_101.model.Achievement;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AchievementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);
        getSupportActionBar().hide();
        updateContent();

    }

    public void updateContent(){

        TextView txtTitle1 = (TextView) findViewById(R.id.txt_achievement_title_1);
        TextView txtTitle2 = (TextView) findViewById(R.id.txt_achievement_title_2);
        TextView txtTitle3 = (TextView) findViewById(R.id.txt_achievement_title_3);
        TextView txtTitle4 = (TextView) findViewById(R.id.txt_achievement_title_4);
        TextView txtTitle5 = (TextView) findViewById(R.id.txt_achievement_title_5);

        TextView txtContent1 = (TextView) findViewById(R.id.txt_achievement_content_1);
        TextView txtContent2 = (TextView) findViewById(R.id.txt_achievement_content_2);
        TextView txtContent3 = (TextView) findViewById(R.id.txt_achievement_content_3);
        TextView txtContent4 = (TextView) findViewById(R.id.txt_achievement_content_4);
        TextView txtContent5 = (TextView) findViewById(R.id.txt_achievement_content_5);

        TextView[] listTitle = {txtTitle1, txtTitle2, txtTitle3, txtTitle4, txtTitle5};
        TextView[] listContent = {txtContent1, txtContent2, txtContent3, txtContent4, txtContent5};

        for (int i = 0; i < AchievementDAO.listAchievement.size(); i++){
            Achievement achievement = AchievementDAO.listAchievement.get(i);
            listTitle[i].setText(achievement.getTitle());
            listContent[i].setText(achievement.getContent());
            if (UserDAO.account.getAchievements().charAt(i) == 'F')
                setDisplayMedal(i);

        }
    }

    public void setDisplayMedal(int i){
        switch(i){
            case 0:
                ImageView medal = findViewById(R.id.img_medal_icon_1);
                medal.setVisibility(View.INVISIBLE);
                break;
            case 1:
                ImageView medal2 = findViewById(R.id.img_medal_icon_2);
                medal2.setVisibility(View.INVISIBLE);
                break;
            case 2:
                ImageView medal3 = findViewById(R.id.img_medal_icon_3);
                medal3.setVisibility(View.INVISIBLE);
                break;
            case 3:
                ImageView medal4 = findViewById(R.id.img_medal_icon_4);
                medal4.setVisibility(View.INVISIBLE);
                break;
            default:
                ImageView medal5 = findViewById(R.id.img_medal_icon_5);
                medal5.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void returnToUserMainMenuIntent(View view){
        this.finish();
    }

}