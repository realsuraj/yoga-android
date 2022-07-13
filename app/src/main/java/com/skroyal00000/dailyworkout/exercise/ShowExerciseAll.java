package com.skroyal00000.dailyworkout.exercise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
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
import com.skroyal00000.dailyworkout.ExerciseListAdapter.HelperClass;
import com.skroyal00000.dailyworkout.PrefConfig;
import com.skroyal00000.dailyworkout.R;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.skroyal00000.dailyworkout.Setting;

import java.util.ArrayList;

public class ShowExerciseAll extends AppCompatActivity {


    private TextView textView ;
    private ToggleButton play_time_btn;
    private ImageView exercise_image;
    private TextView exercise_title, exercise_description;
    private CountDownTimer countDownTimer;
    private long countDownClock;
    private int LoopExerciseManager = 0;
    private ArcProgress arcProgress;
    ImageView settingBtn;
    private Button cancel_btn,next_btn;
    boolean continuetime = true;
    private String whichExercise = "",urlImage = "",titlesText="";
    String[] chestImageUrls,warmupUrls,bicepUrls,tricepUrls,shoulderUrls,backUrls,legUrls,
            titles_legs,titles_chest,titles_biceps,titles_triceps,titles_shoulder,titles_back,titles_warmup;
    int localSetsTime,localCountdownTime;
    MediaPlayer player,tickTickSoundPlayer,tickFinishSoundPlayer;
    boolean soundplayer = true, ispause = false;

