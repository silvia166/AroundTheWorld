package com.example.aroundtheworld.graphiccontroller;

import com.example.aroundtheworld.exception.NotFoundException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class StudentGUIController {

    public void toContactFamily() throws IOException {

        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("contactFamilyForm.fxml"));
        Parent root1 = fxmlLoader.load();
        dialog.setScene(new Scene(root1,918,560));
        dialog.centerOnScreen();
        dialog.show();
    }

    public void toBookingResidence() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("residenceForm.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setScene(new Scene(root1));
        dialog.centerOnScreen();
        dialog.show();
    }


    public void toTravelStudent() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("travelStudent.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toProfileStudent() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("profileStudent.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toRequestStudent() throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("requestStudent.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        StudentRequestGUIController studentRequestGUIController = fxmlLoader.getController();
        studentRequestGUIController.setStudentRequests();
        stage.setScene(scene);
        stage.show();
    }

    public void toHomepageStudent() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("homepageStudent.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logout() throws IOException {
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }

    public void quoteForm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("quoteForm.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setScene(new Scene(root1));
        QuoteGUIController quoteGUIController = fxmlLoader.getController();
        quoteGUIController.setQuoteForm();
        dialog.centerOnScreen();
        dialog.show();
    }

}
