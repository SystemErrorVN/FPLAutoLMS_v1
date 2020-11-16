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
public class QuizCheck {
    private String name;
    private String url;
    private QuizCheckQuestion[] quizCheckQuestion;

    public QuizCheck() {
    }

    public QuizCheck(String name, String url, QuizCheckQuestion[] quizCheckQuestion) {
        this.name = name;
        this.url = url;
        this.quizCheckQuestion = quizCheckQuestion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public QuizCheckQuestion[] getQuizCheckQuestion() {
        return quizCheckQuestion;
    }

    public void setQuizCheckQuestion(QuizCheckQuestion[] quizCheckQuestion) {
        this.quizCheckQuestion = quizCheckQuestion;
    }

    @Override
    public String toString() {
        return "QuizCheck{" + "name=" + name + ", url=" + url + ", quizCheckQuestion=" + Arrays.toString(quizCheckQuestion) + '}';
    }
    
    
}
