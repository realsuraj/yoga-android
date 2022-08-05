package com.skroyal00000.dailyworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private String gender,level,personName;

    static {
        System.loadLibrary("keys");
    }

    public native String getApiHomeData();
    public native String getApiSignIn();
    public native String getApiShowSingleData();
    public native String getApiUserUpdateData();
    public native String getApiUserShowExercise();
    public native String getApiUserShowDiet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (PrefConfig.loadUserEmail(MainActivity.this) != null) {
            Intent intent = new Intent(MainActivity.this, HomePage.class);
            startActivity(intent);
            finish();
        }
       else {
           Intent intent = new Intent(MainActivity.this,LoginPage.class);
           startActivity(intent);
           finish();
       }

    }
    }


