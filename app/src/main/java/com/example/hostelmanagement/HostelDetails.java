package com.example.hostelmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.hostelmanagement.adapter.HostelList;
import com.example.hostelmanagement.retrofit.ApiClient;
import com.example.hostelmanagement.retrofit.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HostelDetails extends AppCompatActivity {
    private RetrofitInterface retrofitInterface;
    public TextView rollno;
    public TextView roomno;
    public TextView bedno;
    public TextView tableno;
    SwipeRefreshLayout swipeRefreshLayoutsths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_details);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        getSupportActionBar().setTitle("Hostel Details");

/////Refresher
        swipeRefreshLayoutsths = findViewById(R.id.swipeforsthsd);
        swipeRefreshLayoutsths.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayoutsths.setRefreshing(false);
                //your code on swipe refresh
                //we are checking networking connectivity
                getHHdata();
            }
        });
        swipeRefreshLayoutsths.setColorSchemeColors(Color.BLACK);


        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        getHHdata();

    }

    private void getHHdata() {
        rollno = findViewById(R.id.textViewd34);
        roomno = findViewById(R.id.textViewd36);
        bedno = findViewById(R.id.textViewd38);
        tableno = findViewById(R.id.textViewd40);
        //Get response form student protal
        String Rollno = getIntent().getStringExtra("Rollno");

        Call<List<HostelList>> call = retrofitInterface.ShoHDbyRollno(Rollno);
        call.enqueue(new Callback<List<HostelList>>() {
            @Override
            public void onResponse(Call<List<HostelList>> call, Response<List<HostelList>> response) {

                List<HostelList> hostelLists = response.body();

                for (HostelList hostelList : hostelLists) {
                    rollno.setText(hostelList.getRollno());
                    roomno.setText(hostelList.getRoomno());
                    bedno.setText(hostelList.getBedno());
                    tableno.setText(hostelList.getTableno());
                }
            }
            @Override
            public void onFailure(Call<List<HostelList>> call, Throwable t) {
                Log.d("failure",t.getLocalizedMessage());
            }
        });

    }


}