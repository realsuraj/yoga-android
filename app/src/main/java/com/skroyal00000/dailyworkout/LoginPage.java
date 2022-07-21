package com.skroyal00000.dailyworkout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.skroyal00000.dailyworkout.Detail.Detail_intro;
import com.skroyal00000.dailyworkout.Utils.LinkApi;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends AppCompatActivity {


    EditText username,password;
    Button signBtn;
    TextView signUp;
    String stringUserName;
    String stringPassword;
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
         stringUserName = username.getText().toString();
         stringPassword = password.getText().toString();


       signBtn.setOnClickListener(new View.OnClickListener() {

           final LinkApi linkApi = new LinkApi();
           String url;
           @Override
           public void onClick(View v) {
             url = linkApi.signInApi;

               if(username.getText().toString().equalsIgnoreCase("") || password.getText().toString().equalsIgnoreCase("")){
                   Toast.makeText(getApplicationContext(), "Enter username or password", Toast.LENGTH_SHORT).show();
               }
               else{
                   stringUserName = username.getText().toString().trim();
                   stringPassword = password.getText().toString().trim();

                   StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                       @Override
                       public void onResponse(String response) {
                           Toast.makeText(LoginPage.this, response, Toast.LENGTH_SHORT).show();
                           if(response.equalsIgnoreCase("success")){
                               Intent intent = new Intent(getApplicationContext(),HomePage.class);
                               startActivity(intent);
                           }
                       }
                   }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           Toast.makeText(LoginPage.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                       }
                   }){
                       @Nullable
                       @Override
                       protected Map<String, String> getParams() throws AuthFailureError {
                           Map<String,String> params = new HashMap<String,String>();
                           params.put("userName",stringUserName);
                           params.put("password",stringPassword);
                           return params;
                       }
                   };

                   RequestQueue requestQueue = Volley.newRequestQueue(LoginPage.this);
                   requestQueue.add(request);

               }


           }
       });



    }




}