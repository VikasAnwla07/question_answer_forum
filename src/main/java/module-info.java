module com.example.question_answer_forum {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.question_answer_forum to javafx.fxml;
    exports com.example.question_answer_forum;
}