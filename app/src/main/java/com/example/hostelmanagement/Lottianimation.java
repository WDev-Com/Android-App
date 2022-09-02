package com.example.hostelmanagement;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;

public class Lottianimation extends AppCompatActivity {
    LottieAnimationView lotte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottianimation);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        lotte=findViewById(R.id.animationView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        },2000);

    }
}