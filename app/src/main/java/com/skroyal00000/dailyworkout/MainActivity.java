package com.skroyal00000.dailyworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.skroyal00000.dailyworkout.Detail.Detail_intro;
import com.skroyal00000.dailyworkout.ProductPage.ProductView;
import com.skroyal00000.dailyworkout.R;

public class MainActivity extends AppCompatActivity {

    private String gender,level,personName;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            Intent intent = new Intent(MainActivity.this, ProductView.class);
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


