package com.skroyal00000.dailyworkout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.skroyal00000.dailyworkout.Utils.LinkApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ShopBuy extends AppCompatActivity {
    String id ,whichTable;
    TextView title, miniTitle1, miniTitle2,description;
    ImageView showAllImage;
    String stringShopAllImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_buy);

        title = findViewById(R.id.showAllTitle);
        miniTitle1 = findViewById(R.id.showAllBuy);
        miniTitle2 = findViewById(R.id.showAllWebsite);
        description = findViewById(R.id.showAllDescription);
        showAllImage = findViewById(R.id.shopAllImage);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        whichTable = intent.getStringExtra("whichT");
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
                        stringShopAllImage = jsonObject.getString("image");
                        description.setText((jsonObject.getString("description")));
                        GetExtraData(jsonObject);
                        Glide.with(ShopBuy.this).load(stringShopAllImage).fitCenter().
                                placeholder(R.drawable.progess_bar).
                                diskCacheStrategy(DiskCacheStrategy.ALL).into(showAllImage);
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
            @NonNull
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

    public void GetExtraData(JSONObject jsonObject) throws JSONException {
        if(whichTable.equalsIgnoreCase("trainer")){
            miniTitle1.setText(jsonObject.getString("price"));
            miniTitle2.setText(jsonObject.getString("rating"));
        }else if(whichTable.equalsIgnoreCase("shop")){
            miniTitle1.setText(jsonObject.getString("price"));
            miniTitle2.setText(jsonObject.getString("which_website"));
        }else if(whichTable.equalsIgnoreCase("gym")){
            miniTitle1.setText(jsonObject.getString("price"));
            miniTitle2.setText(jsonObject.getString("location"));
            }
    }
}