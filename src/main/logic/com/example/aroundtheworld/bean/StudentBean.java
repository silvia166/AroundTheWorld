package com.example.aroundtheworld.bean;

import com.example.aroundtheworld.exception.EmailFormatException;
import com.example.aroundtheworld.exception.PhoneFormatException;
import java.util.regex.Pattern;

public class StudentBean {

    private String name;
    private String surname;
    private String nationality;
    private String birth;
    private String email;
    private String phoneNumber;
    private int id;
    private String password;

    public StudentBean(String name, String surname, String nationality, String birth, String email, String phoneNumber, int id) {
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.birth = birth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public StudentBean(String name, String surname, String nationality, String birth, String email, String phoneNumber, String password) throws EmailFormatException, PhoneFormatException {
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.birth = birth;
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirth() {
        return birth;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws PhoneFormatException {
        if(!isNumeric(phoneNumber)){
            throw new PhoneFormatException(phoneNumber);
        }
        this.phoneNumber = phoneNumber;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void setEmail(String email) throws EmailFormatException {
        String emailRegex = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
        if (!Pattern.compile(emailRegex).matcher(email).matches())
            throw new EmailFormatException(email);
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }
}
