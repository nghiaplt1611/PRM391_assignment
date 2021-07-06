package com.example.gtw_101.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gtw_101.R;
import com.example.gtw_101.utilities.AlertDialogBuilder;

import java.util.HashMap;

/**
 * Create class InGameActivity() to handle event in this activity
 */
public class InGameActivity extends AppCompatActivity {

    // Create variable map (HashMap) to store the ID of random letter button and result letter button
    HashMap<Integer, Integer> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        getSupportActionBar().hide();
        onLetterChosenClick();
        returnChosenLetter();
    }

    public void returnToMainScreen(View view){
        this.finish();
    }

    public void onLetterChosenClick(){
        ConstraintLayout groupRandomLetters = findViewById(R.id.group_answer);
        for (int i = 0; i < groupRandomLetters.getChildCount(); i++){
            Button b = (Button) groupRandomLetters.getChildAt(i);
            b.setOnClickListener(v -> {
                if (displayChoosingLetterToResult(b)){
                    b.setVisibility(View.INVISIBLE);
                }
                // Code here executes on main thread after user presses button
            });
        }
    }

    public boolean displayChoosingLetterToResult(Button randomLetterButton){
        ConstraintLayout groupResultLetters = findViewById(R.id.group_result);
        for (int i = 0; i < groupResultLetters.getChildCount(); i++){
            Button resultButton = (Button) groupResultLetters.getChildAt(i);
            if (resultButton.getText().equals("")){
                resultButton.setText(randomLetterButton.getText());
                map.put(randomLetterButton.getId(), resultButton.getId());
                return true;
            }
        }
        return false;
    }

    public void returnChosenLetter(){
        ConstraintLayout groupResultLetters = findViewById(R.id.group_result);
        for (int i = 0; i < groupResultLetters.getChildCount(); i++){
            Button b = (Button) groupResultLetters.getChildAt(i);
            b.setOnClickListener(v -> {
                if (!b.getText().equals("")){
                    for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
                        if (entry.getValue() == b.getId()){
                            b.setText("");
                            Button randomLetterButton = findViewById(entry.getKey());
                            randomLetterButton.setVisibility(View.VISIBLE);
                            map.remove(entry.getKey());
                            break;
                        }
                    }
                }
                // Code here executes on main thread after user presses button
            });
        }
    }

    public void getHint(View view){
        final String result = "BAOCAO";
        ConstraintLayout groupResultLetters = findViewById(R.id.group_result);
        for (int i = 0; i < groupResultLetters.getChildCount(); i++){
            Button b = (Button) groupResultLetters.getChildAt(i);
            if (b.isEnabled()){
                b.setText(String.valueOf(result.charAt(i)));
                b.setBackground(null);
                b.setEnabled(false);
                hiddenHintLetter(b.getText().toString());
                break;
            }
        }
    }

    public void hiddenHintLetter(String s){
        ConstraintLayout groupRandomLetters = findViewById(R.id.group_answer);
        for (int i = 0; i < groupRandomLetters.getChildCount(); i++){
            Button b = (Button) groupRandomLetters.getChildAt(i);
            if (b.getText().equals(s) && b.isEnabled()){
                b.setVisibility(View.INVISIBLE);
                b.setBackground(null);
                b.setEnabled(false);
                break;
            }
        }
    }

    public void reset(View view){
        ConstraintLayout groupResultLetters = findViewById(R.id.group_result);
        for (int i = 0; i < groupResultLetters.getChildCount(); i++){
            Button b = (Button) groupResultLetters.getChildAt(i);
            if (b.isEnabled()){
                b.setText("");
            }
        }
        ConstraintLayout groupRandomLetters = findViewById(R.id.group_answer);
        for (int i = 0; i < groupRandomLetters.getChildCount(); i++){
            Button b = (Button) groupRandomLetters.getChildAt(i);
            if (b.isEnabled()){
                b.setVisibility(View.VISIBLE);
            }

        }
    }
}