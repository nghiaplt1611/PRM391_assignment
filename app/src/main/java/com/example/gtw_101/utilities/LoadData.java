package com.example.gtw_101.utilities;

import com.example.gtw_101.dao.AchievementDAO;
import com.example.gtw_101.dao.QuestionDAO;
import com.example.gtw_101.dao.ScoreDAO;
import com.example.gtw_101.dao.UserDAO;
import com.google.firebase.auth.FirebaseUser;

public class LoadData {
    public static void loadAllData(){
        AchievementDAO.loadAchievementData();
        ScoreDAO.getScore();
    }

    public static void loadUserData(FirebaseUser user){
        UserDAO.getUserInfo(user.getEmail());
    }

    public static void loadQuestion(){
        QuestionDAO.loadQuestion();
    }


}
