package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.ResourceBundle;

public class StudentProfileGraphicControllerJavaFX extends LogoutGraphicControllerJavaFX  {
    @FXML
    private Label birth;

    @FXML
    private TextField email;

    @FXML
    private Label name;

    @FXML
    private Label nationality;

    @FXML
    private TextField phone;

    @FXML
    private Label surname;

    Stage stage;
    Scene scene;

    public void initialize() {

        StudentBean studentBean = Session.getCurrentSession().getStudentBean();
        name.setText(studentBean.getName());
        surname.setText(studentBean.getSurname());

        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        String date = df.format(studentBean.getBirth());
        birth.setText(date);

        nationality.setText(studentBean.getNationality());
        phone.setPromptText(studentBean.getPhoneNumber());
        email.setPromptText(studentBean.getEmail());

    }

    public void toHomepageStudent(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("homepageStudent.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toRequestStudent(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("requestStudent.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toTravelStudent(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("travelStudent.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
