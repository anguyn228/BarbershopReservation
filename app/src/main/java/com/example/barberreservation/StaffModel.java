package com.example.barberreservation;

public class StaffModel {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    private int id;
    private String staffID;
    private String name;
    private String bio;
    private String shift;
    private String title;
    public StaffModel(int id, String staffID, String name, String bio, String shift, String title){
        this.id = id;
        this.staffID = staffID;
        this.name = name;
        this.bio = bio;
        this.shift = shift;
        this.title = title;
    }

    public StaffModel( String staffID, String name, String bio, String shift, String title) {

        this.staffID = staffID;
        this.name = name;
        this.bio = bio;
        this.shift = shift;
        this.title = title;
    }

    @Override
    public String toString() {
        return  "  ID: " + id + + '\'' +
                "Staff: " + staffID + '\'' +
                "  Name: '" + name + '\'' +
                "Tiltle: '" + title +'\'' +
                "  BioGraphy: '" + bio + '\'' +
                "  Shift:'" + shift + '\'';
    }
}
