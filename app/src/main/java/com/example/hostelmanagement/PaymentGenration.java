package com.example.hostelmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hostelmanagement.retrofit.ApiClient;
import com.example.hostelmanagement.retrofit.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentGenration extends AppCompatActivity {
    private RetrofitInterface retrofitInterface;
    private EditText rollno,billno,billtitle,billamount,billdate,billdue,billstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_genration);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        getSupportActionBar().setTitle("Payment Genration");
        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

         rollno =(EditText) findViewById(R.id.rollnap);
         billno = (EditText) findViewById(R.id.billnot);
         billtitle =(EditText)  findViewById(R.id.bedna);
         billamount =(EditText)  findViewById(R.id.billamt);
         billdate =(EditText)  findViewById(R.id.billdat);
         billdue =(EditText)  findViewById(R.id.billduet);
         billstatus =(EditText)  findViewById(R.id.billstat);

    }
    public void allotpayment(){

                HashMap<String, String> hashMapG = new HashMap<>();
                hashMapG.put("Rollno", rollno.getText().toString());
                hashMapG.put("Billno", billno.getText().toString());
                hashMapG.put("Billtitle", billtitle.getText().toString());
                hashMapG.put("Billamount", billamount.getText().toString());
                hashMapG.put("Billdate", billdate.getText().toString());
                hashMapG.put("Billdue", billdue.getText().toString());
                hashMapG.put("Billstatus", billstatus.getText().toString());



                Call<Void> call = retrofitInterface.genratepayment(hashMapG);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {


                        if (response.code() == 201) {
                            Toast.makeText(PaymentGenration.this,"Created Bill Sucessfull",
                                    Toast.LENGTH_LONG).show();
                        } else if(response.code() == 400) {
                            Toast.makeText(PaymentGenration.this,"That Bill already exisits!'", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 401) {
                            Toast.makeText(PaymentGenration.this,"That Billno already exisits!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(PaymentGenration.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

    }
    public void updatepayment(){
        HashMap<String, String> hashMapUB = new HashMap<>();
                hashMapUB.put("Rollno", rollno.getText().toString());
                hashMapUB.put("Billno", billno.getText().toString());
                hashMapUB.put("Billtitle", billtitle.getText().toString());
                hashMapUB.put("Billamount", billamount.getText().toString());
                hashMapUB.put("Billdate", billdate.getText().toString());
                hashMapUB.put("Billdue", billdue.getText().toString());
                hashMapUB.put("Billstatus", billstatus.getText().toString());


                String rolldb = billno.getText().toString();
                Call<Void> call = retrofitInterface.updatebill(rolldb,hashMapUB);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {
                            Toast.makeText(PaymentGenration.this,"Updated bill Sucessfully",
                                    Toast.LENGTH_LONG).show();

                        } else if(response.code() == 500) {
                            Toast.makeText(PaymentGenration.this,"Server related error", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(PaymentGenration.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });


    }

    public void updatestatus(){
        HashMap<String, String> hashMapUp = new HashMap<>();
                hashMapUp.put("Billstatus", billstatus.getText().toString());
                String rolldb = billno.getText().toString();
                Call<Void> call = retrofitInterface.updatebillstatus(rolldb,hashMapUp);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {
                            Toast.makeText(PaymentGenration.this,"Updated bill Status Sucessfully",
                                    Toast.LENGTH_LONG).show();

                        } else if(response.code() == 500) {
                            Toast.makeText(PaymentGenration.this,"Server related error", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(PaymentGenration.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void deleteonepayment(){
                String bino = billno.getText().toString();
                Call<Void> call = retrofitInterface.deleteonebill(bino);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {


                        if (response.code() == 201) {
                            Toast.makeText(PaymentGenration.this, "Deleted Bill Sucessfull",
                                    Toast.LENGTH_LONG).show();

                        } else if (response.code() == 400) {
                            Toast.makeText(PaymentGenration.this, "Error in code server crash", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(PaymentGenration.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void deleteallpayment(){
                String roll = rollno.getText().toString();
                Call<Void> call = retrofitInterface.deleteallbill(roll);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {


                        if (response.code() == 201) {
                            Toast.makeText(PaymentGenration.this, "Deleted All Sucessfull",
                                    Toast.LENGTH_LONG).show();

                        } else if (response.code() == 400) {
                            Toast.makeText(PaymentGenration.this, "Error in code server crash", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(PaymentGenration.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void subpay(View view) {
        //to getrate bill
        if(rollno.getText().toString().isEmpty() && billno.getText().toString().isEmpty() &&
                billtitle.getText().toString().isEmpty() && billamount.getText().toString().isEmpty() &&
                billdate.getText().toString().isEmpty() && billdue.getText().toString().isEmpty() &&
                billstatus.getText().toString().isEmpty()  ){
            Toast.makeText(getApplicationContext(), "Please Fill all required fields", Toast.LENGTH_SHORT).show();
        }
        else if(rollno.getText().toString().isEmpty() || billno.getText().toString().isEmpty() ||
                billtitle.getText().toString().isEmpty() || billamount.getText().toString().isEmpty() ||
                billdate.getText().toString().isEmpty() || billdue.getText().toString().isEmpty() ||
                billstatus.getText().toString().isEmpty()  ){
            Toast.makeText(getApplicationContext(), "Please Fill all required fields", Toast.LENGTH_SHORT).show();
        }
        else{
            allotpayment();
        }
    }

    public void updpay(View view) {
        if(rollno.getText().toString().isEmpty() || billno.getText().toString().isEmpty() ||
                billtitle.getText().toString().isEmpty() || billamount.getText().toString().isEmpty() ||
                billdate.getText().toString().isEmpty() || billdue.getText().toString().isEmpty() ||
                billstatus.getText().toString().isEmpty() ){
        Toast.makeText(getApplicationContext(), "Enter All Required Fields For Update", Toast.LENGTH_SHORT).show();
        }
        //to update payment
        updatepayment();
    }

    public void updStatusbill(View view) {
        if(billno.getText().toString().isEmpty() || billstatus.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter Bill No And Bill Status", Toast.LENGTH_SHORT).show();
        }
        //to update status
        updatestatus();
    }

    public void delbillone(View view) {
        if(billno.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter Only Bill No To Delete Bill", Toast.LENGTH_SHORT).show();
        }
        //to delete one bill
        deleteonepayment();
    }

    public void delbillmany(View view) {
        //to delete all bill
       if(rollno.getText().toString().isEmpty()){
           Toast.makeText(getApplicationContext(), "Enter Only Roll No To Delete All Bill", Toast.LENGTH_SHORT).show();
       }
        deleteallpayment();
    }

    public void Clear(View view) {
        rollno.setText("");
        billno.setText("");
        billtitle.setText("");
        billamount.setText("");
        billdate.setText("");
        billdue.setText("");
        billstatus.setText("");

    }
}