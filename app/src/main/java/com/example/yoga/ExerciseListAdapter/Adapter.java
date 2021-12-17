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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.yoga.ExerciseList;
import com.example.yoga.HomeAdapter.FeaturedAdapter;
import com.example.yoga.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ExerciseViewHolder>{
    private ExerciseOnClickRecycler ex_listerner;
    ArrayList<HelperClass> exerciseLocation;

    public Adapter(ArrayList<HelperClass> exerciseLocation, ExerciseOnClickRecycler ex_listerner) {
        this.exerciseLocation = exerciseLocation;
        this.ex_listerner = ex_listerner;

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

        Glide.with(holder.imageView).load(helperClass.getExercise_image()).placeholder(R.drawable.blank_image).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);

        holder.Exercise_title.setText(helperClass.getExercise_title());
        holder.Exercise_time.setText(helperClass.getExercise_time());


    }

    @Override
    public int getItemCount() {
        return exerciseLocation.size();
    }


    public class ExerciseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView Exercise_title , Exercise_time;


        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.exercise_icon);
            Exercise_title = itemView.findViewById(R.id.exercise_title);
            Exercise_time = itemView.findViewById(R.id.exercise_time);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            ex_listerner.Onclick(itemView,getAdapterPosition());
        }
    }

    public interface ExerciseOnClickRecycler{
        void Onclick(View v,int position);
    }

}
