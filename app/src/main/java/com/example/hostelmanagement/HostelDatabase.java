package com.example.hostelmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.hostelmanagement.adapter.HostelList;
import com.example.hostelmanagement.adapter.HosteldataAdapter;
import com.example.hostelmanagement.adapter.PayViewAdmin;
import com.example.hostelmanagement.adapter.PaymentlistAD;
import com.example.hostelmanagement.retrofit.ApiClient;
import com.example.hostelmanagement.retrofit.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HostelDatabase extends AppCompatActivity {
    private RecyclerView recyclerViewHD;
    private HosteldataAdapter hosteldataAdapter;
    private RetrofitInterface retrofitInterface;
    SwipeRefreshLayout swipeRefreshLayoutstpayHD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_database);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        getSupportActionBar().setTitle("Hostel Allotment Data");
        /////Refresher
        swipeRefreshLayoutstpayHD = findViewById(R.id.swipeContainerHD);
        swipeRefreshLayoutstpayHD.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayoutstpayHD.setRefreshing(false);
                //your code on swipe refresh
                gethosinfoA();
            }
        });
        swipeRefreshLayoutstpayHD.setColorSchemeColors(Color.BLACK);
        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        recyclerViewHD = findViewById(R.id.recyclerViewHD);
        recyclerViewHD.setHasFixedSize(true);
        recyclerViewHD.setLayoutManager(new LinearLayoutManager(this));
        gethosinfoA();
    }

    public void gethosinfoA(){
        ///// Display All record
        retrofit2.Call<List<HostelList>> call  = retrofitInterface.ALLshowphostelDetails();
        call.enqueue(new Callback<List<HostelList>>() {
            @Override
            public void onResponse(Call<List<HostelList>> call, Response<List<HostelList>> response) {
                if(response.isSuccessful()){
                    List<HostelList> hostelLists = response.body();
                    hosteldataAdapter = new HosteldataAdapter(HostelDatabase.this, hostelLists);
                    recyclerViewHD.setAdapter(hosteldataAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<HostelList>> call, Throwable throwable) {
                Log.d("failure",throwable.getLocalizedMessage());
            }
        });

    }


    //menu of student portal
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.hosdmenu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.hosdsearch);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();
        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                hosteldataAdapter.getFilter().filter(query);
                return false; }
            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                hosteldataAdapter.getFilter().filter(query);
                return false; }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.hosdsearch){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}