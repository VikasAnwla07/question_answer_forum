package com.example.question_answer_forum;


import com.example.question_answer_forum.DBHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.ls.LSOutput;

import  java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import  java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Profile implements Initializable {

    @FXML
    private Button temp_button_logout;


    @FXML
    private Label usernamee;

    @FXML
    private TableView<Person>table;

    @FXML
    private TableColumn<Person,String>question;
    @FXML
    private TableColumn<Person,String>answer;

    private Connection conn;
    private ObservableList<Person> list;
    private DBHandler dbHandler;
    public String us ;



    public void initialize (URL location, ResourceBundle resources){
        dbHandler = new DBHandler();
        dbHandler.getConnection();

        populateTableView();



        temp_button_logout.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                DBUtils.changeScene(event,null,"hello-view.fxml","Home");

            }
        });

    }
    public void setUsername (String username){
        us=username;
        System.out.println(us);
        System.out.println(username);
        usernamee.setText(username );
    }

    public void populateTableView()  {

        try {


            list = FXCollections.observableArrayList();
            String query = "SELECT QUESTION FROM question WHERE USERNAME='"+DBUtils.us+"'";
            conn = dbHandler.getConnection();
            ResultSet set = conn.createStatement().executeQuery(query);

            while (set.next()) {
                Person person = new Person();
                person.setquestion(set.getString("Question"));
                person.setanswer("Reply");
                list.add(person);
            }

            table.setItems(list);
            question.setCellValueFactory(f -> f.getValue().questionProperty());
            answer.setCellValueFactory(f -> f.getValue().answerProperty());


        }catch (SQLException ex){
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE,null,ex);
        }

    }




}