package com.example.aroundtheworld.controller_applicativo;

import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.bean.ResidenceRequestBean;
import com.example.aroundtheworld.dao.FamilyRequestDAO;
import com.example.aroundtheworld.dao.ResidenceRequestDAO;
import com.example.aroundtheworld.dao.StudentDAO;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.FamilyRequest;
import com.example.aroundtheworld.model.ResidenceRequest;

import java.util.ArrayList;
import java.util.List;

public class ReserveRoomController {
    public List<ResidenceRequestBean> getResidenceRequests() throws NotFoundException {
        List<ResidenceRequestBean> residenceRequestBeans = new ArrayList<>();
        List<ResidenceRequest> requests = ResidenceRequestDAO.retrieveResidenceRequests();
        String studentName;

        for(ResidenceRequest request: requests){
            studentName = StudentDAO.getNameById(request.getIdStudent());
            ResidenceRequestBean residenceRequestBean = new ResidenceRequestBean(request.getCity(), request.getArrival(), request.getDeparture(), request.getRoom(), request.getIdStudent(), request.getStatus());
            residenceRequestBean.setStudentName(studentName);
            residenceRequestBean.setIdResidence(request.getIdResidence());
            residenceRequestBean.setId(request.getId());
            residenceRequestBeans.add(residenceRequestBean);
        }
        return residenceRequestBeans;
    }
}
