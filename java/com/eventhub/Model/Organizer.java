package com.eventhub.Model;


public class Organizer {
    
    private String organizerId;
    private String userName;
    private String password;
    private String role;
    private String fullName;
     private String email;

    public Organizer(){} //Required for firebase

    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public String getOrganizerId() {
        return organizerId;
    }


    public void setOrganizerId(String organizerId) {
        this.organizerId = organizerId;
    }
   
}

    

