package com.skroyal00000.dailyworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.skroyal00000.dailyworkout.HomeAdapter.FeaturedAdapter;
import com.skroyal00000.dailyworkout.HomeAdapter.FeaturedhelperClass;
import com.skroyal00000.dailyworkout.R;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    private RecyclerView featuredRecycler;
    private RecyclerView.Adapter adapter;
    private SharedPreferences sharedPreferences;
    private FeaturedAdapter.RecyclerViewOnClickListerner listerner;
    private Button joinNowBtnDate1,joinNowBtnDate2,joinNowBtnDate3;
    ArrayList<FeaturedhelperClass> FeaturedLocation;
    String[] urls;
    String[] chestImageUrls,warmupUrls,bicepUrls,tricepUrls,shoulderUrls,backUrls,legUrls;
    ImageView settingImageview,custom_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        featuredRecycler = findViewById(R.id.featured_recycler);
        joinNowBtnDate1 = (Button) findViewById(R.id.joinNowDate);
        joinNowBtnDate2 = (Button) findViewById(R.id.joinNowDate2);
        joinNowBtnDate3 = (Button) findViewById(R.id.joinNowDate3);
        settingImageview = (ImageView)  findViewById(R.id.setting_imageview);
        custom_btn = findViewById(R.id.create_custom_plan_btn);
        urls = getResources().getStringArray(R.array.home_page_icons_urls);


        JoinNow();
        gettingArraysValues();
        featuredRecycler();
        SettingImageButton();
        custombtnPressed();
    }

    private void custombtnPressed() {
        custom_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,Detail_intro.class);
                intent.putExtra("customPressed","Custom_pressed");
                startActivity(intent);
            }
        });
    }

    private void SettingImageButton() {
        settingImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,Setting.class);
                startActivity(intent);
            }
        });
    }


    private void gettingArraysValues() {
        chestImageUrls = getResources().getStringArray(R.array.chest_urls);
        bicepUrls = getResources().getStringArray(R.array.bicep_urls);
        tricepUrls = getResources().getStringArray(R.array.tricep_urls);
        shoulderUrls = getResources().getStringArray(R.array.shoulder_urls);
        backUrls = getResources().getStringArray(R.array.back_urls);
        legUrls = getResources().getStringArray(R.array.urls_legs);
        warmupUrls = getResources().getStringArray(R.array.warmup_urls);
    }

    private void JoinNow() {
        joinNowBtnDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,ExerciseDays.class);
                startActivity(intent);
            }
        });
        joinNowBtnDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,ExerciseDays.class);
                startActivity(intent);
            }
        });
        joinNowBtnDate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,ExerciseDays.class);
                startActivity(intent);
            }
        });
    }

    public void featuredRecycler() {
        setOnClickLister();
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(HomePage.this,LinearLayoutManager.HORIZONTAL,false));

        FeaturedLocation = new ArrayList<>();

        FeaturedLocation.add(new FeaturedhelperClass(urls[0],"WarmUp","12:00",warmupUrls.length + " exercise"));
        FeaturedLocation.add(new FeaturedhelperClass(urls[1],"Chest","15:00",  chestImageUrls.length+"  exercise"));
        FeaturedLocation.add(new FeaturedhelperClass(urls[2],"Bicep","13:00",  bicepUrls.length+" exercise"));
        FeaturedLocation.add(new FeaturedhelperClass(urls[3],"Triceps","5:00",  tricepUrls.length+" exercise"));
        FeaturedLocation.add(new FeaturedhelperClass(urls[4],"Shoulder","9:00",  shoulderUrls.length+" exercise"));
        FeaturedLocation.add(new FeaturedhelperClass(urls[5],"Leg","10:00",  legUrls.length+" exercise"));
        FeaturedLocation.add(new FeaturedhelperClass(urls[6],"Back","8:00",  backUrls.length+" exercise"));

        adapter = new FeaturedAdapter(FeaturedLocation,listerner);

        featuredRecycler.setAdapter(adapter);

    }

    private void setOnClickLister() {
        listerner = new FeaturedAdapter.RecyclerViewOnClickListerner() {
            @Override
            public void Onclick(View v, int position) {
                Intent intent = new Intent(HomePage.this,ExerciseList.class);
                intent.putExtra("exercise",FeaturedLocation.get(position).getTitle());
                startActivity(intent);
            }
        };
    }
}