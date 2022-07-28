package com.skroyal00000.dailyworkout.exercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.skroyal00000.dailyworkout.PrefConfig;
import com.skroyal00000.dailyworkout.R;
import com.skroyal00000.dailyworkout.Utils.LinkApi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExerciseList extends AppCompatActivity {
    String join , whichT, level, whichT2, level2, whichT3, level3;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    String name_exercise_string;
    private LinearLayoutManager linearLayoutManager;
    Button btn_start_all_exercise;
    int countdownTimeInt;
    String countdownTimeString;
    ArrayList<HelperClass> list;
    ShimmerFrameLayout shimmerFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        recyclerView = (RecyclerView) findViewById(R.id.exercise_list_recyclerView);
        btn_start_all_exercise = findViewById(R.id.btn_start_all_exercise);
        shimmerFrameLayout = findViewById(R.id.shimmerLayout);
        shimmerFrameLayout.startShimmer();
        name_exercise_string = "";

        getPrefs();
        getExtraIntent();

        list = new ArrayList<>();
        adapter = new Adapter(getApplicationContext(),list);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        getData();
        BtnStartAllExercise();
    }

    private void getData() {

        LinkApi linkApi = new LinkApi();
        String url = linkApi.showExercise;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject childObj = new JSONObject(response);
                    JSONArray childArray = childObj.getJSONArray("result");
                    for (int i = 0; i < childArray.length(); i++) {
                        JSONObject jsonObject = childArray.getJSONObject(i);
                        HelperClass helperClass = new HelperClass();
                        helperClass.setExercise_title(jsonObject.getString("title"));
                        helperClass.setExercise_image(jsonObject.getString("image"));
                        helperClass.setExercise_time(jsonObject.getString("miniTitle1"));
                        helperClass.setDescription(jsonObject.getString("description"));
                        list.add(helperClass);
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
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                if(join.equalsIgnoreCase("1")){
                    params.put("MAINTHREE","true");
                    params.put("whichT",whichT);
                    params.put("level",level);
                    params.put("whichT2",whichT2);
                    params.put("level2",level2);
                    params.put("whichT3",whichT3);
                    params.put("level3",level3);
                }
                else if(join.equalsIgnoreCase("2")){
                    params.put("TWO","true");
                    params.put("whichT",whichT);
                    params.put("level",level);
                    params.put("whichT2",whichT2);
                    params.put("level2",level2);

                }
                else if(join.equalsIgnoreCase("3")){
                    params.put("THREE","true");
                    params.put("whichT",whichT);
                    params.put("whichT2",whichT2);
                    params.put("whichT3",whichT3);

                }
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void BtnStartAllExercise()
    {
        ArrayList<HelperClass> allExercise;
        allExercise = list;
        ArrayList<HelperClass> finalAllExercise = allExercise;
        btn_start_all_exercise.setOnClickListener(v -> {
            Intent intent = new Intent(ExerciseList.this, ShowExerciseAll.class);
            intent.putExtra("Exercise", finalAllExercise);
            startActivity(intent);
        });
    }




    private  void getPrefs(){
        countdownTimeInt = PrefConfig.loadSettingCountDown(this);
        countdownTimeString = countdownTimeInt + " seconds";
    }

    private void getExtraIntent() {
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {

            join = extras.getString("join");
            whichT = extras.getString("whichT");
            level = extras.getString("level");
            whichT2 = extras.getString("whichT2");
            level2 = extras.getString("level2");
            whichT3 = extras.getString("whichT3");
            level3 = extras.getString("level3");

        }

    }






}