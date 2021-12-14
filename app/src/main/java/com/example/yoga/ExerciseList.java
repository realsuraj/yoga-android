package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.yoga.ExerciseListAdapter.Adapter;
import com.example.yoga.ExerciseListAdapter.HelperClass;

import java.util.ArrayList;

public class ExerciseList extends AppCompatActivity {

    RecyclerView ExerciseRecyclerView;
    RecyclerView.Adapter adapter;
    TextView exercise_name;
    String name_exercise_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        exercise_name = findViewById(R.id.exercise_name);
        ExerciseRecyclerView = (RecyclerView) findViewById(R.id.exercise_list_recyclerView);

        Lists();

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            name_exercise_string = extras.getString("exercise");
        }

        exercise_name.setText(name_exercise_string);
    }

    public void Lists(){
        ExerciseRecyclerView.setHasFixedSize(true);
        ExerciseRecyclerView.setLayoutManager(new LinearLayoutManager(ExerciseList.this,LinearLayoutManager.VERTICAL,false));

        ArrayList<HelperClass> list = new ArrayList<>();
        list.add(new HelperClass(R.drawable.chest_icon,"Cheast 2","12:00"));
        list.add(new HelperClass(R.drawable.chest_icon,"Cheast 3","2:00"));
        list.add(new HelperClass(R.drawable.chest_icon,"Cheast 5","1:00"));
        list.add(new HelperClass(R.drawable.chest_icon,"Cheast 9","12:00"));
        list.add(new HelperClass(R.drawable.chest_icon,"Cheast 11","2:00")); list.add(new HelperClass(R.drawable.chest_icon,"Cheast 2","12:00"));
        list.add(new HelperClass(R.drawable.chest_icon,"Cheast 3","2:00"));
        list.add(new HelperClass(R.drawable.chest_icon,"Cheast 5","1:00"));
        list.add(new HelperClass(R.drawable.chest_icon,"Cheast 9","12:00"));
        list.add(new HelperClass(R.drawable.chest_icon,"Cheast 11","2:00"));

        adapter = new Adapter(list);
        ExerciseRecyclerView.setAdapter(adapter);

    }
}