package com.skroyal00000.dailyworkout.ProductPage.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skroyal00000.dailyworkout.R;

public class ShopChildViewHolder extends RecyclerView.ViewHolder{
    public TextView shopTitle , shopBuy, shopWebsite;
    public ImageView shopImage;


    public ShopChildViewHolder(@NonNull View itemView) {
        super(itemView);

        shopTitle = itemView.findViewById(R.id.shopTitle);
        shopBuy = itemView.findViewById(R.id.shopBuy);
        shopWebsite = itemView.findViewById(R.id.shopWebsite);
        shopImage = itemView.findViewById(R.id.shopImage);
    }
}