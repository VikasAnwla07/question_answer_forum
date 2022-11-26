package com.example.question_answer_forum;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class DBUtils {
    public static String us;

    public static  void addQuestionToDatabase(ActionEvent event,String username,String question){


        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet result = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/question_answer","root","");

            psInsert = connection.prepareStatement("INSERT INTO question (QUESTION,USERNAME) VALUES (?,?)");
            psInsert.setString(1,question);
            psInsert.setString(2,username);

            psInsert.executeUpdate();

            changeScene(event,username,"loggedin.fxml","welcome");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void goToAddQuestion(ActionEvent event,String username,String fxmlFile,String title){
        Parent root = null;

//        if(username!=null){
        try{
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            add_questions add_question = loader.getController();
            add_question.setUsername(username);

        }catch (IOException e){
            e.printStackTrace();
        }
//        }
//        else{
//            try {
//                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
//
//            }
//            catch (IOException e){
//                e.printStackTrace();
//            }
//        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }


    public static void goToProfile(ActionEvent event,String username,String fxmlFile,String title){

        Parent root = null;

        if(username!=null){
        try{
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
            Profile pr = loader.getController();
            pr.setUsername(username);


        }catch (IOException e){
            e.printStackTrace();
        }
        }
        else{
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));

            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }


    public static void changeScene(ActionEvent event,String username,String fxmlFile,String title) {

        Parent root = null;

        if(username!=null){
            try{
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                home loggedIn = loader.getController();
                loggedIn.setLabel_intro(username);

            }catch (IOException e){
                e.printStackTrace();
            }
        }
        else{
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));

            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root,600,400));
        stage.show();


    }

    public static boolean valMail(String email){
        //Regular Expression
        String regex = "^(.+)@(.+)$";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.find();
    }

    public static void signupuser(ActionEvent event,String username,String password,String email,String confirmPassword){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet result = null;





        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/question_answer","root","");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM user_info WHERE USERNAME = ?");
            psCheckUserExist.setString(1,username);
            result = psCheckUserExist.executeQuery();

            if(result.isBeforeFirst()){
                System.out.println("user already exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you cannot use this username");
                alert.show();
            }
            else if(!confirmPassword.equals(password)){
                System.out.println("password did not match");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("reenter password");
                alert.show();
            }
            else if(!valMail(email)){
                System.out.println("enter a valid email");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("enter a valid email");
                alert.show();
            }
            else{
                psInsert = connection.prepareStatement("INSERT INTO user_info (username,email,password) VALUES (?,?,?)");
                psInsert.setString(1,username);
                psInsert.setString(2,email);
                psInsert.setString(3,password);
                psInsert.executeUpdate();

                us=username;
                us=username;

                changeScene(event,username,"loggedin.fxml","welcome");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (result != null){
                try {
                    result.close();

                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(psCheckUserExist != null){
                try{
                    psCheckUserExist.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(psInsert != null){
                try{
                    psInsert.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try{
                    connection.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }


    }

    public  static  void loginuser(ActionEvent event,String username,String password){
        Connection connection =  null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/question_answer","root","");
            preparedStatement = connection.prepareStatement("SELECT password FROM user_info WHERE username = ?");
            preparedStatement.setString(1,username);
            result = preparedStatement.executeQuery();

            if(!result.isBeforeFirst()){
                System.out.println("user not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("invalid credential");
                alert.show();
            }
            else{
                while(result.next()){
                    String retreivedPassword = result.getString("password");
                    if(retreivedPassword.equals(password)){
                        us=username;
                        changeScene(event,username,"loggedin.fxml","welcome");
                    }
                    else{
                        System.out.println("password didn't match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("invalid credential");
                        alert.show();
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (result != null){
                try {
                    result.close();

                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }


            if(connection != null){
                try{
                    connection.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

    }

}

