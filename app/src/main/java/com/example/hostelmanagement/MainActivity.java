package com.example.hostelmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;

//Retrofit
import android.widget.Toast;

import com.example.hostelmanagement.adapter.Adimlist;
import com.example.hostelmanagement.adapter.Studentdatalist;
import com.example.hostelmanagement.retrofit.ApiClient;
import com.example.hostelmanagement.retrofit.LoginResult;
import com.example.hostelmanagement.retrofit.RetrofitInterface;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {
    private TextView texta, textb;
    private Button b1;
    AlertDialog.Builder builder;
    TextView adname,adcode,adaddress, adcont, ademail;

    private RetrofitInterface retrofitInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        texta = (TextView) findViewById(R.id.textView4);

        textb = (TextView) findViewById(R.id.textView2);

        b1 = (Button) findViewById(R.id.button);

        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);


        final EditText usernameEdit = findViewById(R.id.editTextTextPersonName);
        final EditText passwordEdit = findViewById(R.id.editTextTextPassword);

        //Go to admin Activity
        texta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(MainActivity.this, AdminLogin.class);
                startActivity(in1);
                finish();
            }
        });
        //Go to signup form
        textb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2 = new Intent(MainActivity.this, StudentSignup.class);
                startActivity(in2);
                finish();
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
                //Go to student portal
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this,R.style.MyDialogTheme);
        alertDialog.setTitle("Exit App");
        alertDialog.setMessage("Do you want to exit app");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
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

    private void Login(){
            final EditText usernameEdit = findViewById(R.id.editTextTextPersonName);
            final EditText rollnoEdit = findViewById(R.id.rollnum);
            final EditText passwordEdit = findViewById(R.id.editTextTextPassword);

                    HashMap<String, String> map = new HashMap<>();

                    map.put("username", usernameEdit.getText().toString());
                    map.put("rollno", rollnoEdit.getText().toString());
                    map.put("password", passwordEdit.getText().toString());

                    retrofit2.Call<LoginResult> call = retrofitInterface.executeLogin(map);

                    call.enqueue(new Callback<LoginResult>() {
                        @Override
                        public void onResponse(retrofit2.Call<LoginResult> call, Response<LoginResult> response) {
                            if (response.code() == 200) {
                                LoginResult result = response.body();
                                result.getUsername();
                                result.getRollno();
                                result.getPassword();
                                Toast.makeText(MainActivity.this, "Login Sucessfull",
                                        Toast.LENGTH_LONG).show();
                                String rollnoa = rollnoEdit.getText().toString();
                                //Go to student portal
                                Intent intent = new Intent(MainActivity.this, PortalStudent.class);
                                intent.putExtra("rollno",rollnoa);
                                startActivity(intent);
                                finish();
                            } else if (response.code() == 404) {
                                Toast.makeText(MainActivity.this, "Wrong Credentials",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onFailure(retrofit2.Call<LoginResult> call, Throwable t) {
                            Toast.makeText(MainActivity.this, t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m= getMenuInflater();
        m.inflate(R.menu.info,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.infoo){
            builder =new AlertDialog.Builder(MainActivity.this,R.style.MyDio);
            //Creating dialog box
            AlertDialog alert = builder.create();
            View ComView = getLayoutInflater().inflate(R.layout.adminifo,null);
            adname = (TextView) ComView.findViewById(R.id.textViewa);
            adcode = (TextView) ComView.findViewById(R.id.textViewa3);
            adaddress = (TextView)ComView.findViewById(R.id.textViewa5);
            adcont = (TextView)ComView.findViewById(R.id.textViewa6);
            ademail = (TextView)ComView.findViewById(R.id.textViewa8);

            Call<List<Adimlist>> call = retrofitInterface.getAdmininfo();
            call.enqueue(new Callback<List<Adimlist>>() {
                @Override
                public void onResponse(Call<List<Adimlist>> call, Response<List<Adimlist>> response) {

                    List<Adimlist> adimlists = response.body();

                    for (Adimlist adimlist : adimlists) {
                        adname.setText(adimlist.getadminname());
                        adcode.setText(adimlist.getadmincode());
                        adaddress.setText(adimlist.getadminaddress());
                        adcont.setText(adimlist.getadmincontact());
                        ademail.setText(adimlist.getadminemail());
                    }
                }
                @Override
                public void onFailure(Call<List<Adimlist>> call, Throwable t) {
                    Log.d("failure",t.getLocalizedMessage());
                }
            });
            alert.setView(ComView);
            alert.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
