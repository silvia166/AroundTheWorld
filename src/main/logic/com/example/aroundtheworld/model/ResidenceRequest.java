package com.example.aroundtheworld.model;

public class ResidenceRequest extends Request {
    private int idResidence;
    private String requestedRoom;
    private Room reservedRoom;

    public ResidenceRequest(String city, String arrival, String departure, String requestedRoom, int idStudent, int status) {
        super(city, arrival, departure, idStudent, status);
        this.requestedRoom = requestedRoom;
        this.reservedRoom = null;
    }
    public int getIdResidence() {
        return idResidence;
    }
    public void setIdResidence(int idResidence) {
        this.idResidence = idResidence;
    }
    public String getRequestedRoom() {
        return requestedRoom;
    }
    public Room getReservedRoom() {
        return reservedRoom;
    }
    public void setReservedRoom(Room reservedRoom) {
        this.reservedRoom = reservedRoom;
    }
}
