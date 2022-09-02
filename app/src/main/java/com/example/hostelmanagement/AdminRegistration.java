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


public class AdminRegistration extends AppCompatActivity {
    private Button butal;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registration);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        getSupportActionBar().setTitle("Admin Registration");

        final EditText iname = findViewById(R.id.editTextTextPersonName2);
        final EditText  icode = findViewById(R.id.editTextTextPersonName3);
        final EditText  iaddress = findViewById(R.id.editTextTextPersonName4);
        final EditText  icontact = findViewById(R.id.editTextTextPersonName5);
        final EditText  iemail = findViewById(R.id.editTextTextPersonName6);
        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(iname.getText().toString().isEmpty() && icode.getText().toString().isEmpty() &&
                   iaddress.getText().toString().isEmpty() && icontact.getText().toString().isEmpty() &&
                        iemail.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill all credentials", Toast.LENGTH_SHORT).show();
                }
                else if(iname.getText().toString().isEmpty() || icode.getText().toString().isEmpty() ||
                        iaddress.getText().toString().isEmpty() || icontact.getText().toString().isEmpty() ||
                        iemail.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill all credentials", Toast.LENGTH_SHORT).show();
                }
                else {
                    Adminsignup();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AdminRegistration.this,R.style.MyDialogTheme);
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


    private void Adminsignup() {
        butal=findViewById(R.id.button4);
        final EditText iname = findViewById(R.id.editTextTextPersonName2);
        final EditText  icode = findViewById(R.id.editTextTextPersonName3);
        final EditText  iaddress = findViewById(R.id.editTextTextPersonName4);
        final EditText  icontact = findViewById(R.id.editTextTextPersonName5);
        final EditText  iemail = findViewById(R.id.editTextTextPersonName6);

                HashMap<String, String> hashMap2 = new HashMap<>();

                hashMap2.put("adminname", iname.getText().toString());
                hashMap2.put("admincode", icode.getText().toString());
                hashMap2.put("adminaddress", iaddress.getText().toString());
                hashMap2.put("admincontact", icontact.getText().toString());
                hashMap2.put("adminemail", iemail.getText().toString());


                Call<Void> call = retrofitInterface.executeAdminregister(hashMap2);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {


                        if (response.code() == 201) {
                            Toast.makeText(AdminRegistration.this,"Created Admin Sucessfull",
                                    Toast.LENGTH_LONG).show();
                            //Go to student registration
                            Intent intent1 = new Intent(AdminRegistration.this, AdminLogin.class);
                            startActivity(intent1);
                            finish();

                        } else if(response.code() == 400) {
                            Toast.makeText(AdminRegistration.this,"Already Created", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 401) {
                            Toast.makeText(AdminRegistration.this,"That Admin Username already exisits!", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 402) {
                            Toast.makeText(AdminRegistration.this,"That Admincode already exisits!", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 403) {
                            Toast.makeText(AdminRegistration.this,"That Admin Contact already exisits!", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 404) {
                            Toast.makeText(AdminRegistration.this,"That Admin Email already exisits!", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 405) {
                            Toast.makeText(AdminRegistration.this,"That email is invalid", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(AdminRegistration.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }


}
