package com.example.superalarm.Database.questiondatabase;

public class QuestionModel {
    int id;
    String question;
    String answer;

    public QuestionModel(int id, String question, String answer) {
        this.answer = answer;
        this.id = id;
        this.question = question;
    }

    public QuestionModel(String question, String answer){
        this.question = question;
        this.answer  = answer;
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


