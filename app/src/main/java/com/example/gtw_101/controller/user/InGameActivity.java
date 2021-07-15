package com.example.gtw_101.controller.user;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.gtw_101.R;
import com.example.gtw_101.controller.menu.MainActivity;
import com.example.gtw_101.dao.GuestDAO;
import com.example.gtw_101.dao.QuestionDAO;
import com.example.gtw_101.dao.ScoreDAO;
import com.example.gtw_101.dao.UserDAO;
import com.example.gtw_101.model.Account;
import com.example.gtw_101.utilities.AlertDialogBuilder;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Create class InGameActivity() to handle event in this activity
 */
public class InGameActivity extends AppCompatActivity {


    Dialog congratDiag;
    //var cho ham tao nut
    LinearLayout layout;
    private int firstMar;
    private int maxButton;
    private int buttonSpace = 15;
    private int buttonWid;
    private int buttonHei;
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
        displayRandomLetter();
        if (MainActivity.user == null){
            for (int i = 1; i <= GuestDAO.guest.getNumOfLetterShown(); i++){
                handleGetHintExecute();
            }
        }
        else {
            for (int i = 1; i <= UserDAO.account.getNumOfLetterShown(); i++){
                handleGetHintExecute();
            }
        }

    }


    public void loadData(){
        ImageView imageView = (ImageView) findViewById(R.id.imageView5);
        Picasso.with(this).load(QuestionDAO.question.getImageURL()).fit().into(imageView);
        TextView lblLevel = findViewById(R.id.lb_level);
        lblLevel.setText("Level " + QuestionDAO.question.getLevel());
        TextView lblPoint = findViewById(R.id.lb_point);
        int score;
        if (MainActivity.user == null){
            score = GuestDAO.guest.getScore();
        }
        else {
            score = UserDAO.account.getScore();
        }
        lblPoint.setText(String.valueOf(score));
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
                        int passedLevelScore = ScoreDAO.getRewardScore(QuestionDAO.question.getLevel());
                        if (MainActivity.user == null){

                            int score = GuestDAO.guest.getScore() + passedLevelScore;
                            GuestDAO.updateScoreAndShowHints(score, 0);
                        }
                        else {
                            String id = UserDAO.account.getId();
                            int score = UserDAO.account.getScore() + passedLevelScore;
                            UserDAO.updateScoreAndShowHints(id, score, 0);
                        }

                    }
                    else {
                        int minusScore = ScoreDAO.getWrongAnswerScore(QuestionDAO.question.getLevel());
                        AlertDialogBuilder.showAlertDialog("Notification!", "You gave out a wrong answer. The score will be minus " + minusScore + " points!", this).show();
                        if (MainActivity.user == null){
                            int score = GuestDAO.guest.getScore() - minusScore;
                            if (score < 0){
                                score = 0;
                            }
                            TextView lblPoint = findViewById(R.id.lb_point);
                            lblPoint.setText(String.valueOf(score));
                            GuestDAO.updateScore(score);

                        }
                        else {
                            int score = UserDAO.account.getScore() - minusScore;
                            if (score < 0){
                                score = 0;
                            }
                            TextView lblPoint = findViewById(R.id.lb_point);
                            lblPoint.setText(String.valueOf(score));
                            UserDAO.updateScore(UserDAO.account.getId(), score);
                        }
                        finalChoice = false;

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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        int hintScore = ScoreDAO.getHintScore(QuestionDAO.question.getLevel());
        builder.setMessage("It will cost " + hintScore + " when using SHOW ONE LETTER hint!");
        builder.setTitle("Alert!!!");
        builder.setCancelable(false);
        builder.setPositiveButton("USE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                if (MainActivity.user == null){
                    int score = GuestDAO.guest.getScore() - hintScore;
                    if (score < 0){
                        AlertDialogBuilder.showAlertDialog("Alert!!!", "You don't have enough money to use this hint!", InGameActivity.this).show();
                    }
                    else {
                        int numOfShownLetter = GuestDAO.guest.getNumOfLetterShown();
                        GuestDAO.guest.setScore(score);
                        GuestDAO.guest.setNumOfLetterShown(++numOfShownLetter);
                        GuestDAO.updateScoreAndShowHints(score, numOfShownLetter);

                        TextView lblPoint = findViewById(R.id.lb_point);
                        lblPoint.setText(String.valueOf(GuestDAO.guest.getScore()));

                    }
                }
                else {
                    int score = UserDAO.account.getScore() - hintScore;
                    if (score < 0){
                        AlertDialogBuilder.showAlertDialog("Alert!!!", "You don't have enough money to use this hint!", InGameActivity.this).show();
                    }
                    else {
                        int numOfShownLetter = UserDAO.account.getNumOfLetterShown();
                        UserDAO.account.setScore(score);
                        UserDAO.account.setNumOfLetterShown(++numOfShownLetter);
                        UserDAO.updateScoreAndShowHints(UserDAO.account.getId(), score, numOfShownLetter);

                        TextView lblPoint = findViewById(R.id.lb_point);
                        lblPoint.setText(String.valueOf(UserDAO.account.getScore()));
                    }
                }
                handleGetHintExecute();
            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }

    public void handleGetHintExecute(){
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
        if (MainActivity.user == null){
            GuestDAO.guest.setQuestion("");
        }
        else {
            UserDAO.account.setQuestionID("");
        }
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
        loadQuestion();
    }

    public void loadQuestion(){
        reset(findViewById(android.R.id.content).getRootView());
        loadData();
        splitString();
        createButton();
        onLetterChosenClick();
        layoutChosen();
        displayRandomLetter();
        finalChoice = false;
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