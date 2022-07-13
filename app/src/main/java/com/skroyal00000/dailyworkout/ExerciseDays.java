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
                Intent iin= getIntent();
                Bundle b = iin.getExtras();
                String joinBtn = null;
                if(b!=null)
                {
                    joinBtn =(String) b.get("JoinBtn");
                }

                int[] oneWeek = {1,8,15,22,29};
                int[] twoWeek = {2,9,16,23,30};
                int[] threeWeek = {3,10,24,0,0};
                int[] fourWeek = {4,11,18,25,0};
                int[] fiveWeek = {5,12,19,26,0};
                int[] sixWeek = {6,13,20,27,0};
                int[] offWeek = {7,14,21,28,0};
                boolean weekOffBoolean = false;
                Toast.makeText(ExerciseDays.this, " " + joinBtn, Toast.LENGTH_SHORT).show();

                   if(joinBtn.equalsIgnoreCase("Beginner") ){
                      for(int i = 0; i < 5; i++){
                          if (whichDay.equalsIgnoreCase("Day " + oneWeek[i])) {
                              whichExerciseDo = "bicep or chest or triceps";
                              break;
                          }

                          if( whichDay.equalsIgnoreCase("Day " + twoWeek[i])) {
                              whichExerciseDo = "shoulder or back";
                              break;
                          }

                          if( whichDay.equalsIgnoreCase("Day " + threeWeek[i])) {
                              whichExerciseDo = "chest or shoulder";
                              break;
                          }

                          if(whichDay.equalsIgnoreCase("Day " + fourWeek[i])) {
                              whichExerciseDo = "bicep or triceps or back";
                              break;
                          }

                          if(whichDay.equalsIgnoreCase("Day " + fiveWeek[i])) {
                              whichExerciseDo = "chest or back";
                              break;
                          }

                          if(whichDay.equalsIgnoreCase("Day " + sixWeek[i])) {
                              whichExerciseDo = "leg";
                              break;
                          }
                          if(whichDay.equalsIgnoreCase("Day " + offWeek[i])) {
                              weekOffBoolean = true;
                              break;
                          }
                      }
                       Toast.makeText(ExerciseDays.this, " "  + whichExerciseDo, Toast.LENGTH_SHORT).show();


                       if(!weekOffBoolean){
                           Intent intent = new Intent(getApplicationContext(), ExerciseList.class);
                           intent.putExtra("exercise", whichExerciseDo);
                           startActivity(intent);
                       }
                       else {
                           Toast.makeText(ExerciseDays.this, "Take a Break", Toast.LENGTH_SHORT).show();
                       }
                   }


//intermediate
                if(joinBtn.equalsIgnoreCase("Intermediate") ){
                    for(int i = 0; i < 5; i++){
                        if (whichDay.equalsIgnoreCase("Day " + oneWeek[i])) {
                            whichExerciseDo = "chest";
                            break;
                        }

                        if( whichDay.equalsIgnoreCase("Day " + twoWeek[i])) {
                            whichExerciseDo = "back";
                            break;
                        }

                        if( whichDay.equalsIgnoreCase("Day " + threeWeek[i])) {
                            whichExerciseDo = "shoulder";
                            break;
                        }

                        if(whichDay.equalsIgnoreCase("Day " + fourWeek[i])) {
                            whichExerciseDo = "bicep";
                            break;
                        }

                        if(whichDay.equalsIgnoreCase("Day " + fiveWeek[i])) {
                            whichExerciseDo = "triceps";
                            break;
                        }

                        if(whichDay.equalsIgnoreCase("Day " + sixWeek[i])) {
                            whichExerciseDo = "leg";
                            break;
                        }
                    }
                    Toast.makeText(ExerciseDays.this, " "  + whichExerciseDo, Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(ExerciseDays.this, ExerciseList.class);
                    intent.putExtra("exercise", whichExerciseDo);
                    startActivity(intent);
                }
//advance
                if(joinBtn.equalsIgnoreCase("Advanced") ){
                    for(int i = 0; i < 5; i++){
                        if (whichDay.equalsIgnoreCase("Day " + oneWeek[i])) {
                            whichExerciseDo = "push";
                            break;
                        }

                        if( whichDay.equalsIgnoreCase("Day " + twoWeek[i])) {
                            whichExerciseDo = "pull";
                            break;
                        }

                        if( whichDay.equalsIgnoreCase("Day " + threeWeek[i])) {
                            whichExerciseDo = "leg";
                            break;
                        }

                        if(whichDay.equalsIgnoreCase("Day " + fourWeek[i])) {
                            whichExerciseDo = "push";
                            break;
                        }

                        if(whichDay.equalsIgnoreCase("Day " + fiveWeek[i])) {
                            whichExerciseDo = "pull";
                            break;
                        }

                        if(whichDay.equalsIgnoreCase("Day " + sixWeek[i])) {
                            whichExerciseDo = "leg";
                            break;
                        }
                    }
                    Toast.makeText(ExerciseDays.this, " "  + whichExerciseDo, Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(getApplicationContext(), ExerciseList.class);
                    intent.putExtra("exercise", whichExerciseDo);
                    startActivity(intent);
                }
            }
        });
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