package com.example.gtw_101.utilities;

import com.example.gtw_101.dao.AchievementDAO;
import com.example.gtw_101.dao.QuestionDAO;

public class LoadData {
    public static void loadAllData(){
        AchievementDAO.loadAchievementData();
        QuestionDAO.getAllQuestionsInLevel(1);
    }
}
