package com.example.hostelmanagement.adapter;

import com.google.gson.annotations.SerializedName;

public class Adimlist {
    @SerializedName("adminname")
    private String adminname;
    @SerializedName("admincode")
    private String admincode;
    @SerializedName("adminaddress")
    private String  adminaddress;
    @SerializedName("admincontact")
    private String  admincontact;
    @SerializedName("adminemail")
    private String  adminemail;

    public Adimlist(String adminname, String admincode, String adminaddress, String admincontact,String adminemail){
        this.adminname = adminname;
        this.admincode = admincode;
        this.adminaddress = adminaddress;
        this.admincontact = admincontact;
        this.adminemail = adminemail;
    }

    /// 1
    public String getadminname() {
        return adminname;
    }
    public String getadmincode() {
        return admincode;
    }
    public String getadminaddress() {
        return adminaddress;
    }
    public String getadmincontact() {
        return admincontact;
    }
    public String getadminemail() {
        return adminemail;
    }

    @Override
    public String toString() {
        return "Adimlist{" +
                ",adminname'" + adminname + '\'' +
                "'admincode'" + admincode + '\''+
                "'adminaddress'" +adminaddress+ '\''+
                "'admincontact'" +admincontact+ '\''+
                "'adminemail'" +adminemail+ '\''+
                '}';
    }
}
