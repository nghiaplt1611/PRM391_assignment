package com.example.gtw_101.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.gtw_101.controller.menu.MainActivity;

public class CheckNetworkConnection {
    public static boolean isConnected() {
        boolean connected;
        try {
            ConnectivityManager cm = (ConnectivityManager)MainActivity.context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
        } catch (Exception e) {
            return false;
        }
        return connected;
    }
}
