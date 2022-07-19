package com.skroyal00000.dailyworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.annotations.Nullable;
import com.skroyal00000.dailyworkout.Detail.Detail_intro;

public class LoginPage extends AppCompatActivity {


    EditText username,password;
    Button signBtn;
    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

       username = findViewById(R.id.username);
       password = findViewById(R.id.password);
       signBtn = findViewById(R.id.signInBtn);
       signUp = findViewById(R.id.signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this,Register.class);
                startActivity(intent);
            }
        });
       signBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              String stringUserName = username.getText().toString();
              String StringPassword = password.getText().toString();



           }
       });



    }




}