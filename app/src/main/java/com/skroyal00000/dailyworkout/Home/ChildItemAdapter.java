package com.skroyal00000.dailyworkout.Home;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.skroyal00000.dailyworkout.R;

import java.util.Collections;
import java.util.List;

public class ChildItemAdapter
        extends RecyclerView
        .Adapter<ChildItemAdapter.ChildViewHolder> {

    private List<ChildItem> childItemList;

    // Constructor
    public void setChildItemList(List<ChildItem> childItemList){
        this.childItemList = childItemList;
        this.childItemList.removeAll(Collections.singleton(null));
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup,
            int i)
    {

        // Here we inflate the corresponding
        // layout of the child item
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(
                        R.layout.home_child_item_view,
                        viewGroup, false);

        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ChildViewHolder childViewHolder,
            int position)
    {

        // Create an instance of the ChildItem
        // class for the given position
        ChildItem childItem
                = childItemList.get(position);

        // For the created instance, set title.
        // No need to set the image for
        // the ImageViews because we have
        // provided the source for the images
        // in the layout file itself
        childViewHolder.ChildItemTitle.setText(childItem.getTitle());
        childViewHolder.ChildMiniTitle1.setText(childItem.getMiniTitle1());
        childViewHolder.ChildMiniTitle2.setText(childItem.getMiniTitle2());
        Glide.with(childViewHolder.ChildItemImage).load(childItem.getImage()).placeholder(R.drawable.progess_bar).diskCacheStrategy(DiskCacheStrategy.ALL).into(childViewHolder.ChildItemImage);
        Glide.with(childViewHolder.MiniTitleIcon1).load(childItem.getMiniIcon1()).placeholder(R.drawable.progess_bar).diskCacheStrategy(DiskCacheStrategy.ALL).into(childViewHolder.MiniTitleIcon1);
        Glide.with(childViewHolder.MiniTitleIcon2).load(childItem.getMiniIcon2()).placeholder(R.drawable.progess_bar).diskCacheStrategy(DiskCacheStrategy.ALL).into(childViewHolder.MiniTitleIcon2);

    }

    @Override
    public int getItemCount()
    {

        if (childItemList != null){
            return childItemList.size();
        }else{
            return  0;
        }
    }

    // This class is to initialize
    // the Views present
    // in the child RecyclerView
    class ChildViewHolder
            extends RecyclerView.ViewHolder {

        TextView ChildItemTitle,ChildMiniTitle1, ChildMiniTitle2;
        ImageView ChildItemImage,MiniTitleIcon1, MiniTitleIcon2;
        ChildViewHolder(View itemView)
        {
            super(itemView);
            ChildItemTitle = itemView.findViewById(R.id.titleBar);
            ChildMiniTitle1 = itemView.findViewById(R.id.childMiniTitle1);
            ChildMiniTitle2 = itemView.findViewById(R.id.childMiniTitle2);
            ChildItemImage = itemView.findViewById(R.id.imageBar);
            MiniTitleIcon1 = itemView.findViewById(R.id.miniTitleIcon1);
            MiniTitleIcon2 = itemView.findViewById(R.id.miniTitleIcon2);
        }
    }
}

