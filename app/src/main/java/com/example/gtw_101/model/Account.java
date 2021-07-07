package com.example.gtw_101.model;

import java.io.Serializable;

public class Account implements Serializable {
    private String email;
    private String fullName;
    private int yearOfBirth;
//    private int level;
//    private int score;
//    private String answerOfQuestion;
//    private int numOfLetterShown;
//    private String achievements;

    public Account() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

//    public int getLevel() {
//        return level;
//    }
//
//    public void setLevel(int level) {
//        this.level = level;
//    }
//
//    public int getScore() {
//        return score;
//    }
//
//    public void setScore(int score) {
//        this.score = score;
//    }
//
//    public String getAnswerOfQuestion() {
//        return answerOfQuestion;
//    }
//
//    public void setAnswerOfQuestion(String answerOfQuestion) {
//        this.answerOfQuestion = answerOfQuestion;
//    }
//
//    public int getNumOfLetterShown() {
//        return numOfLetterShown;
//    }
//
//    public void setNumOfLetterShown(int numOfLetterShown) {
//        this.numOfLetterShown = numOfLetterShown;
//    }
//
//    public String getAchievements() {
//        return achievements;
//    }
//
//    public void setAchievements(String achievements) {
//        this.achievements = achievements;
//    }
}
