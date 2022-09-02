package com.example.hostelmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

public class AdminSignup extends AppCompatActivity {
    private RetrofitInterface retrofitInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        final EditText usernameEdit = findViewById(R.id.editTextTextPersonNameaa16);
        final EditText codeEdit = findViewById(R.id.codesig);
        final EditText enadpass = findViewById(R.id.adpass);
        final EditText conadpass = findViewById(R.id.copass);
        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        findViewById(R.id.buttonad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernameEdit.getText().toString().isEmpty() ||
                        codeEdit.getText().toString().isEmpty()  ||
                        enadpass.getText().toString().isEmpty() ||
                        conadpass.getText().toString().isEmpty()
                ){
                    Toast.makeText(getApplicationContext(), "Please fill all credentials", Toast.LENGTH_SHORT).show();
                }   // check if both password matches
                if(!enadpass.getText().toString().equals(conadpass.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    Adminsignup();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AdminSignup.this,R.style.MyDialogTheme);
        alertDialog.setTitle("Signup Complete Plese !");
        alertDialog.setMessage("Do you don't want to signup");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent in = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);
                finish();
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

    private void Adminsignup() {
        final EditText usernameEdit = findViewById(R.id.editTextTextPersonNameaa16);
        final EditText codeEdit = findViewById(R.id.codesig);
        final EditText passwordEdit = findViewById(R.id.copass);

        HashMap<String, String> mapa = new HashMap<>();

                mapa.put("adusername", usernameEdit.getText().toString());
                mapa.put("admincode", codeEdit.getText().toString());
                mapa.put("adminpassword", passwordEdit.getText().toString());

                Call<Void> call = retrofitInterface.executeAdminsignup(mapa);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {


                        if (response.code() == 201) {
                            Toast.makeText(AdminSignup.this,"Signup Sucessfull",
                                    Toast.LENGTH_LONG).show();
                            //Go to student registration
                            Intent intent1 = new Intent(AdminSignup.this, AdminRegistration.class);
                            startActivity(intent1);
                            finish();

                        } else if(response.code() == 400) {
                            Toast.makeText(AdminSignup.this,"Already registered", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 401) {
                            Toast.makeText(AdminSignup.this,"That Admin Username already exisits!", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 402) {
                            Toast.makeText(AdminSignup.this, "That Admincode already exisits!", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 403) {
                            Toast.makeText(AdminSignup.this, "Non-Authorise Admin Registration", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(AdminSignup.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}