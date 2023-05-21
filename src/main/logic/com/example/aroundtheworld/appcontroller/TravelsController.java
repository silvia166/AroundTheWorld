package com.example.aroundtheworld.appcontroller;

import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.bean.TravelBean;
import com.example.aroundtheworld.dao.FamilyDAO;
import com.example.aroundtheworld.dao.FamilyRequestDAO;
import com.example.aroundtheworld.dao.ResidenceRequestDAO;
import com.example.aroundtheworld.dao.StudentDAO;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.Family;
import com.example.aroundtheworld.model.FamilyRequest;
import com.example.aroundtheworld.model.ResidenceRequest;
import com.example.aroundtheworld.model.Student;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class TravelsController {
    public List<TravelBean> getFamilyTravels(FamilyBean familyBean) throws NotFoundException {
        List<TravelBean> travelBeanList = new ArrayList<>();
        List<FamilyRequest> requests = FamilyRequestDAO.retrieveTravelsByFamily(familyBean.getId());

        for (FamilyRequest request : requests) {
            Student student = StudentDAO.retrieveStudent(null, request.getIdStudent());
            int currYear = Year.now().getValue();
            int birthYear = Integer.parseInt(String.format("%." + 4 + "s", student.getDateOfBirth()));
            int age = currYear - birthYear;
            String studentname = student.getName();
            studentname = studentname.concat(" ");
            studentname = studentname.concat(student.getSurname());
            TravelBean travelBean = new TravelBean(studentname, request.getArrival(), request.getDeparture(), request.getId(), student.getNationality(), age);
            travelBean.setRate(request.getRate());
            travelBeanList.add(travelBean);
        }
        return travelBeanList;
    }

    public List<TravelBean> getStudentTravels(StudentBean studentBean) {
        List<TravelBean> travelBeanList = new ArrayList<>();
        List<FamilyRequest> familyRequests = FamilyRequestDAO.retrieveTravelsByStudent(studentBean.getId());
        List<ResidenceRequest> residenceRequests = ResidenceRequestDAO.retrieveTravelsByStudent(studentBean.getId());


        for (FamilyRequest request : familyRequests) {
            Family family = FamilyDAO.retrieveFamilyName(request.getIdFamily());
            TravelBean travelBean = new TravelBean(request.getCity(), request.getArrival(), request.getDeparture(), request.getId(), family.getName(), family.getImgSrc());
            travelBean.setRate(request.getRate());
            travelBeanList.add(travelBean);
        }

        for (ResidenceRequest request : residenceRequests) {
            String image = "image/";
            if(request.getCity().equals("New York")){
                image = image.concat("newYork");
            }else{
                image = image.concat(request.getCity().toLowerCase());
            }
            image = image.concat("Flag.png");
            TravelBean travelBean = new TravelBean(request.getCity(), request.getArrival(), request.getDeparture(), request.getId(), image);
            travelBeanList.add(travelBean);
        }

        return travelBeanList;
    }
}
