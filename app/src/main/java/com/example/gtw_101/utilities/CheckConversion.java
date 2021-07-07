package com.example.gtw_101.utilities;

import android.util.Log;

public class CheckConversion {

    public static boolean convertToInteger(String s){
        try {
            int result = Integer.parseInt(s);
        } catch (Exception e){
            return false;
        }
        return true;
    }

}
