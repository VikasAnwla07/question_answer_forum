package com.example.question_answer_forum;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import  java.net.URL;
import  java.util.ResourceBundle;


public class home implements Initializable {
    private String userName;
    @FXML
    private Button temp_button_logout;

    @FXML
    private Button profile_button;

    @FXML
    private Label label_intro;

    @FXML
    private Button add_question;

    public void initialize (URL location, ResourceBundle resources){

        temp_button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                DBUtils.changeScene(event,null,"hello-view.fxml","login");

            }
        });

        add_question.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.goToAddQuestion(event,userName,"add-questions.fxml","add-question");
            }
        });

        profile_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.goToProfile(event,userName,"Profile.fxml","Profile");
            }
        });

    }



    public void setLabel_intro (String username){
        userName=username;
        label_intro.setText("hello!" + userName + "welcome to our website");
    }

}
