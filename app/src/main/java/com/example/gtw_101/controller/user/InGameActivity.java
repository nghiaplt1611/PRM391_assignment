package com.example.gtw_101.controller.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.gtw_101.R;
import com.example.gtw_101.dao.QuestionDAO;
import com.example.gtw_101.model.Question;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Create class InGameActivity() to handle event in this activity
 */
public class InGameActivity extends AppCompatActivity {


    Dialog congratDiag;
    //var cho ham tao nut
    LinearLayout layout;
    private int firstMar;
    private int lastMar;
    private int buttonMar=30;
    private int maxButton;
    private int buttonSpace=15;
    private int buttonWid=150;
    private int buttonHei=180;
    private int number=1;
    private String word = "";
    private List<String> list1 = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    Button newBtn;

    //cai chua chay nay giai thich sau :v
    private int chuachay=998;


    // Create variable map (HashMap) to store the ID of random letter button and result letter button
    HashMap<Integer, Integer> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        getSupportActionBar().hide();

        congratDiag = new Dialog(this);
//        onLetterChosenClick();
//        returnChosenLetter();

        //tao nut
        layout = findViewById(R.id.twoline_layout1);
        splitString();
        createButton();
        getImage();
    }

    public void getImage(){
        ImageView imageView = (ImageView)findViewById(R.id.imageView5);
        Picasso.with(this).load(QuestionDAO.question.getImageURL()).fit().into(imageView);
    }

    public void returnToMainScreen(View view){
        this.finish();
    }

    public void onLetterChosenClick(){
        ConstraintLayout groupRandomLetters = findViewById(R.id.group_key);
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
        ConstraintLayout groupResultLetters = findViewById(R.id.group_answer);
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
        ConstraintLayout groupResultLetters = findViewById(R.id.group_answer);
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
        ConstraintLayout groupResultLetters = findViewById(R.id.group_answer);
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
        ConstraintLayout groupRandomLetters = findViewById(R.id.group_key);
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
        ConstraintLayout groupResultLetters = findViewById(R.id.group_answer);
        for (int i = 0; i < groupResultLetters.getChildCount(); i++){
            Button b = (Button) groupResultLetters.getChildAt(i);
            if (b.isEnabled()){
                b.setText("");
            }
        }
        ConstraintLayout groupRandomLetters = findViewById(R.id.group_key);
        for (int i = 0; i < groupRandomLetters.getChildCount(); i++){
            Button b = (Button) groupRandomLetters.getChildAt(i);
            if (b.isEnabled()){
                b.setVisibility(View.VISIBLE);
            }

        }
    }




    //tao nut moi
    public void addButton(String character, LinearLayout.LayoutParams layoutParams){
        newBtn = new Button(this);
        newBtn.setText(character);
        layout.addView(newBtn,layoutParams);
        Log.e("newButton","shit just got birth");
    }

    //tinh toan khoan cach neu ko chia dong
    public void calForMiddle(){
        firstMar = chuachay-((maxButton*buttonWid)+(maxButton-1)*buttonSpace+buttonSpace);
        firstMar= firstMar/2;
        lastMar = firstMar;
    }

    //tinh toan khoan cach neu chia dong
    public void calForTwoLine(int nums){

        firstMar = chuachay-((nums*buttonWid)+(nums-1)*buttonSpace+buttonSpace);
        firstMar= firstMar/2;
    }


    //xoa het child cua linear layout
    public void removeButton(){
        layout = findViewById(R.id.oneline_lay1);
        layout.removeAllViews();
        layout = findViewById(R.id.oneline_lay2);
        layout.removeAllViews();
        layout = findViewById(R.id.twoline_layout1);
        layout.removeAllViews();
        layout = findViewById(R.id.twoline_layout2);
        layout.removeAllViews();
    }

    //tach chu
    public void splitString(){
        word = "bao cao";
        maxButton = word.length();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        for (int i = 0; i<word.length();i++){
            if (String.valueOf(word.charAt(i)).endsWith(" ")){
                for (int j =i+1;j<word.length();j++){
                    list2.add(String.valueOf(word.charAt(j)));
                }
                break;
            }else {
                list1.add(String.valueOf(word.charAt(i)));
            }
        }
    }


    //code tao nut
    public void createButton(){
        if (maxButton<=6){


            removeButton();
            calForMiddle();

            layout = findViewById(R.id.oneline_lay1);
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(150, 180);
            layoutParams1.setMargins(0, 0, 15, 0);
            layout.setPadding(firstMar,0,0,0);
            for (int i=0;i<list1.size();i++){
                Log.e("for","so lan" + i);
                addButton(list1.get(i),layoutParams1);
            }

            layout = findViewById(R.id.oneline_lay2);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(150, 180);
            layoutParams2.setMargins(15, 0, 0, 0);
            layout.setPadding(0,0,firstMar,0);
            for (int i=0;i<list2.size();i++){
                Log.e("for","so lan" + i);
                addButton(list2.get(i),layoutParams2);
            }
        }else {
            removeButton();
            layout = findViewById(R.id.twoline_layout1);
            calForTwoLine(list1.size());
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(150, 180);
            layoutParams1.setMargins(0, 0, 15, 0);
            layout.setPadding(firstMar,15,0,0);
            for (int i=0;i<list1.size();i++){
                Log.e("for","so lan" + i);
                addButton(list1.get(i),layoutParams1);
            }

            layout = findViewById(R.id.twoline_layout2);
            System.out.println("sau khi doi layout");
            calForTwoLine(list2.size());
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(150, 180);
            layoutParams2.setMargins(0, 0, 15, 0);
            layout.setPadding(firstMar,15,0,0);
            for (int i=0;i<list2.size();i++){
                Log.e("for","so lan" + i);
                addButton(list2.get(i),layoutParams2);
            }
        }
    }


    public void showPopup(View v){
        congratDiag.setContentView(R.layout.popup_congratulation);
        congratDiag.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        congratDiag.show();
    }

}