package com.example.question_answer_forum;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Person {
    private final StringProperty question = new SimpleStringProperty();
    private final StringProperty answer = new SimpleStringProperty();

    public String getquestion(){return question.get();}
    public void setquestion(String value){question.set(value);}
    public StringProperty questionProperty(){return question;}

    public String getanswer(){return answer.get();}
    public void setanswer(String value){answer.set(value);}
    public StringProperty answerProperty(){return answer;}



}
