package com.example.aroundtheworld.appcontroller;

import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.dao.FamilyDAO;
import com.example.aroundtheworld.dao.FamilyRequestDAO;
import com.example.aroundtheworld.model.Family;
import com.example.aroundtheworld.model.FamilyRequest;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class BookingFamilyController {
    public List<FamilyRequestBean> getStudentFamilyRequest(int id) {
        List<FamilyRequestBean> familyRequestBeanList = new ArrayList<>();
        List<FamilyRequest> requests = FamilyRequestDAO.retrieveStudentRequest(id);

        for(FamilyRequest request: requests){
            Family family = FamilyDAO.retrieveFamilyName(request.getIdFamily());
            FamilyRequestBean familyRequestBean = new FamilyRequestBean(request.getCity(),request.getArrival(), request.getDeparture(), request.getStatus(), request.getIdFamily());
            familyRequestBean.setId(request.getId());
            familyRequestBean.setFamilyName(family.getName());
            familyRequestBean.setImgFamily(family.getImgSrc());
            familyRequestBeanList.add(familyRequestBean);
        }
        return familyRequestBeanList;
    }

    public void rejectRequest(FamilyRequestBean familyRequest, Pane pane) {
        if(pane != null) {
            familyRequest.notifyObserversFamily(familyRequest, pane);
        }
        FamilyRequestDAO.deleteRequest(familyRequest.getId());
    }


    public void bookFamily(FamilyRequestBean familyRequest, Pane pane) {
        if(pane != null){
            familyRequest.notifyObserversFamily(familyRequest, pane);
        }
        FamilyRequestDAO.updateStatus(2, familyRequest.getId());
    }
}
