package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashScreen extends AppCompatActivity {

    TextView splashTextview,splashTextview2;
    View view1,view2;
    ImageView splashImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_splash_screen);
        splashTextview = findViewById(R.id.splashText);
        splashTextview2 = findViewById(R.id.splashTextUnder);
        view1 = findViewById(R.id.view1);
        splashImage = findViewById(R.id.splash_img);

        YoYo.with(Techniques.SlideInLeft).duration(2000).playOn(splashTextview);
        YoYo.with(Techniques.FadeIn).duration(1000).playOn(view1);
        YoYo.with(Techniques.FadeInRight).duration(3000).playOn(splashTextview2);
        YoYo.with(Techniques.FadeIn).duration(700).repeat(0).playOn(splashImage);





        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}