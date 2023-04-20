package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.engineering.Session;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;


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

    Scene scene;

    public void initialize() {
        StudentBean studentBean = Session.getCurrentSession().getStudentBean();
        name.setText(studentBean.getName());
        surname.setText(studentBean.getSurname());
        birth.setText(studentBean.getBirth());

        nationality.setText(studentBean.getNationality());
        phone.setPromptText(studentBean.getPhoneNumber());
        email.setPromptText(studentBean.getEmail());
    }

    public void toHomepageStudent() throws IOException {
        StudentGraphicControllerJavaFX studentGraphicControllerJavaFX = new StudentGraphicControllerJavaFX();
        studentGraphicControllerJavaFX.toHomepageStudent();
    }

    public void toRequestStudent() throws IOException {
        StudentGraphicControllerJavaFX studentGraphicControllerJavaFX = new StudentGraphicControllerJavaFX();
        studentGraphicControllerJavaFX.toRequestStudent();
    }

    public void toTravelStudent() throws IOException {
        StudentGraphicControllerJavaFX studentGraphicControllerJavaFX = new StudentGraphicControllerJavaFX();
        studentGraphicControllerJavaFX.toTravelStudent();
    }
}
