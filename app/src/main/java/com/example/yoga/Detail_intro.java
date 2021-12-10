package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class Detail_intro extends AppCompatActivity {
    private Button btnChangeTxt, backbtn,finishbtn;
    private ViewSwitcher viewSwitcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_intro);

        btnChangeTxt = findViewById(R.id.nextbtn);
        backbtn = (Button) findViewById(R.id.btnback);
        viewSwitcher = (ViewSwitcher) findViewById(R.id.switcher);
        finishbtn = (Button) findViewById(R.id.finishbtn);

        Animation in = AnimationUtils.loadAnimation(Detail_intro.this,android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(Detail_intro.this,android.R.anim.slide_out_right);

        viewSwitcher.setAnimation(in);
        viewSwitcher.setAnimation(out);
        btnChangeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewSwitcher.showNext();
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewSwitcher.showPrevious();
            }
        });

    finishbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Detail_intro.this,HomePage.class);
            startActivity(intent);

        }
    });
    }
}