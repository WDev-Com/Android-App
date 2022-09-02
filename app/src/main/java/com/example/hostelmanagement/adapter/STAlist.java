package com.example.hostelmanagement.adapter;

import com.google.gson.annotations.SerializedName;

public class STAlist {
    @SerializedName("date")
    private String date;
    @SerializedName("note")
    private String  note;

    public STAlist(/*String annocid,*/ String date, String note){
        this.date = date;
        this.note = note;
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
                "'date'" + date + '\''+
                "'note'" +note+ '\''+
                '}';
    }
}
