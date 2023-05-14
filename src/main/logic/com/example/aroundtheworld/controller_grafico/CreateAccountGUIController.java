package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.controller_applicativo.CreateAccountController;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.EmailFormatException;
import com.example.aroundtheworld.exception.FormEmptyException;
import com.example.aroundtheworld.exception.MessageException;
import com.example.aroundtheworld.exception.PhoneFormatException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CreateAccountGUIController {

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
    void addStudent() throws IOException{

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


            StudentBean studentBean = new StudentBean(nameField.getText(), surnameField.getText(), nationalityField.getText(), birthField.getValue().toString(), emailField.getText(), phoneField.getText(), passwordField.getText());

            CreateAccountController createAccountController = new CreateAccountController();
            createAccountController.createAccount(studentBean);

            Stage stage = Main.getStage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("accessPage.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch(EmailFormatException | FormEmptyException | PhoneFormatException e ){
            e.printStackTrace();
        } catch (MessageException e) {
            ShowExceptionSupport.showException(e.getMessage());
        }
    }

    public void logout() throws IOException {
        LogoutGUIControllerJavaFX logoutGUIControllerJavaFX = new LogoutGUIControllerJavaFX();
        logoutGUIControllerJavaFX.logout();
    }
}
