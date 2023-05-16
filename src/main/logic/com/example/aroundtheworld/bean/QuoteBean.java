package com.example.aroundtheworld.bean;

public class QuoteBean {

    private String city;
    private int weeks;
    private String accommodation;
    private String room;
    private int activity1;
    private int activity2;
    private int activity3;
    private int sport;

    public QuoteBean(String city, int weeks, String accommodation, String room) {
        this.city = city;
        this.weeks = weeks;
        this.accommodation = accommodation;
        this.room = room;

    }
    public void setActivity1(int activity1) {
        this.activity1 = activity1;
    }

    public void setActivity2(int activity2) {
        this.activity2 = activity2;
    }

    public void setActivity3(int activity3) {
        this.activity3 = activity3;
    }

    public void setSport(int sport) {
        this.sport = sport;
    }

    public String getCity() {
        return city;
    }

    public int getWeeks() {
        return weeks;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public String getRoom() {
        return room;
    }

    public int getActivity1() {
        return activity1;
    }

    public int getActivity2() {
        return activity2;
    }

    public int getActivity3() {
        return activity3;
    }

    public int getSport() {
        return sport;
    }

}
