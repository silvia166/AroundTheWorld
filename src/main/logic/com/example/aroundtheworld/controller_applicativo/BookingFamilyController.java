package com.example.aroundtheworld.controller_applicativo;

import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.dao.FamilyRequestDAO;
import com.example.aroundtheworld.model.FamilyRequest;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class BookingFamilyController {
    public List<FamilyRequestBean> getStudentFamilyRequest(int id) {
        List<FamilyRequestBean> familyRequestList = new ArrayList<>();
        List<FamilyRequest> requests = FamilyRequestDAO.retrieveStudentFamilyRequest(id);

        for(FamilyRequest request: requests){
            FamilyRequestBean familyRequestBean = new FamilyRequestBean(request.getCity(), request.getArrival(), request.getDeparture(), request.getStatus(), request.getIdFamily(), request.getCompatibility());
            familyRequestBean.setId(request.getId());
            familyRequestBean.setFamilyName(request.getFamilyName());
            familyRequestBean.setImgFamily(request.getImgFamily());
            familyRequestList.add(familyRequestBean);
        }
        return familyRequestList;
    }

    public void rejectRequest(FamilyRequestBean familyRequest, Pane pane) {
        familyRequest.notifyObserversFamily(familyRequest, pane);
        FamilyRequestDAO.deleteRequest(familyRequest.getId());
    }


    public void bookFamily(FamilyRequestBean familyRequest, Pane pane) {
        familyRequest.notifyObserversFamily(familyRequest, pane);
        FamilyRequestDAO.updateStatus(2, familyRequest.getId());
    }
}
