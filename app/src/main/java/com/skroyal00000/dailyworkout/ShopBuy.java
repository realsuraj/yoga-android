package com.skroyal00000.dailyworkout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.MotionEventCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

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
    VideoView videoView;
    String videoUrl = "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1";
    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    ImageView videoPlayBtn;
    ImageView indicatorCircle1, indicatorCircle2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_buy);

        videoView = findViewById(R.id.videoView);
        title = findViewById(R.id.showAllTitle);
        miniTitle1 = findViewById(R.id.showAllBuy);
        miniTitle2 = findViewById(R.id.showAllWebsite);
        description = findViewById(R.id.showAllDescription);
        showAllImage = findViewById(R.id.shopAllImage);
        indicatorCircle1 = findViewById(R.id.indicatorCircle1);
        indicatorCircle2 = findViewById(R.id.indicatorCircle2);
        videoPlayBtn = findViewById(R.id.play_button);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        whichTable = intent.getStringExtra("whichT");
        getData();
        indicatorCircle1.setBackground(ContextCompat.getDrawable(ShopBuy.this, R.drawable.circle));
        indicatorCircle2.setBackground(ContextCompat.getDrawable(ShopBuy.this, R.drawable.circle_2));
        videoPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(videoUrl);
                videoView.setVideoURI(uri);
                MediaController mediaController = new MediaController(ShopBuy.this);
                mediaController.setAnchorView(videoView);
                mediaController.setMediaPlayer(videoView);
                videoView.setMediaController(mediaController);
                videoView.start();
                videoPlayBtn.setVisibility(View.GONE);

            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1)
                    {
                        //previous
                        videoView.setVisibility(View.GONE);
                        showAllImage.setVisibility(View.VISIBLE);
                        indicatorCircle1.setBackground(ContextCompat.getDrawable(ShopBuy.this, R.drawable.circle));
                        indicatorCircle2.setBackground(ContextCompat.getDrawable(ShopBuy.this, R.drawable.circle_2));


                    }

                    // Right to left swipe action
                    else
                    {
                       //next
                        videoView.setVisibility(View.VISIBLE);
                        showAllImage.setVisibility(View.GONE);
                        videoPlayBtn.setVisibility(View.VISIBLE);
                        indicatorCircle1.setBackground(ContextCompat.getDrawable(ShopBuy.this, R.drawable.circle_2));
                        indicatorCircle2.setBackground(ContextCompat.getDrawable(ShopBuy.this, R.drawable.circle));

                    }

                }

                break;
        }
        return super.onTouchEvent(event);
    }

    private void getData(){
        MainActivity mainActivity = new MainActivity();
        String url = mainActivity.getApiShowSingleData();
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
                        miniTitle1.setText("₹ " + jsonObject.getString("price"));
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
            miniTitle2.setText(jsonObject.getString("rating"));
        }else if(whichTable.equalsIgnoreCase("shop")){
            miniTitle2.setText(jsonObject.getString("which_website"));
        }else if(whichTable.equalsIgnoreCase("gym")){
            miniTitle2.setText(jsonObject.getString("location"));
            }
    }
}