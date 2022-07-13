package com.skroyal00000.dailyworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.skroyal00000.dailyworkout.Detail.Detail_intro;
import com.skroyal00000.dailyworkout.Home.ChildItem;
import com.skroyal00000.dailyworkout.Home.ParentItem;
import com.skroyal00000.dailyworkout.Home.ParentItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private Button begginerBtnJoin, intermediateBtnJoin, advanceBtnJoin;
    String[] workoutUrl;
    String[] chestImageUrls,warmupUrls,bicepUrls,tricepUrls,shoulderUrls,backUrls,legUrls;
    ImageView settingImageview,custom_btn;


//    Recycler

    RecyclerView ParentRecyclerViewItem ;

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

        ParentRecyclerViewItem = findViewById(R.id.parentRecyclerView);

        // Initialise the Linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(HomePage.this);
        // Pass the arguments
        // to the parentItemAdapter.
        // These arguments are passed
        // using a method ParentItemList()
        ParentItemAdapter parentItemAdapter = new ParentItemAdapter(ParentItemList());
        // Set the layout manager
        // and adapter for items
        // of the parent recyclerview
        ParentRecyclerViewItem.setAdapter(parentItemAdapter);
        ParentRecyclerViewItem.setLayoutManager(layoutManager);
        ParentRecyclerViewItem.suppressLayout(true);


        BeginnerJoinFunc();
        gettingArraysValues();
        featuredRecycler();
        SettingImageButton();
        custombtnPressed();
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


    private void gettingArraysValues() {
        chestImageUrls = getResources().getStringArray(R.array.chest_urls);
        bicepUrls = getResources().getStringArray(R.array.bicep_urls);
        tricepUrls = getResources().getStringArray(R.array.tricep_urls);
        shoulderUrls = getResources().getStringArray(R.array.shoulder_urls);
        backUrls = getResources().getStringArray(R.array.back_urls);
        legUrls = getResources().getStringArray(R.array.urls_legs);
        warmupUrls = getResources().getStringArray(R.array.warmup_urls);
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

    public void featuredRecycler() {
        setOnClickLister();

    }

    private List<ParentItem> ParentItemList()
    {
        List<ParentItem> itemList
                = new ArrayList<>();

        ParentItem item
                = new ParentItem(
                "Body focus",
                ChildItemList());
        itemList.add(item);
        ParentItem item1
                = new ParentItem(
                "Nearby Gym",
                ChildItemList());
        itemList.add(item1);
        ParentItem item2
                = new ParentItem(
                "Clothes",
                ChildItemList());
        itemList.add(item2);
        ParentItem item3
                = new ParentItem(
                "Equipment",
                ChildItemList());
        itemList.add(item3);

        return itemList;
    }

    private List<ChildItem> ChildItemList()
    {
        List<ChildItem> ChildItemList
                = new ArrayList<>();

        ChildItemList.add(new ChildItem(workoutUrl[0],"WarmUp","12:00","9 Sets","https://cdn.icon-icons.com/icons2/510/PNG/512/at_icon-icons.com_50456.png","https://cdn.icon-icons.com/icons2/510/PNG/512/at_icon-icons.com_50456.png"));
        ChildItemList.add(new ChildItem(workoutUrl[1],"Chest","12:00","9 Sets","https://cdn.icon-icons.com/icons2/510/PNG/512/at_icon-icons.com_50456.png","https://cdn.icon-icons.com/icons2/510/PNG/512/at_icon-icons.com_50456.png"));
        ChildItemList.add(new ChildItem(workoutUrl[2],"Bicep","12:00","9 Sets","https://cdn.icon-icons.com/icons2/510/PNG/512/at_icon-icons.com_50456.png","https://cdn.icon-icons.com/icons2/510/PNG/512/at_icon-icons.com_50456.png"));
        ChildItemList.add(new ChildItem(workoutUrl[3],"Triceps","12:00","9 Sets","https://cdn.icon-icons.com/icons2/510/PNG/512/at_icon-icons.com_50456.png","https://cdn.icon-icons.com/icons2/510/PNG/512/at_icon-icons.com_50456.png"));
        ChildItemList.add(new ChildItem(workoutUrl[4],"Shoulder","12:00","9 Sets","https://cdn.icon-icons.com/icons2/510/PNG/512/at_icon-icons.com_50456.png","https://cdn.icon-icons.com/icons2/510/PNG/512/at_icon-icons.com_50456.png"));
        ChildItemList.add(new ChildItem(workoutUrl[5],"Leg","12:00","9 Sets","https://cdn.icon-icons.com/icons2/510/PNG/512/at_icon-icons.com_50456.png","https://cdn.icon-icons.com/icons2/510/PNG/512/at_icon-icons.com_50456.png"));
        ChildItemList.add(new ChildItem(workoutUrl[6],"Back","12:00","9 Sets","https://cdn.icon-icons.com/icons2/510/PNG/512/at_icon-icons.com_50456.png","https://cdn.icon-icons.com/icons2/510/PNG/512/at_icon-icons.com_50456.png"));

        return ChildItemList;
    }

    private void setOnClickLister() {
//        listerner = new FeaturedAdapter.RecyclerViewOnClickListerner() {
//            @Override
//            public void Onclick(View v, int position) {
//                Intent intent = new Intent(HomePage.this,ExerciseList.class);
//                intent.putExtra("exercise",FeaturedLocation.get(position).getTitle());
//                startActivity(intent);
//            }
//        };
    }
}