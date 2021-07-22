package com.example.gtw_101.utilities;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.gtw_101.R;

import org.w3c.dom.Text;

public class CustomPopupCongrats {

    public static Dialog showDialog(Activity activity, String result, int score){
        Dialog congratDiag = new Dialog(activity);
        congratDiag.setContentView(R.layout.popup_congratulation);
        congratDiag.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        congratDiag.setCancelable(false);

        TextView txtResult = congratDiag.findViewById(R.id.txt_congrat_result);
        TextView txtScore = congratDiag.findViewById(R.id.txt_congrat_reward_point);
        txtResult.setText(result);
        txtScore.setText(String.valueOf(score));
        return congratDiag;
    }

    public static Dialog showFinishedGameDialog(Activity activity, int score){
        Dialog congratDiag = new Dialog(activity);
        congratDiag.setContentView(R.layout.popup_congratulation);
        congratDiag.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        congratDiag.setCancelable(false);

        TextView txtResult = congratDiag.findViewById(R.id.txt_congrat_result);
        TextView txtScore = congratDiag.findViewById(R.id.txt_congrat_reward_point);
        TextView txtCongrat = congratDiag.findViewById(R.id.txt_congrat_your_reward);

        Button btn = congratDiag.findViewById(R.id.btn_congrat_next);
        btn.setEnabled(false);
        txtResult.setText("YOU HAVE PASSED ALL THE QUESTION! PLEASE WAIT FOR THE NEXT UPDATE");
        txtResult.setTextSize(13);
        txtScore.setText(String.valueOf(score));
        txtCongrat.setText("YOUR HIGHEST SCORE");
        return congratDiag;
    }
}
