package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String gender,level;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        sharedPreferences = getApplicationContext().getSharedPreferences("storage", Context.MODE_PRIVATE);
        gender = sharedPreferences.getString("gender",null);
        level = sharedPreferences.getString("level",null);

       if(gender != null && level != null){
           Intent intent = new Intent(MainActivity.this,HomePage.class);
           startActivity(intent);
           finish();
       }
       else if(gender == null || level == null){
           Intent intent = new Intent(MainActivity.this,Detail_intro.class);
           startActivity(intent);
           finish();

       }
       else {
           Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
       }





    }
    }


