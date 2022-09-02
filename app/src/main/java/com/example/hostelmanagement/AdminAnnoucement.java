package com.example.hostelmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.hostelmanagement.adapter.ADAlist;
import com.example.hostelmanagement.adapter.Adminacconadapter;
import com.example.hostelmanagement.adapter.HostelList;
import com.example.hostelmanagement.adapter.HosteldataAdapter;
import com.example.hostelmanagement.adapter.RecyclerViewAdapter;
import com.example.hostelmanagement.adapter.Studentdatalist;
import com.example.hostelmanagement.retrofit.ApiClient;
import com.example.hostelmanagement.retrofit.RetrofitInterface;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminAnnoucement extends AppCompatActivity {
    private RecyclerView recyclerViewAN;
    private Adminacconadapter adminacconadapter;
    private RetrofitInterface retrofitInterface;
    AlertDialog.Builder builder;
    SwipeRefreshLayout swipeRefreshLayoutAnnoAA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annoucement);
        getSupportActionBar().setTitle("Hostel Announcement");
/////Refresher
        swipeRefreshLayoutAnnoAA = findViewById(R.id.swipeContainerAAT);
        swipeRefreshLayoutAnnoAA.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayoutAnnoAA.setRefreshing(false);
                //your code on swipe refresh
                getAnnouncement();
            }
        });
        swipeRefreshLayoutAnnoAA.setColorSchemeColors(Color.BLACK);
        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        recyclerViewAN = findViewById(R.id.recyclerViewAnno);
        recyclerViewAN.setHasFixedSize(true);
        recyclerViewAN.setLayoutManager(new LinearLayoutManager(this));

        getAnnouncement();
    }
    public void  getAnnouncement(){
        retrofit2.Call<List<ADAlist>> call = retrofitInterface.getNoteAD();
        call.enqueue(new Callback<List<ADAlist>>() {

            @Override
            public void onResponse(Call<List<ADAlist>> call, Response<List<ADAlist>> response) {

                if(response.isSuccessful()){
                    List<ADAlist>  adAlist = response.body();
                    adminacconadapter = new Adminacconadapter(AdminAnnoucement.this, adAlist);
                    recyclerViewAN.setAdapter(adminacconadapter);

                }

            }
            @Override
            public void onFailure(Call<List<ADAlist>> call, Throwable t) {
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
        inflater.inflate(R.menu.annou, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // return super.onCreateOptionsMenu(menu);
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Get response form student protal
        int id = item.getItemId();
        if(id == R.id.adda){
            builder =new AlertDialog.Builder(AdminAnnoucement.this,R.style.MyDialogTheme);
            View ComView = getLayoutInflater().inflate(R.layout.addnotice,null);
            EditText inputTextS1 = (EditText)ComView.findViewById(R.id.editTextTextPersonName8);
            EditText inputTextS2 = (EditText)ComView.findViewById(R.id.editTextTextPersonName7);
            EditText inputTextS3 = (EditText)ComView.findViewById(R.id.editTextTextMultiLine);
            Button btn_add = (Button)ComView.findViewById(R.id.button3);

            builder.setTitle("NEW Notice");
            //Creating dialog box
            AlertDialog alert = builder.create();
            alert.setView(ComView);
            //Setting the title manually
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (inputTextS1.getText().toString().isEmpty() || inputTextS2.getText().toString().isEmpty() ||
                            inputTextS3.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Fill Require Fields", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        HashMap<String, String> hashMapNote = new HashMap<>();
                        hashMapNote.put("annocid", inputTextS1.getText().toString());
                        hashMapNote.put("date", inputTextS2.getText().toString());
                        hashMapNote.put("note", inputTextS3.getText().toString());

                        Call<Void> call = retrofitInterface.newnote(hashMapNote);

                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {


                                if (response.code() == 201) {
                                    Toast.makeText(AdminAnnoucement.this,"Submitted Sucessfull",
                                            Toast.LENGTH_LONG).show();

                                } else if(response.code() == 400) {
                                    Toast.makeText(AdminAnnoucement.this,"That Notice No already exisits!", Toast.LENGTH_LONG).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(AdminAnnoucement.this, t.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                }
            });
            alert.show();
        }
        if(id == R.id.upaad){
            builder =new AlertDialog.Builder(AdminAnnoucement.this,R.style.MyDialogTheme);
            View ComView = getLayoutInflater().inflate(R.layout.updatenotice,null);
            EditText inputTexta1 = (EditText)ComView.findViewById(R.id.UeditTextTextPersonName8);
            EditText inputTexta2 = (EditText)ComView.findViewById(R.id.UeditTextTextPersonName7);
            EditText inputTexta3 = (EditText)ComView.findViewById(R.id.UeditTextTextMultiLine);
            Button btn_up = (Button)ComView.findViewById(R.id.button3UUU);

            builder.setTitle("Update Notice");
            //Creating dialog box
            AlertDialog alert = builder.create();
            alert.setView(ComView);
            //Setting the title manually
            btn_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (inputTexta1.getText().toString().isEmpty() || inputTexta2.getText().toString().isEmpty() ||
                    inputTexta3.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Fill Require Fields", Toast.LENGTH_SHORT).show();
                    }
                    HashMap<String, String> hashMapUpNote = new HashMap<>();
                    hashMapUpNote.put("date", inputTexta2.getText().toString());
                    hashMapUpNote.put("note", inputTexta3.getText().toString());
                    String notno = inputTexta1.getText().toString();
                    Call<Void> call = retrofitInterface.updatenotice(notno,hashMapUpNote);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 201) {
                                Toast.makeText(AdminAnnoucement.this,"Update Sucessfully",
                                        Toast.LENGTH_LONG).show();

                            }
                            else if(response.code() == 401) {
                                Toast.makeText(AdminAnnoucement.this,"Wrong Notice No", Toast.LENGTH_LONG).show();
                            }
                            else if(response.code() == 500) {
                                Toast.makeText(AdminAnnoucement.this,"Server related error", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(AdminAnnoucement.this, t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                       }

            });
            alert.show();
        }
       if(id == R.id.rmann){
           builder =new AlertDialog.Builder(AdminAnnoucement.this,R.style.MyDialogTheme);
           View ComView = getLayoutInflater().inflate(R.layout.delnotice,null);
           EditText inputText = (EditText)ComView.findViewById(R.id.DeditTextTextPersonName8);
           Button btn_cancel = (Button)ComView.findViewById(R.id.Ucancel);
           Button btn_del = (Button)ComView.findViewById(R.id.button3D);
           CheckBox proce =(CheckBox)ComView.findViewById(R.id.DcheckBox);
           builder.setTitle("Delete Notice");
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
                   if (inputText.getText().toString().isEmpty()){
                       Toast.makeText(getApplicationContext(), "Fill Require Fields", Toast.LENGTH_SHORT).show();
                   }else if (proce.isChecked()) {
                       String Nno = inputText.getText().toString();
                       Call<Void> call = retrofitInterface.noticedelete(Nno);

                       call.enqueue(new Callback<Void>() {
                           @Override
                           public void onResponse(Call<Void> call, Response<Void> response) {


                               if (response.code() == 201) {
                                   Toast.makeText(AdminAnnoucement.this, "Deleted Sucessfull",
                                           Toast.LENGTH_LONG).show();

                               } else if (response.code() == 400) {
                                   Toast.makeText(AdminAnnoucement.this, "Error in code server crash", Toast.LENGTH_LONG).show();
                               }
                           }
                           @Override
                           public void onFailure(Call<Void> call, Throwable t) {
                               Toast.makeText(AdminAnnoucement.this, t.getMessage(),
                                       Toast.LENGTH_LONG).show();
                           }
                       }); }

               }
           });
           alert.show();
       }
        return super.onOptionsItemSelected(item);
    }
}