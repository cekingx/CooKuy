package id.dirga.cookuydirga.Activity;


import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import id.dirga.cookuydirga.R;

public class SplashActivity extends AppCompatActivity {
    private static int Splash_Time_Out = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent menuIntent;
                menuIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(menuIntent);
                finish();
            }
        },Splash_Time_Out);
    }
}