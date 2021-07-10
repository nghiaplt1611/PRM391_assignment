package com.example.gtw_101.controller.user;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.gtw_101.R;
import com.example.gtw_101.dao.QuestionDAO;
import com.example.gtw_101.dao.UserDAO;
import com.example.gtw_101.model.Question;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Create class InGameActivity() to handle event in this activity
 */
public class InGameActivity extends AppCompatActivity {


    Dialog congratDiag;
    //var cho ham tao nut
    LinearLayout layout;
    private int firstMar;
    private int lastMar;
    private int buttonMar = 30;
    private int maxButton;
    private int buttonSpace = 15;
    private int buttonWid;
    private int buttonHei;
    private int number = 1;
    private String word = "";
    private List<String> list1 = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private int sceenWid;
    private boolean finalChoice = false;
    Button newBtn;

    //cai chua chay nay giai thich sau :v
    private int chuachay = 998;


    // Create variable map (HashMap) to store the ID of random letter button and result letter button
    HashMap<Button, Button> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        getSupportActionBar().hide();
        QuestionDAO.getCurrentQuestion();

        congratDiag = new Dialog(this);

        getSceenPec();
        calForButton();

        //tao nut
        layout = findViewById(R.id.twoline_layout1);
        splitString();
        createButton();
        onLetterChosenClick();
        layoutChosen();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData(){
        ImageView imageView = (ImageView) findViewById(R.id.imageView5);
        Picasso.with(this).load(QuestionDAO.question.getImageURL()).fit().into(imageView);
        TextView lblLevel = findViewById(R.id.lb_level);
        lblLevel.setText("Level " + QuestionDAO.question.getLevel());
        TextView lblPoint = findViewById(R.id.lb_point);
        lblPoint.setText(String.valueOf(UserDAO.account.getScore()));
        displayRandomLetter();
    }

    public void returnToMainScreen(View view) {
        this.finish();
    }

