package com.skroyal00000.dailyworkout.ProductPage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.skroyal00000.dailyworkout.PrefConfig;
import com.skroyal00000.dailyworkout.ProductPage.Model.ShopChildItem;
import com.skroyal00000.dailyworkout.ProductPage.ViewHolder.ShopAdapder;
import com.skroyal00000.dailyworkout.R;
import com.skroyal00000.dailyworkout.Utils.LinkApi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductView extends AppCompatActivity{

    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    List<ShopChildItem> childItemList;
    RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ShimmerFrameLayout shimmerFrameLayout;
    public String whichT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        shimmerFrameLayout = findViewById(R.id.shimmerLayout);
        shimmerFrameLayout.startShimmer();
        recyclerView = findViewById(R.id.shopPRecyclerView);
        childItemList = new ArrayList<>();
        adapter = new ShopAdapder(getApplicationContext(),childItemList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        whichT = intent.getStringExtra("whichT");
        PrefConfig.saveTableNameProductView(getApplicationContext(),whichT);
        getData();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           getting volley data
     private void getData() {

        LinkApi linkApi = new LinkApi();
        String url = linkApi.shopData;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject childObj = new JSONObject(response);
                    JSONArray childArray = childObj.getJSONArray("result");
                    for (int i = 0; i < childArray.length(); i++) {
                        JSONObject jsonObject = childArray.getJSONObject(i);
                        ShopChildItem shopChildItem = new ShopChildItem();
                        shopChildItem.setId(jsonObject.getInt("id"));
                        shopChildItem.setTitle(jsonObject.getString("title"));
                        shopChildItem.setImage(jsonObject.getString("image"));
                        shopChildItem.setMiniTitle2(jsonObject.getString("miniTitle1"));
                        shopChildItem.setMiniTitle1(jsonObject.getString("miniTitle2"));
                        shopChildItem.setMiniIcon1(jsonObject.getString("miniIcon1"));
                        shopChildItem.setMiniIcon2(jsonObject.getString("miniIcon2"));
                        childItemList.add(shopChildItem);
                    }
                    adapter.notifyDataSetChanged();
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("TAG", "Error : " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("whichT",whichT);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}