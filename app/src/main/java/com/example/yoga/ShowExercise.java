package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.lzyzsd.circleprogress.ArcProgress;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ShowExercise extends AppCompatActivity {

    private TextView textView ;
    private ToggleButton play_time_btn;
    private ImageView exercise_image;
    private TextView exercise_title, exercise_description;
    private CountDownTimer countDownTimer;
    private long countDownClock;
    private int exerciseManager = 0;
    private ArcProgress arcProgress;
    private String showExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_exercise);

        arcProgress = findViewById(R.id.arcprogress);
        play_time_btn = findViewById(R.id.play_time_btn);
        exercise_image = findViewById(R.id.exercise_imageview);
        exercise_title = findViewById(R.id.exercise_title);
        exercise_description = findViewById(R.id.exercise_description);
        arcProgress.setSuffixText("");
        countDownClock = 5000;

        getExtra();
        Toast.makeText(ShowExercise.this,"" + showExercise,Toast.LENGTH_SHORT).show();
        CountDown();
        play_time_btn_on_click();
        ExerciseManager();

    }

    private void getExtra() {
        Bundle extra = getIntent().getExtras();
        if(extra != null){
            showExercise = extra.getString("showExercise");
        }
    }

    private void ExerciseManager() {
       switch (exerciseManager)
       {
           case 1:
                NextExercise("https://picsum.photos/100?image=50","bicep","10/7",20);
                break;
           case 2:
                NextExercise("https://picsum.photos/100?image=55","Chest","11/7",10);
                break;
           default:
               Toast.makeText(ShowExercise.this,"done",Toast.LENGTH_SHORT).show();
       }


    }

    private void play_time_btn_on_click() {
        play_time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(play_time_btn.isChecked())
                {
                    countDownTimer.cancel();

                }
                else
                {
                        CountDown();
                }

            }
        });
    }

    private void CountDown() {
        arcProgress.setMax((int)countDownClock/1000);
        countDownTimer =  new CountDownTimer(countDownClock, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            countDownClock = millisUntilFinished;

            arcProgress.setProgress((int)(millisUntilFinished/1000));
        }


        @Override
        public void onFinish() {
            exerciseManager += 1;
            ExerciseManager();

        }
    }.start();

    }

    private void NextExercise(String url,String title,String howmanyExercise,int timer) {
        Glide.with(ShowExercise.this).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(exercise_image);

        exercise_title.setText(title);
        exercise_description.setText(howmanyExercise);
        countDownClock = timer * 1000;
        CountDown();

    }

}

