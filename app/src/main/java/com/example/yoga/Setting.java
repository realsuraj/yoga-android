package com.example.yoga;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Setting extends AppCompatActivity {

    int restTimeChanged,CountdownTimeChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        LinearLayout restbtn = (LinearLayout) findViewById(R.id.setting_rest_btn_ly);
        LinearLayout countdownbtn = (LinearLayout) findViewById(R.id.setting_countdown_btn_ly);
        TextView resttimetxt = findViewById(R.id.rest_time_textview);
        TextView countdownTime = findViewById(R.id.Countdown_txt);

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
                        resttimetxt.setText("" + numberPicker.getValue());
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
                        countdownTime.setText("" + numberPicker.getValue());
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
}