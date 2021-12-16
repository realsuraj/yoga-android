package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.yoga.HomeAdapter.FeaturedAdapter;
import com.example.yoga.HomeAdapter.FeaturedhelperClass;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    private RecyclerView featuredRecycler;
    private RecyclerView.Adapter adapter;
    private SharedPreferences sharedPreferences;
    private FeaturedAdapter.RecyclerViewOnClickListerner listerner;
    ArrayList<FeaturedhelperClass> FeaturedLocation;
    String[] urls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        featuredRecycler = findViewById(R.id.featured_recycler);
        urls = getResources().getStringArray(R.array.home_page_icons_urls);


        sharedPreferences = getApplicationContext().getSharedPreferences("storage", Context.MODE_PRIVATE);
        String g , l;
        g = sharedPreferences.getString("gender","");
        l = sharedPreferences.getString("level","");



        featuredRecycler();


    }

    public void featuredRecycler() {
        setOnClickLister();
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(HomePage.this,LinearLayoutManager.HORIZONTAL,false));

        FeaturedLocation = new ArrayList<>();

        FeaturedLocation.add(new FeaturedhelperClass(urls[0],"WarmUp","12:00","5 exercise"));
        FeaturedLocation.add(new FeaturedhelperClass(urls[1],"Chest","15:00","10 exercise"));
        FeaturedLocation.add(new FeaturedhelperClass(urls[2],"bicep","13:00","7 exercise"));
        FeaturedLocation.add(new FeaturedhelperClass(urls[3],"Tricep","5:00","8 exercise"));
        FeaturedLocation.add(new FeaturedhelperClass(urls[4],"Shoulder","9:00","9 exercise"));
        FeaturedLocation.add(new FeaturedhelperClass(urls[5],"Leg","10:00","7 exercise"));
        FeaturedLocation.add(new FeaturedhelperClass(urls[6],"Back","8:00","9 exercise"));

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