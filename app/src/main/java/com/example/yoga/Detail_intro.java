package com.example.yoga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class Detail_intro extends AppCompatActivity {
    private Button btnChangeTxt, backbtn,finishbtn;
    private ViewSwitcher viewSwitcher;
    private RadioGroup radio_grp_gender,radio_grp_level;
    private RadioButton gender_selection_radio_btn, level_selection_radio_btn;
    private SharedPreferences sharedPreferences;

    private String gender_string,level_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_intro);

        btnChangeTxt = findViewById(R.id.nextbtn);
        backbtn = (Button) findViewById(R.id.btnback);
        viewSwitcher = (ViewSwitcher) findViewById(R.id.switcher);
        finishbtn = (Button) findViewById(R.id.finishbtn);
        radio_grp_gender = (RadioGroup) findViewById(R.id.radio_grp_gender);
        radio_grp_level = (RadioGroup) findViewById(R.id.radio_grp_level);



        sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);



        Animation in = AnimationUtils.loadAnimation(Detail_intro.this,android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(Detail_intro.this,android.R.anim.slide_out_right);

        viewSwitcher.setAnimation(in);
        viewSwitcher.setAnimation(out);


        btnChangeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               gender_selecion();

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

            level_selection();



        }
    });
    }

    public void gender_selecion(){
       if(radio_grp_gender.getCheckedRadioButtonId() == -1)
       {
         Toast.makeText(this,"please select gender",Toast.LENGTH_SHORT).show();
       }
       else
       {
           int radioId = radio_grp_gender.getCheckedRadioButtonId();
           gender_selection_radio_btn = findViewById(radioId);
           gender_string = "" + gender_selection_radio_btn.getText();
           Toast.makeText(Detail_intro.this,"selected radio btn: " + gender_string,Toast.LENGTH_SHORT).show();
           viewSwitcher.showNext();
       }




    }
    public void level_selection(){
      if(radio_grp_level.getCheckedRadioButtonId() == -1)
      {
          Toast.makeText(this,"please select level",Toast.LENGTH_SHORT).show();
      }
      else
      {
          int radioId = radio_grp_level.getCheckedRadioButtonId();
          level_selection_radio_btn = findViewById(radioId);
          level_string = "" + level_selection_radio_btn.getText();

          SharedPreferences.Editor editor = sharedPreferences.edit();
          editor.putString("level", gender_string);
          editor.putString("gender",level_string);
          editor.apply();

          Toast.makeText(Detail_intro.this,"selected radio btn" + level_string,Toast.LENGTH_SHORT).show();

          Intent intent = new Intent(Detail_intro.this,HomePage.class);
          startActivity(intent);
      }


    }

}