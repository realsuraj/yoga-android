package com.skroyal00000.dailyworkout.ProductPage.ViewHolder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skroyal00000.dailyworkout.R;

public class ShopParentViewHolder extends RecyclerView.ViewHolder {

    public TextView parentTitle;
    public RecyclerView parentRecycler;
    public RecyclerView.LayoutManager manager;

    public ShopParentViewHolder(@NonNull View itemView) {
        super(itemView);
        manager = new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        parentTitle = itemView.findViewById(R.id.shopParentTitle);
        parentRecycler =  itemView.findViewById(R.id.shopParentRecycler);
        parentRecycler.setLayoutManager(manager);
    }
}
