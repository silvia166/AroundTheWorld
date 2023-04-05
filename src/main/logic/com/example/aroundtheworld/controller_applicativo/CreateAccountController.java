package com.example.aroundtheworld.controller_applicativo;

import com.example.aroundtheworld.bean.NewStudentBean;
import com.example.aroundtheworld.dao.StudentDAO;
import com.example.aroundtheworld.model.Student;

public class CreateAccountController {

    public void createAccount(NewStudentBean newStudentBean){
        StudentDAO.addStudent(newStudentBean.getName(), newStudentBean.getSurname(), newStudentBean.getBirth(), newStudentBean.getNationality(), newStudentBean.getPhoneNumber(), newStudentBean.getEmail(), newStudentBean.getPassword());
    }
}
