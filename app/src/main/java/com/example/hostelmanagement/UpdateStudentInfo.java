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

public class UpdateStudentInfo extends AppCompatActivity {
    private RetrofitInterface retrofitInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student_info);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        getSupportActionBar().setTitle("Student Information Update");

        String Roll = getIntent().getStringExtra("Rollno");
        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);
        final EditText name = findViewById(R.id.editTextTextPersonNameu9);
        final EditText rollno = findViewById(R.id.editTextTextPersonNameu10);
        final EditText adharno = findViewById(R.id.editTextTextPersonNameu11);
        final EditText address = findViewById(R.id.editTextTextPersonNameu12);
        final EditText contact = findViewById(R.id.editTextTextPersonNameu13);
        final EditText email = findViewById(R.id.editTextTextPersonNameu14);
        final EditText gender = findViewById(R.id.editTextTextPersonNameu15);
        final EditText date = findViewById(R.id.editTextDateu2);
        findViewById(R.id.buttonu3).setOnClickListener(new View.OnClickListener() {
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
                    updatestudinfo(Roll);
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(UpdateStudentInfo.this,R.style.MyDialogTheme);
        alertDialog.setTitle("Update infomation Complete Plese !");
        alertDialog.setMessage("Do you don't want to update infomation");
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


    public void updatestudinfo(String Roll){
        String Rolld = getIntent().getStringExtra("Rollno");
        final EditText name = findViewById(R.id.editTextTextPersonNameu9);
        final EditText rollno = findViewById(R.id.editTextTextPersonNameu10);
        final EditText adharno = findViewById(R.id.editTextTextPersonNameu11);
        final EditText address = findViewById(R.id.editTextTextPersonNameu12);
        final EditText contact = findViewById(R.id.editTextTextPersonNameu13);
        final EditText email = findViewById(R.id.editTextTextPersonNameu14);
        final EditText gender = findViewById(R.id.editTextTextPersonNameu15);
        final EditText date = findViewById(R.id.editTextDateu2);

        HashMap<String, String> hashMap4 = new HashMap<>();

                hashMap4.put("name", name.getText().toString());
                hashMap4.put("rollno", rollno.getText().toString());
                hashMap4.put("adharno", adharno.getText().toString());
                hashMap4.put("address", address.getText().toString());
                hashMap4.put("contact", contact.getText().toString());
                hashMap4.put("email", email.getText().toString());
                hashMap4.put("gender", gender.getText().toString());
                hashMap4.put("date", date.getText().toString());

                Call<Void> call = retrofitInterface.executeUpdatestudinfo(Rolld,hashMap4);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {
                            Toast.makeText(UpdateStudentInfo.this,"Update Sucessfully",
                                    Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(),PortalStudent.class);
                            startActivity(i);

                        } else if(response.code() == 500) {
                            Toast.makeText(UpdateStudentInfo.this,"Server related error", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(UpdateStudentInfo.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

    }
}