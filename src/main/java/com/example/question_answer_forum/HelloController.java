package com.example.question_answer_forum;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TextField temp_username;
    @FXML
    private  TextField temp_password;

    @FXML
    private Button button_login;
    @FXML
    private Button button_signup;
    @FXML
    private Label label_message;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.loginuser(event,temp_username.getText(),temp_password.getText());
            }
        });

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,null,"signup.fxml","signup");
            }
        });

    }


}