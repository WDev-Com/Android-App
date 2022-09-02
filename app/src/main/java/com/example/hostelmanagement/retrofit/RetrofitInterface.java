package com.example.hostelmanagement.retrofit;

import com.example.hostelmanagement.adapter.ADAlist;
import com.example.hostelmanagement.adapter.Adimlist;
import com.example.hostelmanagement.adapter.HostelList;
import com.example.hostelmanagement.adapter.HostelUtilsList;
import com.example.hostelmanagement.adapter.Paymentlist;
import com.example.hostelmanagement.adapter.PaymentlistAD;
import com.example.hostelmanagement.adapter.STAlist;
import com.example.hostelmanagement.adapter.Studentdatalist;

import java.util.HashMap;
import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface RetrofitInterface {

    @POST("/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> executeSignup(@Body HashMap<String, String> map);

    ///For Registration Infomation
    @POST("/register")
    Call<Void> executeRegister(@Body HashMap<String, String> hashMap);

    //Update student login password
    @PATCH("/studentpassup/{rollno}")
    Call<Void> updatepassforstud(@Path("rollno") String rollno, @Body HashMap<String, String> hashMap88);

    //For get admin data on main activity
    @GET("/admindatainfo")
    Call<List<Adimlist>> getAdmininfo();

    ///For Admin Registration & Login
    @POST("/adminsignup")
    Call<Void> executeAdminsignup(@Body HashMap<String, String> mapa);

    @POST("/adminregister")
    Call<Void> executeAdminregister(@Body HashMap<String, String> hashMap2);

    @POST("/adminlogin")
    Call<Adminloginresult> executeAdminlogin(@Body HashMap<String, String> hashMap3);

    //Update admin password
    @PATCH("/adminpassup/{admincode}")
    Call<Void> updatepassfor(@Path("admincode") String admincode, @Body HashMap<String, String> hashMap55);

    //For display data to admin
    @GET("/studentdata")
    Call<List<Studentdatalist>> getStudentDetails();

    //For display student profile
    @GET("/findbyroll/{Rollno}")
    Call<List<Studentdatalist>> ShoprofilebyRollno(@Path("Rollno") String Rollno);

    //For update student profile
    @PATCH("/findbyroll/{Rollno}")
    Call<Void> executeUpdatestudinfo(@Path("Rollno") String Rollno, @Body HashMap<String, String> hashMap4);

    //For delete student info And ALL Data about them By admin
    @DELETE("/findbyroll/{Rollno}")
    Call<Void> executeDeleteALL(@Path("Rollno") String Rollno);

    //For delete student info And user Data about them By student
    @DELETE("/findbyrollforS/{Rollno}")
    Call<Void> executeDelByStudi(@Path("Rollno") String Rollno);

    //For delete student info And user Data about them By student
    @DELETE("/admindelete/{code}")
    Call<Void> executeDelByadmin(@Path("code") String admincode);
 //////////////////////////////////////////// ANNOUNCEMENT SECTION ???????????????????????????????????????????
//For Admin
    @GET("/getanounce")
    Call<List<ADAlist>> getAnnouncement();
/// For Student
    @GET("/Studgetanounce")
    Call<List<STAlist>> getAnnouncementST();

    ////////////////////////////////// Hostel Section ????????????????????????????????????????????????????
    //For alloting hostel detials
    @POST("/hostelallot")
    Call<Void> allotdetailshos(@Body HashMap<String, String> hashMapd);

    //Update hostel allotment
    @PATCH("/findHDbyroll/{Rollno}")
    Call<Void> executeUpdatehosinfo(@Path("Rollno") String Rollno, @Body HashMap<String, String> hashMapup);

    //For delete hostel  details
    @DELETE("/findHDbyroll/{Rollno}")
    Call<Void> executeDeletehostelinfo(@Path("Rollno") String Rollno);

    //For display hostel ditails according to student
    @GET("/Hfindbyroll/{Rolln}")
    Call<List<HostelList>> ShoHDbyRollno(@Path("Rolln") String Rollno);

    //Show all hostel Details
    @GET("/AllHostelDe")
    Call<List<HostelList>> ALLshowphostelDetails();

    ////Count of utils
    @GET("/Counter")
    Call<List<HostelUtilsList>> showcountHostelD();

    ////Count of utils
    @GET("/Counterget")
    Call<List<HostelUtilsList>> ss();

    @GET("/AllHostelDe")
    Call<List<HostelList>> dd();

    ////////////////////////////// Payment Section ??????????????????????????????????????????????????????????
    /// Genrate payment
    @POST("/paymentallot")
    Call<Void> genratepayment(@Body HashMap<String, String> hashMapG);

    //For  getting payment info according to roll no for students
    @GET("/findbillbyroll/{Rolln}")
    Call<List<Paymentlist>> showpaymentDetails(@Path("Rolln") String Rollno);

    //For  getting payment info according to roll no for Admin
    @GET("/AllPaymentsD")
    Call<List<PaymentlistAD>> ALLshowpaymentDetails();

    //update bill
    @PATCH("/findbillbyroll/{billno}")
    Call<Void> updatebill(@Path("billno") String billno, @Body HashMap<String, String> hashMapUB);

    //Update status
    @PATCH("/findbillforstatus/{billno}")
    Call<Void> updatebillstatus(@Path("billno") String billno, @Body HashMap<String, String> hashMapUp);

    //Delete one bill
    @DELETE("/findbillbyroll/{billno}")
    Call<Void> deleteonebill(@Path("billno") String billno);

    //Delete All bills
    @DELETE("/findbyrolldelall/{Rollno}")
    Call<Void> deleteallbill(@Path("Rollno") String Rollno);

///////////////////////// Notice
   //Enter new notice
    @POST("/NewNote")
    Call<Void> newnote(@Body HashMap<String, String> hashMapNote);

    //Get notice student
    @GET("/getnotiST")
    Call<List<STAlist>> getNoteST();

    //Get notice Admin
    @GET("/getnotiAD")
    Call<List<ADAlist>> getNoteAD();

    //update notice
    @PATCH("/findnoticeno/{notno}")
    Call<Void> updatenotice(@Path("notno") String notno, @Body HashMap<String, String> hashMapUpNote);

    //Delete notice
    @DELETE("/findnoticenodel/{notno}")
    Call<Void> noticedelete(@Path("notno") String notno);
}



