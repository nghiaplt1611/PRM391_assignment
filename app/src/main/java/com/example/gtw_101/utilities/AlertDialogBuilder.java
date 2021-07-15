package com.example.gtw_101.utilities;

import android.app.AlertDialog;
import android.content.Context;

public class AlertDialogBuilder {

    /**
     * Create method showAlertDialog() to create and show alert dialog
     *
     * @param title store alert title
     * @param message store message
     * @param context store application context
     */
    public static AlertDialog showAlertDialog(String title, String message, Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setTitle(title);
        AlertDialog dialog = builder.create();
        return dialog;
    }

}
