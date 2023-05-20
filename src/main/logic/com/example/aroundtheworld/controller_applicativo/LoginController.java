package com.example.aroundtheworld.controller_applicativo;

import com.example.aroundtheworld.bean.*;
import com.example.aroundtheworld.dao.FamilyDAO;
import com.example.aroundtheworld.dao.LoginDAO;
import com.example.aroundtheworld.dao.StudentDAO;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.Animal;
import com.example.aroundtheworld.model.Family;
import com.example.aroundtheworld.model.FamilyMember;
import com.example.aroundtheworld.model.Student;


public class LoginController {
    public void checkUser(LoginBean loginBean) {
            int role = LoginDAO.checkUser(loginBean.getUsername(),loginBean.getPassword());
            loginBean.setRole(role);
    }

    public void studentLogin(LoginBean loginBean) throws NotFoundException {
        Student student = StudentDAO.retrieveStudent(loginBean.getUsername(),0);

        StudentBean studentBean = new StudentBean(student.getName(), student.getSurname(), student.getNationality(), student.getDateOfBirth(), student.getEmail(), student.getPhoneNumber(), student.getId());
        Session.setSessionInstance(studentBean);
    }

    public void familyLogin(LoginBean loginBean) throws NotFoundException {
        Family family = FamilyDAO.retrieveFamily(loginBean.getUsername());

        FamilyBean familyBean = new FamilyBean(family.getName(), family.getCity(), family.getAddress(), family.getId(), family.getPhoneNumber(), family.getEmail());
        familyBean.setHouse(family.getPreferences().getHouse());
        familyBean.setFamilyFood(family.getPreferences().getVegetarian(), family.getPreferences().getVegan());
        familyBean.setFamilyHobbies(family.getPreferences().getTravels(), family.getPreferences().getSport(), family.getPreferences().getBooks(), family.getPreferences().getNature(), family.getPreferences().getFilm(), family.getPreferences().getVideoGames(), family.getPreferences().getCooking());

        for(Animal animal: family.getAnimals()){
            AnimalBean animalBean = new AnimalBean(animal.getType(), animal.getQuantity());
            familyBean.addAnimal(animalBean);
        }

        for(FamilyMember member: family.getMembers()){
            FamilyMemberBean memberBean = new FamilyMemberBean(member.getName(), member.getAge(), member.getParenthood());
            familyBean.addMember(memberBean);
        }

        familyBean.setImgSrc(family.getImgSrc());
        Session.setSessionInstance(familyBean);
    }

}
