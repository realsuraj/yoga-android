package com.example.yoga;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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
    private int exerciseManager = 1;
    private ArcProgress arcProgress;
    private String showExerciseExtra,showImageExtra,urlImage;
    ImageView gifDrawable;
    private Button cancel_btn,next_btn;
    boolean continuetime = true;
    String getExercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_exercise);

        arcProgress = findViewById(R.id.arcprogress);
        play_time_btn = findViewById(R.id.play_time_btn);
        exercise_image =(ImageView) findViewById(R.id.exercise_imageview);
        exercise_title = findViewById(R.id.exercise_title);
        exercise_description = findViewById(R.id.exercise_description);
        cancel_btn = findViewById(R.id.cancel_btn);
        next_btn = findViewById(R.id.next_btn);
        arcProgress.setSuffixText("");

        getExtra();
        play_time_btn_on_click();
        ExerciseManager();
        cancelBtnPress();
        nextBtnPress();



    }

    private void nextBtnPress() {
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ShowExercise.this, ShowExerciseAll.class);
                intent.putExtra("Exercise",getExercise);
                startActivity(intent);
            }
        });

    }


    private void cancelBtnPress() {
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ShowExercise.this)
                        .setTitle("Stop the exercise")
                        .setMessage("Are you sure to stop the exercise")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                continuetime = false;
                                countDownTimer.cancel();
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.cancel,null)
                        .show();
            }
        });
    }

    int countfinish = 1;

    @Override
    public void onBackPressed() {
        if(countfinish == 2)
        {
            continuetime = false;
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
            getExercise = extra.getString("Exercise_previous_activity");
            showExerciseExtra = extra.getString("showExerciseTitle");
            showImageExtra = extra.getString("showExerciseImage");
        }
    }

    private void ExerciseManager() {


         if(continuetime)
         {
             switch (exerciseManager)
             {
                 case 1:
                     NextExercise("bicep","1/3",20);
                     break;

                 case 2:
                     NextExercise("Chest","2/3",10);
                     break;

                 case 3:
                     NextExercise("Chest","3/3",10);
                     break;

                 default:
                     Toast.makeText(ShowExercise.this,"done",Toast.LENGTH_SHORT).show();
             }
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

    private void NextExercise(String title,String howmanyExercise,int timer) {
        urlImage = showImageExtra;
        Glide.with(ShowExercise.this).asGif().load(urlImage).placeholder(R.drawable.blank_image).diskCacheStrategy(DiskCacheStrategy.ALL).into(exercise_image);
        exercise_title.setText(title);
        exercise_description.setText(howmanyExercise);
        countDownClock = timer * 1000;
        CountDown();

    }

}

