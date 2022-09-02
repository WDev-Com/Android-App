package com.example.hostelmanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hostelmanagement.adapter.Paymentlist;
import com.example.hostelmanagement.adapter.Paymentviewadapter;
import com.example.hostelmanagement.adapter.RecyclerViewAdapter;
import com.example.hostelmanagement.retrofit.ApiClient;
import com.example.hostelmanagement.retrofit.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentPayment extends AppCompatActivity {
    private RecyclerView paymentrecyview;
    private Paymentviewadapter paymentviewadapter;
    private RetrofitInterface retrofitInterface;
    SwipeRefreshLayout swipeRefreshLayoutstpay;
    AlertDialog.Builder builder;
    Button send;
    EditText   amountEt, noteEt,nameEt,upiIdEt;
    final int UPI_PAYMENT = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_payment);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        getSupportActionBar().setTitle("Student Payment");
        /// Create Instences of xml views
        send =  findViewById(R.id.payupi);
        amountEt = findViewById(R.id.amounts);
        noteEt = findViewById(R.id.notes);
        nameEt = findViewById(R.id.names);
        upiIdEt = findViewById(R.id.upis);

        /////Refresher
        swipeRefreshLayoutstpay = findViewById(R.id.swipeforstpay);
        swipeRefreshLayoutstpay.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayoutstpay.setRefreshing(false);
                //your code on swipe refresh
                getpamentinfo();
            }
        });
        swipeRefreshLayoutstpay.setColorSchemeColors(Color.BLACK);
        //retrofit builder
        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        paymentrecyview = findViewById(R.id.paymentrecview);
        paymentrecyview.setHasFixedSize(true);
        paymentrecyview.setLayoutManager(new LinearLayoutManager(this));
        getpamentinfo();
    }

   public void getpamentinfo(){
       //Get response form student protal
       String Rollno = getIntent().getStringExtra("Rollno");
      retrofit2.Call<List<Paymentlist>> call  = retrofitInterface.showpaymentDetails(Rollno);
      call.enqueue(new Callback<List<Paymentlist>>() {
          @Override
          public void onResponse(Call<List<Paymentlist>> call, Response<List<Paymentlist>> response) {
              if(response.isSuccessful()){
                  List<Paymentlist> paymentlists = response.body();
                  paymentviewadapter = new Paymentviewadapter(StudentPayment.this,paymentlists);
                  paymentrecyview.setAdapter(paymentviewadapter);
              }
          }

          @Override
          public void onFailure(Call<List<Paymentlist>> call, Throwable throwable) {
              Log.d("failure",throwable.getLocalizedMessage());
          }
      });

   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu1) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.studpay, menu1);
        return super.onCreateOptionsMenu(menu1);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.paybill) {
            builder =new AlertDialog.Builder(StudentPayment.this,R.style.MyDialogTheme);
            View ComView = getLayoutInflater().inflate(R.layout.paybillcon,null);
         amountEt = (EditText) ComView.findViewById(R.id.amounts);
         upiIdEt = (EditText) ComView.findViewById(R.id.upis);
         nameEt = (EditText) ComView.findViewById(R.id.names);
         noteEt = (EditText) ComView.findViewById(R.id.notes);
         send = (Button) ComView.findViewById(R.id.payupi);
         builder.setTitle("PAY WITH UPI");
            //Creating dialog box
            AlertDialog alert = builder.create();
            alert.setView(ComView);
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Getting the values from the EditTexts
                    String amount = amountEt.getText().toString();
                    String note = noteEt.getText().toString();
                    String name = nameEt.getText().toString();
                    String upiId = upiIdEt.getText().toString();

                    if(name.isEmpty() || upiId.isEmpty()){
                        Toast.makeText(StudentPayment.this, "Name and Upi Id is necessary", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Please use Google Pay it is more comfortable for you", Toast.LENGTH_SHORT).show();
                        payUsingUpi(amount, upiId, name, note);}
                }
            });
            alert.show();
        }
        return super.onOptionsItemSelected(item);
    }
/////////////////////////////// PAYMENT FUNTIONS ////////////////////
private  void payUsingUpi(String amount, String upiId, String name, String note) {

    Uri uri = Uri.parse("upi://pay").buildUpon()
            .appendQueryParameter("pa", upiId)
            .appendQueryParameter("pn", name)
            .appendQueryParameter("tn", note)
            .appendQueryParameter("am", amount)
            .appendQueryParameter("cu", "INR")
            .build();


    Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
    upiPayIntent.setData(uri);

    // will always show a dialog to user to choose an app
    Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");

    // check if intent resolves
    if(null != chooser.resolveActivity(getPackageManager())) {
        startActivityForResult(chooser, UPI_PAYMENT);
    } else {
        Toast.makeText(StudentPayment.this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
    }

}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.d("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.d("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    Log.d("UPI", "onActivityResult: " + "Return data is null"); //when user simply back without payment
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }

    private void upiPaymentDataOperation(ArrayList<String> data) {
        if (isConnectionAvailable(StudentPayment.this)) {
            String str = data.get(0);
            Log.d("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                }
                else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }

            if (status.equals("success")) {
                //Code to handle successful transaction here.
                Toast.makeText(StudentPayment.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
                Log.d("UPI", "responseStr: "+approvalRefNo);
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(StudentPayment.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(StudentPayment.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(StudentPayment.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }


}