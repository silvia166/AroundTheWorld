package com.example.aroundtheworld.engineering.factory;

import com.example.aroundtheworld.dao.*;

import java.time.LocalTime;

public class StudentDAOFactory {


    private StudentDAOFactory(){}

    private static StudentDAOFactory instance = null;

    public static StudentDAOFactory getInstance() {
        if(instance ==null){
            instance = new StudentDAOFactory();
        }
        return instance;
    }

    public StudentDAO createStudentDAO(){

        if (LocalTime.now().getMinute()%2 == 0) {
            return new StudentDAOJDBC();
        } else {
            return new StudentDAOCSV();
        }

    }
}
