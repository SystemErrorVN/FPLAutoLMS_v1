/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;

/**
 *
 * @author Administrator
 */
public class QuizCheckQuestion {
    private int index;
    private String question;
    private String[] answer;
    private int isIndexCorrect;

    public QuizCheckQuestion() {
    }

    public QuizCheckQuestion(int index, String question, String[] answer, int isIndexCorrect) {
        this.index = index;
        this.question = question;
        this.answer = answer;
        this.isIndexCorrect = isIndexCorrect;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswer() {
        return answer;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }

    public int getIsIndexCorrect() {
        return isIndexCorrect;
    }

    public void setIsIndexCorrect(int isIndexCorrect) {
        this.isIndexCorrect = isIndexCorrect;
    }

    @Override
    public String toString() {
        return "QuizCheckQuestion{" + "index=" + index + ", question=" + question + ", answer=" + Arrays.toString(answer) + ", isIndexCorrect=" + isIndexCorrect + '}';
    }
    
    
    
}