    public boolean compareAnswer(LinearLayout linearLayout, List<String> list){
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            Button b = (Button) linearLayout.getChildAt(i);
            if (!b.getText().toString().equals(list.get(i))){
                return false;
            }
        }
        return true;
    }

    public boolean checkResult(){
        if (maxButton <= 6) {
            LinearLayout linearLayout1 = findViewById(R.id.oneline_lay1);
            LinearLayout linearLayout2 = findViewById(R.id.oneline_lay2);
            return compareAnswer(linearLayout1, list1) && compareAnswer(linearLayout2, list2);

        } else {
            LinearLayout linearLayout3 = findViewById(R.id.twoline_layout1);
            LinearLayout linearLayout4 = findViewById(R.id.twoline_layout2);
            return compareAnswer(linearLayout3, list1) && compareAnswer(linearLayout4, list2);
        }
    }

    public void onLetterChosenClick() {
        ConstraintLayout groupRandomLetters = findViewById(R.id.group_key);
        for (int i = 0; i < groupRandomLetters.getChildCount(); i++) {
            Button b = (Button) groupRandomLetters.getChildAt(i);
            b.setOnClickListener(v -> {
                if (displayChoosingLetterToResult(b)) {
                    b.setVisibility(View.INVISIBLE);
                }
                if (finalChoice) {
                    if (checkResult()){
                        showPopup(findViewById(android.R.id.content).getRootView());
                    }
                }
                // Code here executes on main thread after user presses button
            });
        }
    }

    public boolean displayChoosingLetterToResult(Button randomLetterButton) {

        if (maxButton <= 6) {
            LinearLayout linearLayout1 = findViewById(R.id.oneline_lay1);
            for (int i = 0; i < linearLayout1.getChildCount(); i++) {
                Button resultButton = (Button) linearLayout1.getChildAt(i);
                if (resultButton.getText().equals("")) {
                    resultButton.setText(randomLetterButton.getText());
                    map.put(randomLetterButton, resultButton);
                    return true;
                }
            }

            LinearLayout linearLayout2 = findViewById(R.id.oneline_lay2);
            for (int i = 0; i < linearLayout2.getChildCount(); i++) {
                Button resultButton = (Button) linearLayout2.getChildAt(i);
                if (resultButton.getText().equals("")) {
                    resultButton.setText(randomLetterButton.getText());
                    map.put(randomLetterButton, resultButton);

                    if (i == linearLayout2.getChildCount() - 1)
                        finalChoice = true;
                    return true;
                }
            }
            return false;

        } else {
            LinearLayout linearLayout3 = findViewById(R.id.twoline_layout1);
            for (int i = 0; i < linearLayout3.getChildCount(); i++) {
                Button resultButton = (Button) linearLayout3.getChildAt(i);
                if (resultButton.getText().equals("")) {
                    resultButton.setText(randomLetterButton.getText());
                    map.put(randomLetterButton, resultButton);
                    return true;
                }
            }

            LinearLayout linearLayout4 = findViewById(R.id.twoline_layout2);
            for (int i = 0; i < linearLayout4.getChildCount(); i++) {
                Button resultButton = (Button) linearLayout4.getChildAt(i);
                if (resultButton.getText().equals("")) {
                    resultButton.setText(randomLetterButton.getText());
                    map.put(randomLetterButton, resultButton);

                    if (i == linearLayout4.getChildCount() - 1)
                        finalChoice = true;
                    return true;
                }
            }
            return false;
        }
    }

    public void layoutChosen(){
        if (maxButton <= 6){
            LinearLayout linearLayout1 = findViewById(R.id.oneline_lay1);
            LinearLayout linearLayout2 = findViewById(R.id.oneline_lay2);
            returnChosenLetter(linearLayout1);
            returnChosenLetter(linearLayout2);
        }
        else {
            LinearLayout linearLayout3 = findViewById(R.id.twoline_layout1);
            LinearLayout linearLayout4 = findViewById(R.id.twoline_layout2);
            returnChosenLetter(linearLayout3);
            returnChosenLetter(linearLayout4);
        }
    }

    public void returnChosenLetter(LinearLayout linearLayout) {
        LinearLayout groupResultLetters = linearLayout;
        for (int i = 0; i < groupResultLetters.getChildCount(); i++) {
            Button b = (Button) groupResultLetters.getChildAt(i);
            b.setOnClickListener(v -> {
                if (!b.getText().equals("")) {
                    for (HashMap.Entry<Button, Button> entry : map.entrySet()) {
                        if (entry.getValue() == b) {
                            b.setText("");
                            Button randomLetterButton = entry.getKey();
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

    public void getHint(View view) {
        if (maxButton <= 6){
            LinearLayout linearLayout1 = findViewById(R.id.oneline_lay1);
            LinearLayout linearLayout2 = findViewById(R.id.oneline_lay2);
            if (!showHint(linearLayout1, list1))
                showHint(linearLayout2, list2);
        }
        else {
            LinearLayout linearLayout3 = findViewById(R.id.twoline_layout1);
            LinearLayout linearLayout4 = findViewById(R.id.twoline_layout2);
            if (!showHint(linearLayout3, list1))
                showHint(linearLayout4, list2);
        }
    }

    public boolean showHint(LinearLayout linearLayout, List<String> list){
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            Button b = (Button) linearLayout.getChildAt(i);
            if (b.isEnabled()) {
                b.setText(list.get(i));
                b.setBackground(null);
                b.setEnabled(false);
                hiddenHintLetter(b.getText().toString());

                for (HashMap.Entry<Button, Button> entry : map.entrySet()) {
                    if (entry.getValue() == b) {
                        Button randomLetterButton = entry.getKey();
                        randomLetterButton.setVisibility(View.VISIBLE);
                        map.remove(entry.getKey());
                        break;
                    }
                }

                return true;
            }
        }
        return false;
    }

    public void hiddenHintLetter(String s) {
        ConstraintLayout groupRandomLetters = findViewById(R.id.group_key);
        for (int i = 0; i < groupRandomLetters.getChildCount(); i++) {
            Button b = (Button) groupRandomLetters.getChildAt(i);
            if (b.getText().equals(s) && b.isEnabled()) {
                b.setVisibility(View.INVISIBLE);
                b.setBackground(null);
                b.setEnabled(false);
                break;
            }
        }
    }

    public void resetLayoutAnswer(LinearLayout linearLayout){
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            Button b = (Button) linearLayout.getChildAt(i);
            if (b.isEnabled()) {
                b.setText("");
            }
        }
    }

    public void resetAllLayout(){
        if (maxButton <= 6){
            resetLayoutAnswer(findViewById(R.id.oneline_lay1));
            resetLayoutAnswer(findViewById(R.id.oneline_lay2));
        }
        else {
            resetLayoutAnswer(findViewById(R.id.twoline_layout1));
            resetLayoutAnswer(findViewById(R.id.twoline_layout2));
        }
    }

    public void reset(View view) {

        resetAllLayout();

        ConstraintLayout groupRandomLetters = findViewById(R.id.group_key);
        for (int i = 0; i < groupRandomLetters.getChildCount(); i++) {
            Button b = (Button) groupRandomLetters.getChildAt(i);
            if (b.isEnabled()) {
                b.setVisibility(View.VISIBLE);
            }

        }
    }


    //tao nut moi
    public void addButton(String character, LinearLayout.LayoutParams layoutParams) {
        newBtn = new Button(this);
        newBtn.setBackground(this.getResources().getDrawable(R.drawable.achievemen_item_background));
        layout.addView(newBtn, layoutParams);
    }

    //tinh toan khoan cach neu ko chia dong
    public void calForMiddle() {
        firstMar = sceenWid - ((maxButton * buttonWid) + (maxButton - 1) * buttonSpace + buttonSpace);
        firstMar = firstMar / 2;
    }

    //tinh toan khoan cach neu chia dong
    public void calForTwoLine(int nums) {
        firstMar = sceenWid - ((nums * buttonWid) + (nums - 1) * buttonSpace + buttonSpace);
        firstMar = firstMar / 2;
    }


    //xoa het child cua linear layout
    public void removeButton() {
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
    public void splitString() {
        word = QuestionDAO.question.getAnswer();
        maxButton = word.length();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (String.valueOf(word.charAt(i)).endsWith(" ")) {
                for (int j = i + 1; j < word.length(); j++) {
                    list2.add(String.valueOf(word.charAt(j)));
                }
                break;
            } else {
                list1.add(String.valueOf(word.charAt(i)));
            }
        }
    }


    //code tao nut
    public void createButton() {
        if (maxButton <= 6) {


            removeButton();
            calForMiddle();

            layout = findViewById(R.id.oneline_lay1);
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(buttonWid, buttonHei);
            layoutParams1.setMargins(0, 0, 15, 0);
            layout.setPadding(firstMar, 0, 0, 0);
            for (int i = 0; i < list1.size(); i++) {
                Log.e("for", "so lan" + i);
                addButton(list1.get(i), layoutParams1);
            }

            layout = findViewById(R.id.oneline_lay2);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(buttonWid, buttonHei);
            layoutParams2.setMargins(15, 0, 0, 0);
            layout.setPadding(0, 0, firstMar, 0);
            for (int i = 0; i < list2.size(); i++) {
                Log.e("for", "so lan" + i);
                addButton(list2.get(i), layoutParams2);
            }
        } else {
            removeButton();
            layout = findViewById(R.id.twoline_layout1);
            calForTwoLine(list1.size());
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(buttonWid, buttonHei);
            layoutParams1.setMargins(0, 0, 15, 0);
            layout.setPadding(firstMar, 15, 0, 0);
            for (int i = 0; i < list1.size(); i++) {
                Log.e("for", "so lan" + i);
                addButton(list1.get(i), layoutParams1);
            }

            layout = findViewById(R.id.twoline_layout2);
            System.out.println("sau khi doi layout");
            calForTwoLine(list2.size());
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(buttonWid, buttonHei);
            layoutParams2.setMargins(0, 0, 15, 0);
            layout.setPadding(firstMar, 15, 0, 0);
            for (int i = 0; i < list2.size(); i++) {
                Log.e("for", "so lan" + i);
                addButton(list2.get(i), layoutParams2);
            }
        }
    }

    public void getSceenPec() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        sceenWid = displayMetrics.widthPixels;
    }

    public void calForButton() {
        buttonWid = sceenWid / 7;
        buttonHei = sceenWid / 6;
    }


    public void showPopup(View v) {
        QuestionDAO.getAllQuestionsInLevel(QuestionDAO.question.getLevel()+1);
        UserDAO.account.setQuestionID("");
        congratDiag.setContentView(R.layout.popup_congratulation);
        congratDiag.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        congratDiag.show();
        congratDiag.setCancelable(false);
    }

    public void returnToUserMainMenuIntent(View view){
        congratDiag.cancel();
        QuestionDAO.getCurrentQuestion();
        this.finish();
    }

    public void nextQuestion(View view){
        congratDiag.cancel();
        QuestionDAO.getCurrentQuestion();
    }

    public void displayRandomLetter(){
        List<String> newList = new ArrayList<>();
        newList.addAll(list1);
        newList.addAll(list2);
        for (int i = newList.size(); i < 14; i++){
            char c = (char) (Math.random()*(90-65+1)+65);
            newList.add(String.valueOf(c));
        }

        ConstraintLayout groupRandomLetters = findViewById(R.id.group_key);
        for (int i = 0; i < groupRandomLetters.getChildCount(); i++) {
            Button b = (Button) groupRandomLetters.getChildAt(i);
            Random rand = new Random();
            int randNumber = rand.nextInt(newList.size());
            b.setText(newList.get(randNumber));
            newList.remove(randNumber);
        }
    }
}