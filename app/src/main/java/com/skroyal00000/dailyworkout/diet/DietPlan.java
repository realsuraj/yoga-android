package com.skroyal00000.dailyworkout.diet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
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
import com.skroyal00000.dailyworkout.ExerciseListAdapter.Adapter;
import com.skroyal00000.dailyworkout.ExerciseListAdapter.HelperClass;
import com.skroyal00000.dailyworkout.R;
import com.skroyal00000.dailyworkout.Utils.LinkApi;
import com.skroyal00000.dailyworkout.exercise.ExerciseList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DietPlan extends AppCompatActivity {
    private ArrayList<helperForDiet> list;
    private adapterForDiet adapter;
    private RecyclerView recyclerView;
    ShimmerFrameLayout shimmerFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);

        recyclerView = findViewById(R.id.dietRecycler) ;
        shimmerFrameLayout = findViewById(R.id.shimmerLayout);
        list = new ArrayList<>();
        adapter = new adapterForDiet(list,DietPlan.this);
        shimmerFrameLayout.startShimmer();
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        getData();
    }

    private void getData() {
        LinkApi linkApi = new LinkApi();
        String url = linkApi.showDiet;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject childObj = new JSONObject(response);
                    JSONArray childArray = childObj.getJSONArray("result");
                    for (int i = 0; i < childArray.length(); i++) {
                        JSONObject jsonObject = childArray.getJSONObject(i);
                        helperForDiet deitHelper = new helperForDiet();
                        deitHelper.setDietTitle(jsonObject.getString("title"));
                        deitHelper.setDietImage(jsonObject.getString("image"));
                        deitHelper.setDietCalories(jsonObject.getString("calories"));
                        deitHelper.setDietProtein(jsonObject.getString("protein"));
                        list.add(deitHelper);
                    }
                    adapter.notifyDataSetChanged();
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);

                } catch (Exception e) {
                    e.printStackTrace();
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
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
        });

        requestQueue.add(stringRequest);
    }
}