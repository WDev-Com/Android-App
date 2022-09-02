package com.example.hostelmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hostelmanagement.retrofit.*;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentSignup extends AppCompatActivity {
    private RetrofitInterface retrofitInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        getSupportActionBar().setTitle("Student Signup");

        final EditText usernameEdit = findViewById(R.id.editTextTextPersonName16);
        final EditText rollnoEdit = findViewById(R.id.rollsig);
        final EditText passwordEdit= findViewById(R.id.passv);
        final EditText conpasswordEdit = findViewById(R.id.editTextTextPersonName17);
        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernameEdit.getText().toString().isEmpty() ||
                        rollnoEdit.getText().toString().isEmpty()  ||  passwordEdit.getText().toString().isEmpty() ||
                        conpasswordEdit.getText().toString().isEmpty()
                ){ Toast.makeText(getApplicationContext(), "Please fill all credentials", Toast.LENGTH_SHORT).show();
                }
                // check if both password matches
                if(!passwordEdit.getText().toString().equals(conpasswordEdit.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    Signup();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(StudentSignup.this,R.style.MyDialogTheme);
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


    private void Signup() {
        final EditText usernameEdit = findViewById(R.id.editTextTextPersonName16);
        final EditText rollnoEdit = findViewById(R.id.rollsig);
        final EditText passwordEdit = findViewById(R.id.editTextTextPersonName17);

        HashMap<String, String> map = new HashMap<>();

                map.put("username", usernameEdit.getText().toString());
                map.put("rollno", rollnoEdit.getText().toString());
                map.put("password", passwordEdit.getText().toString());

                Call<Void> call = retrofitInterface.executeSignup(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {


                        if (response.code() == 201) {
                            Toast.makeText(StudentSignup.this, "Signup Sucessfull",
                                    Toast.LENGTH_LONG).show();
                            //Go to student registration
                            Intent intent1 = new Intent(StudentSignup.this, Stud_registration.class);
                            startActivity(intent1);
                            finish();

                        }else if(response.code() == 401) {
                            Toast.makeText(StudentSignup.this,"Already registered", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 402) {
                            Toast.makeText(StudentSignup.this,"Username Already Exist", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 403) {
                            Toast.makeText(StudentSignup.this,"Roll no Already Exist", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(StudentSignup.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
}




