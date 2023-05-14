package com.example.aroundtheworld.model;

public class ResidenceRequest extends Request{
    private int idResidence;
    private String room;

    public ResidenceRequest(String city, String arrival, String departure, String room, int idStudent, int status) {
        super(city, arrival, departure, idStudent, status);
        this.room = room;
    }
    public int getIdResidence() {
        return idResidence;
    }
    public void setIdResidence(int idResidence) {
        this.idResidence = idResidence;
    }

    public String getRoom() {
        return room;
    }
}
