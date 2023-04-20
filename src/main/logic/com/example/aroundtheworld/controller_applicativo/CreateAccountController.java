package com.example.aroundtheworld.controller_applicativo;

import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.dao.StudentDAO;
import com.example.aroundtheworld.exception.MessageException;

import java.time.LocalDate;

public class CreateAccountController {

    public void createAccount(StudentBean studentBean) throws MessageException {
        checkBirth(studentBean.getBirth());
        StudentDAO.addStudent(studentBean.getName(), studentBean.getSurname(), studentBean.getBirth(), studentBean.getNationality(), studentBean.getPhoneNumber(), studentBean.getEmail(), studentBean.getPassword());
    }

    private void checkBirth(String birth) throws MessageException {
        LocalDate date = LocalDate.parse(birth);
        LocalDate currentDate = LocalDate.now();

        if(date.isAfter(currentDate.minusYears(10)) || date.isAfter(currentDate.minusDays(1))){
            throw new MessageException("You must be at least 10 years old");
        }
    }
}
