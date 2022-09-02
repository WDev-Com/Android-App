package com.example.hostelmanagement.retrofit;


import com.google.gson.annotations.SerializedName;

public class LoginResult {

    private String username;

   private String password;
    @SerializedName("rollno")
    private String rollno;

    public String getUsername() {
      return username;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getPassword() {
       return password;
    }

}

