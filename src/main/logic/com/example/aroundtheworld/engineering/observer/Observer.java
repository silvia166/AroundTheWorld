package com.example.aroundtheworld.engineering.observer;

import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.bean.ResidenceRequestBean;
import javafx.scene.layout.Pane;

public interface Observer {
    void updateResidence(ResidenceRequestBean requestBean, Pane pane);
    void updateFamily(FamilyRequestBean requestBean, Pane pane);
}
