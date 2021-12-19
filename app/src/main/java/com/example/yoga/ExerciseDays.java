package com.example.yoga;

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



        gridViewDate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ExerciseList.class);
                intent.putExtra("exercise","day");
                startActivity(intent);
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
                {0,0,0,0,0,0,R.drawable.break_icon,0,0,R.drawable.break_icon,
                 0,0,0,0,0,0,R.drawable.break_icon,0,0,R.drawable.break_icon,
                 0,0,0,0,0,0,R.drawable.break_icon,0,0,R.drawable.break_icon};
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