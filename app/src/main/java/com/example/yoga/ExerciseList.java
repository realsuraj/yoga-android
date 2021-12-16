package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yoga.ExerciseListAdapter.Adapter;
import com.example.yoga.ExerciseListAdapter.HelperClass;
import com.example.yoga.databinding.ActivityMainBinding;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ExerciseList extends AppCompatActivity {

    RecyclerView ExerciseRecyclerView;
    RecyclerView.Adapter adapter;
    TextView exercise_name;
    String name_exercise_string;
    ImageView imageViewFire;
    ActivityMainBinding binding;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    Adapter.ExerciseOnClickRecycler listerner;
    ArrayList<HelperClass> list;
    String[] urls_legs,urls_chest,urls_biceps,urls_triceps,urls_shoulder,urls_backs,urls_warmup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        exercise_name = findViewById(R.id.exercise_name);
        ExerciseRecyclerView = (RecyclerView) findViewById(R.id.exercise_list_recyclerView);
        imageViewFire = findViewById(R.id.firebaseimage);
        getUrlsInArrayList();
        getExtraIntent();
        Lists();



    }

    private void getUrlsInArrayList() {

        urls_legs = getResources().getStringArray(R.array.urls_legs);
        urls_biceps = getResources().getStringArray(R.array.bicep_urls);
        urls_backs = getResources().getStringArray(R.array.back_urls);
        urls_chest = getResources().getStringArray(R.array.chest_urls);
        urls_warmup = getResources().getStringArray(R.array.warmup_urls);
        urls_shoulder = getResources().getStringArray(R.array.shoulder_urls);
        urls_triceps = getResources().getStringArray(R.array.tricep_urls);
    }


    private void getExtraIntent() {
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            name_exercise_string = extras.getString("exercise");
        }

        exercise_name.setText(name_exercise_string);
    }


    public void Lists(){
        setOnClickLister();
        ExerciseRecyclerView.setHasFixedSize(true);
        ExerciseRecyclerView.setLayoutManager(new LinearLayoutManager(ExerciseList.this,LinearLayoutManager.VERTICAL,false));

        list = new ArrayList<>();
        if(name_exercise_string.equalsIgnoreCase("bicep")) {
            list.add(new HelperClass(urls_biceps[0],"Hammer Curl", "12:00"));
            list.add(new HelperClass(urls_biceps[1], "Zottman Curl", "2:00"));
            list.add(new HelperClass(urls_biceps[2], "Decline Dumbbell Curl", "1:00"));
            list.add(new HelperClass(urls_biceps[3], "Chin up", "12:00"));
            list.add(new HelperClass(urls_biceps[4],"Regular EZ Bar Curl", "2:00"));
            list.add(new HelperClass(urls_biceps[5], "Reverse Curl Straight Bar", "2:00"));

        }
        if(name_exercise_string.equalsIgnoreCase("chest")) {
                list.add(new HelperClass(urls_chest[0], "bicep 2", "12:00"));
                list.add(new HelperClass(urls_chest[1] ,"bicep 3", "2:00"));
                list.add(new HelperClass(urls_chest[2], "bicep 5", "1:00"));
                list.add(new HelperClass(urls_chest[3], "bicep 9", "12:00"));
                list.add(new HelperClass(urls_chest[4], "bicep 2", "12:00"));
                list.add(new HelperClass(urls_chest[5],"3", "2:00"));
                list.add(new HelperClass(urls_chest[6] ,"bicep", "1:00"));
                }

                  if(name_exercise_string.equalsIgnoreCase("leg")) {

                list.add(new HelperClass(urls_legs[0], "bicep test", "2:00"));
                list.add(new HelperClass(urls_legs[1], "bicep 5", "1:00"));
                list.add(new HelperClass(urls_legs[2], "bicep 9", "12:00"));
                list.add(new HelperClass(urls_legs[3], "bicep 2", "12:00"));
                list.add(new HelperClass(urls_legs[4], "bicep 3", "2:00"));
                list.add(new HelperClass(urls_legs[5], "bicep 5", "1:00"));
                list.add(new HelperClass(urls_legs[6], "bicep 9", "12:00"));
                list.add(new HelperClass(urls_legs[7], "bicep 9", "12:00"));
                }

                  if(name_exercise_string.equalsIgnoreCase("tricep")) {
                list.add(new HelperClass(urls_triceps[0], "bicep 2", "12:00"));
                list.add(new HelperClass(urls_triceps[1], "bicep 3", "2:00"));
                list.add(new HelperClass(urls_triceps[2], "bicep 5", "1:00"));
                list.add(new HelperClass(urls_triceps[3], "bicep 9", "12:00"));
                list.add(new HelperClass(urls_triceps[4], "bicep 2", "12:00"));
                list.add(new HelperClass(urls_triceps[5], "bicep 3", "2:00"));
                list.add(new HelperClass(urls_triceps[6], "bicep 5", "1:00"));
                list.add(new HelperClass(urls_triceps[7], "bicep 9", "12:00"));
                list.add(new HelperClass(urls_triceps[8], "bicep 9", "12:00"));
                }

                  if(name_exercise_string.equalsIgnoreCase("shoulder")) {
                list.add(new HelperClass(urls_shoulder[0], "bicep 2", "12:00"));
                list.add(new HelperClass(urls_shoulder[1], "bicep 3", "2:00"));
                list.add(new HelperClass(urls_shoulder[2], "bicep 5", "1:00"));
                list.add(new HelperClass(urls_shoulder[3], "bicep 9", "12:00"));
                list.add(new HelperClass(urls_shoulder[4], "bicep 2", "12:00"));
                list.add(new HelperClass(urls_shoulder[5], "bicep 3", "2:00"));
                list.add(new HelperClass(urls_shoulder[6], "bicep 5", "1:00"));
                }

                  if(name_exercise_string.equalsIgnoreCase("back")) {
                list.add(new HelperClass(urls_backs[0], "bicep 2", "12:00"));
                list.add(new HelperClass(urls_backs[1], "bicep 3", "2:00"));
                list.add(new HelperClass(urls_backs[2], "bicep 5", "1:00"));
                list.add(new HelperClass(urls_backs[3], "bicep 9", "12:00"));
                list.add(new HelperClass(urls_backs[4], "bicep 2", "12:00"));
                list.add(new HelperClass(urls_backs[5], "bicep 3", "2:00"));
                list.add(new HelperClass(urls_backs[6], "bicep 5", "1:00"));
                list.add(new HelperClass(urls_backs[7], "bicep 9", "12:00"));
                list.add(new HelperClass(urls_backs[8], "bicep 9", "12:00"));
                }

                  if(name_exercise_string.equalsIgnoreCase("warm-up")) {
                list.add(new HelperClass(urls_warmup[0], "bicep 2", "12:00"));
                list.add(new HelperClass(urls_warmup[1], "bicep 3", "2:00"));
                list.add(new HelperClass(urls_warmup[2], "bicep 5", "1:00"));
                list.add(new HelperClass(urls_warmup[3], "bicep 9", "12:00"));
                list.add(new HelperClass(urls_warmup[4], "bicep 2", "12:00"));
                list.add(new HelperClass(urls_warmup[5], "bicep 3", "2:00"));
                list.add(new HelperClass(urls_warmup[6], "bicep 5", "1:00"));
                list.add(new HelperClass(urls_warmup[7], "bicep 9", "12:00"));
                }


        adapter = new Adapter(list,listerner);
        ExerciseRecyclerView.setAdapter(adapter);

    }


    private void setOnClickLister() {
        listerner = new Adapter.ExerciseOnClickRecycler(){

            @Override
            public void Onclick(View v, int position) {
                Intent intent = new Intent(ExerciseList.this,ShowExercise.class);
                intent.putExtra("showExercise",list.get(position).getExercise_title());
                startActivity(intent);
            }
        };
    }


}