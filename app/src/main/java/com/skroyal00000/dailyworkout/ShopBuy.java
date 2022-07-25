package com.skroyal00000.dailyworkout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.skroyal00000.dailyworkout.Home.ChildItem;
import com.skroyal00000.dailyworkout.Utils.LinkApi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopBuy extends AppCompatActivity {
    String id ,whichTable;
    TextView title, miniTitle1, miniTitle2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_buy);

        title = findViewById(R.id.showAllTitle);
        miniTitle1 = findViewById(R.id.showAllBuy);
        miniTitle1 = findViewById(R.id.showAllWebsite);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        whichTable = intent.getStringExtra("whichT");

        Toast.makeText(getApplicationContext(), "" + id + whichTable, Toast.LENGTH_LONG).show();
        getData();

    }

    private void getData(){
        LinkApi linkApi = new LinkApi() ;
        String url = linkApi.showSingleData;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject childObj = new JSONObject(response);
                    JSONArray result = childObj.getJSONArray("result");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jsonObject = result.getJSONObject(i);
                        title.setText(jsonObject.getString("title"));
                        miniTitle1.setText(jsonObject.getString("miniTitle1"));
                        miniTitle2.setText(jsonObject.getString("miniTitle2"));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("TAG", "Error : " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("whichT",whichTable);
                params.put("id",id);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}