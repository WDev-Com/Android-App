package com.example.hostelmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

//import android.content.Intent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.hostelmanagement.retrofit.*;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Stud_registration extends AppCompatActivity {
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_registration);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        getSupportActionBar().setTitle("Student Registration");

        final EditText name = findViewById(R.id.editTextTextPersonNameE9);
        final EditText rollno = findViewById(R.id.editTextTextPersonNameE10);
        final EditText adharno = findViewById(R.id.editTextTextPersonNameE11);
        final EditText address = findViewById(R.id.editTextTextPersonNameE12);
        final EditText contact = findViewById(R.id.editTextTextPersonNameE13);
        final EditText email = findViewById(R.id.editTextTextPersonNameE14);
        final EditText gender = findViewById(R.id.editTextTextPersonNameE15);
        final EditText date = findViewById(R.id.editTextDateE2);
        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        findViewById(R.id.buttonE3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().isEmpty() && rollno.getText().toString().isEmpty() &&
                        adharno.getText().toString().isEmpty() && address.getText().toString().isEmpty() &&
                        contact.getText().toString().isEmpty() &&
                        email.getText().toString().isEmpty() && gender.getText().toString().isEmpty() &&
                        date.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill all credentials", Toast.LENGTH_SHORT).show();
                }
                else if(name.getText().toString().isEmpty() || rollno.getText().toString().isEmpty() ||
                        adharno.getText().toString().isEmpty() || address.getText().toString().isEmpty() ||
                        contact.getText().toString().isEmpty() ||
                        email.getText().toString().isEmpty() || gender.getText().toString().isEmpty() ||
                        date.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill all credentials", Toast.LENGTH_SHORT).show();
                }
                else {
                    Register();
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Stud_registration.this,R.style.MyDialogTheme);
        alertDialog.setTitle("Registration Complete Plese !");
        alertDialog.setMessage("Do you don't want to register");
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


    private void Register() {
        final EditText name = findViewById(R.id.editTextTextPersonNameE9);
        final EditText rollno = findViewById(R.id.editTextTextPersonNameE10);
        final EditText adharno = findViewById(R.id.editTextTextPersonNameE11);
        final EditText address = findViewById(R.id.editTextTextPersonNameE12);
        final EditText contact = findViewById(R.id.editTextTextPersonNameE13);
        final EditText email = findViewById(R.id.editTextTextPersonNameE14);
        final EditText gender = findViewById(R.id.editTextTextPersonNameE15);
        final EditText date = findViewById(R.id.editTextDateE2);


        HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put("name", name.getText().toString());
                hashMap.put("rollno", rollno.getText().toString());
                hashMap.put("adharno", adharno.getText().toString());
                hashMap.put("address", address.getText().toString());
                hashMap.put("contact", contact.getText().toString());
                hashMap.put("email", email.getText().toString());
                hashMap.put("gender", gender.getText().toString());
                hashMap.put("date", date.getText().toString());

                Call<Void> call = retrofitInterface.executeRegister(hashMap);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {


                        if (response.code() == 201) {
                            Toast.makeText(Stud_registration.this,"Signup Sucessfull",
                                    Toast.LENGTH_LONG).show();

                            //Go to student registration
                            Intent intent = new Intent(Stud_registration.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else if(response.code() == 400) {
                            Toast.makeText(Stud_registration.this,"Already registered", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 401) {
                            Toast.makeText(Stud_registration.this,"That Rollno already exisits!", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 402) {
                            Toast.makeText(Stud_registration.this,"That Adhar No already exisits!", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 403) {
                            Toast.makeText(Stud_registration.this,"That Contact already exisits!", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 404) {
                            Toast.makeText(Stud_registration.this,"That Email already exisits!", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 405) {
                            Toast.makeText(Stud_registration.this,"That email is invalid", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Stud_registration.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

}