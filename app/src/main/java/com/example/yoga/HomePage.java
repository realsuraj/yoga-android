package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yoga.HomeAdapter.FeaturedAdapter;
import com.example.yoga.HomeAdapter.FeaturedhelperClass;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    private RecyclerView featuredRecycler;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        featuredRecycler = findViewById(R.id.featured_recycler);

        featuredRecycler();

    }

    public void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(HomePage.this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedhelperClass> featuredLocaiton = new ArrayList<>();

        featuredLocaiton.add(new FeaturedhelperClass(R.drawable.chest_icon,"Warm up","12:00","5 exercise"));
        featuredLocaiton.add(new FeaturedhelperClass(R.drawable.chest_icon,"Chest","15:00","10 exercise"));
        featuredLocaiton.add(new FeaturedhelperClass(R.drawable.chest_icon,"bicep","13:00","7 exercise"));
        featuredLocaiton.add(new FeaturedhelperClass(R.drawable.chest_icon,"Tricep","5:00","8 exercise"));
        featuredLocaiton.add(new FeaturedhelperClass(R.drawable.chest_icon,"Shoulder","9:00","9 exercise"));
        featuredLocaiton.add(new FeaturedhelperClass(R.drawable.chest_icon,"leg","10:00","7 exercise"));
        featuredLocaiton.add(new FeaturedhelperClass(R.drawable.chest_icon,"back","8:00","9 exercise"));

        adapter = new FeaturedAdapter(featuredLocaiton);

        featuredRecycler.setAdapter(adapter);

    }
}