package com.skroyal00000.dailyworkout.Home;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.skroyal00000.dailyworkout.R;
import com.skroyal00000.dailyworkout.ShopBuy;

import java.util.List;

public class ChildItemAdapter extends RecyclerView.Adapter<ChildItemAdapter.ChildViewHolder> {

    private List<ChildItem> ChildItemList;
    Context context;

    String whichT;

    public String getWhichT() {
        return whichT;
    }

    public void setWhichT(String whichT) {
        this.whichT = whichT;
    }

    // Constructor
    ChildItemAdapter(Context context, List<ChildItem> childItemList) {
        this.ChildItemList = childItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_child_item_view, viewGroup, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder childViewHolder, int position) {

        ChildItem childItem = ChildItemList.get(position);
        childViewHolder.ChildItemTitle.setText(childItem.getTitle());
        childViewHolder.ChildMiniTitle1.setText(childItem.getMiniTitle1());
        childViewHolder.ChildMiniTitle2.setText(childItem.getMiniTitle2());
        Glide.with(childViewHolder.ChildItemImage).load(childItem.getImage()).placeholder(R.drawable.progess_bar).diskCacheStrategy(DiskCacheStrategy.ALL).into(childViewHolder.ChildItemImage);
        Glide.with(childViewHolder.MiniTitleIcon1).load(childItem.getMiniIcon1()).placeholder(R.drawable.progess_bar).diskCacheStrategy(DiskCacheStrategy.ALL).into(childViewHolder.MiniTitleIcon1);
        Glide.with(childViewHolder.MiniTitleIcon2).load(childItem.getMiniIcon2()).placeholder(R.drawable.progess_bar).diskCacheStrategy(DiskCacheStrategy.ALL).into(childViewHolder.MiniTitleIcon2);
        childViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShopBuy.class);

                intent.putExtra("whichT",getWhichT() +"");
                intent.putExtra("id",childItem.getId() + "");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return ChildItemList.size();
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {

        public TextView ChildItemTitle, ChildMiniTitle1, ChildMiniTitle2;
        public ImageView ChildItemImage, MiniTitleIcon1, MiniTitleIcon2;

        public ChildViewHolder(View itemView) {
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
