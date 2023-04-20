package com.example.aroundtheworld.controller_grafico;

import com.example.aroundtheworld.bean.CompatibleFamilyBean;
import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.model.Animal;
import com.example.aroundtheworld.model.FamilyMember;
import com.example.aroundtheworld.model.FamilyPreferences;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


public class FamilyListGraphicControllerJavaFX {

    Stage stage;
    @FXML
    private GridPane familyGrid = new GridPane();
    @FXML
    private List<CompatibleFamilyBean> families = new ArrayList<>();

    @FXML
    private Label address;

    @FXML
    private Label name;

    @FXML
    private Label animals;

    @FXML
    private Label food;

    @FXML
    private Label hobbies;

    @FXML
    private Label house;

    @FXML
    private ImageView imgFamily;

    @FXML
    private TableView<FamilyMember> tableViewMembers;

    @FXML
    private TableColumn<FamilyMember, String> nameColumn;

    @FXML
    private TableColumn<FamilyMember, Integer> ageColumn;

    @FXML
    private TableColumn<FamilyMember, String> parenthoodColumn;

    @FXML
    private Label phone;

    @FXML
    private Label compatibilityL;

    private String cityString;

    public void backToForm(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("contactFamilyForm.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,918,560));
        stage.centerOnScreen();
        stage.show();
    }

    public int setList(List<CompatibleFamilyBean> compatibleFamilyBeans, FamilyRequestBean familyRequestBean) throws IOException {
        this.families = compatibleFamilyBeans;
        this.cityString = familyRequestBean.getCity();
        int column = 0;
        int row = 1;
        int count = 0;

        try {

            for (CompatibleFamilyBean family : families) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("familyItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                FamilyItemGraphicControllerJavaFX familyItemController = fxmlLoader.getController();

                if(family.getCompatibility()>=50.0){
                    count++;
                }
                    familyItemController.setData(family, familyRequestBean);

                    if (column == 3) {
                        column = 0;
                        row++;
                    }

                    familyGrid.add(anchorPane, column++, row);

                    familyGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    familyGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    familyGrid.setMaxWidth(Region.USE_PREF_SIZE);

                    familyGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    familyGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    familyGrid.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(10));
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        return count;
    }

    public void savedRequest() throws IOException {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("savedRequest.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        dialog.setScene(scene);
        dialog.centerOnScreen();
        dialog.show();
    }

    @FXML
    void backStudHomepage(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Main.getSecondaryStage().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homepageStudent.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.getStage().setScene(scene);
        Main.getStage().show();
    }

    @FXML
    void close(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
