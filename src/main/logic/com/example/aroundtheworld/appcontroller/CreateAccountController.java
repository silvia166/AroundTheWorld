package com.example.aroundtheworld.appcontroller;

import com.example.aroundtheworld.bean.StudentBean;
import com.example.aroundtheworld.dao.StudentDAO;
import com.example.aroundtheworld.exception.MessageException;

import java.io.*;
import java.time.LocalDate;

public class CreateAccountController {

    private static final String CSV_FILE_NAME = "src/main/res/Users.csv";
    public void createAccount(StudentBean studentBean) throws MessageException {
        checkBirth(studentBean.getBirth());
        StudentDAO.addStudent(studentBean.getName(), studentBean.getSurname(), studentBean.getBirth(), studentBean.getNationality(), studentBean.getPhoneNumber(), studentBean.getEmail(), studentBean.getPassword());

        String user = studentBean.getEmail();
        user = user.concat(",");
        user = user.concat(studentBean.getPassword());
        user = user.concat(",student");

        File file = new File(CSV_FILE_NAME);

        try {
            BufferedReader input = new BufferedReader(new StringReader(user));
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            output.println(user);
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        private void checkBirth(String birth) throws MessageException {
        LocalDate date = LocalDate.parse(birth);
        LocalDate currentDate = LocalDate.now();

        if(date.isAfter(currentDate.minusYears(10)) || date.isAfter(currentDate.minusDays(1))){
            throw new MessageException("You must be at least 10 years old");
        }
    }
}
