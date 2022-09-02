package com.example.hostelmanagement.adapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Studentdatalist{

        @SerializedName("name")
        private String name;
        @SerializedName("rollno")
        private String rollno;
        @SerializedName("adharno")
        private String adharno;
        @SerializedName("address")
        private String address;
        @SerializedName("contact")
        private String contact;
        @SerializedName("email")
        private String email;
        @SerializedName("gender")
        private String gender;
        @SerializedName("date")
        private String date;
       /* ///////for hostel
        @SerializedName("Rollno")
        private String Rollno;
    @SerializedName("Roomno")
    private String Roomno;
    @SerializedName("Bedno")
    private String Bedno;
    @SerializedName("Tableno")
    private String Tableno;*/

        public Studentdatalist(String name, String rollno, String adharno, String address, String contact, String email,
                               String gender, String date){
            this.name = name;
            this.rollno = rollno;
            this.adharno = adharno;
            this.address = address;
            this.contact = contact;
            this.email = email;
            this.gender = gender;
            this.date = date;
            /////////
           /* this.Rollno = Rollno;
            this.Roomno = Roomno;
            this.Bedno = Bedno;
            this.Tableno = Tableno;*/
        }


    ///name
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        ///rollno
        public String getRollno() {
            return rollno;
        }

        public void setRollno(String rollno) {
            this.rollno = rollno;
        }
        ////adhar no
        public String getAdharno() {
            return adharno;
        }

        public void setAdharno(String adharno) {
            this.adharno = adharno;
        }
        ////address
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.name = address;
        }
        ///contact
        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }
        ///email id
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
        ///gender
        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        /// data of register
        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

   /* /// 2 Roomno
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
*/

    @Override
        public String toString() {
            return "Studentdetails{" +
                    ", name='" + name + '\'' +
                    "'rollno'" + rollno + '\''+
                    "'adharno'" +adharno+ '\''+
                    "'address'" +address+ '\''+
                    "'contact'" +contact+ '\''+
                    "'email'" +email+ '\''+
                    "'gender'" +gender+'\''+
                    "'date'" +date+ '\''+
                   /* ",Rollno'" + Rollno + '\'' +
                    "'Roomno'" + Roomno + '\''+
                    "'Bedno'" +Bedno+ '\''+
                    "'Tableno'" +Tableno+ '\''+*/
                    '}';
        }
    }

