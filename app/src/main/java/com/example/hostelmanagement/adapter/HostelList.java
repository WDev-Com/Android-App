package com.example.hostelmanagement.adapter;
import com.google.gson.annotations.SerializedName;
import java.util.List;
public class HostelList {
    @SerializedName("Rollno")
    private String Rollno;
    @SerializedName("Roomno")
    private String Roomno;
    @SerializedName("Bedno")
    private String Bedno;
    @SerializedName("Tableno")
    private String Tableno;

    public HostelList(String Rollno, String Roomno, String Bedno, String Tableno){
        this.Rollno = Rollno;
        this.Roomno = Roomno;
        this.Bedno = Bedno;
        this.Tableno = Tableno;
    }

    /// 1 Rollno
    public String getRollno() {
        return Rollno;
    }

    public void setRollno(String Rollno) { this.Rollno = Rollno; }
    /// 2 Roomno
    public String getRoomno() {
        return Roomno;
    }

    public void setRoomno(String Roomno) {
        this.Roomno = Roomno;
    }
    //// 3 Bedno
    public String getBedno() {
        return Bedno;
    }

    public void setBedno(String Bedno) {
        this.Bedno = Bedno;
    }
    //// 4 Tableno
    public String getTableno() {
        return Tableno;
    }

    public void setTableno(String Tableno) {
        this.Tableno = Tableno;
    }


    @Override
    public String toString() {
        return "HostelList{" +
                ",Rollno'" + Rollno + '\'' +
                "'Roomno'" + Roomno + '\''+
                "'Bedno'" +Bedno+ '\''+
                "'Tableno'" +Tableno+ '\''+
                '}';
    }
}
