package com.example.aroundtheworld.controller_applicativo;

import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.dao.StudentDAO;

public class CreateAccountController {

    public void createAccount(StudentBean studentBean){
        StudentDAO.addStudent(studentBean.getName(), studentBean.getSurname(), studentBean.getBirth().toString(), studentBean.getNationality(), studentBean.getPhoneNumber(), studentBean.getEmail(), studentBean.getPassword());
    }
}
