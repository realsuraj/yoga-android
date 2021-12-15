package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.ArcProgress;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ShowExercise extends AppCompatActivity {

    TextView textView ;
    int i=100;
    ArcProgress arcProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_exercise);
        arcProgress = findViewById(R.id.arcprogress);
        CountDown(30);

    }

    private void CountDown(int countdown) {
        arcProgress.setMax(countdown);
    new CountDownTimer(countdown*1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            arcProgress.setSuffixText("");
            arcProgress.setProgress((int)(millisUntilFinished/1000));

        }

        @Override
        public void onFinish() {

        }
    }.start();

    }



    public void UpdateProgressbar(){

    }
}

