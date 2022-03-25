package com.example.barberreservation;

public class UserModel {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }
    private String name;
    private String userId;
    private String password;
    private String title;

    public UserModel(int id, String name, String userId, String password, String title) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.title = title;

    }

    public UserModel( String name, String userId, String password, String title) {

        this.name = name;
        this.userId = userId;
        this.password = password;
        this.title = title;

    }



    @Override
    public String toString() {
        return  " ID: " + id +
                "  Name:'" + name + '\'' +
                "  UserId: '" + userId + '\'' +
                "title" + title + '\'' +
                "  Password:'" + password + '\'';
    }
}
