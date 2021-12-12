package com.example.yoga.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoga.R;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    ArrayList<FeaturedhelperClass> featuredLocation;

    public FeaturedAdapter(ArrayList<FeaturedhelperClass> featuredLocation) {
        this.featuredLocation = featuredLocation;
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

        holder.imageView.setImageResource(featuredhelperClass.getImage());
        holder.title.setText(featuredhelperClass.getTitle());
        holder.description.setText(featuredhelperClass.getDescription());

    }

    @Override
    public int getItemCount() {
        return featuredLocation.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title,description;


        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.featured_image);
            title = itemView.findViewById(R.id.featured_title);
            description = itemView.findViewById(R.id.featured_description);
        }
    }
}
