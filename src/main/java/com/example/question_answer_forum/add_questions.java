package com.example.question_answer_forum;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class add_questions implements Initializable {

    private String  username ;

    @FXML
    private TextArea temp_question;

    @FXML
    private Button temp_submit_question;

    public void initialize (URL location, ResourceBundle resources){

        temp_submit_question.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.addQuestionToDatabase(event,username,temp_question.getText());
            }
        });

    }

    public void setUsername (String username){
        this.username=username;
    }



}
