package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.engineering.Session;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;

public class StudentProfileGUIController {
    @FXML
    private Label birth;

    @FXML
    private Label email;

    @FXML
    private Label name;

    @FXML
    private Label nationality;

    @FXML
    private Label phone;

    @FXML
    private Label surname;

    Scene scene;

    public void initialize() {
        StudentBean studentBean = Session.getCurrentSession().getStudentBean();
        name.setText(studentBean.getName());
        surname.setText(studentBean.getSurname());
        birth.setText(studentBean.getBirth());

        nationality.setText(studentBean.getNationality());
        phone.setText(studentBean.getPhoneNumber());
        email.setText(studentBean.getEmail());
    }

    public void toHomepageStudent() throws IOException {
        StudentGUIController studentGraphicControllerJavaFX = new StudentGUIController();
        studentGraphicControllerJavaFX.toHomepageStudent();
    }

    public void toRequestStudent() throws IOException {
        StudentGUIController studentGraphicControllerJavaFX = new StudentGUIController();
        studentGraphicControllerJavaFX.toRequestStudent();
    }

    public void toTravelStudent() throws IOException {
        StudentGUIController studentGraphicControllerJavaFX = new StudentGUIController();
        studentGraphicControllerJavaFX.toTravelStudent();
    }

    public void logout() throws IOException {
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }
}
