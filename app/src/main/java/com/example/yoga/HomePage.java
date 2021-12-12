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

        featuredLocaiton.add(new FeaturedhelperClass(R.drawable.chest_icon,"Warm up","working on Chest"));
        featuredLocaiton.add(new FeaturedhelperClass(R.drawable.chest_icon,"Chest","working on tricep"));
        featuredLocaiton.add(new FeaturedhelperClass(R.drawable.chest_icon,"bicep","working on Bicep"));
        featuredLocaiton.add(new FeaturedhelperClass(R.drawable.chest_icon,"Tricep","working on Bicep"));
        featuredLocaiton.add(new FeaturedhelperClass(R.drawable.chest_icon,"Shoulder","working on Bicep"));
        featuredLocaiton.add(new FeaturedhelperClass(R.drawable.chest_icon,"leg","working on Bicep"));
        featuredLocaiton.add(new FeaturedhelperClass(R.drawable.chest_icon,"back","working on Bicep"));

        adapter = new FeaturedAdapter(featuredLocaiton);

        featuredRecycler.setAdapter(adapter);

    }
}