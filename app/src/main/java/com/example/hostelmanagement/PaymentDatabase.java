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

import com.example.hostelmanagement.adapter.PayViewAdmin;
import com.example.hostelmanagement.adapter.Paymentlist;
import com.example.hostelmanagement.adapter.PaymentlistAD;
import com.example.hostelmanagement.adapter.Paymentviewadapter;
import com.example.hostelmanagement.retrofit.ApiClient;
import com.example.hostelmanagement.retrofit.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentDatabase extends AppCompatActivity {
    private RecyclerView recyclerViewPD;
    private PayViewAdmin payViewAdmin;
    private RetrofitInterface retrofitInterface;
    SwipeRefreshLayout swipeRefreshLayoutstpayPD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_database);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        getSupportActionBar().setTitle("Alloted Payment Data");
        /////Refresher
        swipeRefreshLayoutstpayPD = findViewById(R.id.swipeContainerPD);
        swipeRefreshLayoutstpayPD.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayoutstpayPD.setRefreshing(false);
                //your code on swipe refresh
                getpamentinfoA();
            }
        });
        swipeRefreshLayoutstpayPD.setColorSchemeColors(Color.BLACK);
        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        recyclerViewPD = findViewById(R.id.recyclerViewPD);
        recyclerViewPD.setHasFixedSize(true);
        recyclerViewPD.setLayoutManager(new LinearLayoutManager(this));
        getpamentinfoA();
    }

    public void getpamentinfoA(){
       ///// Display All record
        retrofit2.Call<List<PaymentlistAD>> call  = retrofitInterface.ALLshowpaymentDetails();
        call.enqueue(new Callback<List<PaymentlistAD>>() {
            @Override
            public void onResponse(Call<List<PaymentlistAD>> call, Response<List<PaymentlistAD>> response) {
                if(response.isSuccessful()){
                    List<PaymentlistAD> paymentlistAD = response.body();
                    payViewAdmin = new PayViewAdmin(PaymentDatabase.this, paymentlistAD);
                    recyclerViewPD.setAdapter(payViewAdmin);
                }
            }

            @Override
            public void onFailure(Call<List<PaymentlistAD>> call, Throwable throwable) {
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
        inflater.inflate(R.menu.adminac, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.paysearch);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();
        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                payViewAdmin.getFilter().filter(query);
                return false; }
            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                payViewAdmin.getFilter().filter(query);
                return false; }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_search){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}