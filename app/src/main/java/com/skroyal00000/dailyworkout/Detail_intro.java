package com.skroyal00000.dailyworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.skroyal00000.dailyworkout.R;

public class Detail_intro extends AppCompatActivity {
    private Button btnChangeTxt,finishbtn;
    private TextView btnskip;
    private ViewSwitcher viewSwitcher;
    private RadioGroup radio_grp_gender,radio_grp_level;
    private RadioButton gender_selection_radio_btn, level_selection_radio_btn;
    private ImageView backbtn;
    private String gender_string,level_string,previousExtrasValues = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_intro);

        btnChangeTxt = findViewById(R.id.nextbtn);
        backbtn = (ImageView) findViewById(R.id.btnback);
        viewSwitcher = (ViewSwitcher) findViewById(R.id.switcher);
        finishbtn = (Button) findViewById(R.id.finishbtn);
        radio_grp_gender = (RadioGroup) findViewById(R.id.radio_grp_gender);
        radio_grp_level = (RadioGroup) findViewById(R.id.radio_grp_level);
        btnskip = (TextView) findViewById(R.id.btnskip);
        Animation fade = AnimationUtils.loadAnimation(Detail_intro.this,android.R.anim.fade_in);

        viewSwitcher.setAnimation(fade);

        getExtras();

        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             nextExtaDepandActivity();
            }
        });

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

    if(PrefConfig.loadGender(Detail_intro.this) != null && PrefConfig.loadLevel(Detail_intro.this) != null){
        if( previousExtrasValues.equalsIgnoreCase("Custom_pressed")){
            Intent intent = new Intent(Detail_intro.this, ExerciseDays.class);
            startActivity(intent);
            finish();
        }
    }
    }
    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            previousExtrasValues = extras.getString("customPressed");

        }
    }
    private void nextExtaDepandActivity() {
        if (previousExtrasValues.equalsIgnoreCase("Custom_pressed")){
            Intent intent = new Intent(Detail_intro.this, ExerciseDays.class);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent(Detail_intro.this,HomePage.class);
            startActivity(intent);
            finish();

        }
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
           PrefConfig.saveGender(Detail_intro.this,gender_string);
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
          PrefConfig.saveLevel(Detail_intro.this,level_string);
          nextExtaDepandActivity();

      }


    }




}