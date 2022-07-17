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


    public class ChildViewHolder
            extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ClickInterface clickInterface;

        public TextView ChildItemTitle,ChildMiniTitle1, ChildMiniTitle2;
        public ImageView ChildItemImage,MiniTitleIcon1, MiniTitleIcon2;
        public ChildViewHolder(View itemView)
        {
            super(itemView);
            ChildItemTitle = itemView.findViewById(R.id.titleBar);
            ChildMiniTitle1 = itemView.findViewById(R.id.childMiniTitle1);
            ChildMiniTitle2 = itemView.findViewById(R.id.childMiniTitle2);
            ChildItemImage = itemView.findViewById(R.id.imageBar);
            MiniTitleIcon1 = itemView.findViewById(R.id.miniTitleIcon1);
            MiniTitleIcon2 = itemView.findViewById(R.id.miniTitleIcon2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickInterface.OnItemClick(v,false);
        }
        public void InterfaceClick(ClickInterface clickInterface){
            this.clickInterface = clickInterface;

        }
    }


