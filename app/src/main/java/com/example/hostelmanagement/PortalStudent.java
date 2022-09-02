package com.example.hostelmanagement;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelmanagement.retrofit.ApiClient;
import com.example.hostelmanagement.retrofit.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PortalStudent extends AppCompatActivity {
    private TextView roll;
    private Button pro, hdetail, pay;
    private RetrofitInterface retrofitInterface;
    String Rollno;
    AlertDialog.Builder builder;
    EditText  stnewpass ,stcofirmpass ;
    Button chanst ,calst ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_portal);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        getSupportActionBar().setTitle("Student Portal");
        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);
        //Get response form main_activiy
         Rollno = getIntent().getStringExtra("rollno");


        pro = (Button) findViewById(R.id.button5);
        hdetail = (Button) findViewById(R.id.button6);
        pay = (Button) findViewById(R.id.button7);

        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ina = new Intent(PortalStudent.this, StudentProfile.class);
                ina.putExtra("Rollno", Rollno);
                startActivity(ina);
            }
        });

        hdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inb = new Intent(PortalStudent.this, HostelDetails.class);
                inb.putExtra("Rollno",Rollno);
                startActivity(inb);
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inc = new Intent(PortalStudent.this, StudentPayment.class);
                inc.putExtra("Rollno", Rollno);
                startActivity(inc);
            }
        });

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PortalStudent.this,R.style.MyDialogTheme);
        alertDialog.setTitle("Exit App");
        alertDialog.setMessage("Do you want to exit app \n Do you want to go home page");
        alertDialog.setPositiveButton("Home", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intt = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intt);
                finish();
            }
        });
        alertDialog.setNeutralButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        alertDialog.setNegativeButton("No",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialog.show();
    }
    //menu of student portal
    @Override
    public boolean onCreateOptionsMenu(Menu menu1) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu, menu1);
        return super.onCreateOptionsMenu(menu1);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sign) {
            Intent ii = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(ii);
            Toast.makeText(getApplicationContext(), "Logout Sucessfully", Toast.LENGTH_SHORT).show();
            finishAffinity();
        }
        if (id == R.id.delete) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(PortalStudent.this,R.style.MyDialogTheme);
            alertDialog.setTitle("Delete Account Confirm");
            alertDialog.setMessage("Do you want to delete your account");
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Call<Void> call = retrofitInterface.executeDelByStudi(Rollno);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {


                            if (response.code() == 201) {
                                Toast.makeText(PortalStudent.this, "Deleted Sucessfull",
                                        Toast.LENGTH_LONG).show();
                                Intent ii = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(ii);
                                finishAffinity();

                            } else if (response.code() == 400) {
                                Toast.makeText(PortalStudent.this, "Error in code server crash", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(PortalStudent.this, t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
            alertDialog.setNegativeButton("No",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            alertDialog.show();
        }
        if(id == R.id.notify){
            Intent iii =new Intent(PortalStudent.this,StudAnnoucment.class);
            startActivity(iii);
        }
        if(id == R.id.chapas){
            builder =new AlertDialog.Builder(PortalStudent.this,R.style.MyDialogTheme);
            builder.setTitle("CHANGE PASSWORD");
            builder.setMessage("Do you want to change your login password");
            //Creating dialog box
            AlertDialog alert = builder.create();
            View ComView = getLayoutInflater().inflate(R.layout.studchpass,null);
            stnewpass = (EditText)ComView.findViewById(R.id.stpass2);
            stcofirmpass = (EditText)ComView.findViewById(R.id.stpass3);
            chanst = (Button) ComView.findViewById(R.id.stchange);
            calst = (Button) ComView.findViewById(R.id.stcancel);
            chanst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(stnewpass.getText().toString().isEmpty() || stcofirmpass.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Plese fillup all requre fields", Toast.LENGTH_SHORT).show();
                    }
                    else if(!stnewpass.getText().toString().equals(stcofirmpass.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                        return;}
                    else{
                        HashMap<String, String> hashMap88 = new HashMap<>();
                        hashMap88.put("password",stcofirmpass.getText().toString());

                        Call<Void> call = retrofitInterface.updatepassforstud(Rollno,hashMap88);

                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.code() == 201) {
                                    Toast.makeText(PortalStudent.this,"Update Password Sucessfully",
                                            Toast.LENGTH_LONG).show();
                                    alert.dismiss();

                                } else if(response.code() == 500) {
                                    Toast.makeText(PortalStudent.this,"Server related error", Toast.LENGTH_LONG).show();
                                    alert.dismiss();
                                }else if(response.code() == 401) {
                                    Toast.makeText(PortalStudent.this,"Wrong Roll No", Toast.LENGTH_LONG).show();
                                    alert.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(PortalStudent.this, t.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                }

            });
            calst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alert.dismiss();
                }
            });
            alert.setView(ComView);
            alert.show();
        }
            return super.onOptionsItemSelected(item);
    }

}