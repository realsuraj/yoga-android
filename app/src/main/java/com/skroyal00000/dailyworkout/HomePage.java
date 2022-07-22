package com.skroyal00000.dailyworkout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.skroyal00000.dailyworkout.Detail.Detail_intro;
import com.skroyal00000.dailyworkout.Home.ChildItem;
import com.skroyal00000.dailyworkout.Home.ParentItem;
import com.skroyal00000.dailyworkout.Home.ParentItemAdapter;
import com.skroyal00000.dailyworkout.Utils.LinkApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePage extends AppCompatActivity {
    private Button begginerBtnJoin, intermediateBtnJoin, advanceBtnJoin;
    String[] workoutUrl;
    ImageView settingImageview,custom_btn;
    RecyclerView parentRecyclerView ;
    ParentItemAdapter parentItemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        begginerBtnJoin = (Button) findViewById(R.id.joinNowDate);
        intermediateBtnJoin = (Button) findViewById(R.id.joinNowDate2);
        advanceBtnJoin = (Button) findViewById(R.id.joinNowDate3);
        settingImageview = (ImageView)  findViewById(R.id.setting_imageview);
        custom_btn = findViewById(R.id.create_custom_plan_btn);
        workoutUrl = getResources().getStringArray(R.array.home_page_icons_urls);
        parentRecyclerView = findViewById(R.id.parentRecyclerView);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                              calling function

        BeginnerJoinFunc();
        SettingImageButton();
        custombtnPressed();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                              Setting recyclerview
        parentRecyclerView.setHasFixedSize(true);
        parentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(HomePage.this);
        parentItemAdapter = new ParentItemAdapter(ParentItemList());
        parentRecyclerView.setAdapter(parentItemAdapter);
        parentRecyclerView.setLayoutManager(layoutManager);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                             setting array list for parent
    private List<ParentItem> ParentItemList()
    {
        List<ParentItem> itemList = new ArrayList<>();
        ParentItem item = new ParentItem("Daily Workout", ChildItemList("Daily Workout"));
        itemList.add(item);
        ParentItem item1 = new ParentItem("Gym", ChildItemList("Gym"));
        itemList.add(item1);
        ParentItem item2 = new ParentItem("Clothes", ChildItemList("Daily Workout"));
        itemList.add(item2);
        ParentItem item3 = new ParentItem("Supplements", ChildItemList("Gym"));itemList.add(item3);
        return itemList;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                            setting array list for child


    private List<ChildItem> ChildItemList(String whichT) {
        List<ChildItem> childItemsList = new ArrayList<>();
        LinkApi linkApi = new LinkApi() ;
        String url = linkApi.homeData;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject childObj = new JSONObject(response);
                    JSONArray childArray = childObj.getJSONArray("result");
                    List<ChildItem> childItemsList2 = new ArrayList<>();
                    for (int i = 0; i < childArray.length(); i++) {
                     JSONObject jsonObject = childArray.getJSONObject(i);
                     childItemsList.add(new ChildItem(jsonObject.getString("title"),jsonObject.getString("image")
                     ,jsonObject.getString("miniTitle1"),jsonObject.getString("miniTitle2")
                     ,jsonObject.getString("miniIcon1"),jsonObject.getString("miniIcon2")));

                    }
                    parentItemAdapter.notifyDataSetChanged();

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
                params.put("whichT",whichT);
                return params;
            }
        };

        requestQueue.add(stringRequest);

            return childItemsList;



    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           custom button press
    private void custombtnPressed() {
        custom_btn.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, Detail_intro.class);
            intent.putExtra("customPressed","Custom_pressed");
            startActivity(intent);
        });
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                          setting button

    private void SettingImageButton() {
        settingImageview.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this,Setting.class);
            startActivity(intent);
        });
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           intent for join


    private void BeginnerJoinFunc() {
        begginerBtnJoin.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this,ExerciseDays.class);
            intent.putExtra("JoinBtn","Beginner");
            startActivity(intent);
        });
        intermediateBtnJoin.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this,ExerciseDays.class);
            intent.putExtra("JoinBtn","Intermediate");
            startActivity(intent);
        });
        advanceBtnJoin.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this,ExerciseDays.class);
            intent.putExtra("JoinBtn","Advanced");
            startActivity(intent);
        });
    }

}