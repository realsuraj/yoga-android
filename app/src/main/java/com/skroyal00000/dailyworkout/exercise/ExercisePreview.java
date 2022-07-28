package com.skroyal00000.dailyworkout.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.skroyal00000.dailyworkout.R;
import com.skroyal00000.dailyworkout.Setting;

public class ExercisePreview extends AppCompatActivity {

    private ImageView exercise_image;
    private TextView exercise_title, exercise_description;
    private String title,image,description;
    ImageView settingbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_preview);
        exercise_image =(ImageView) findViewById(R.id.exercisePreviewImage);
        exercise_title = findViewById(R.id.exercisePreviewTitle);
        exercise_description = findViewById(R.id.exercisePreviewDescription);
        getExtra();
        Glide.with(exercise_image).
                load(image).
                placeholder(R.drawable.progess_bar).
                diskCacheStrategy(DiskCacheStrategy.ALL).
                into(exercise_image);
        exercise_title.setText(title);
        exercise_description.setText(description);
    }




    private void getExtra() {
        Bundle extra = getIntent().getExtras();
        if(extra != null){
            title = extra.getString("showExerciseTitle");
            image = extra.getString("showExerciseImage");
            description = extra.getString("description");
        }}
    }

