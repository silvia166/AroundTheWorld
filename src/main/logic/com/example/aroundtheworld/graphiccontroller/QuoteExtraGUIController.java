package com.example.aroundtheworld.graphiccontroller;

import com.example.aroundtheworld.bean.CityBean;
import com.example.aroundtheworld.bean.QuoteBean;
import com.example.aroundtheworld.appcontroller.QuoteController;
import com.example.aroundtheworld.engineering.ShowExceptionSupport;
import com.example.aroundtheworld.exception.FormEmptyException;
import com.example.aroundtheworld.exception.NotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class QuoteExtraGUIController {
    @FXML
    private CheckBox act1Label;
    @FXML
    private CheckBox act2Label;
    @FXML
    private CheckBox act3Label;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private RadioButton sportNo;
    @FXML
    private RadioButton sportYes;

    QuoteBean quoteBean;

    @FXML
    public void setExtraActivities(QuoteBean quoteBean) throws NotFoundException {
        this.quoteBean = quoteBean;
        QuoteController quoteController = new QuoteController();
        CityBean cityBean = quoteController.getCityInfo(quoteBean.getCity());

        act1Label.setText(cityBean.getAct1Bean());
        act2Label.setText(cityBean.getAct2Bean());
        act3Label.setText(cityBean.getAct3Bean());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(cityBean.getAct1ImgSrcBean())));
        img1.setImage(image);
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(cityBean.getAct2ImgSrcBean())));
        img2.setImage(image);
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(cityBean.getAct3ImgSrcBean())));
        img3.setImage(image);

    }
    public void getExtra() throws IOException {
        try {
            if (act1Label.isSelected()) {
                quoteBean.setActivity1(1);
            }
            if (act2Label.isSelected()) {
                quoteBean.setActivity2(1);
            }
            if (act3Label.isSelected()) {
                quoteBean.setActivity3(1);
            }
            if (!sportYes.isSelected() && !sportNo.isSelected())
                throw new FormEmptyException("Sport");
            if (sportYes.isSelected()){
                quoteBean.setSport(1);
            }

            QuoteController quoteController = new QuoteController();
            int price = quoteController.calculateQuote(quoteBean);

            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initStyle(StageStyle.UNDECORATED);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("quoteResult.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            QuoteGUIController quoteGUIController = fxmlLoader.getController();
            quoteGUIController.showQuoteResult(price, quoteBean);

            dialog.setScene(scene);
            dialog.centerOnScreen();
            dialog.show();

        } catch (FormEmptyException e) {
            ShowExceptionSupport.showException(e.getMessage());
        }
    }

    public void backButton(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
