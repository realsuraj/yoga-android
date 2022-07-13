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

import java.util.List;

public class ChildItemAdapter
        extends RecyclerView
        .Adapter<ChildItemAdapter.ChildViewHolder> {

    private List<ChildItem> ChildItemList;

    // Constructor
    ChildItemAdapter(List<ChildItem> childItemList)
    {
        this.ChildItemList = childItemList;
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
                = ChildItemList.get(position);

        // For the created instance, set title.
        // No need to set the image for
        // the ImageViews because we have
        // provided the source for the images
        // in the layout file itself
        childViewHolder.ChildItemTitle.setText(childItem.getTitle());
        childViewHolder.ChildMiniTitle1.setText(childItem.getMiniTitle1());
        childViewHolder.ChildMiniTitle2.setText(childItem.getMiniTitle2());
        Glide.with(childViewHolder.ChildItemImage).load(childItem.getImage()).placeholder(R.drawable.progess_bar).diskCacheStrategy(DiskCacheStrategy.ALL).into(childViewHolder.ChildItemImage);
        Glide.with(childViewHolder.MiniTitleIcon1).load(childItem.getMiniTitleIcon1()).placeholder(R.drawable.progess_bar).diskCacheStrategy(DiskCacheStrategy.ALL).into(childViewHolder.MiniTitleIcon1);
        Glide.with(childViewHolder.MiniTitleIcon2).load(childItem.getMiniTitleIcon2()).placeholder(R.drawable.progess_bar).diskCacheStrategy(DiskCacheStrategy.ALL).into(childViewHolder.MiniTitleIcon2);

    }

    @Override
    public int getItemCount()
    {

        // This method returns the number
        // of items we have added
        // in the ChildItemList
        // i.e. the number of instances
        // of the ChildItemList
        // that have been created
        return ChildItemList.size();
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

