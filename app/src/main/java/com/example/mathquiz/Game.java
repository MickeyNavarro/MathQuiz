package com.example.mathquiz;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Question> questions;

    private int numCorrect, numIncorrect, totalQs, score;
    private Question currQ;

    public Game() {
        numCorrect = 0;
        numIncorrect = 0;
        totalQs = 0;
        score = 0;
        currQ = new Question(10);
        questions = new ArrayList<Question>();
    }

    public void makeNewQuestion() {
        currQ = new Question(totalQs * 2 + 5);
        totalQs++;
        questions.add(currQ);
    }

    public boolean checkAnswer(int submittedAnswer) {
        boolean isCorr;
        if (currQ.getAnswer() == submittedAnswer) {
            numCorrect++;
            isCorr = true;
        } else {
            numIncorrect++;
            isCorr = false;
        }

        score = numCorrect * 10 - numIncorrect * 30;
        return isCorr;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getNumCorrect() {
        return numCorrect;
    }

    public void setNumCorrect(int numCorrect) {
        this.numCorrect = numCorrect;
    }

    public int getNumIncorrect() {
        return numIncorrect;
    }

    public void setNumIncorrect(int numIncorrect) {
        this.numIncorrect = numIncorrect;
    }

    public int getTotalQs() {
        return totalQs;
    }

    public void setTotalQs(int totalQs) {
        this.totalQs = totalQs;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Question getCurrQ() {
        return currQ;
    }

    public void setCurrQ(Question currQ) {
        this.currQ = currQ;
    }
}
