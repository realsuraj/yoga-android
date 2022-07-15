package com.skroyal00000.dailyworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skroyal00000.dailyworkout.Detail.Detail_intro;
import com.skroyal00000.dailyworkout.Home.ChildItem;
import com.skroyal00000.dailyworkout.Home.ParentItem;
import com.skroyal00000.dailyworkout.Home.ParentItemAdapter;
import com.skroyal00000.dailyworkout.Home.Repo.FirebaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private Button begginerBtnJoin, intermediateBtnJoin, advanceBtnJoin;
    String[] workoutUrl;
    String[] chestImageUrls,warmupUrls,bicepUrls,tricepUrls,shoulderUrls,backUrls,legUrls;
    ImageView settingImageview,custom_btn;
    String[] aTitle,aImageUrl,aMiniIcon1,aMiniIcon2,aMiniTitle1,aMiniTitle2;
//    Recycler
    RecyclerView parentRecyclerView ;
    private FirebaseViewModel firebaseViewModel;
    private ParentItemAdapter parentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        begginerBtnJoin = (Button) findViewById(R.id.joinNowDate);
        intermediateBtnJoin = (Button) findViewById(R.id.joinNowDate2);
        advanceBtnJoin = (Button) findViewById(R.id.joinNowDate3);
        settingImageview = (ImageView)  findViewById(R.id.setting_imageview);
        custom_btn = findViewById(R.id.create_custom_plan_btn);
        workoutUrl = getResources().getStringArray(R.array.home_page_icons_urls);

        parentRecyclerView = findViewById(R.id.parentRecyclerView);


        parentRecyclerView.setHasFixedSize(true);
        parentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        parentAdapter = new ParentItemAdapter();
        parentRecyclerView.setAdapter(parentAdapter);


        firebaseViewModel = new ViewModelProvider(this).get(FirebaseViewModel.class);

        firebaseViewModel.getAllData();
        firebaseViewModel.getParentItemMutableLiveData().observe(this, new Observer<List<ParentItem>>() {
            @Override
            public void onChanged(List<ParentItem> parentItemList) {
                parentAdapter.setParentItemList(parentItemList);
                parentAdapter.notifyDataSetChanged();
            }
        });
        firebaseViewModel.getDatabaseErrorMutableLiveData().observe(this, new Observer<DatabaseError>() {
            @Override
            public void onChanged(DatabaseError error) {
                Toast.makeText(HomePage.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
       //firebase things


        BeginnerJoinFunc();
        SettingImageButton();
        custombtnPressed();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("GymTab");


    }


    private void custombtnPressed() {
        custom_btn.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, Detail_intro.class);
            intent.putExtra("customPressed","Custom_pressed");
            startActivity(intent);
        });
    }

    private void SettingImageButton() {
        settingImageview.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this,Setting.class);
            startActivity(intent);
        });
    }




    private void BeginnerJoinFunc() {
        begginerBtnJoin.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this,ExerciseDays.class);
            intent.putExtra("JoinBtn","Beginner");
            startActivity(intent);
        });
        intermediateBtnJoin.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this,ExerciseDays.class);
            intent.putExtra("JoinBtn","Intermediate");
            startActivity(intent);
        });
        advanceBtnJoin.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this,ExerciseDays.class);
            intent.putExtra("JoinBtn","Advanced");
            startActivity(intent);
        });
    }

}