    int exerciseManager = 1;
    int loopCount = 0;
    ArrayList<HelperClass> allExercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_exercise_all);

        arcProgress = findViewById(R.id.arcprogress);
        play_time_btn = findViewById(R.id.play_time_btn);
        exercise_image =(ImageView) findViewById(R.id.exercise_imageview);
        exercise_title = findViewById(R.id.exercise_title);
        exercise_description = findViewById(R.id.exercise_description);
        cancel_btn = findViewById(R.id.cancel_btn);
        next_btn = findViewById(R.id.next_btn);
        settingBtn = findViewById(R.id.settingbtn);
        arcProgress.setSuffixText("");
        localCountdownTime = PrefConfig.loadSettingCountDown(this);
        localSetsTime = PrefConfig.loadSettingSetsCount(this);

        allExercise = (ArrayList<HelperClass>)getIntent().getSerializableExtra("Exercise");

        getExtra();
        gettingArraysValues();
        nextBtnPress();
        play_time_btn_on_click();
        loopExerciseManager();
        cancelBtnPress();
        startBackgroundSong();
        settingbtn();

    }

    private void settingbtn() {
      settingBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              soundPlayer("stopCountdownMusic");
              soundplayer = false;
              stopBackgroundSong();
              continuetime = false;
              countDownTimer.cancel();
              Intent intent = new Intent(ShowExerciseAll.this, Setting.class);
              finish();
              startActivity(intent);
          }
      });
    }

    private void stopBackgroundSong() {
        if(player != null){
            player.release();
            player = null;
        }
    }

    private void startBackgroundSong() {
        if(!PrefConfig.loadIsMusicOn(ShowExerciseAll.this)) {
            player = MediaPlayer.create(ShowExerciseAll.this,R.raw.bgsound);
            player.seekTo(15000);
            player.setLooping(true);
            player.start();
        }
        else{
            stopBackgroundSong();
        }
    }


    public void soundPlayer(String whichsound){
       if(!PrefConfig.loadIsSoundOn(ShowExerciseAll.this)){
           if(whichsound.equalsIgnoreCase("countdownMusic") && tickTickSoundPlayer == null){
               tickTickSoundPlayer = MediaPlayer.create(ShowExerciseAll.this,R.raw.tick);
               tickTickSoundPlayer.start();
           }
           if(whichsound.equalsIgnoreCase("finish")){
               tickFinishSoundPlayer = MediaPlayer.create(ShowExerciseAll.this,R.raw.finish);
               tickFinishSoundPlayer.start();
           }
           if(whichsound.equalsIgnoreCase("stopCountdownMusic")){
               if(tickTickSoundPlayer != null){
                   tickTickSoundPlayer.release();
                   tickTickSoundPlayer = null;
               }

           }
       }

    }


    private void nextBtnPress() {
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loopCount ++;
                exerciseManager = 1;
                countDownTimer.cancel();
                loopExerciseManager();
            }
        });
    }

    private void gettingArraysValues() {
        chestImageUrls = getResources().getStringArray(R.array.chest_urls);
        bicepUrls = getResources().getStringArray(R.array.bicep_urls);
        tricepUrls = getResources().getStringArray(R.array.tricep_urls);
        shoulderUrls = getResources().getStringArray(R.array.shoulder_urls);
        backUrls = getResources().getStringArray(R.array.back_urls);
        legUrls = getResources().getStringArray(R.array.urls_legs);
        warmupUrls = getResources().getStringArray(R.array.warmup_urls);

        titles_legs = getResources().getStringArray(R.array.leg_titles);
        titles_biceps = getResources().getStringArray(R.array.bicep_titles);
        titles_back = getResources().getStringArray(R.array.back_titles);
        titles_chest = getResources().getStringArray(R.array.chest_titles);
        titles_warmup = getResources().getStringArray(R.array.warmup_titles);
        titles_shoulder = getResources().getStringArray(R.array.shoulder_title);
        titles_triceps = getResources().getStringArray(R.array.tricep_titles);
    }


    private void cancelBtnPress() {
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ShowExerciseAll.this)
                        .setTitle("Stop the exercise")
                        .setMessage("Are you sure to stop the exercise")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                soundPlayer("stopCountdownMusic");
                                soundplayer = false;
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
            soundPlayer("stopCountdownMusic");
            soundplayer = false;
            stopBackgroundSong();
            continuetime = false;
            countDownTimer.cancel();
            finish();
            super.onBackPressed();
        }
        else
        {
            Toast.makeText(ShowExerciseAll.this, "once more", Toast.LENGTH_SHORT).show();
            countfinish++;
        }
    }

    private void getExtra() {
        Bundle extra = getIntent().getExtras();

        if(extra != null){
            whichExercise = extra.getString("Exercise");
        }
    }


    private void loopExerciseManager() {


           if(loopCount < allExercise.size())
           {
                   urlImage = allExercise.get(loopCount).getExercise_image();
                   titlesText = allExercise.get(loopCount).getExercise_title();
                   ExerciseManager();
  }
           else{
               DialogBox();
           }
       }
    int i = 1;
    private void ExerciseManager() {
         if(continuetime)
        {

            if(i <= localSetsTime){
                i++;
                NextExercise(titlesText,(i-1) +"/"+localSetsTime,localCountdownTime);

            }
            else {
                loopCount ++;
                i = 1;
                loopExerciseManager();

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
                    soundPlayer("stopCountdownMusic");
                    ispause = true;
                    play_time_btn.setBackgroundResource(R.drawable.pause_icon);
                    countDownTimer.cancel();

                }
                else
                {  Drawable drawable = exercise_image.getDrawable();
                    if(drawable instanceof Animatable){
                        ((Animatable) drawable).start();
                    }
                    play_time_btn.setBackgroundResource(R.drawable.play_icon);
                    soundPlayer("countdownMusic");
                    CountDown();
                    ispause = false;

                }

            }
        });
    }

    long countDownClockTime,milliSecondLeft;

    private void CountDown() {
        arcProgress.setMax((int)countDownClock/1000);
        if(ispause){
            countDownClockTime = milliSecondLeft;
        }
        else{
            countDownClockTime = countDownClock;
        }
        countDownTimer =  new CountDownTimer(countDownClock, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDownClock = millisUntilFinished;
                soundPlayer("countdownMusic");
                arcProgress.setProgress((int)(millisUntilFinished/1000));
            }


            @Override
            public void onFinish() {
                soundPlayer("stopCountdownMusic");
                soundPlayer("finish");
                alertDialogFinishTime();

            }
        }.start();

    }

    private void alertDialogFinishTime() {
        new AlertDialog.Builder(ShowExerciseAll.this)
                .setTitle((i-1) + " Set Finished")
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

    private void NextExercise(String title,String howmanyExercise,int timer) {
        Glide.with(ShowExerciseAll.this).asGif().load(urlImage).placeholder(R.drawable.progess_bar).diskCacheStrategy(DiskCacheStrategy.ALL).into(exercise_image);
        exercise_title.setText(title);
        exercise_description.setText(howmanyExercise);
        countDownClock = timer * 1000;
        CountDown();

    }

    private void DialogBox(){
        new AlertDialog.Builder(ShowExerciseAll.this)
                .setTitle("All Exercise Finished")
                .setMessage("Congratulation you have finished all exercise")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        soundplayer = false;
                        stopBackgroundSong();
                        continuetime = false;
                        countDownTimer.cancel();
                        finish();
                    }
                })
                .show();

}


}