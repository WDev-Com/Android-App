package com.example.hostelmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hostelmanagement.adapter.Studentdatalist;
import com.example.hostelmanagement.retrofit.ApiClient;
import com.example.hostelmanagement.retrofit.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentProfile extends AppCompatActivity {
    private RetrofitInterface retrofitInterface;

    public TextView name;
    public TextView rollno;
    public TextView adharno;
    public TextView address;
    public TextView contact;
    public TextView email;
    public TextView gender;
    public TextView date;
    SwipeRefreshLayout swipeRefreshLayoutstp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        getSupportActionBar().setTitle("Student Profile");
        //Get response form student protal
        String Rollno = getIntent().getStringExtra("Rollno");
        /////Refresher
        swipeRefreshLayoutstp = findViewById(R.id.swipeforstpro);
        swipeRefreshLayoutstp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayoutstp.setRefreshing(false);
                //your code on swipe refresh
                //we are checking networking connectivity
                getdata(Rollno);
            }
        });
        swipeRefreshLayoutstp.setColorSchemeColors(Color.BLACK);



        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);


        getdata(Rollno);

   }
      //  menu of student portal profile
       public boolean onCreateOptionsMenu(Menu menua) {
           MenuInflater mm = getMenuInflater();
            mm.inflate(R.menu.menu2,menua);
            return super.onCreateOptionsMenu(menua);
        }
        //Go to update student information form
      @Override
       public boolean onOptionsItemSelected(@NonNull MenuItem item) {
          //Get response form student protal
          String Rollno = getIntent().getStringExtra("Rollno");
            int id = item.getItemId();
            if (id == R.id.edit){
               Intent inu = new Intent(StudentProfile.this, UpdateStudentInfo.class);
                inu.putExtra("Rollno", Rollno);
                startActivity(inu);
            }
           return super.onOptionsItemSelected(item);

        }

    private void getdata(String Roll) {
        name = findViewById(R.id.textView14);
        rollno = findViewById(R.id.textView16);
        adharno = findViewById(R.id.textView18);
        address = findViewById(R.id.textView20);
        contact = findViewById(R.id.textView22);
        email = findViewById(R.id.textView24);
        gender = findViewById(R.id.textView26);
        date = findViewById(R.id.textView28);
        Call<List<Studentdatalist>> call = retrofitInterface.ShoprofilebyRollno(Roll);
        call.enqueue(new Callback<List<Studentdatalist>>() {
            @Override
            public void onResponse(Call<List<Studentdatalist>> call, Response<List<Studentdatalist>> response) {

                List<Studentdatalist> studentdatalists = response.body();

                for (Studentdatalist studentdatalist : studentdatalists) {
                    name.setText(studentdatalist.getName());
                    rollno.setText(studentdatalist.getRollno());
                    adharno.setText(studentdatalist.getAdharno());
                    address.setText(studentdatalist.getAddress());
                    contact.setText(studentdatalist.getContact());
                    email.setText(studentdatalist.getEmail());
                    gender.setText(studentdatalist.getGender());
                    date.setText(studentdatalist.getDate());
                }
            }
            @Override
            public void onFailure(Call<List<Studentdatalist>> call, Throwable t) {
                Log.d("failure",t.getLocalizedMessage());
            }
        });

    }

    }


