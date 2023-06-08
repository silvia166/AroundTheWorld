package com.example.aroundtheworld.engineering.observer;

import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.bean.ResidenceRequestBean;
import javafx.scene.layout.Pane;

public interface Observer {
    void update();
    public void updateResidencePage(ResidenceRequestBean requestBean, Pane pane);
    public void updateFamilyPage(FamilyRequestBean requestBean, Pane pane);
}
