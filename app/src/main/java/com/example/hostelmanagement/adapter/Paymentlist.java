package com.example.hostelmanagement.adapter;

import com.google.gson.annotations.SerializedName;
import java.util.List;
public class Paymentlist{

    @SerializedName("Billno")
    private String Billno;
    @SerializedName("Billtitle")
    private String Billtitle;
    @SerializedName("Billamount")
    private String Billamount;
    @SerializedName("Billdate")
    private String Billdate;
    @SerializedName("Billdue")
    private String Billdue;
    @SerializedName("Billstatus")
    private String Billstatus;

    public Paymentlist(String Billno, String Billtitle, String Billamount, String Billdate, String Billdue, String Billstatus){
        this.Billno = Billno;
        this.Billtitle = Billtitle;
        this.Billamount = Billamount;
        this.Billdate = Billdate;
        this.Billdue = Billdue;
        this.Billstatus = Billstatus;
    }
    /// 1 Billno
    public String getBillno() {
        return Billno;
    }

    public void setBillno(String Billno) {
        this.Billno = Billno;
    }
    /// 2 Billtitle
    public String getBilltitle() {
        return Billtitle;
    }

    public void setBilltitle(String Billtitle) {
        this.Billtitle = Billtitle;
    }
    //// 3 Billamount
    public String getBillamount() {
        return Billamount;
    }

    public void setBillamount(String Billamount) {
        this.Billamount = Billamount;
    }
    //// 4 Billdate
    public String getBilldate() {
        return Billdate;
    }

    public void setBilldate(String Billdate) {
        this.Billdate = Billdate;
    }
    /// 5 Billdue
    public String getBilldue() {
        return Billdue;
    }

    public void setBilldue(String Billdue) {
        this.Billdue = Billdue;
    }
    /// 6 Billstatus
    public String getBillstatus() {
        return Billstatus;
    }

    public void setBillstatus(String Billstatus) {
        this.Billstatus = Billstatus;
    }

    @Override
    public String toString() {
        return "Paymentlist{" +
                ", Billno='" + Billno + '\'' +
                "'Billtitle'" + Billtitle + '\''+
                "'Billamount'" +Billamount+ '\''+
                "'Billdate'" +Billdate+ '\''+
                "'Billdue'" +Billdue+ '\''+
                "'Billstatus'" +Billstatus+ '\''+
                '}';
    }
}

