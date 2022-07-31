package com.skroyal00000.dailyworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.skroyal00000.dailyworkout.exercise.ExerciseList;

public class ExerciseDays extends AppCompatActivity {

    GridView gridViewDate;
    String[] dateString;
    int[]  imageDate;
    int doneDays;
    boolean isLocked;
    boolean weekOffBoolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_days);
        gridViewDate = findViewById(R.id.grid_view_date);
        getAllArrayDate();
        gridAdapter adapter = new gridAdapter();
        gridViewDate.setAdapter(adapter);
        OnclickDate();

    }

    private void OnclickDate() {
        gridViewDate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String whichDay = dateString[position];
                //Toast.makeText(ExerciseDays.this, " " + dateString[position], Toast.LENGTH_SHORT).show();

                String whichExerciseDo = "";

                //getting data from intent
                Intent intent = getIntent();
                Bundle extras = intent.getExtras();
                String joinBtn = null;
                if (extras != null) {
                    joinBtn = (String) extras.get("JoinBtn");
                }


                int[] oneWeek = {1, 8, 15, 22, 29};
                int[] twoWeek = {2, 9, 16, 23, 30};
                int[] threeWeek = {3, 10, 24, 0, 0};
                int[] fourWeek = {4, 11, 18, 25, 0};
                int[] fiveWeek = {5, 12, 19, 26, 0};
                int[] sixWeek = {6, 13, 20, 27, 0};
                int[] offWeek = {7, 14, 21, 28, 0};

                if (joinBtn.equalsIgnoreCase("Beginner")) {

                    for (int i = 0; i < 5; i++) {
                        if (whichDay.equalsIgnoreCase("Day " + oneWeek[i])) {
                            SetExtraBeginner("chest", "1", "bicep", "1", "triceps", "1");
                            break;
                        } else if (whichDay.equalsIgnoreCase("Day " + twoWeek[i])) {
                            SetExtraBeginner("leg", "1", "shoulder", "1", "warmup", "1");

                            break;
                        } else if (whichDay.equalsIgnoreCase("Day " + threeWeek[i])) {
                            SetExtraBeginner("chest", "1", "bicep", "1", "triceps", "1");

                            break;
                        } else if (whichDay.equalsIgnoreCase("Day " + fourWeek[i])) {
                            SetExtraBeginner("chest", "1", "bicep", "1", "triceps", "1");

                            break;
                        } else if (whichDay.equalsIgnoreCase("Day " + fiveWeek[i])) {
                            SetExtraBeginner("chest", "1", "bicep", "1", "triceps", "1");

                            break;
                        } else if (whichDay.equalsIgnoreCase("Day " + sixWeek[i])) {
                            SetExtraBeginner("chest", "1", "bicep", "1", "triceps", "1");

                            break;
                        } else if (whichDay.equalsIgnoreCase("Day " + offWeek[i])) {
                            Toast.makeText(ExerciseDays.this, "Take a Break", Toast.LENGTH_SHORT).show();
                            break;
                        } else {
                            Toast.makeText(ExerciseDays.this, "this locked", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


//intermediate
                if (joinBtn.equalsIgnoreCase("Intermediate")) {
                    for (int i = 0; i < 5; i++) {
                        if (whichDay.equalsIgnoreCase("Day " + oneWeek[i])) {
                            SetExtraIntermediate("chest", "2", "bicep", "2");
                            break;
                        } else if (whichDay.equalsIgnoreCase("Day " + twoWeek[i])) {
                            SetExtraIntermediate("chest", "2", "bicep", "2");
                            break;
                        } else if (whichDay.equalsIgnoreCase("Day " + threeWeek[i])) {
                            SetExtraIntermediate("chest", "2", "bicep", "2");
                            break;
                        } else if (whichDay.equalsIgnoreCase("Day " + fourWeek[i])) {
                            SetExtraIntermediate("chest", "2", "bicep", "2");
                            break;
                        } else if (whichDay.equalsIgnoreCase("Day " + fiveWeek[i])) {
                            SetExtraIntermediate("chest", "2", "bicep", "2");
                            break;
                        } else if (whichDay.equalsIgnoreCase("Day " + sixWeek[i])) {
                            SetExtraIntermediate("chest", "2", "bicep", "2");
                            break;
                        } else if (whichDay.equalsIgnoreCase("Day " + offWeek[i])) {
                            weekOffBoolean = true;
                            Toast.makeText(ExerciseDays.this, "Take a Break", Toast.LENGTH_SHORT).show();
                            break;
                        } else {
                            isLocked = true;
                            Toast.makeText(ExerciseDays.this, "this locked", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }

//advance
                if(joinBtn.equalsIgnoreCase("Advanced") ){
                    for(int i = 0; i < 5; i++){
                        if (whichDay.equalsIgnoreCase("Day " + oneWeek[i])) {
                            SetExtraAdvance("leg","back","triceps");
                            break;
                        }

                        else if( whichDay.equalsIgnoreCase("Day " + twoWeek[i])) {
                            SetExtraAdvance("leg","back","triceps");
                            break;
                        }

                        else if( whichDay.equalsIgnoreCase("Day " + threeWeek[i])) {
                            SetExtraAdvance("leg","back","triceps");
                            break;
                        }

                        else if(whichDay.equalsIgnoreCase("Day " + fourWeek[i])) {
                            SetExtraAdvance("leg","back","triceps");
                            break;
                        }

                        else if(whichDay.equalsIgnoreCase("Day " + fiveWeek[i])) {
                            SetExtraAdvance("leg","back","triceps");
                            break;
                        }

                        else if(whichDay.equalsIgnoreCase("Day " + sixWeek[i])) {
                            SetExtraAdvance("leg","back","triceps");
                            break;
                        }
                        else if(whichDay.equalsIgnoreCase("Day " + offWeek[i])) {
                            weekOffBoolean = true;
                            Toast.makeText(ExerciseDays.this, "Take a Break", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        else{
                            isLocked = true;
                            Toast.makeText(ExerciseDays.this, "this locked", Toast.LENGTH_SHORT).show();
                            break;
                        }

                    }
                }
            }
        });
    }

    public void SetExtraBeginner(String whichT, String level, String whichT2, String level2, String whichT3, String level3){
        if(!weekOffBoolean && !isLocked){
            Intent intent = new Intent(getApplicationContext(), ExerciseList.class);
            intent.putExtra("join", "1");
            intent.putExtra("whichT",whichT);
            intent.putExtra("level",level);
            intent.putExtra("whichT2",whichT2);
            intent.putExtra("level2",level2);
            intent.putExtra("whichT3",whichT3);
            intent.putExtra("level3",level3);
            startActivity(intent);
        }
    }

    public void SetExtraIntermediate(String whichT, String level, String whichT2, String level2){
        if(!weekOffBoolean && !isLocked){
            Intent intent = new Intent(getApplicationContext(), ExerciseList.class);
            intent.putExtra("join", "2");
            intent.putExtra("whichT",whichT);
            intent.putExtra("level",level);
            intent.putExtra("whichT2",whichT2);
            intent.putExtra("level2",level2);
            intent.putExtra("whichT3","");
            intent.putExtra("level3","");
            startActivity(intent);
        }
    }

    public void SetExtraAdvance(String whichT, String whichT2, String whichT3){
        if(!weekOffBoolean && !isLocked){
            Intent intent = new Intent(getApplicationContext(), ExerciseList.class);
            intent.putExtra("join", "3");
            intent.putExtra("whichT",whichT);
            intent.putExtra("level","");
            intent.putExtra("whichT2",whichT2);
            intent.putExtra("level2","");
            intent.putExtra("whichT3","");
            intent.putExtra("level3",whichT3);
            startActivity(intent);
        }
    }

    private void getAllArrayDate() {
        dateString = new String[]
                {"DAY 1","DAY 2","DAY 3","DAY 4","DAY 5","DAY 6","DAY 7",
                  "DAY 8","DAY 9","DAY 10","DAY 11","DAY 12","DAY 13","DAY 14",
                  "DAY 15","DAY 16","DAY 17","DAY 18","DAY 19","DAY 20","DAY 21",
                  "DAY 22","DAY 23","DAY 24","DAY 25","DAY 26","DAY 27","DAY 28","DAY 29","DAY 30"};

        imageDate = new int[]
                {0,0,0,0,0,0,R.drawable.break_icon,
                 0,0,0,0,0,0,R.drawable.break_icon,
                 0,0,0,0,0,0,R.drawable.break_icon,
                 0,0,0,0,0,0,R.drawable.break_icon,0,0
                };
    }

    class gridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return dateString.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view1 = getLayoutInflater().inflate(R.layout.grid_for_date,parent,false);
            ImageView imageBreak = view1.findViewById(R.id.image_date_brake);
            TextView textViewDate = view1.findViewById(R.id.textDate);
            imageBreak.setImageResource(imageDate[position]);
            textViewDate.setText(dateString[position]);
            return view1;
        }
    }
}