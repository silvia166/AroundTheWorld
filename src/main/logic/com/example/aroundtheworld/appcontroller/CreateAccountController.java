package com.example.aroundtheworld.appcontroller;

import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.dao.StudentDAO;
import com.example.aroundtheworld.dao.StudentDAOJDBC;
import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.engineering.factory.StudentDAOFactory;
import com.example.aroundtheworld.exception.MessageException;

import java.io.*;
import java.time.LocalDate;

public class CreateAccountController {

    private static final String CSV_FILE_NAME = "src/main/res/Users.csv";
    public void createAccount(StudentBean studentBean) throws MessageException {
        checkBirth(studentBean.getBirth());
        StudentDAO studentDAO = StudentDAOFactory.getInstance().createStudentDAO();
        studentDAO.addStudent(studentBean.getName(), studentBean.getSurname(), studentBean.getBirth(), studentBean.getNationality(), studentBean.getPhoneNumber(), studentBean.getEmail(), studentBean.getPassword());
    }

        private void checkBirth(String birth) throws MessageException {
        LocalDate date = LocalDate.parse(birth);
        LocalDate currentDate = LocalDate.now();

        if(date.isAfter(currentDate.minusYears(10)) || date.isAfter(currentDate.minusDays(1))){
            throw new MessageException("You must be at least 10 years old");
        }
    }
}
