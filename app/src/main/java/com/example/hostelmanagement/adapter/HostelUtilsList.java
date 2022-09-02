package com.example.hostelmanagement.adapter;

import com.google.gson.annotations.SerializedName;

public class HostelUtilsList {
    @SerializedName("TRoomno")
    private String TRoomno;
    @SerializedName("TBedno")
    private String TBedno;
    @SerializedName("TTableno")
    private String TTableno;
    @SerializedName("ORoomno")
    private String ORoomno;
    @SerializedName("OBedno")
    private String OBedno;
    @SerializedName("OTableno")
    private String OTableno;

    public HostelUtilsList( String TRoomno, String TBedno, String TTableno,String ORoomno, String OBedno, String OTableno){
        this.TRoomno = TRoomno;
        this.TBedno = TBedno;
        this.TTableno = TTableno;
        this.ORoomno = ORoomno;
        this.OBedno = OBedno;
        this.OTableno = OTableno;
    }

    /// 2 Roomno
    public String getTRoomno() {
        return TRoomno;
    }

    public void setTRoomno(String TRoomno) {
        this.TRoomno = TRoomno;
    }
    //// 3 Bedno
    public String getTBedno() {
        return TBedno;
    }

    public void setTBedno(String TBedno) {
        this.TBedno = TBedno;
    }
    //// 4 Tableno
    public String getTTableno() {
        return TTableno;
    }

    public void setTTableno(String TTableno) {
        this.TTableno = TTableno;
    }

    /// 2 Roomno
    public String getORoomno() {
        return ORoomno;
    }

    public void setORoomno(String ORoomno) {
        this.ORoomno = ORoomno;
    }
    //// 3 Bedno
    public String getOBedno() {
        return OBedno;
    }

    public void setOBedno(String OBedno) {
        this.OBedno = OBedno;
    }
    //// 4 Tableno
    public String getOTableno() {
        return OTableno;
    }

    public void setOTableno(String OTableno) {
        this.OTableno =OTableno;
    }

    @Override
    public String toString() {
        return "HostelUtilsList{" +

                "'TRoomno'" + TRoomno + '\''+
                "'TBedno'" +TBedno+ '\''+
                "'TTableno'" +TTableno+ '\''+
                "'ORoomno'" + ORoomno + '\''+
                "'OBedno'" +OBedno+ '\''+
                "'OTableno'" +OTableno+ '\''+
                '}';
    }
}
