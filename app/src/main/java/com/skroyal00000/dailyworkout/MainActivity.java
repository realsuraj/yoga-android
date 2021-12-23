package com.skroyal00000.dailyworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.skroyal00000.dailyworkout.R;

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


