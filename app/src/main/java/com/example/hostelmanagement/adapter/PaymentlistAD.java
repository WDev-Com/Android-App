package com.example.hostelmanagement.adapter;

import com.google.gson.annotations.SerializedName;
    public class PaymentlistAD{
        @SerializedName("Rollno")
        private String Rollno;
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

        public PaymentlistAD(String Rollno,String Billno, String Billtitle, String Billamount, String Billdate, String Billdue, String Billstatus){
            this.Rollno = Rollno;
            this.Billno = Billno;
            this.Billtitle = Billtitle;
            this.Billamount = Billamount;
            this.Billdate = Billdate;
            this.Billdue = Billdue;
            this.Billstatus = Billstatus;
        }
        /// 0 Rollno
        public String getaRollno() {
            return Rollno;
        }

        public void setaRollno(String Rollno) {
            this.Rollno = Rollno;
        }
        /// 1 Billno
        public String getaBillno() {
            return Billno;
        }

        public void setaBillno(String Billno) {
            this.Billno = Billno;
        }
        /// 2 Billtitle
        public String getaBilltitle() {
            return Billtitle;
        }

        public void setaBilltitle(String Billtitle) {
            this.Billtitle = Billtitle;
        }
        //// 3 Billamount
        public String getaBillamount() {
            return Billamount;
        }

        public void setaBillamount(String Billamount) {
            this.Billamount = Billamount;
        }
        //// 4 Billdate
        public String getaBilldate() {
            return Billdate;
        }

        public void setaBilldate(String Billdate) {
            this.Billdate = Billdate;
        }
        /// 5 Billdue
        public String getaBilldue() {
            return Billdue;
        }

        public void setaBilldue(String Billdue) {
            this.Billdue = Billdue;
        }
        /// 6 Billstatus
        public String getaBillstatus() {
            return Billstatus;
        }

        public void setaBillstatus(String Billstatus) {
            this.Billstatus = Billstatus;
        }

        @Override
        public String toString() {
            return "PayViewAdmin{" +
                    ", Rollno='" + Rollno + '\'' +
                    ", Billno='" + Billno + '\'' +
                    "'Billtitle'" + Billtitle + '\''+
                    "'Billamount'" +Billamount+ '\''+
                    "'Billdate'" +Billdate+ '\''+
                    "'Billdue'" +Billdue+ '\''+
                    "'Billstatus'" +Billstatus+ '\''+
                    '}';
        }
    }
