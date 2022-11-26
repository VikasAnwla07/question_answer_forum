package com.example.question_answer_forum;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class signupController implements Initializable {

    @FXML
    private Button button_signup;

    @FXML
    private Button button_login;
    @FXML
    private TextField temp_username;

    @FXML
    private PasswordField temp_password_1;

    @FXML
    private PasswordField temp_password;

    @FXML
    private TextField temp_email;

    public void initialize(URL location, ResourceBundle resources){
        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(!temp_password.getText().trim().isEmpty() && !temp_email.getText().trim().isEmpty() && !temp_password.getText().trim().isEmpty() && !temp_password_1.getText().isEmpty()){

                    DBUtils.signupuser(event,temp_username.getText(),temp_password.getText(),temp_email.getText(),temp_password_1.getText());
                }
                else{
                    System.out.println("fill all table");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("fill form");
                    alert.show();
                }

            }
        });

        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                DBUtils.changeScene(event,null,"hello-view.fxml","login");

            }
        });
    }
}
