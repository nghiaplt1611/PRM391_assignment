package com.example.gtw_101.model;

import java.io.Serializable;

public class Account implements Serializable {
    private String id;
    private String email;
    private String fullName;
    private int yearOfBirth;
    private Question question;
    private int numOfLetterShown;
    private String achievements;
    private int score;
    private int avatar;

    public Account() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
