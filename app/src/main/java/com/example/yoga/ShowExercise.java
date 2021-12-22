package com.example.yoga;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.lzyzsd.circleprogress.ArcProgress;

public class ShowExercise extends AppCompatActivity {

    private TextView textView ;
    private ToggleButton play_time_btn;
    private ImageView exercise_image;
    private TextView exercise_title, exercise_description;
    private CountDownTimer countDownTimer;
    private long countDownClock;
    private ArcProgress arcProgress;
    private String showExerciseExtra,showImageExtra,urlImage;
    ImageView gifDrawable;
    private Button cancel_btn,next_btn;
    boolean continuetime = true;
    String getExercise;
    int countdowntime, localSetsCount,localCountdownTime;
    MediaPlayer player;

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
        localSetsCount = PrefConfig.loadSettingSetsCount(this);
        localCountdownTime = PrefConfig.loadSettingCountDown(this);
        soundPlayer("");
        getExtra();
        play_time_btn_on_click();
        ExerciseManager();
        cancelBtnPress();
        nextBtnPress();
        startBackgroundSong();

    }

    private void stopBackgroundSong() {
        if(player != null){
            player.release();
            player = null;
        }
    }

    private void startBackgroundSong() {
        if(!PrefConfig.loadIsMusicOn(ShowExercise.this)) {
            player = MediaPlayer.create(ShowExercise.this,R.raw.song);
            player.seekTo(22000);
            player.setLooping(true);
            player.start();
        }
        else{
            stopBackgroundSong();
        }
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
                                stopBackgroundSong();
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
            stopBackgroundSong();
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
            countdowntime = extra.getInt("CountdownTime");
        }
    }
    int i = 1;
    private void ExerciseManager() {

      if(continuetime)
         {



             if(i <= localSetsCount) {
                 NextExercise("bicep",i +"/" + localSetsCount,localCountdownTime);
                 i++;

             }
             else{
                 DialogBox();
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
    ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);

    public void soundPlayer(String whichsound){
        if(!PrefConfig.loadIsSoundOn(ShowExercise.this)){
            if(whichsound.equalsIgnoreCase("countdown")){
                toneG.startTone(ToneGenerator.TONE_PROP_BEEP, 400);

            }
            if(whichsound.equalsIgnoreCase("finish")){
                toneG.startTone(ToneGenerator.TONE_CDMA_PIP, 1000);

            }

        }
    }


    private void CountDown() {
        arcProgress.setMax((int)countDownClock/1000);
        arcProgress.setProgress(100);
        countDownTimer =  new CountDownTimer(countDownClock, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            arcProgress.setProgress((int)millisUntilFinished/1000);
            soundPlayer("countdown");
        }


        @Override
        public void onFinish() {
            arcProgress.setProgress(0);
            soundPlayer("finish");
            DialogBoxEndTime();

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

    private void DialogBox(){
        new AlertDialog.Builder(ShowExercise.this)
                .setTitle("All Exercise Finished")
                .setMessage("Congratulation you have finished exercise")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        stopBackgroundSong();
                        continuetime = false;
                        countDownTimer.cancel();
                        finish();
                    }
                })
                .show();

    }
 private void DialogBoxEndTime(){
        if(i > localSetsCount)
        {
            ExerciseManager();
        }
        else
        {

            new AlertDialog.Builder(ShowExercise.this)
                    .setTitle("Set " + (i-1) + " Finished")
                    .setMessage("Do you want to continue")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ExerciseManager();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            stopBackgroundSong();
                            continuetime = false;
                            countDownTimer.cancel();
                            finish();
                        }
                    })
                    .show();
        }

    }

}

