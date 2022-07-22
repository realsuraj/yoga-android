package com.skroyal00000.dailyworkout.ProductPage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skroyal00000.dailyworkout.Home.ChildItem;
import com.skroyal00000.dailyworkout.ProductPage.Model.ShopChildItem;
import com.skroyal00000.dailyworkout.ProductPage.ViewHolder.ShopAdapder;
import com.skroyal00000.dailyworkout.R;
import com.skroyal00000.dailyworkout.Utils.LinkApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);


        recyclerView = findViewById(R.id.shopPRecyclerView);

        childItemList = new ArrayList<>();
        adapter = new ShopAdapder(getApplicationContext(),childItemList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        getData();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           getting volley data
     private void getData() {
        String whichT = "Gym Shop";
        LinkApi linkApi = new LinkApi();
        String url = linkApi.shopData;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
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
                        shopChildItem.setTitle(jsonObject.getString("title"));
                        shopChildItem.setImage(jsonObject.getString("image"));
                        shopChildItem.setWebsite(jsonObject.getString("website"));
                        shopChildItem.setBuy(jsonObject.getString("buy"));
                        shopChildItem.setShopMiniIcon1(jsonObject.getString("shopMiniIcon1"));
                        shopChildItem.setShopMiniIcon2(jsonObject.getString("shopMiniIcon2"));

                        childItemList.add(shopChildItem);
                    }
                    adapter.notifyDataSetChanged();
                    progressDialog.dismiss();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("TAG", "Error : " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

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