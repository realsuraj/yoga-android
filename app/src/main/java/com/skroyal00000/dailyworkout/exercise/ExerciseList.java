package com.skroyal00000.dailyworkout.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.skroyal00000.dailyworkout.ExerciseListAdapter.Adapter;
import com.skroyal00000.dailyworkout.ExerciseListAdapter.HelperClass;
import com.skroyal00000.dailyworkout.PrefConfig;
import com.skroyal00000.dailyworkout.R;

import java.util.ArrayList;

public class ExerciseList extends AppCompatActivity {

    RecyclerView ExerciseRecyclerView;
    RecyclerView.Adapter adapter;
    String name_exercise_string;
    Adapter.ExerciseOnClickRecycler adapterRecycler;
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
        ExerciseRecyclerView = (RecyclerView) findViewById(R.id.exercise_list_recyclerView);
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
        ArrayList<HelperClass> allExercise;
        allExercise = list;
        String testimage = allExercise.get(1).getExercise_image();
        Toast.makeText(ExerciseList.this, " " + testimage, Toast.LENGTH_SHORT).show();

        ArrayList<HelperClass> finalAllExercise = allExercise;
        btn_start_all_exercise.setOnClickListener(v -> {
            Intent intent = new Intent(ExerciseList.this, ShowExerciseAll.class);
            intent.putExtra("Exercise", finalAllExercise);
            startActivity(intent);
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
    }


    public void Lists(){
        setOnClickLister();
        ExerciseRecyclerView.setHasFixedSize(true);
        ExerciseRecyclerView.setLayoutManager(new LinearLayoutManager(ExerciseList.this,LinearLayoutManager.VERTICAL,false));

        list = new ArrayList<>();

        //beginner

        if(name_exercise_string.equalsIgnoreCase("bicep or chest or triceps") ) {
            for(int i=0;i<urls_biceps.length - 1;i++){
                list.add(new HelperClass(urls_biceps[i], titles_biceps[i], "" + countdownTimeString));
            }
            for(int i=0;i<urls_chest.length - 1;i++) {
                list.add(new HelperClass(urls_chest[i],titles_chest[i] , "" + countdownTimeString));
            }
            for(int i=0;i<urls_triceps.length - 1;i++) {
                list.add(new HelperClass(urls_triceps[i],titles_triceps[i] , "" + countdownTimeString));
            }

        }

        if(name_exercise_string.equalsIgnoreCase("shoulder or back") ) {
            for(int i=0;i<urls_shoulder.length - 1;i++){
                list.add(new HelperClass(urls_shoulder[i], titles_shoulder[i], "" + countdownTimeString));
            }
            for(int i=0;i<urls_backs.length - 1;i++) {
                list.add(new HelperClass(urls_backs[i],titles_back[i] , "" + countdownTimeString));
            }
        }

        if(name_exercise_string.equalsIgnoreCase("chest or shoulder") ) {
            for(int i=0;i<urls_chest.length - 1;i++){
                list.add(new HelperClass(urls_chest[i], titles_chest[i], "" + countdownTimeString));
            }
            for(int i=0;i<urls_shoulder.length - 1;i++) {
                list.add(new HelperClass(urls_shoulder[i],titles_shoulder[i] , "" + countdownTimeString));
            }
        }

        if(name_exercise_string.equalsIgnoreCase("bicep or triceps or back") ) {
            for(int i=0;i<urls_biceps.length - 1;i++){
                list.add(new HelperClass(urls_biceps[i], titles_biceps[i], "" + countdownTimeString));
            }
            for(int i=0;i<urls_triceps.length - 1;i++) {
                list.add(new HelperClass(urls_triceps[i],titles_triceps[i] , "" + countdownTimeString));
            }
            for(int i=0;i<urls_backs.length - 1;i++) {
                list.add(new HelperClass(urls_backs[i],titles_back[i] , "" + countdownTimeString));
            }
        }

        if(name_exercise_string.equalsIgnoreCase("chest or back") ) {
            for(int i=0;i<urls_chest.length - 1;i++){
                list.add(new HelperClass(urls_chest[i], titles_chest[i], "" + countdownTimeString));
            }
            for(int i=0;i<urls_backs.length - 1;i++) {
                list.add(new HelperClass(urls_backs[i],titles_back[i] , "" + countdownTimeString));
            }
        }


        // intermediate

        if(name_exercise_string.equalsIgnoreCase("bicep") ) {
            for(int i=0;i<urls_biceps.length - 1;i++){
                list.add(new HelperClass(urls_biceps[i], titles_biceps[i], "" + countdownTimeString));
            }

        }

        if(name_exercise_string.equalsIgnoreCase("chest")) {
            for(int i=0;i<urls_chest.length - 1;i++) {
                list.add(new HelperClass(urls_chest[i],titles_chest[i] , "" + countdownTimeString));
            }
        }

        if(name_exercise_string.equalsIgnoreCase("leg")) {

            for(int i=0;i<urls_legs.length - 1;i++) {
                list.add(new HelperClass(urls_legs[i], titles_legs[i], "" + countdownTimeString));
            }
        }

        if(name_exercise_string.equalsIgnoreCase("triceps")) {
            for(int i=0;i<urls_triceps.length - 1;i++) {
                list.add(new HelperClass(urls_triceps[i], titles_triceps[i], "" + countdownTimeString));
            }
        }

        if(name_exercise_string.equalsIgnoreCase("shoulder")) {
            for(int i=0;i<urls_shoulder.length - 1;i++) {
                list.add(new HelperClass(urls_shoulder[i], titles_shoulder[i], "" + countdownTimeString));
            }
        }

        if(name_exercise_string.equalsIgnoreCase("back")) {
            for(int i=0;i<urls_backs.length - 1;i++) {
                list.add(new HelperClass(urls_backs[i], titles_back[i], "" + countdownTimeString));
            }
        }

        if (name_exercise_string.equalsIgnoreCase("WarmUp")) {
            for (int i = 0; i < urls_warmup.length - 1; i++) {
                list.add(new HelperClass(urls_warmup[i], titles_warmup[i], "" + countdownTimeString));
            }
        }

        //advanced
        if (name_exercise_string.equalsIgnoreCase("push")) {
            for(int i=0;i<urls_chest.length - 1;i++) {
                list.add(new HelperClass(urls_chest[i],titles_chest[i] , "" + countdownTimeString));
            }
            for(int i=0;i<urls_shoulder.length - 1;i++) {
                list.add(new HelperClass(urls_shoulder[i], titles_shoulder[i], "" + countdownTimeString));
            }
            for(int i=0;i<urls_triceps.length - 1;i++) {
                list.add(new HelperClass(urls_triceps[i], titles_triceps[i], "" + countdownTimeString));
            }

        }

        if (name_exercise_string.equalsIgnoreCase("pull")) {
            for(int i=0;i<urls_backs.length - 1;i++) {
                list.add(new HelperClass(urls_backs[i], titles_back[i], "" + countdownTimeString));
            }
            for(int i=0;i<urls_biceps.length - 1;i++){
                list.add(new HelperClass(urls_biceps[i], titles_biceps[i], "" + countdownTimeString));
            }


        }
        adapter = new Adapter(list, adapterRecycler);
        ExerciseRecyclerView.setAdapter(adapter);

    }


    private void setOnClickLister() {
        adapterRecycler = (v, position) -> {
            Intent intent = new Intent(ExerciseList.this, ShowExercise.class);
            intent.putExtra("showExerciseTitle",list.get(position).getExercise_title());
            intent.putExtra("showExerciseImage",list.get(position).getExercise_image());
            intent.putExtra("Exercise_previous_activity",name_exercise_string);
            intent.putExtra("start","startExercise");
            startActivity(intent);
        };
    }


}