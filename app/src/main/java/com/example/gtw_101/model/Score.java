package com.example.gtw_101.model;

public class Score {
    private int wrongAnswerScore;
    private int hintScore;
    private int changeQuestionScore;
    private int passedLevelScore;
    private int initialScore;

    public Score() {
    }

    public int getWrongAnswerScore() {
        return wrongAnswerScore;
    }

    public void setWrongAnswerScore(int wrongAnswerScore) {
        this.wrongAnswerScore = wrongAnswerScore;
    }

    public int getHintScore() {
        return hintScore;
    }

    public void setHintScore(int hintScore) {
        this.hintScore = hintScore;
    }

    public int getChangeQuestionScore() {
        return changeQuestionScore;
    }

    public void setChangeQuestionScore(int changeQuestionScore) {
        this.changeQuestionScore = changeQuestionScore;
    }

    public int getPassedLevelScore() {
        return passedLevelScore;
    }

    public void setPassedLevelScore(int passedLevelScore) {
        this.passedLevelScore = passedLevelScore;
    }

    public int getInitialScore() {
        return initialScore;
    }

    public void setInitialScore(int initialScore) {
        this.initialScore = initialScore;
    }
}
