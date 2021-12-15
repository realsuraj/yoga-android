package com.example.yoga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.yoga.ExerciseListAdapter.Adapter;
import com.example.yoga.ExerciseListAdapter.HelperClass;
import com.example.yoga.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ExerciseList extends AppCompatActivity {

    RecyclerView ExerciseRecyclerView;
    RecyclerView.Adapter adapter;
    TextView exercise_name;
    String name_exercise_string;
    ImageView imageViewFire;
    ActivityMainBinding binding;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    String url = "https://github.com/square/picasso/raw/master/website/static/sample.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        exercise_name = findViewById(R.id.exercise_name);
        ExerciseRecyclerView = (RecyclerView) findViewById(R.id.exercise_list_recyclerView);
        imageViewFire = findViewById(R.id.firebaseimage);

        storageReference = FirebaseStorage.getInstance().getReference("images/hard-removebg-preview 1.png");

        PicassoCache(url);
       getExtraIntent();
        Lists();

    }

    private void PicassoCache(String url) {

        Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageViewFire);
    }

    private void getExtraIntent() {
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            name_exercise_string = extras.getString("exercise");
        }

        exercise_name.setText(name_exercise_string);
    }


    public void Lists(){
        ExerciseRecyclerView.setHasFixedSize(true);
        ExerciseRecyclerView.setLayoutManager(new LinearLayoutManager(ExerciseList.this,LinearLayoutManager.VERTICAL,false));

        ArrayList<HelperClass> list = new ArrayList<>();
        if(name_exercise_string.equalsIgnoreCase("chest")) {
            list.add(new HelperClass("https://picsum.photos/100?image=1", "Cheast 2", "12:00"));
            list.add(new HelperClass("https://picsum.photos/100?image=2", "Cheast 3", "2:00"));
            list.add(new HelperClass("https://picsum.photos/100?image=3", "Cheast 5", "1:00"));
            list.add(new HelperClass("https://picsum.photos/100?image=4", "Cheast 9", "12:00"));
            list.add(new HelperClass("https://picsum.photos/100?image=5", "Cheast 11", "2:00"));

        }
            else if(name_exercise_string.equalsIgnoreCase("bicep")) {
                list.add(new HelperClass("https://picsum.photos/100?image=6", "bicep 2", "12:00"));
                list.add(new HelperClass("https://picsum.photos/100?image=7", "bicep 3", "2:00"));
                list.add(new HelperClass("https://picsum.photos/100?image=8", "bicep 5", "1:00"));
                list.add(new HelperClass("https://picsum.photos/100?image=9", "bicep 9", "12:00"));
                }

            else{
            list.add(new HelperClass("https://picsum.photos/100?image=10", "Cheast 3", "2:00"));
            list.add(new HelperClass("https://picsum.photos/100?image=11", "Cheast 5", "1:00"));
            list.add(new HelperClass("https://picsum.photos/100?image=12", "Cheast 9", "12:00"));
            list.add(new HelperClass("https://picsum.photos/100?image=13", "Cheast 11", "2:00"));

        }

        adapter = new Adapter(list);
        ExerciseRecyclerView.setAdapter(adapter);

    }
}