package com.skroyal00000.dailyworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skroyal00000.dailyworkout.Detail.Detail_intro;
import com.skroyal00000.dailyworkout.Home.ChildItem;
import com.skroyal00000.dailyworkout.Home.ChildViewHolder;
import com.skroyal00000.dailyworkout.Home.ClickInterface;
import com.skroyal00000.dailyworkout.Home.ParentItem;
import com.skroyal00000.dailyworkout.Home.ParentViewHolder;
import com.skroyal00000.dailyworkout.exercise.ExerciseList;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private Button begginerBtnJoin, intermediateBtnJoin, advanceBtnJoin;
    String[] workoutUrl;
    ImageView settingImageview,custom_btn;
    RecyclerView parentRecyclerView ;
    FirebaseRecyclerAdapter<ChildItem, ChildViewHolder> adapter2;
    FirebaseRecyclerAdapter<ParentItem, ParentViewHolder> adapter1;
   DatabaseReference reference;
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



        BeginnerJoinFunc();
        SettingImageButton();
        custombtnPressed();

//firebase things

        reference = FirebaseDatabase.getInstance().getReference().child("homeTab");

        FirebaseRecyclerOptions<ParentItem> options1 = new FirebaseRecyclerOptions.Builder<ParentItem>()
                .setQuery(reference,ParentItem.class)
                .build();

        adapter1 = new FirebaseRecyclerAdapter<ParentItem, ParentViewHolder>(options1) {
            @Override
            protected void onBindViewHolder(@NonNull ParentViewHolder holder, int position, @NonNull ParentItem model) {
                holder.ParentItemTitle.setText(model.getTitle());
                FirebaseRecyclerOptions<ChildItem> options2 = new FirebaseRecyclerOptions.Builder<ChildItem>()
                        .setQuery(reference.child(model.getId()).child("childData"),ChildItem.class)
                        .build();
                adapter2 = new FirebaseRecyclerAdapter<ChildItem, ChildViewHolder>(options2) {
                    @Override
                    protected void onBindViewHolder(@NonNull ChildViewHolder childViewHolder, int position, @NonNull ChildItem childItem) {
                        childViewHolder.ChildItemTitle.setText(childItem.getTitle());
                        childViewHolder.ChildMiniTitle1.setText(childItem.getMiniTitle1());
                        childViewHolder.ChildMiniTitle2.setText(childItem.getMiniTitle2());
                        Glide.with(childViewHolder.ChildItemImage).load(childItem.getImage()).placeholder(R.drawable.progess_bar).diskCacheStrategy(DiskCacheStrategy.ALL).into(childViewHolder.ChildItemImage);
                        Glide.with(childViewHolder.MiniTitleIcon1).load(childItem.getMiniIcon1()).placeholder(R.drawable.progess_bar).diskCacheStrategy(DiskCacheStrategy.ALL).into(childViewHolder.MiniTitleIcon1);
                        Glide.with(childViewHolder.MiniTitleIcon2).load(childItem.getMiniIcon2()).placeholder(R.drawable.progess_bar).diskCacheStrategy(DiskCacheStrategy.ALL).into(childViewHolder.MiniTitleIcon2);

                        childViewHolder.InterfaceClick(new ClickInterface() {
                            @Override
                            public void OnItemClick(View v, Boolean longPress) {
                               if(model.getTitle().equalsIgnoreCase("body workout")){
                                   Intent intent = new Intent(HomePage.this, ExerciseList.class);
                                   intent.putExtra("exercise",childItem.getTitle());
                                   startActivity(intent);
                               }
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view2 = LayoutInflater.from(getBaseContext()).inflate(R.layout.home_child_item_view,parent,false);
                        return new ChildViewHolder(view2);
                    }
                };
                adapter2.startListening();
                adapter2.notifyDataSetChanged();
                holder.ChildRecyclerView.setAdapter(adapter2);
            }

            @NonNull
            @Override
            public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.home_parent_item_view,parent,false);
                return new ParentViewHolder(view);
            }
        };

        adapter1.startListening();
        adapter1.notifyDataSetChanged();
        parentRecyclerView.setAdapter(adapter1);

        //end

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