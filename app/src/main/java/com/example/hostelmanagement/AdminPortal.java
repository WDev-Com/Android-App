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
import android.widget.Toast;

import com.example.hostelmanagement.retrofit.ApiClient;
import com.example.hostelmanagement.retrofit.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPortal extends AppCompatActivity {
    private Button SData,SAllot,PGerate,Pdata,hddata, announc;
    private RetrofitInterface retrofitInterface;
    String Adcode;
    AlertDialog.Builder builder;

    EditText  newpass ,cofirmpass ;
    Button chan ,caln ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_portal);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        getSupportActionBar().setTitle("Admin Portal");
        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        //Get response form main_activiy
        Adcode = getIntent().getStringExtra("adcode");

        SData = (Button) findViewById(R.id.button10);
        SAllot = (Button) findViewById(R.id.button11);
        PGerate = (Button) findViewById(R.id.button12);
        Pdata = (Button) findViewById(R.id.paydt);
        hddata = (Button)findViewById(R.id.hosdt) ;
        announc = (Button) findViewById(R.id.anouce);
        //student database of admin portal
        SData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ina = new Intent(AdminPortal.this, StudentDatabase.class);
                startActivity(ina);
            }
        });
        //hostel details of student portal
        SAllot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inb = new Intent(AdminPortal.this, HostelAllotment.class);
                startActivity(inb);
            }
        });

        PGerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inc = new Intent(AdminPortal.this, PaymentGenration.class);
                startActivity(inc);
            }
        });
        Pdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ins = new Intent(AdminPortal.this, PaymentDatabase.class);
                startActivity(ins);
            }
        });
        hddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ind = new Intent(AdminPortal.this, HostelDatabase.class);
                startActivity(ind);
            }
        });
        announc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iin = new Intent(AdminPortal.this,AdminAnnoucement.class);
                startActivity(iin);
            }
        });
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AdminPortal.this,R.style.MyDialogTheme);
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
    public boolean onCreateOptionsMenu(Menu menu2) {
        MenuInflater m= getMenuInflater();
        m.inflate(R.menu.menu3,menu2);
        return super.onCreateOptionsMenu(menu2);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sign2) {
            Intent ii = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(ii);
            Toast.makeText(getApplicationContext(), "Logout Sucessfully", Toast.LENGTH_SHORT).show();
            finishAffinity();
        }
        if (id == R.id.delete2) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AdminPortal.this,R.style.MyDialogTheme);
            alertDialog.setTitle("Delete Account Confirm");
            alertDialog.setMessage("Do you want to delete your account");
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Call<Void> call = retrofitInterface.executeDelByadmin(Adcode);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {


                            if (response.code() == 201) {
                                Toast.makeText(AdminPortal.this, "Deleted Sucessfull",
                                        Toast.LENGTH_LONG).show();
                                Intent ii = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(ii);
                                finishAffinity();

                            } else if (response.code() == 400) {
                                Toast.makeText(AdminPortal.this, "Error in code server crash", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(AdminPortal.this, t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            alertDialog.show();
        }
        if(id == R.id.chpass){
            builder =new AlertDialog.Builder(AdminPortal.this,R.style.MyDialogTheme);
            builder.setTitle("CHANGE PASSWORD");
            builder.setMessage("Do you want to change your login password");
            //Creating dialog box
            AlertDialog alert = builder.create();
            View ComView = getLayoutInflater().inflate(R.layout.adminchpass,null);
           newpass = (EditText)ComView.findViewById(R.id.pass2);
           cofirmpass = (EditText)ComView.findViewById(R.id.pass3);
           chan = (Button) ComView.findViewById(R.id.adchange);
           caln = (Button) ComView.findViewById(R.id.adcancel);
           chan.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if(newpass.getText().toString().isEmpty() || cofirmpass.getText().toString().isEmpty()){
                       Toast.makeText(getApplicationContext(), "Plese fillup all requre fields", Toast.LENGTH_SHORT).show();
                   }
                  else if(!newpass.getText().toString().equals(cofirmpass.getText().toString())){
                       Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                      return;}
                   else{
                       HashMap<String, String> hashMap55 = new HashMap<>();
                       hashMap55.put("adminpassword",cofirmpass.getText().toString());

                       Call<Void> call = retrofitInterface.updatepassfor(Adcode,hashMap55);

                       call.enqueue(new Callback<Void>() {
                           @Override
                           public void onResponse(Call<Void> call, Response<Void> response) {
                               if (response.code() == 201) {
                                   Toast.makeText(AdminPortal.this,"Update Password Sucessfully",
                                           Toast.LENGTH_LONG).show();
                                   alert.dismiss();

                               } else if(response.code() == 500) {
                                   Toast.makeText(AdminPortal.this,"Server related error", Toast.LENGTH_LONG).show();
                                   alert.dismiss();
                               }else if(response.code() == 401) {
                                   Toast.makeText(AdminPortal.this,"Wrong admin code", Toast.LENGTH_LONG).show();
                                   alert.dismiss();
                               }
                           }

                           @Override
                           public void onFailure(Call<Void> call, Throwable t) {
                               Toast.makeText(AdminPortal.this, t.getMessage(),
                                       Toast.LENGTH_LONG).show();
                           }
                       });

                   }
               }

           });
           caln.setOnClickListener(new View.OnClickListener() {
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