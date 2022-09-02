package com.example.hostelmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.SearchView;
//import androidx.appcompat.widget.SearchView;

import java.util.List;
import retrofit2.Response;

import com.example.hostelmanagement.adapter.HostelList;
import com.example.hostelmanagement.adapter.RecyclerViewAdapter;
import com.example.hostelmanagement.adapter.Studentdatalist;
import com.example.hostelmanagement.retrofit.ApiClient;
import com.example.hostelmanagement.retrofit.RetrofitInterface;
import retrofit2.Callback;
import retrofit2.Call;

public class StudentDatabase extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RetrofitInterface retrofitInterface;
    AlertDialog.Builder builder;
    SwipeRefreshLayout swipeRefreshLayout;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_database);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        getSupportActionBar().setTitle("Registered Student Data");
        swipeRefreshLayout = findViewById(R.id.swipeContainer);
        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getStudentDetails();

        /////Refresher
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                //your code on swipe refresh
                //we are checking networking connectivity
                getStudentDetails();
            }
        });
        swipeRefreshLayout.setColorSchemeColors(Color.BLACK);
    }

    public void  getStudentDetails(){
        retrofit2.Call<List<Studentdatalist>> call = retrofitInterface.getStudentDetails();
        call.enqueue(new Callback<List<Studentdatalist>>() {

            @Override
            public void onResponse(Call<List<Studentdatalist>> call, Response<List<Studentdatalist>> response) {

                if(response.isSuccessful()){
                    List<Studentdatalist>  studentdatalists = response.body();
                    recyclerViewAdapter = new RecyclerViewAdapter(StudentDatabase.this, studentdatalists);
                    recyclerView.setAdapter(recyclerViewAdapter);

                }

            }
            @Override
            public void onFailure(Call<List<Studentdatalist>> call, Throwable t) {
                Log.d("failure",t.getLocalizedMessage());
            }
        });
    }
    //menu of student portal
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.menu4, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.menu_search);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();
        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                recyclerViewAdapter.getFilter().filter(query);
                return false; }
            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                recyclerViewAdapter.getFilter().filter(query);
                return false; }
        });
        return true;
    }
       // return super.onCreateOptionsMenu(menu);
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Get response form student protal
        int id = item.getItemId();
        if (id == R.id.deleteac) {
        builder =new AlertDialog.Builder(StudentDatabase.this,R.style.MyDialogTheme);
        View ComView = getLayoutInflater().inflate(R.layout.customdelete,null);
            EditText inputText = (EditText)ComView.findViewById(R.id.enterroll);
            Button btn_del = (Button)ComView.findViewById(R.id.delac);
            Button btn_cancel = (Button)ComView.findViewById(R.id.cancel);
            CheckBox proce =(CheckBox)ComView.findViewById(R.id.proce);

        builder.setMessage("Do you want to delete account");
        //Creating dialog box
        AlertDialog alert = builder.create();
        alert.setView(ComView);
        //Setting the title manually
        // alert.setTitle("AlertDialogExample");
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alert.dismiss();
                }
            });
                btn_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (proce.isChecked()) {
                        String rollsam = inputText.getText().toString();
                        Call<Void> call = retrofitInterface.executeDeleteALL(rollsam);

                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {


                                if (response.code() == 201) {
                                    Toast.makeText(StudentDatabase.this, "Deleted Sucessfull",
                                            Toast.LENGTH_LONG).show();

                                } else if (response.code() == 400) {
                                    Toast.makeText(StudentDatabase.this, "Error in code server crash", Toast.LENGTH_LONG).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(StudentDatabase.this, t.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }); }

                    }
                });
        alert.show();
        }
       if(id == R.id.menu_search){
            return true;
        }
    return super.onOptionsItemSelected(item);
    }
}
