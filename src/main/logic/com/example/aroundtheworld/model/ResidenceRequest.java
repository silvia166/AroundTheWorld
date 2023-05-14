package com.example.aroundtheworld.model;

public class ResidenceRequest extends Request {
    private int idResidence;
    private String room;
    private int roomNumber;

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
    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
