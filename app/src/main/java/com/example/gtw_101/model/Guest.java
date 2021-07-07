package com.example.gtw_101.model;

import java.io.Serializable;

public class Guest implements Serializable {
    private int level;
    private int score;
    private String answerOfQuestion;
    private int numOfLetterShown;
    private String achievements;

    public Guest() {
    }

    public Guest(int level, int score, String answerOfQuestion, int numOfLetterShown, String achievements) {
        this.level = level;
        this.score = score;
        this.answerOfQuestion = answerOfQuestion;
        this.numOfLetterShown = numOfLetterShown;
        this.achievements = achievements;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAnswerOfQuestion() {
        return answerOfQuestion;
    }

    public void setAnswerOfQuestion(String answerOfQuestion) {
        this.answerOfQuestion = answerOfQuestion;
    }

    public int getNumOfLetterShown() {
        return numOfLetterShown;
    }

    public void setNumOfLetterShown(int numOfLetterShown) {
        this.numOfLetterShown = numOfLetterShown;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }
}
