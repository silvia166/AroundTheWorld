package com.example.aroundtheworld.appcontroller;

import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.bean.TravelBean;
import com.example.aroundtheworld.dao.*;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.factory.StudentDAOFactory;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class TravelsController {
    public List<TravelBean> getFamilyTravels(FamilyBean familyBean) {
        List<TravelBean> travelBeanList = new ArrayList<>();
        List<FamilyRequest> requests = FamilyRequestDAO.retrieveTravelsByFamily(familyBean.getId());

        for (FamilyRequest request : requests) {
            StudentDAO studentDAO = StudentDAOFactory.getInstance().createStudentDAO();
            Student student = studentDAO.retrieveStudentById(request.getIdStudent());
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
            Family family = FamilyDAO.retrieveFamilyInfo(request.getIdFamily());
            TravelBean travelBean = new TravelBean(request.getCity(), request.getArrival(), request.getDeparture(), request.getId(), family.getName(), family.getImgSrc());
            travelBean.setRate(request.getRate());
            travelBeanList.add(travelBean);
        }

        for (ResidenceRequest request : residenceRequests) {
            try {
                Residence residence = ResidenceDAO.retrieveResidenceById(request.getIdResidence());
                String image = "image/";
                if (residence.getCity().equals("New York")) {
                    image = image.concat("newYork");
                } else {
                    image = image.concat(residence.getCity().toLowerCase());
                }
                image = image.concat("Flag.png");

                TravelBean travelBean = new TravelBean(residence.getCity(), request.getArrival(), request.getDeparture(), request.getId(), image);
                travelBeanList.add(travelBean);
            } catch (NotFoundException e) {
                Printer.printError(e.getMessage());
            }
        }

        return travelBeanList;
    }
}
