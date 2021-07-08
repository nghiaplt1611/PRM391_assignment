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

}
