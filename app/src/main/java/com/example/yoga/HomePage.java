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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        featuredRecycler = findViewById(R.id.featured_recycler);


        sharedPreferences = getApplicationContext().getSharedPreferences("storage", Context.MODE_PRIVATE);
        String g , l;
        g = sharedPreferences.getString("gender","");
        l = sharedPreferences.getString("level","");

        Toast.makeText(HomePage.this,g + "  " + l,Toast.LENGTH_SHORT).show();


        featuredRecycler();


    }

    public void featuredRecycler() {
        setOnClickLister();
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(HomePage.this,LinearLayoutManager.HORIZONTAL,false));

        FeaturedLocation = new ArrayList<>();

        FeaturedLocation.add(new FeaturedhelperClass(R.drawable.chest_icon,"Warm up","12:00","5 exercise"));
        FeaturedLocation.add(new FeaturedhelperClass(R.drawable.chest_icon,"Chest","15:00","10 exercise"));
        FeaturedLocation.add(new FeaturedhelperClass(R.drawable.chest_icon,"bicep","13:00","7 exercise"));
        FeaturedLocation.add(new FeaturedhelperClass(R.drawable.chest_icon,"Tricep","5:00","8 exercise"));
        FeaturedLocation.add(new FeaturedhelperClass(R.drawable.chest_icon,"Shoulder","9:00","9 exercise"));
        FeaturedLocation.add(new FeaturedhelperClass(R.drawable.chest_icon,"leg","10:00","7 exercise"));
        FeaturedLocation.add(new FeaturedhelperClass(R.drawable.chest_icon,"back","8:00","9 exercise"));

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