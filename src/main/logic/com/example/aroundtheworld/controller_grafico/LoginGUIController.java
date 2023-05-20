package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.CityBean;
import com.example.aroundtheworld.bean.LoginBean;
import com.example.aroundtheworld.controller_applicativo.CityController;
import com.example.aroundtheworld.controller_applicativo.LoginController;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.EmailFormatException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.exception.UserNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginGUIController {
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;

    public void loginAction() throws IOException, NotFoundException {

        try {
            LoginBean loginBean = new LoginBean(usernameField.getText(),passwordField.getText());
            LoginController loginController = new LoginController();
            loginController.checkUser(loginBean);
            Parent root;
            Stage dialog = Main.getStage();

            switch(loginBean.getRole()){
                case 1 ->  {
                    loginController.studentLogin(loginBean);
                    root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("homepageStudent.fxml")));
                }
                case 2 -> {
                    loginController.familyLogin(loginBean);
                    root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("homepageFamily.fxml")));
                }
                case 3 -> root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("homepageAgency.fxml")));
                default -> throw new UserNotFoundException();
            }

            Scene scene = new Scene(root);
            dialog.setScene(scene);
            dialog.show();

        } catch (EmailFormatException | UserNotFoundException  e) {
            ShowExceptionSupport.showException(e.getMessage());
        } catch (IOException e){
            Printer.error(e.getMessage());
        }
    }

    public void toCreateAccount() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("createAccount.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toCity(ActionEvent event) throws NotFoundException, IOException {
        String cityName = ((Button)event.getSource()).getText();
        CityBean cityBean = new CityBean(cityName);
        CityController cityController = new CityController();
        cityController.setCity(cityBean);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("city.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));

        CityGUIController cityGUIController = fxmlLoader.getController();
        cityGUIController.setData(cityBean);

        stage.show();
    }

}
