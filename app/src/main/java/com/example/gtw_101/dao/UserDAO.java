package com.example.gtw_101.dao;

import com.example.gtw_101.controller.MainActivity;
import com.example.gtw_101.model.Account;

public class UserDAO {

    public static boolean checkEmailVerified(){
        return MainActivity.user.isEmailVerified();
    }

    public static Account getUserInfo(String email){
        return null;
    }

    public static Account registeredNewAccount(String email, String fullName, String yearOfBirth){
        Account account = new Account();
        account.setEmail(email);
        account.setFullName(fullName);
        account.setYearOfBirth(Integer.parseInt(yearOfBirth));
        account.setQuestion(null);
        account.setNumOfLetterShown(0);
        account.setAchievements("FFFFF");
        account.setScore(GameManagement.score);
        return account;
    }
}
