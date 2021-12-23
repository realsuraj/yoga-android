package com.skroyal00000.dailyworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.skroyal00000.dailyworkout.ExerciseListAdapter.Adapter;
import com.skroyal00000.dailyworkout.ExerciseListAdapter.HelperClass;
import com.skroyal00000.dailyworkout.R;

import java.util.ArrayList;

public class ExerciseList extends AppCompatActivity {

    RecyclerView ExerciseRecyclerView;
    RecyclerView.Adapter adapter;
    TextView exercise_name;
    String name_exercise_string,dayExercise;
    ImageView imageViewFire;
    Adapter.ExerciseOnClickRecycler listerner;
    ArrayList<HelperClass> list;
    String[] urls_legs,urls_chest,urls_biceps,urls_triceps,urls_shoulder,urls_backs,urls_warmup,
    titles_legs,titles_chest,titles_biceps,titles_triceps,titles_shoulder,titles_back,titles_warmup;
    Button btn_start_all_exercise;
    int countdownTimeInt;
    String countdownTimeString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        exercise_name = findViewById(R.id.exercise_name);
        ExerciseRecyclerView = (RecyclerView) findViewById(R.id.exercise_list_recyclerView);
        imageViewFire = findViewById(R.id.firebaseimage);
        btn_start_all_exercise = findViewById(R.id.btn_start_all_exercise);
        name_exercise_string = "";
        getPrefs();
        getUrlsInArrayList();
        getExtraIntent();
        Lists();
        BtnStartAllExercise();

    }

    private void BtnStartAllExercise()
    {
        btn_start_all_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseList.this,ShowExerciseAll.class);
                intent.putExtra("Exercise",name_exercise_string);
                startActivity(intent);
            }
        });
    }

    private void getUrlsInArrayList() {

        urls_legs = getResources().getStringArray(R.array.urls_legs);
        urls_biceps = getResources().getStringArray(R.array.bicep_urls);
        urls_backs = getResources().getStringArray(R.array.back_urls);
        urls_chest = getResources().getStringArray(R.array.chest_urls);
        urls_warmup = getResources().getStringArray(R.array.warmup_urls);
        urls_shoulder = getResources().getStringArray(R.array.shoulder_urls);
        urls_triceps = getResources().getStringArray(R.array.tricep_urls);

        titles_legs = getResources().getStringArray(R.array.leg_titles);
        titles_biceps = getResources().getStringArray(R.array.bicep_titles);
        titles_back = getResources().getStringArray(R.array.back_titles);
        titles_chest = getResources().getStringArray(R.array.chest_titles);
        titles_warmup = getResources().getStringArray(R.array.warmup_titles);
        titles_shoulder = getResources().getStringArray(R.array.shoulder_title);
        titles_triceps = getResources().getStringArray(R.array.tricep_titles);


    }

    private  void getPrefs(){
        countdownTimeInt = PrefConfig.loadSettingCountDown(this);
        countdownTimeString = countdownTimeInt + " seconds";
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
        if(name_exercise_string.equalsIgnoreCase("bicep") ) {
            String time = countdownTimeString;
            list.add(new HelperClass(urls_biceps[0],titles_biceps[0], "" + time));
            list.add(new HelperClass(urls_biceps[1],titles_biceps[1] , "" + time));
            list.add(new HelperClass(urls_biceps[2],titles_biceps[2] , "" + time));
            list.add(new HelperClass(urls_biceps[3],titles_biceps[3] , "" + time));
            list.add(new HelperClass(urls_biceps[4],titles_biceps[4], "" + time));
            list.add(new HelperClass(urls_biceps[5],titles_biceps[5] , "" + time));

        }
        if(name_exercise_string.equalsIgnoreCase("chest")) {
                list.add(new HelperClass(urls_chest[0],titles_chest[0] , "" + countdownTimeString));
                list.add(new HelperClass(urls_chest[1],titles_chest[1], "" + countdownTimeString));
                list.add(new HelperClass(urls_chest[2],titles_chest[2] , "" + countdownTimeString));
                list.add(new HelperClass(urls_chest[3],titles_chest[3] , "" + countdownTimeString));
                list.add(new HelperClass(urls_chest[4],titles_chest[4] , "" + countdownTimeString));
                list.add(new HelperClass(urls_chest[5],titles_chest[5], "" + countdownTimeString));
                list.add(new HelperClass(urls_chest[6],titles_chest[6] ,"" + countdownTimeString));
                }

        if(name_exercise_string.equalsIgnoreCase("leg")) {

                list.add(new HelperClass(urls_legs[0],titles_legs[0] , "" + countdownTimeString));
                list.add(new HelperClass(urls_legs[1],titles_legs[1] , "" + countdownTimeString));
                list.add(new HelperClass(urls_legs[2],titles_legs[2] , "" + countdownTimeString));
                list.add(new HelperClass(urls_legs[3],titles_legs[3] , "" + countdownTimeString));
                list.add(new HelperClass(urls_legs[4],titles_legs[4] , "" + countdownTimeString));
                list.add(new HelperClass(urls_legs[5],titles_legs[5] , "" + countdownTimeString));
                list.add(new HelperClass(urls_legs[6],titles_legs[6] , "" + countdownTimeString));
                list.add(new HelperClass(urls_legs[7],titles_legs[7] , "" + countdownTimeString));
                }

        if(name_exercise_string.equalsIgnoreCase("tricep")) {
                list.add(new HelperClass(urls_triceps[0], titles_triceps[0], "" + countdownTimeString));
                list.add(new HelperClass(urls_triceps[1], titles_triceps[1], "" + countdownTimeString));
                list.add(new HelperClass(urls_triceps[2], titles_triceps[2], "" + countdownTimeString));
                list.add(new HelperClass(urls_triceps[3], titles_triceps[3], "" + countdownTimeString));
                list.add(new HelperClass(urls_triceps[4], titles_triceps[4], "" + countdownTimeString));
                list.add(new HelperClass(urls_triceps[5], titles_triceps[5], "" + countdownTimeString));
                list.add(new HelperClass(urls_triceps[6], titles_triceps[6], "" + countdownTimeString));
                list.add(new HelperClass(urls_triceps[7], titles_triceps[7], "" + countdownTimeString));
                list.add(new HelperClass(urls_triceps[8], titles_triceps[8], "" + countdownTimeString));
                }

        if(name_exercise_string.equalsIgnoreCase("shoulder")) {
                list.add(new HelperClass(urls_shoulder[0], titles_shoulder[0], "" + countdownTimeString));
                list.add(new HelperClass(urls_shoulder[1], titles_shoulder[1], "" + countdownTimeString));
                list.add(new HelperClass(urls_shoulder[2], titles_shoulder[2], "" + countdownTimeString));
                list.add(new HelperClass(urls_shoulder[3], titles_shoulder[3], "" + countdownTimeString));
                list.add(new HelperClass(urls_shoulder[4], titles_shoulder[4], "" + countdownTimeString));
                list.add(new HelperClass(urls_shoulder[5], titles_shoulder[5], "" + countdownTimeString));
                list.add(new HelperClass(urls_shoulder[6], titles_shoulder[6], "" + countdownTimeString));
                }

        if(name_exercise_string.equalsIgnoreCase("back")) {
                list.add(new HelperClass(urls_backs[0], titles_back[0], "" + countdownTimeString));
                list.add(new HelperClass(urls_backs[1], titles_back[1], "" + countdownTimeString));
                list.add(new HelperClass(urls_backs[2], titles_back[2], "" + countdownTimeString));
                list.add(new HelperClass(urls_backs[3], titles_back[3], "" + countdownTimeString));
                list.add(new HelperClass(urls_backs[4], titles_back[4], "" + countdownTimeString));
                list.add(new HelperClass(urls_backs[5], titles_back[5], "" + countdownTimeString));
                list.add(new HelperClass(urls_backs[6], titles_back[6], "" + countdownTimeString));
                list.add(new HelperClass(urls_backs[7], titles_back[7], "" + countdownTimeString));
                list.add(new HelperClass(urls_backs[8], titles_back[8], "" + countdownTimeString));
                }

        if (name_exercise_string.equalsIgnoreCase("WarmUp")) {
                list.add(new HelperClass(urls_warmup[0], titles_warmup[0], "" + countdownTimeString));
                list.add(new HelperClass(urls_warmup[1], titles_warmup[1], "" + countdownTimeString));
                list.add(new HelperClass(urls_warmup[2], titles_warmup[2], "" + countdownTimeString));
                list.add(new HelperClass(urls_warmup[3], titles_warmup[3], "" + countdownTimeString));
                list.add(new HelperClass(urls_warmup[4], titles_warmup[4], "" + countdownTimeString));
                list.add(new HelperClass(urls_warmup[5], titles_warmup[5], "" + countdownTimeString));
                list.add(new HelperClass(urls_warmup[6], titles_warmup[6], "" + countdownTimeString));
                }


        adapter = new Adapter(list,listerner);
        ExerciseRecyclerView.setAdapter(adapter);

    }


    private void setOnClickLister() {
        listerner = new Adapter.ExerciseOnClickRecycler(){

            @Override
            public void Onclick(View v, int position) {
                Intent intent = new Intent(ExerciseList.this,ShowExercise.class);
                intent.putExtra("showExerciseTitle",list.get(position).getExercise_title());
                intent.putExtra("showExerciseImage",list.get(position).getExercise_image());
                intent.putExtra("Exercise_previous_activity",name_exercise_string);
                intent.putExtra("start","startExercise");
                startActivity(intent);
            }
        };
    }


}