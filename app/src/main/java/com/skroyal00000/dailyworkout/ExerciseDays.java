package com.skroyal00000.dailyworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.skroyal00000.dailyworkout.exercise.ExerciseList;

public class ExerciseDays extends AppCompatActivity {

    GridView gridViewDate;
    int[] dateString;
    String[] txtMessageStr;
    int doneDays;
    boolean isLocked;
    boolean weekOffBoolean = false;
    int whichDayExtra;
    TextView planNameTxt;
    String joinBtnStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_days);
        //getting data from intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            joinBtnStr = extras.getString("JoinBtn");
        }

        gridViewDate = findViewById(R.id.grid_view_date);
        planNameTxt = findViewById(R.id.planName);
        getAllArrayDate();
        gridAdapter adapter = new gridAdapter();
        gridViewDate.setAdapter(adapter);
        OnclickDate();



        planNameTxt.setText(joinBtnStr);


    }

    private void OnclickDate() {
        gridViewDate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int whichDay = dateString[position];
                //Toast.makeText(ExerciseDays.this, " " + dateString[position], Toast.LENGTH_SHORT).show();
                whichDayExtra = whichDay;
                String whichExerciseDo = "";
                int[] oneWeek = {1, 8, 15, 22, 29};
                int[] twoWeek = {2, 9, 16, 23, 30};
                int[] threeWeek = {3, 10, 24, 0, 0};
                int[] fourWeek = {4, 11, 18, 25, 0};
                int[] fiveWeek = {5, 12, 19, 26, 0};
                int[] sixWeek = {6, 13, 20, 27, 0};
                int[] offWeek = {7, 14, 21, 28, 0};

                if (joinBtnStr.equalsIgnoreCase("Beginner")) {

                    for (int i = 0; i < 5; i++) {
                        if (txtMessageStr[position].equalsIgnoreCase("lock")) {
                            Toast.makeText(ExerciseDays.this, "This is lock", Toast.LENGTH_SHORT).show();
                            break;
                        }

                    if (whichDay == oneWeek[i]) {
                        SetExtraBeginner("chest", "1", "bicep", "1", "triceps", "1");
                        break;
                    } else if (whichDay == twoWeek[i]) {
                        SetExtraBeginner("shoulder", "1", "back", "1", "warmup", "1");

                        break;
                    } else if (whichDay == threeWeek[i]) {
                        SetExtraBeginner("chest", "2", "bicep", "2", "triceps", "2");

                        break;
                    } else if (whichDay == fourWeek[i]) {
                        SetExtraBeginner("shoulder", "2", "back", "2", "warmup", "2");

                        break;
                    } else if (whichDay == fiveWeek[i]) {
                        SetExtraBeginner("chest", "3", "bicep", "3", "triceps", "3");

                        break;
                    } else if (whichDay == sixWeek[i]) {
                        SetExtraBeginner("shoulder", "3", "back", "3", "leg", "3");

                        break;
                    } else if (whichDay == offWeek[i]) {
                        SetExtraBeginner("chest", "2", "bicep", "3", "triceps", "1");
                        break;
                    }
                }
            }


//intermediate
                if (joinBtnStr.equalsIgnoreCase("Intermediate")) {
                    for (int i = 0; i < 5; i++) {
                        if (txtMessageStr[position].equalsIgnoreCase("lock")) {
                            Toast.makeText(ExerciseDays.this, "This is lock", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        if (whichDay == oneWeek[i]) {
                            SetExtraIntermediate("chest", "2", "bicep", "2");
                            break;
                        } else if (whichDay == twoWeek[i]) {
                            SetExtraIntermediate("back", "2", "triceps", "2");
                            break;
                        } else if (whichDay == threeWeek[i]) {
                            SetExtraIntermediate("shoulder", "2", "warmup", "2");
                            break;
                        } else if (whichDay == fourWeek[i]) {
                            SetExtraIntermediate("leg", "2", "warmup", "3");
                            break;
                        } else if (whichDay == fiveWeek[i]) {
                            SetExtraIntermediate("chest", "3", "bicep", "3");
                            break;
                        } else if (whichDay == sixWeek[i]) {
                            SetExtraIntermediate("back", "3", "triceps", "3");
                            break;
                        } else if (whichDay == offWeek[i]) {
                            SetExtraIntermediate("chest", "3", "warmup", "3");
                            break;
                        }
                    }
                }

//advance
                if(joinBtnStr.equalsIgnoreCase("Advanced") ){
                    for(int i = 0; i < 5; i++){
                        if (txtMessageStr[position].equalsIgnoreCase("lock")) {
                            Toast.makeText(ExerciseDays.this, "This is lock", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        if (whichDay == oneWeek[i]) {
                            SetExtraAdvance("chest","bicep","triceps");
                            break;
                        }

                        else if( whichDay == twoWeek[i]) {
                            SetExtraAdvance("back","shoulder","warmup");
                            break;
                        }

                        else if( whichDay == threeWeek[i]) {
                            SetExtraSingle("leg");
                            break;
                        }

                        else if(whichDay == fourWeek[i]) {
                            SetExtraAdvance("chest","bicep","triceps");
                            break;
                        }

                        else if(whichDay == fiveWeek[i]) {
                            SetExtraAdvance("back","shoulder","warmup");
                            break;
                        }

                        else if(whichDay == sixWeek[i]) {
                            SetExtraSingle("leg");
                            break;
                        }
                        else if(whichDay == offWeek[i]) {
                            SetExtraAdvance("back","shoulder","warmup");
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
            intent.putExtra("whichDay",whichDayExtra+"");
            intent.putExtra("joinBtn",joinBtnStr);
            finish();
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
            intent.putExtra("whichDay",whichDayExtra + "");
            intent.putExtra("joinBtn",joinBtnStr);
            finish();
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
            intent.putExtra("whichDay",whichDayExtra + "");
            intent.putExtra("joinBtn",joinBtnStr);
            finish();
            startActivity(intent);
        }
    }
    public void SetExtraSingle(String whichT){
        if(!weekOffBoolean && !isLocked){
            Intent intent = new Intent(getApplicationContext(), ExerciseList.class);
            intent.putExtra("join", "4");
            intent.putExtra("whichT",whichT);
            intent.putExtra("level","");
            intent.putExtra("whichT2","");
            intent.putExtra("level2","");
            intent.putExtra("whichT3","");
            intent.putExtra("level3","");
            intent.putExtra("whichDay",whichDayExtra + "");
            intent.putExtra("joinBtn",joinBtnStr);
            finish();
            startActivity(intent);
        }
    }

    private void getAllArrayDate() {
        dateString = new int[30];
        txtMessageStr = new String[30];

        for(int i = 0; i < 30 ; i ++){
                    int j=i;
                    if(joinBtnStr.equalsIgnoreCase("beginner")){
                        if(PrefConfig.loadWhichDayBeginner(ExerciseDays.this) > j+1){
                            dateString[i] = (i+1);
                            txtMessageStr[i] = "Done";
                        } else if (PrefConfig.loadWhichDayBeginner(ExerciseDays.this) == j+1){
                            dateString[i] = (i+1) ;
                            txtMessageStr[i] = "";
                        }else{
                            dateString[i] = (i+1) ;
                            txtMessageStr[i] = "Lock";
                        }
                    }

                    else if(joinBtnStr.equalsIgnoreCase("intermediate")){
                        if(PrefConfig.loadLevelIntermediate(ExerciseDays.this) > j+1){
                        dateString[i] = (i+1);
                        txtMessageStr[i] = "Done";
                    } else if (PrefConfig.loadLevelIntermediate(ExerciseDays.this) == j+1){
                        dateString[i] = (i+1) ;
                        txtMessageStr[i] = "";
                    }else{
                        dateString[i] = (i+1) ;
                        txtMessageStr[i] = "Lock";
                        }
                    }
                    else if(joinBtnStr.equalsIgnoreCase("advanced")){
                        if(PrefConfig.loadLevelAdvanced(ExerciseDays.this) > j+1){
                            dateString[i] = (i+1);
                            txtMessageStr[i] = "Done";
                        } else if (PrefConfig.loadLevelAdvanced(ExerciseDays.this) == j+1){
                            dateString[i] = (i+1) ;
                            txtMessageStr[i] = "";
                        }else{
                            dateString[i] = (i+1) ;
                            txtMessageStr[i] = "Lock";
                        }
                    }

}
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
            TextView txtMessage = view1.findViewById(R.id.textMessage);
            TextView textViewDate = view1.findViewById(R.id.textDate);

            if(txtMessageStr[position].equalsIgnoreCase("Done")){
                txtMessage.setText(txtMessageStr[position]);
                txtMessage.setBackground(ContextCompat.getDrawable(ExerciseDays.this, R.drawable.date_massage_done));
                txtMessage.setWidth(100);
                txtMessage.setTextColor(Color.WHITE);
            }else if(txtMessageStr[position].equalsIgnoreCase("Lock")){
                txtMessage.setText(txtMessageStr[position]);
                txtMessage.setBackground(ContextCompat.getDrawable(ExerciseDays.this, R.drawable.date_massage_lock));

                txtMessage.setWidth(100);
                txtMessage.setTextColor(Color.WHITE);
            }else{
                txtMessage.setText(txtMessageStr[position]);
            }
            String conTxtToStr = String.valueOf(dateString[position]);
            textViewDate.setText(conTxtToStr);
            return view1;
        }
    }
}