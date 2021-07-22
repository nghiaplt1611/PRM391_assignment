package com.example.gtw_101.dao;

import android.util.Log;

import com.example.gtw_101.controller.menu.SlashScreenActivity;
import com.example.gtw_101.model.Guest;

public class GuestDAO {
    public static Guest guest;

    public static void getGuestData(){

        Log.e("AAAAAAAA", String.valueOf(guest == null));
        try {
            guest = SlashScreenActivity.database.getGuestInfo();
            Log.e("BBBBBBB", String.valueOf(guest == null));
            if (guest == null){
                createNewGuest();
            }
        } catch (Exception e){

        }


    }

    public static void createNewGuest(){
        guest = new Guest();
        guest.setScore(ScoreDAO.score.getInitialScore());
        guest.setQuestion("");
        guest.setNumOfLetterShown(0);
        SlashScreenActivity.database.addGuestInfo(guest);
    }

    public static void updateQuestion(String id){
        guest.setQuestion(id);
        SlashScreenActivity.database.updateGuestInfo(guest);
    }

    public static void updateScore(int score){
        guest.setScore(score);
        SlashScreenActivity.database.updateGuestInfo(guest);
    }

    public static void updateScoreAndShowHints(int score, int numOfShownLetter){
        guest.setScore(score);
        guest.setNumOfLetterShown(numOfShownLetter);
        SlashScreenActivity.database.updateGuestInfo(guest);
    }


}
