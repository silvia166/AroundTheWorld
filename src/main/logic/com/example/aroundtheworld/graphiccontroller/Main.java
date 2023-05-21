package com.example.aroundtheworld.graphiccontroller;

import com.example.aroundtheworld.connection.ConnectionDB;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.graphiccontroller.cli.LoginCLIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {

    private static Stage stage;
    private static Stage secondaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("accessPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setStage(stage);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        ConnectionDB.getConnection();
        Scanner reader = new Scanner(System.in);
        int selection;
        Printer.printMessage("Which type of view do you want to use?\n\n1) Graphic interface\n2) Command Line interface\n\nInsert the number: ");

        while (true) {
            selection = reader.nextInt();
            if (selection == 1) {
                launch();
                break;
            } else if (selection == 2) {
                LoginCLIController loginCLIController= new LoginCLIController();
                loginCLIController.start();

            } else {
                Printer.printError("Number not valid, please insert 1 or 2");
            }
        }
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }

    public static Stage getSecondaryStage() {
        return secondaryStage;
    }

    public static void setSecondaryStage(Stage secondaryStage) {
        Main.secondaryStage = secondaryStage;
    }
}