package com.example.yoga;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class Setting extends AppCompatActivity{

    int setsCountChanged,CountdownTimeChanged;
    LinearLayout restbtn,countdownbtn;
    SharedPreferences sharedPreferences;
    TextView setsCounttxt;
    TextView countdownTimetxt;
    SwitchCompat soundOnOffBtn,musicOnOffBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setsCountChanged = PrefConfig.loadSettingSetsCount(Setting.this);


        restbtn = (LinearLayout) findViewById(R.id.setting_rest_btn_ly);
        countdownbtn = (LinearLayout) findViewById(R.id.setting_countdown_btn_ly);
        setsCounttxt = findViewById(R.id.rest_time_textview);
        countdownTimetxt = findViewById(R.id.Countdown_txt);
        soundOnOffBtn = findViewById(R.id.soundOnOffbtn);
        musicOnOffBtn = findViewById(R.id.musicOnOffbtn);

        setsCounttxt.setText("" + PrefConfig.loadSettingSetsCount(this));
        countdownTimetxt.setText("" + PrefConfig.loadSettingCountDown(this));


       SetsOnClick();
       CountDownOnclick();
       soundOnClick();
       musicOnclick();


        soundOnOffBtn.setChecked(PrefConfig.loadIsSoundOn(this));
        musicOnOffBtn.setChecked(PrefConfig.loadIsMusicOn(this));
    }

    private void soundOnClick() {
        soundOnOffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundOnOffBtn.isChecked()){
                    PrefConfig.saveIsSoundOn(Setting.this,true);
                }
                else{
                    PrefConfig.saveIsSoundOn(Setting.this,false);

                }

            }
        });
    }

    private void musicOnclick() {

        musicOnOffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(musicOnOffBtn.isChecked()){
                    PrefConfig.saveIsMusicOn(Setting.this,true);
                }
                else
                {
                    PrefConfig.saveIsMusicOn(Setting.this,false);

                }

            }
        });
    }

    private void CountDownOnclick() {
        countdownbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder d = new AlertDialog.Builder(Setting.this);
                LayoutInflater inflater = Setting.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.setting_time_dialogbox, null);
                d.setTitle("Title");
                d.setMessage("Message");
                d.setView(dialogView);
                final NumberPicker numberPicker = (NumberPicker) dialogView.findViewById(R.id.dialog_number_picker);
                numberPicker.setMaxValue(50);
                numberPicker.setMinValue(1);
                numberPicker.setWrapSelectorWheel(false);
                numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        Log.d("", "onValueChange: ");
                    }
                });
                d.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CountdownTimeChanged = numberPicker.getValue();
                        PrefConfig.saveSettingCountdownTimeInPref(Setting.this,CountdownTimeChanged);
                        countdownTimetxt.setText("" + CountdownTimeChanged);
                        Log.d("", "onClick: " + numberPicker.getValue());
                    }
                });
                d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog alertDialog = d.create();
                alertDialog.show();


            }
        });
    }

    private void SetsOnClick() {
        restbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder d = new AlertDialog.Builder(Setting.this);
                LayoutInflater inflater = Setting.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.setting_time_dialogbox, null);
                d.setTitle("Title");
                d.setMessage("Message");
                d.setView(dialogView);
                final NumberPicker numberPicker = (NumberPicker) dialogView.findViewById(R.id.dialog_number_picker);
                numberPicker.setMaxValue(50);
                numberPicker.setMinValue(1);
                numberPicker.setWrapSelectorWheel(false);
                numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        Log.d("", "onValueChange: ");
                    }
                });
                d.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setsCountChanged = numberPicker.getValue();
                        PrefConfig.saveSettingSetsCountInPref(getApplicationContext(), setsCountChanged);
                        setsCounttxt.setText("" + setsCountChanged);
                    }
                });
                d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog alertDialog = d.create();
                alertDialog.show();

            }
        });
    }

}