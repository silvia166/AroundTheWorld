package com.example.aroundtheworld.controller_applicativo;

import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.TravelBean;
import com.example.aroundtheworld.dao.FamilyRequestDAO;
import com.example.aroundtheworld.dao.StudentDAO;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.FamilyRequest;
import com.example.aroundtheworld.model.Student;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class FamilyTravelsController {
    public List<TravelBean> getFamilyTravels(FamilyBean familyBean) throws NotFoundException {
        List<TravelBean> travelBeanList = new ArrayList<>();
        List<FamilyRequest> requests = FamilyRequestDAO.retrieveTravelsByFamily(familyBean.getId());

        for (FamilyRequest request : requests) {
            Student student = StudentDAO.retrieveStudent(null, request.getIdStudent());
            int currYear = Year.now().getValue();
            int birthYear = Integer.parseInt(String.format("%." + 4 + "s", student.getDateOfBirth()));
            int age = currYear - birthYear;
            TravelBean travelBean = new TravelBean(student.getName(), request.getArrival(), request.getDeparture(), request.getId(), student.getNationality(), age);
            travelBean.setRate(request.getRate());
            travelBeanList.add(travelBean);
        }
        return travelBeanList;
    }
}
