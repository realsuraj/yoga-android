package com.skroyal00000.dailyworkout.diet;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skroyal00000.dailyworkout.R;


import java.util.ArrayList;

public class adapterForDiet extends RecyclerView.Adapter<adapterForDiet.ViewHolder> {

    // variable for our array list and context.
    private final ArrayList<helperForDiet> userModalArrayList;
    private final Context context;

    // creating a constructor.
    public adapterForDiet(ArrayList<helperForDiet> userModalArrayList, Context context) {
        this.userModalArrayList = userModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating our layout file on below line.
        View view = LayoutInflater.from(context).inflate(R.layout.diet_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // getting data from our array list in our modal class.
        helperForDiet helper = userModalArrayList.get(position);

        // on below line we are setting data to our text view.
        holder.dietTitle.setText(helper.getDietTitle());
        holder.dietCalories.setText(helper.getDietCalories());
        holder.dietProtein.setText(helper.getDietProtein());


        // on below line we are loading our image
        // from url in our image view using picasso.
        Glide.with(context).
                load(helper.getDietImage()).
                placeholder(R.drawable.progess_bar).
                diskCacheStrategy(DiskCacheStrategy.ALL).
                into(holder.dietImage);
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return userModalArrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // creating a variable for our text view and image view.
        private final TextView dietTitle, dietCalories, dietProtein;
        private final ImageView dietImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our variables.
            dietTitle = itemView.findViewById(R.id.dietTitle);
            dietCalories = itemView.findViewById(R.id.dietCalorie);
            dietProtein = itemView.findViewById(R.id.dietProtein);
            dietImage = itemView.findViewById(R.id.dietImage);
        }
    }
}
