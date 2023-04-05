package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.CityBean;
import com.example.aroundtheworld.bean.LoginBean;
import com.example.aroundtheworld.controller_applicativo.CityController;
import com.example.aroundtheworld.controller_applicativo.LoginController;
import com.example.aroundtheworld.exception.EmailFormatException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.exception.UserNotFoundException;
import com.example.aroundtheworld.model.City;
import com.example.aroundtheworld.model.Residence;
import com.example.aroundtheworld.model.School;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginGraphicControllerJavaFX extends LogoutGraphicControllerJavaFX{

    private Stage stage;
    private Scene scene;
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;

    public void loginAction(ActionEvent event) throws IOException, EmailFormatException, UserNotFoundException, NotFoundException {

        try {
            LoginBean loginBean = new LoginBean(usernameField.getText(),passwordField.getText());
            LoginController loginController = new LoginController();
            loginController.checkUser(loginBean);
            Parent root;

            switch(loginBean.getRole()){
                case 1 ->  {
                    loginController.studentLogin(loginBean);
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepageStudent.fxml")));
                    break;
                }
                case 2 -> {
                    loginController.familyLogin(loginBean);
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepageFamily.fxml")));
                    break;
                }
                case 3 -> {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepageAgency.fxml")));
                    break;
                }
                default -> throw new UserNotFoundException();
            }

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (EmailFormatException | UserNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void toCreateAccount(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("createAccount.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toHomepageStudent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepageStudent.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toCity(ActionEvent event) throws NotFoundException, IOException {
        String cityName = ((Button)event.getSource()).getText();
        CityBean cityBean = new CityBean(cityName);
        CityController cityController = new CityController();
        cityController.setCity(cityBean);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("city.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));

        CityGraphicControllerJavaFX cityGraphicControllerJavaFX = fxmlLoader.getController();
        cityGraphicControllerJavaFX.setData(cityBean);

        stage.show();
    }

}
