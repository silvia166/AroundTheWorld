package com.example.aroundtheworld.appcontroller;

import com.example.aroundtheworld.bean.*;
import com.example.aroundtheworld.dao.*;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.engineering.factory.LoginDAOFactory;
import com.example.aroundtheworld.engineering.factory.StudentDAOFactory;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.*;


public class LoginController {
    public void checkUser(LoginBean loginBean) {

        LoginDAO loginDAO = LoginDAOFactory.getInstance().createLoginDAO();

        UserProfile userProfile = loginDAO.checkUser(loginBean.getUsername(),loginBean.getPassword());
        loginBean.setRole(userProfile.getRole());
    }

    public void studentLogin(LoginBean loginBean) {
        StudentDAO studentDAO = StudentDAOFactory.getInstance().createStudentDAO();
        Student student = studentDAO.retrieveStudentByUsername(loginBean.getUsername());

        StudentBean studentBean = new StudentBean(student.getName(), student.getSurname(), student.getNationality(), student.getDateOfBirth(), student.getEmail(), student.getPhoneNumber(), student.getId());
        Session.setSessionInstance(studentBean);
    }

    public void familyLogin(LoginBean loginBean) throws NotFoundException {
        Family family = FamilyDAO.retrieveFamilyByUsername(loginBean.getUsername());

        FamilyBean familyBean = new FamilyBean(family.getName(), family.getCity(), family.getAddress(), family.getId(), family.getPhoneNumber(), family.getEmail());
        familyBean.setHouse(family.getPreferences().getHouse());
        familyBean.setFamilyFood(family.getPreferences().getVegetarian(), family.getPreferences().getVegan());
        familyBean.setFamilyHobbies(family.getPreferences().getTravels(), family.getPreferences().getSport(), family.getPreferences().getBooks(), family.getPreferences().getNature(), family.getPreferences().getFilm(), family.getPreferences().getVideoGames(), family.getPreferences().getCooking());

        for(Animal animal: family.getAnimals()){
            familyBean.addAnimal(animal.getType(), animal.getQuantity());
        }

        for(FamilyMember member: family.getMembers()){
            familyBean.addMember(member.getName(), member.getAge(), member.getParenthood());
        }

        familyBean.setImgSrc(family.getImgSrc());
        Session.setSessionInstance(familyBean);
    }

}
