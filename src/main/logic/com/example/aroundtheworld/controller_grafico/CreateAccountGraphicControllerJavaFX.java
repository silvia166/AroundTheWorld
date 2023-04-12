package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.controller_applicativo.CreateAccountController;
import com.example.aroundtheworld.exception.EmailFormatException;
import com.example.aroundtheworld.exception.FormEmptyException;
import com.example.aroundtheworld.exception.PhoneFormatException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CreateAccountGraphicControllerJavaFX extends LogoutGraphicControllerJavaFX {

    @FXML
    private DatePicker birthField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField nationalityField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField surnameField;

    @FXML
    void addStudent(ActionEvent event) throws IOException{

        try{
            if(nameField.getText() == null)
                throw new FormEmptyException("Name");
            if(surnameField.getText() == null)
                throw new FormEmptyException("Surname");
            if(nationalityField.getText() == null)
                throw new FormEmptyException("Nationality");
            if(phoneField.getText() == null)
                throw new FormEmptyException("Phone Number");
            if(passwordField.getText() == null)
                throw new FormEmptyException("Password");
            if(emailField.getText() == null)
                throw new FormEmptyException("Email");
            if(birthField.getValue() == null)
                throw new FormEmptyException("Date of birth");

            StudentBean studentBean = new StudentBean(nameField.getText(), surnameField.getText(), nationalityField.getText(), birthField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), emailField.getText(), phoneField.getText(), passwordField.getText());

            CreateAccountController createAccountController = new CreateAccountController();
            createAccountController.createAccount(studentBean);

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("accessPage.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch(EmailFormatException | FormEmptyException | PhoneFormatException| ParseException e ){
            e.printStackTrace();
        }
    }
}
