package com.example.gtw_101.model;

import java.io.Serializable;

public class Guest implements Serializable {
    private int score;
    private int numOfLetterShown;
    private String question;

    public Guest() {
    }

    public Guest(int score, int numOfLetterShown, String question) {
        this.score = score;
        this.numOfLetterShown = numOfLetterShown;
        this.question = question;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumOfLetterShown() {
        return numOfLetterShown;
    }

    public void setNumOfLetterShown(int numOfLetterShown) {
        this.numOfLetterShown = numOfLetterShown;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
