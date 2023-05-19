package com.example.aroundtheworld.bean;

import com.example.aroundtheworld.engineering.observer.Subject;

public class TravelBean extends Subject {
    private String city;
    private String studentName;
    private String arrival;
    private String departure;
    private int id;
    private String studentNationality;
    private int studentAge;
    private String familyName;
    private int rate;
    private String img;

    //costruttore per prenotazioni residence
    public TravelBean(String city, String arrival, String departure, int id, String img) {
        this.city = city;
        this.arrival = arrival;
        this.departure = departure;
        this.id = id;
        this.img = img;
    }

    //costruttore per prenotazioni in famiglia
    public TravelBean(String city, String arrival, String departure, int id, String familyName, String img) {
        this.city = city;
        this.arrival = arrival;
        this.departure = departure;
        this.id = id;
        this.familyName = familyName;
        this.img = img;
    }

    //costruttore per le famiglie
    public TravelBean(String studentName, String arrival, String departure, int id, String studentNationality, int studentAge) {
        this.studentName = studentName;
        this.arrival = arrival;
        this.departure = departure;
        this.id = id;
        this.studentNationality = studentNationality;
        this.studentAge = studentAge;
    }

    public String getImg() { return img; }
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getCity() {
        return city;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public int getId() {
        return id;
    }

    public String getStudentNationality() {
        return studentNationality;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public String getFamilyName() {
        return familyName;
    }
}
