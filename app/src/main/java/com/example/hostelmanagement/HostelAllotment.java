package com.example.hostelmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelmanagement.adapter.HostelList;
import com.example.hostelmanagement.adapter.HostelUtilsList;
import com.example.hostelmanagement.retrofit.ApiClient;
import com.example.hostelmanagement.retrofit.RetrofitInterface;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HostelAllotment extends AppCompatActivity {
    private RetrofitInterface retrofitInterface;
    public TextView troomno ;
    public TextView tbedno ;
    public TextView ttableno ;
    public TextView oroomno;
    public TextView obedno ;
    public TextView otableno ;

    private EditText rollno, roomno ,bedno ,tableno;
    SwipeRefreshLayout swipeRefreshLayoutshds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_allotment);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        getSupportActionBar().setTitle("Hostel Allotment");

      rollno = (EditText) findViewById(R.id.rollna);
       roomno =(EditText)  findViewById(R.id.roomna);
         bedno =(EditText)  findViewById(R.id.bedna);
       tableno= (EditText) findViewById(R.id.tablena);
        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        /////Refresher
        swipeRefreshLayoutshds = findViewById(R.id.refreshhd);
        swipeRefreshLayoutshds.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayoutshds.setRefreshing(false);
                //your code on swipe refresh
                //we are checking networking connectivity
                getHDdata();
            }
        });
        swipeRefreshLayoutshds.setColorSchemeColors(Color.BLACK);


        findViewById(R.id.button13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rollno.getText().toString().isEmpty() && roomno.getText().toString().isEmpty()  &&
                        bedno.getText().toString().isEmpty() && tableno.getText().toString().isEmpty() ){
                    Toast.makeText(getApplicationContext(), "Please Fill all required fields", Toast.LENGTH_SHORT).show();
                }else if(rollno.getText().toString().isEmpty() || roomno.getText().toString().isEmpty()  ||
                        bedno.getText().toString().isEmpty() || tableno.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Fill all required fields", Toast.LENGTH_SHORT).show();
                }else {
                    submithostelallot();
                }
                }
        });

        findViewById(R.id.button14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rollno.getText().toString().isEmpty() || roomno.getText().toString().isEmpty()  ||
                        bedno.getText().toString().isEmpty() || tableno.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Fill all required fields", Toast.LENGTH_SHORT).show();
                }
                updatehostelinfo();
            }
        });

        findViewById(R.id.button15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rollno.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Enter Roll No", Toast.LENGTH_SHORT).show();
                }
                deletehostelD();
            }
        });
        getHDdata();
    }

    private void submithostelallot() {

                HashMap<String, String> hashMapd = new HashMap<>();

                hashMapd.put("Rollno", rollno.getText().toString());
                hashMapd.put("Roomno", roomno.getText().toString());
                hashMapd.put("Bedno", bedno.getText().toString());
                hashMapd.put("Tableno", tableno.getText().toString());

                Call<Void> call = retrofitInterface.allotdetailshos(hashMapd);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {


                        if (response.code() == 201) {
                            Toast.makeText(HostelAllotment.this,"Submitted Sucessfull",
                                    Toast.LENGTH_LONG).show();

                        } else if(response.code() == 400) {
                            Toast.makeText(HostelAllotment.this,"That Hostel data already exisits!", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 401) {
                            Toast.makeText(HostelAllotment.this,"That Rollno already exisits!", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 402) {
                            Toast.makeText(HostelAllotment.this,"That Roomno already exisits!", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 403) {
                            Toast.makeText(HostelAllotment.this,"That Bed no already exisits!", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 404) {
                            Toast.makeText(HostelAllotment.this,"That Table no already exisits!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(HostelAllotment.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });


    }

    public void updatehostelinfo(){

                HashMap<String, String> hashMapup = new HashMap<>();
                hashMapup.put("Rollno", rollno.getText().toString());
                hashMapup.put("Roomno", roomno.getText().toString());
                hashMapup.put("Bedno", bedno.getText().toString());
                hashMapup.put("Tableno", tableno.getText().toString());

                String rollup = rollno.getText().toString();
                Call<Void> call = retrofitInterface.executeUpdatehosinfo(rollup,hashMapup);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {
                            Toast.makeText(HostelAllotment.this,"Update Sucessfully",
                                    Toast.LENGTH_LONG).show();

                        } else if(response.code() == 500) {
                            Toast.makeText(HostelAllotment.this,"Server related error", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(HostelAllotment.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });


    }

    public void deletehostelD(){
                String rollup = rollno.getText().toString();
                    Call<Void> call = retrofitInterface.executeDeletehostelinfo(rollup);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {


                            if (response.code() == 201) {
                                Toast.makeText(HostelAllotment.this, "Deleted Sucessfull",
                                        Toast.LENGTH_LONG).show();

                            } else if (response.code() == 400) {
                                Toast.makeText(HostelAllotment.this, "Error in code server crash", Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(HostelAllotment.this, t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });


    }



    private void getHDdata() {
         troomno = findViewById(R.id.Occupr);
         tbedno = findViewById(R.id.bedc);
         ttableno = findViewById(R.id.Occudbedtablec);
         oroomno = findViewById(R.id.roomc);
         obedno = findViewById(R.id.Occupb);
         otableno = findViewById(R.id.tablec);

        Call<List<HostelUtilsList>> call = retrofitInterface.ss();
        call.enqueue(new Callback<List<HostelUtilsList>>() {
            @Override
            public void onResponse(Call<List<HostelUtilsList>> call, Response<List<HostelUtilsList>> response) {
                if(response.code() == 201){
                List<HostelUtilsList> hostelUtilsLists = response.body();
                for (HostelUtilsList hostelUtilsList : hostelUtilsLists) {
                    troomno.setText(hostelUtilsList.getTRoomno());
                    tbedno.setText(hostelUtilsList.getTBedno());
                    ttableno.setText(hostelUtilsList.getTTableno());
                    oroomno.setText(hostelUtilsList.getORoomno());
                    obedno.setText(hostelUtilsList.getOBedno());
                    otableno.setText(hostelUtilsList.getOTableno());
                }
            }
         }
            @Override
            public void onFailure(Call<List<HostelUtilsList>> call, Throwable t) {
                Log.d("failure",t.getLocalizedMessage());
            }
        });

    }

    public void ClearAA(View view) {
        rollno.setText("");
        roomno.setText("");
        bedno.setText("");
        tableno.setText("");
    }
}