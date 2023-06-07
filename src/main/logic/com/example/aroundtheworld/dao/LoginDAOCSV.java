package com.example.aroundtheworld.dao;

import com.example.aroundtheworld.engineering.Printer;
import com.example.aroundtheworld.exception.NotFoundException;
import com.example.aroundtheworld.model.UserProfile;

import java.io.*;

public class LoginDAOCSV implements LoginDAO{

    private static final String CSV_FILE_NAME = "src/main/res/Users.csv";
    private static final int USERNAME = 0;
    private static final int PASSWORD = 1;
    private static final int ROLE = 2;

    @Override
    public UserProfile checkUser(String username, String password) {
        String nameRole = null;
        int role;
        UserProfile userProfile = null;

        try{
            File file = new File(CSV_FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String row;
            String[] data;

            while((row = bufferedReader.readLine()) != null){
                data = row.split(",");
                if(data[USERNAME].equals(username) && data[PASSWORD].equals(password)){
                    nameRole = data[ROLE];
                }
            }

            switch(nameRole){
                case "student" -> role = 1;
                case "family" -> role = 2;
                case "agency" -> role = 3;
                default -> throw new NotFoundException("No role found");
            }

            bufferedReader.close();

            userProfile = new UserProfile(role, username);
        }catch (IOException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }
        return userProfile;
    }
}
