package com.example.hostelmanagement.adapter;

import com.google.gson.annotations.SerializedName;

public class ADAlist {
    @SerializedName("annocid")
    private String annocid;
    @SerializedName("date")
    private String date;
    @SerializedName("note")
    private String  note;

    public ADAlist(String annocid, String date, String note){
        this.annocid = annocid;
        this.date = date;
        this.note = note;
    }
    /// 1
    public String getannocid() {
        return annocid;
    }
    public String getdate() {
        return date;
    }
    public String getnote() {
        return note;
    }
    @Override
    public String toString() {
        return "STAlist{" +
                ",annocid'" + annocid + '\'' +
                "'date'" + date + '\''+
                "'note'" +note+ '\''+
                '}';
    }
}
