package com.example.hostelmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.hostelmanagement.adapter.STAlist;
import com.example.hostelmanagement.adapter.Studannocadapter;
import com.example.hostelmanagement.retrofit.ApiClient;
import com.example.hostelmanagement.retrofit.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudAnnoucment extends AppCompatActivity {
    private RecyclerView recyclerViewST;
    private Studannocadapter studannocadapter;
    private RetrofitInterface retrofitInterface;
    SwipeRefreshLayout swipeRefreshLayoutAnnoS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_annoucment);
        getSupportActionBar().setTitle("Hostel Announcement");
/////Refresher
        swipeRefreshLayoutAnnoS = findViewById(R.id.swipeContainerSAT);
        swipeRefreshLayoutAnnoS.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayoutAnnoS.setRefreshing(false);
                //your code on swipe refresh
                getAnnouncement();
            }
        });
        swipeRefreshLayoutAnnoS.setColorSchemeColors(Color.BLACK);
        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        recyclerViewST = findViewById(R.id.recyclerViewAnnoST);
        recyclerViewST.setHasFixedSize(true);
        recyclerViewST.setLayoutManager(new LinearLayoutManager(this));

        getAnnouncement();
    }
    public void  getAnnouncement(){
        retrofit2.Call<List<STAlist>> call = retrofitInterface.getNoteST();
        call.enqueue(new Callback<List<STAlist>>() {

            @Override
            public void onResponse(Call<List<STAlist>> call, Response<List<STAlist>> response) {

                if(response.isSuccessful()){
                    List<STAlist>  stAlists = response.body();
                    studannocadapter = new Studannocadapter(StudAnnoucment.this, stAlists);
                    recyclerViewST.setAdapter(studannocadapter);
                }
            }
            @Override
            public void onFailure(Call<List<STAlist>> call, Throwable t) {
                Log.d("failure",t.getLocalizedMessage());
            }
        });
    }
}