package com.example.gtw_101.utilities;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

import com.example.gtw_101.R;

public class LoadingPopup {

    public static Dialog loadingDialog(Activity activity){
        Dialog congratDiag = new Dialog(activity);
        congratDiag.setContentView(R.layout.popup_loading);
        congratDiag.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        congratDiag.setCancelable(false);
        return congratDiag;
    }
}
