package com.skroyal00000.dailyworkout.Home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.skroyal00000.dailyworkout.R;

import java.util.List;

    // This class is to initialize
    // the Views present in
    // the parent RecyclerView
    public class ParentViewHolder
            extends RecyclerView.ViewHolder {

        public TextView ParentItemTitle;
        public RecyclerView ChildRecyclerView;
        public RecyclerView.LayoutManager manager;

        public ParentViewHolder(final View itemView)
        {
            super(itemView);

            ParentItemTitle = itemView.findViewById(R.id.mainTitle);
            ChildRecyclerView = itemView.findViewById(R.id.child_recyclerview);
            manager = new LinearLayoutManager(itemView.getContext(),RecyclerView.HORIZONTAL,false);
            ChildRecyclerView.setLayoutManager(manager);
        }
    }

