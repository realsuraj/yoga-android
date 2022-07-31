package com.skroyal00000.dailyworkout.ExerciseListAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.skroyal00000.dailyworkout.R;
import com.skroyal00000.dailyworkout.exercise.ExerciseList;
import com.skroyal00000.dailyworkout.exercise.ExercisePreview;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ExerciseViewHolder>{
    ArrayList<HelperClass> exerciseLocation;
    private Context context;
    public Adapter(Context context,ArrayList<HelperClass> exerciseLocation) {
        this.exerciseLocation = exerciseLocation;
        this.context = context;

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

        Glide.with(holder.imageView).
                load(helperClass.getExercise_image()).
                placeholder(R.drawable.progess_bar).
                diskCacheStrategy(DiskCacheStrategy.ALL).
                into(holder.imageView);


        holder.Exercise_title.setText(helperClass.getExercise_title());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ExercisePreview.class);
                intent.putExtra("showExerciseTitle",helperClass.getExercise_title());
                intent.putExtra("showExerciseImage",helperClass.getExercise_image());
                intent.putExtra("description",helperClass.getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseLocation.size();
    }


    public class ExerciseViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView Exercise_title ;


        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.exercise_icon);
            Exercise_title = itemView.findViewById(R.id.exercise_title);


        }
    }

}
