package com.example.aroundtheworld.appcontroller;

import com.example.aroundtheworld.bean.*;
import com.example.aroundtheworld.dao.FamilyDAO;
import com.example.aroundtheworld.dao.FamilyRequestDAO;
import com.example.aroundtheworld.dao.StudentDAO;
import com.example.aroundtheworld.exception.DuplicateRequestException;
import com.example.aroundtheworld.exception.MessageException;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.*;
import javafx.scene.layout.Pane;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContactFamilyController {

    public float calculateCompatibility(FamilyRequestBean familyRequestBean, Family family){
        double compatibility;
        int siblings = 0;

        FamilyPreferences preferences = new FamilyPreferences();
        preferences.setHouse(familyRequestBean.getHouse());
        preferences.setFood(familyRequestBean.getVegetarian(), familyRequestBean.getVegan());
        preferences.setHobbies(familyRequestBean.getTravels(), familyRequestBean.getSport(), familyRequestBean.getBooks(), familyRequestBean.getNature(), familyRequestBean.getFilm(), familyRequestBean.getVideoGames(), familyRequestBean.getCooking());

        int checked = checkPreferences(preferences, family.getPreferences());

        if(family.getAnimals().isEmpty() && familyRequestBean.getAnimalsBean() == 0){
            checked++;
        }
        for (FamilyMember member : family.getMembers()) {
            if ((member.getParenthood().equals("Sister") || member.getParenthood().equals("Brother") || member.getParenthood().equals("Cousin")) && siblings == 0) {
                siblings = 1;
            }
        }
        if (siblings == familyRequestBean.getSiblingsBean()){
            checked++;
        }
        compatibility = (double) checked/12 *100;
        compatibility = Math.round(compatibility*100.0)/100.0;
        return (float) compatibility;
    }

    private int checkPreferences(FamilyPreferences requestPref, FamilyPreferences familyPref) {
        int checked = 0;

        if(requestPref.getHouse().equals(familyPref.getHouse())){
            checked++;
        }
        if(requestPref.getVegetarian() == familyPref.getVegetarian()){
            checked++;
        }
        if(requestPref.getVegan() == familyPref.getVegan()){
            checked++;
        }
        if(requestPref.getTravels() == familyPref.getTravels()){
            checked++;
        }
        if(requestPref.getBooks() == familyPref.getBooks()){
            checked++;
        }
        if(requestPref.getFilm() == familyPref.getFilm()){
            checked++;
        }
        if(requestPref.getSport() == familyPref.getSport()){
            checked++;
        }
        if(requestPref.getNature() == familyPref.getNature()){
            checked++;
        }
        if(requestPref.getCooking() == familyPref.getCooking()){
            checked++;
        }
        if(requestPref.getVideoGames() == familyPref.getVideoGames()){
            checked++;
        }
        return checked;
    }

    public List<CompatibleFamilyBean> getCompatibleFamilies(FamilyRequestBean familyRequestBean) throws MessageException {
        float compatibility;
        List<CompatibleFamilyBean> compatibleFamilies = new ArrayList<>();
        CompatibleFamilyBean compatibleFamilyBean;
        checkRequestDate(familyRequestBean.getArrivalBean(), familyRequestBean.getDepartureBean());
        List<Family> familyList = FamilyDAO.retrieveFamilies(familyRequestBean.getCityBean());
        for (Family family: familyList){
            compatibility = calculateCompatibility(familyRequestBean,family);
            compatibleFamilyBean = new CompatibleFamilyBean(family.getId(),family.getName(), family.getImgSrc(), compatibility);
            compatibleFamilies.add(compatibleFamilyBean);
        }
        return compatibleFamilies;
    }

    private void checkRequestDate(String arrival, String departure) throws MessageException {
        LocalDate dateA = LocalDate.parse(arrival);
        LocalDate dateD = LocalDate.parse(departure);
        LocalDate currentDate = LocalDate.now();

        if(dateA.isBefore(currentDate.plusDays(7))){
            throw new MessageException("Arrival must be at least \n 7 days from today!");
        }
        if(dateD.isBefore(dateA.plusDays(7))){
            throw new MessageException("Departure must be at least \n 7 days from arrival!");
        }
    }

    public FamilyBean getFamilyProfile(CompatibleFamilyBean compatibleFamilyBean) throws NotFoundException {
        Family family = FamilyDAO.retrieveFamily(compatibleFamilyBean.getEmail());

        FamilyBean familyBean = new FamilyBean(family.getName(), family.getCity(), family.getAddress(), family.getId(), family.getPhoneNumber(), family.getEmail());
        familyBean.setFamilyHobbies(family.getPreferences().getTravels(), family.getPreferences().getSport(), family.getPreferences().getBooks(), family.getPreferences().getNature(), family.getPreferences().getFilm(), family.getPreferences().getVideoGames(), family.getPreferences().getCooking());
        familyBean.setFamilyFood(family.getPreferences().getVegetarian(), family.getPreferences().getVegan());
        familyBean.setHouse(family.getPreferences().getHouse());

        for(FamilyMember member: family.getMembers()){
            FamilyMemberBean memberBean = new FamilyMemberBean(member.getName(), member.getAge(), member.getParenthood());
            familyBean.addMember(memberBean);
        }

        for(Animal animal: family.getAnimals()){
            AnimalBean animalBean = new AnimalBean(animal.getType(), animal.getQuantity());
            familyBean.addAnimal(animalBean);
        }

        familyBean.setImgSrc(family.getImgSrc());

        return familyBean;
    }

    public void saveRequest(FamilyRequestBean familyRequestBean) throws DuplicateRequestException {
        FamilyRequest familyRequest;
        familyRequest = new FamilyRequest(familyRequestBean.getCityBean(), familyRequestBean.getArrivalBean(), familyRequestBean.getDepartureBean(), familyRequestBean.getSiblingsBean(), familyRequestBean.getAnimalsBean(), familyRequestBean.getIdStudentBean(), 0);
        familyRequest.setIdFamily(familyRequestBean.getIdFamilyBean());
        familyRequest.setCompatibility(familyRequestBean.getCompatibilityBean());

        FamilyPreferences preferences = new FamilyPreferences();
        preferences.setHouse(familyRequestBean.getHouse());
        preferences.setFood(familyRequestBean.getVegetarian(), familyRequestBean.getVegan());
        preferences.setHobbies(familyRequestBean.getTravels(), familyRequestBean.getSport(), familyRequestBean.getBooks(), familyRequestBean.getNature(), familyRequestBean.getFilm(), familyRequestBean.getVideoGames(), familyRequestBean.getCooking());
        familyRequest.setFamilyPreferences(preferences);

        FamilyRequestDAO.newRequest(familyRequest);

    }

    public List<FamilyRequestBean> getFamilyRequests(FamilyBean familyBean) {
        List<FamilyRequestBean> familyRequestsBeans = new ArrayList<>();
        List<FamilyRequest> requests = FamilyRequestDAO.retrieveRequests(familyBean.getId());

        for(FamilyRequest request: requests){
            FamilyRequestBean familyRequestBean = new FamilyRequestBean(request.getCity(), request.getArrival(), request.getDeparture(), request.getSiblings(), request.getAnimals(), request.getIdStudent());
            familyRequestBean.setFood(request.getFamilyPreferences().getVegetarian(), request.getFamilyPreferences().getVegan());
            familyRequestBean.setHouse(request.getFamilyPreferences().getHouse());
            familyRequestBean.setHobbies(request.getFamilyPreferences().getTravels(), request.getFamilyPreferences().getSport(), request.getFamilyPreferences().getBooks(), request.getFamilyPreferences().getNature(), request.getFamilyPreferences().getFilm(), request.getFamilyPreferences().getVideoGames(), request.getFamilyPreferences().getCooking());
            familyRequestBean.setId(request.getId());
            familyRequestBean.setCompatibility(request.getCompatibility());
            familyRequestBean.setIdFamily(familyBean.getId());
            familyRequestBean.setStatus(request.getStatus());
            familyRequestBean.setStudentName(request.getStudentName());
            familyRequestsBeans.add(familyRequestBean);
        }
        return familyRequestsBeans;
    }

    public void acceptRequest(FamilyRequestBean familyRequest, Pane pane) {
        familyRequest.setStatus(1);
        familyRequest.notifyObserversFamily(familyRequest, pane);
        FamilyRequestDAO.updateStatus(1, familyRequest.getId());
    }

    public void rejectRequest(FamilyRequestBean familyRequest, Pane pane) {
        familyRequest.notifyObserversFamily(familyRequest, pane);
        FamilyRequestDAO.deleteRequest(familyRequest.getId());
    }

    public StudentBean getStudent(FamilyRequestBean familyRequest) throws NotFoundException {

        Student student = StudentDAO.retrieveStudent(null, familyRequest.getIdStudentBean());
        return new StudentBean(student.getName(), student.getSurname(), student.getNationality(), student.getDateOfBirth(), student.getEmail(), student.getPhoneNumber(), student.getId());
    }
}
