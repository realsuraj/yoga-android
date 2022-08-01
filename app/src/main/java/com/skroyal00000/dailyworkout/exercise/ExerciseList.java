package com.skroyal00000.dailyworkout.exercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.facebook.shimmer.ShimmerFrameLayout;
import com.skroyal00000.dailyworkout.ExerciseListAdapter.Adapter;
import com.skroyal00000.dailyworkout.ExerciseListAdapter.HelperClass;
import com.skroyal00000.dailyworkout.PrefConfig;
import com.skroyal00000.dailyworkout.R;
import com.skroyal00000.dailyworkout.Utils.LinkApi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExerciseList extends AppCompatActivity {
    String join , whichT, level, whichT2, level2, whichT3, level3,whichDay;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    String name_exercise_string;
    Button btn_start_all_exercise;
    int countdownTimeInt;
    String countdownTimeString;
    ArrayList<HelperClass> list;
    ShimmerFrameLayout shimmerFrameLayout;
    TextView whichExerciseTxt;
    ImageView ExerciseListInfoImg;
    String joinBtnStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        recyclerView = (RecyclerView) findViewById(R.id.exercise_list_recyclerView);
        btn_start_all_exercise = findViewById(R.id.btn_start_all_exercise);
        shimmerFrameLayout = findViewById(R.id.shimmerLayout);
        ExerciseListInfoImg = findViewById(R.id.ExerciseListInfoTxt);
        shimmerFrameLayout.startShimmer();
        name_exercise_string = "";
        whichExerciseTxt = findViewById(R.id.exerciseName);
        getPrefs();
        getExtraIntent();
        ExerciseListInfoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfoClick();
            }
        });



        list = new ArrayList<>();
        adapter = new Adapter(ExerciseList.this,list);

        GridLayoutManager layoutManager=new GridLayoutManager(this,2);;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getData();
        BtnStartAllExercise();
    }

    private void getInfoClick() {
            AlertDialog.Builder builder = new AlertDialog.Builder(ExerciseList.this);
            builder.setMessage("Have You Done Exercise");
            builder.setTitle("Alert!");
            builder.setCancelable(false);

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    int whichIntDay = Integer.parseInt(whichDay);
                                    if(joinBtnStr.equalsIgnoreCase("beginner")){
                                        PrefConfig.saveWhichDayBeginner(ExerciseList.this,whichIntDay+1);
                                    }else if(joinBtnStr.equalsIgnoreCase("intermediate")){
                                        PrefConfig.saveWhichDayIntermediate(ExerciseList.this,whichIntDay+1);
                                    }else if(joinBtnStr.equalsIgnoreCase("advanced")){
                                        PrefConfig.saveWhichDayAdvanced(ExerciseList.this,whichIntDay+1);

                                    }
                                }
                            });


            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.cancel();
                                }
                            });

            AlertDialog alertDialog = builder.create();

            alertDialog.show();
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
                        helperClass.setExercise_time(jsonObject.getString("duration"));
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
                    params.put("medium","level_5");
                    params.put("whichT",whichT);
                    params.put("level",level);
                    params.put("whichT2",whichT2);
                    params.put("level2",level2);
                    params.put("whichT3",whichT3);
                    params.put("level3",level3);
                }
                else if(join.equalsIgnoreCase("2")){
                    params.put("medium","level_3");
                    params.put("whichT",whichT);
                    params.put("level",level);
                    params.put("whichT2",whichT2);
                    params.put("level2",level2);

                }
                else if(join.equalsIgnoreCase("3")){
                    params.put("medium","level_4");
                    params.put("whichT",whichT);
                    params.put("whichT2",whichT2);
                    params.put("whichT3",whichT3);

                }
                else if(join.equalsIgnoreCase("4")){
                    params.put("medium","level_1");
                    params.put("whichT",whichT);
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
            whichDay = extras.getString("whichDay");
            joinBtnStr = extras.getString("joinBtn");
            if(whichDay != null) {
                whichExerciseTxt.setText("Day " + whichDay);
            }else{
                whichExerciseTxt.setText(joinBtnStr);
            }
        }

    }






}