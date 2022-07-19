package com.skroyal00000.dailyworkout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.skroyal00000.dailyworkout.Utils.LinkApi;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText Rusername,Rpassword;
    Button registerBtn;
    String strUsername,strPassword;
    String url = "https://dailyworkout.co.in/appApi/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Rusername = findViewById(R.id.registerUsername);
        Rpassword = findViewById(R.id.RegisterPassword);
        registerBtn = findViewById(R.id.registerBtn);


    }

    public void RegisterClick(View view){
        if(Rusername.getText().toString().equalsIgnoreCase("") || Rpassword.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(), "Enter username or password", Toast.LENGTH_SHORT).show();
        }
        else{
            strUsername = Rusername.getText().toString().trim();
            strPassword = Rpassword.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(Register.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Register.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String,String>();
                    params.put("userName",strUsername);
                    params.put("password",strPassword);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(Register.this);
            requestQueue.add(request);

        }
    }
}