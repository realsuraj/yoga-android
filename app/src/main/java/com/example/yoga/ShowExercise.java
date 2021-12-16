package com.example.yoga;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.media.Image;
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
    private String showExerciseExtra,showImageExtra;
    ImageView gifDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_exercise);

        arcProgress = findViewById(R.id.arcprogress);
        play_time_btn = findViewById(R.id.play_time_btn);
        exercise_image =(ImageView) findViewById(R.id.exercise_imageview);
        exercise_title = findViewById(R.id.exercise_title);
        exercise_description = findViewById(R.id.exercise_description);
        arcProgress.setSuffixText("");
        countDownClock = 5000;



        getExtra();
        Toast.makeText(ShowExercise.this,"" + showExerciseExtra,Toast.LENGTH_SHORT).show();
        CountDown();
        play_time_btn_on_click();
        ExerciseManager();


    }

    int countfinish = 1;

    @Override
    public void onBackPressed() {
        if(countfinish == 2)
        {
            countDownTimer.cancel();
            finish();
            super.onBackPressed();
        }
        else
        {
            Toast.makeText(ShowExercise.this, "once more", Toast.LENGTH_SHORT).show();
            countfinish++;
        }
    }

    private void getExtra() {
        Bundle extra = getIntent().getExtras();
        if(extra != null){
            showExerciseExtra = extra.getString("showExerciseTitle");
            showImageExtra = extra.getString("showExerciseImage");
        }
    }

    private void ExerciseManager() {
       switch (exerciseManager)
       {
           case 1:
                NextExercise(showImageExtra,"bicep","1/3",20);
                break;
           case 2:
                NextExercise(showImageExtra,"Chest","2/3",10);
                break;
           case 3:
                NextExercise(showImageExtra,"Chest","3/3",10);
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
                    Drawable drawable = exercise_image.getDrawable();
                    if(drawable instanceof Animatable){
                        ((Animatable) drawable).stop();
                    }
                    play_time_btn.setBackgroundResource(R.drawable.pause_icon);
                    countDownTimer.cancel();

                }
                else
                {  Drawable drawable = exercise_image.getDrawable();
                    if(drawable instanceof Animatable){
                        ((Animatable) drawable).start();
                    }
                    play_time_btn.setBackgroundResource(R.drawable.play_icon);
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
        Glide.with(ShowExercise.this).asGif().load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(exercise_image);

        exercise_title.setText(title);
        exercise_description.setText(howmanyExercise);
        countDownClock = timer * 1000;
        CountDown();

    }

}

