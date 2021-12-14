package com.example.yoga.ExerciseListAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoga.ExerciseList;
import com.example.yoga.HomeAdapter.FeaturedAdapter;
import com.example.yoga.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ExerciseViewHolder>{

    ArrayList<HelperClass> exerciseLocation;

    public Adapter(ArrayList<HelperClass> exerciseLocation) {
        this.exerciseLocation = exerciseLocation;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_list_card,parent,false);
        ExerciseViewHolder exerciseViewHolder = new ExerciseViewHolder(view);
        return exerciseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        HelperClass helperClass = exerciseLocation.get(position);

        holder.imageView.setImageResource(helperClass.getExercise_image());
        holder.Exercise_title.setText(helperClass.getExercise_title());

        holder.Exercise_time.setText(helperClass.getExercise_time());


    }

    @Override
    public int getItemCount() {
        return exerciseLocation.size();
    }


    public static class ExerciseViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView Exercise_title , Exercise_time;


        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.exercise_icon);
            Exercise_title = itemView.findViewById(R.id.exercise_title);
            Exercise_time = itemView.findViewById(R.id.exercise_time);
        }
    }




}
