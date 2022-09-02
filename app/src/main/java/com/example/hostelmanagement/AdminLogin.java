package com.example.hostelmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.example.hostelmanagement.retrofit.Adminloginresult;
import com.example.hostelmanagement.retrofit.ApiClient;
import com.example.hostelmanagement.retrofit.LoginResult;
import com.example.hostelmanagement.retrofit.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AdminLogin extends AppCompatActivity {
    private TextView textaa;
    private Button buttona;
    private RetrofitInterface retrofitInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        textaa = (TextView) findViewById(R.id.textView7);
        buttona = (Button) findViewById(R.id.button2);

        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);


        //Go to Create Admin Form
        textaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(AdminLogin.this, AdminSignup.class);
                startActivity(in1);
                finish();

            }
        });


        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Adminlogin();
            }
        });
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AdminLogin.this,R.style.MyDialogTheme);
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


    private void Adminlogin(){

        final EditText adminusernameEdit = findViewById(R.id.editTextTextPersonName);
        final EditText  admincodeEdit = findViewById(R.id.editTextTextPersonNamecode);
        final EditText  adminpasswordEdit= findViewById(R.id.editTextTextPassword);

                HashMap<String, String> hashMap3 = new HashMap<>();

                hashMap3.put("adusername", adminusernameEdit.getText().toString());
                hashMap3.put("admincode", admincodeEdit.getText().toString());
                hashMap3.put("adminpassword", adminpasswordEdit.getText().toString());

                retrofit2.Call<Adminloginresult> call = retrofitInterface.executeAdminlogin(hashMap3);

                call.enqueue(new Callback<Adminloginresult>() {
                    @Override
                    public void onResponse(retrofit2.Call<Adminloginresult> call, Response<Adminloginresult> response) {
                        if (response.code() == 200) {
                            Adminloginresult result = response.body();
                            result.getAdminusername();
                            result.getAdmincode();
                            result.getAdminpassword();
                            Toast.makeText(AdminLogin.this, "Login Sucessfull",
                                    Toast.LENGTH_LONG).show();
                            String code = admincodeEdit.getText().toString();
                            //Go to admin portal
                            Intent in2 = new Intent(AdminLogin.this, AdminPortal.class);
                            in2.putExtra("adcode",code);
                            startActivity(in2);
                            finish();
                        } else if (response.code() == 404) {
                            Toast.makeText(AdminLogin.this, "Wrong Credentials",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(retrofit2.Call<Adminloginresult> call, Throwable t) {
                        Toast.makeText(AdminLogin.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

}

