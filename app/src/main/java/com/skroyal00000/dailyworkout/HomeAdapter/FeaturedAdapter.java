package com.skroyal00000.dailyworkout.HomeAdapter;

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

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    ArrayList<FeaturedhelperClass> featuredLocation;
    private RecyclerViewOnClickListerner listerner;
    int position;


    public FeaturedAdapter(ArrayList<FeaturedhelperClass> featuredLocation,RecyclerViewOnClickListerner listerner) {
        this.featuredLocation = featuredLocation;
        this.listerner = listerner;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design,parent,false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        FeaturedhelperClass featuredhelperClass = featuredLocation.get(position);


        Glide.with(holder.imageView).load(featuredhelperClass.getImage()).placeholder(R.drawable.blank_image).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);

        holder.title.setText(featuredhelperClass.getTitle());
        holder.time.setText(featuredhelperClass.getTime());
        holder.exercise_count.setText(featuredhelperClass.getExercise_count());

    }

    @Override
    public int getItemCount() {
        return featuredLocation.size();
    }

    public class FeaturedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView title,time,exercise_count;


        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.featured_image);
            title = itemView.findViewById(R.id.featured_title);
            time = itemView.findViewById(R.id.featured_time);
            exercise_count = itemView.findViewById(R.id.featured_exercise_count);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            listerner.Onclick(itemView,getAdapterPosition());
        }
    }

    public interface RecyclerViewOnClickListerner{
        void Onclick(View v, int position);

    }
}
