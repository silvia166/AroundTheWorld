package com.example.aroundtheworld.controller_applicativo;

import com.example.aroundtheworld.bean.FamilyBean;
import com.example.aroundtheworld.bean.LoginBean;
import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.dao.FamilyDAO;
import com.example.aroundtheworld.dao.LoginDAO;
import com.example.aroundtheworld.dao.StudentDAO;
import com.example.aroundtheworld.engineering.Session;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.Family;
import com.example.aroundtheworld.model.Student;

public class LoginController {
    public void checkUser(LoginBean loginBean) {
            int role = LoginDAO.checkUser(loginBean.getUsername(),loginBean.getPassword());
            loginBean.setRole(role);
    }

    public void studentLogin(LoginBean loginBean) throws NotFoundException {
        Student student = StudentDAO.retrieveStudent(loginBean.getUsername());

        StudentBean studentBean = new StudentBean(student.getName(), student.getSurname(), student.getNationality(), student.getDateOfBirth(), student.getEmail(), student.getPhoneNumber(), student.getId());
        Session.setSessionInstance(studentBean);
    }

    public void familyLogin(LoginBean loginBean) throws NotFoundException {
        Family family = FamilyDAO.retrieveFamily(loginBean.getUsername());

        FamilyBean familyBean = new FamilyBean(family.getName(), family.getImgSrc(), family.getCity(), family.getAddress(), family.getHouse(), family.getAnimals(), family.getMembers(), family.getFood(), family.getHoobies(), family.getId(), family.getPhoneNumber(), family.getEmail());
        Session.setSessionInstance(familyBean);
    }
}
