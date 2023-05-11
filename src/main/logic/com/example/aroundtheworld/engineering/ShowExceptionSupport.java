package com.example.aroundtheworld.engineering;

import com.example.aroundtheworld.controller_grafico.MessageBoxGUIController;
import com.example.aroundtheworld.controller_grafico.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ShowExceptionSupport {
    private ShowExceptionSupport() {
        //Costruttore privato perché ho tutti i metodi statici
    }

    public static void showException(String message) throws IOException {

        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("errorBox.fxml"));
        Scene scene = null;

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        MessageBoxGUIController errorBox = fxmlLoader.getController();
        errorBox.setMessage(message);
        dialog.setScene(scene);
        dialog.centerOnScreen();
        dialog.show();
    }
}
