package com.example.barberreservation;

public class CustomerModel {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String username;

    public CustomerModel(String username, String address, String name) {
        this.username = username;
        this.address = address;
        this.name = name;
    }

    private String address;
    private String name;
}
