package com.skroyal00000.dailyworkout.ProductPage.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.skroyal00000.dailyworkout.PrefConfig;
import com.skroyal00000.dailyworkout.ProductPage.Model.ShopChildItem;
import com.skroyal00000.dailyworkout.ProductPage.ProductView;
import com.skroyal00000.dailyworkout.R;
import com.skroyal00000.dailyworkout.ShopBuy;

import java.util.List;

public class ShopAdapder extends RecyclerView.Adapter<ShopAdapder.ShopChildViewHolder> {

    private Context context;
    private List<ShopChildItem> list;

    public ShopAdapder(Context context, List<ShopChildItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ShopChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shop_page_card, viewGroup, false);
        return new ShopChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopChildViewHolder holder, int position) {
        ShopChildItem ShopItem = list.get(position);
        holder.shopTitle.setText(ShopItem.getTitle());
        holder.shopBuy.setText(ShopItem.getMiniTitle1());
        holder.shopWebsite.setText(ShopItem.getMiniTitle2());
        Glide.with(holder.shopImage).load(ShopItem.getImage()).placeholder(R.drawable.progess_bar).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.shopImage);
        Glide.with(holder.shopMiniIcon1).load(ShopItem.getMiniIcon1()).placeholder(R.drawable.progess_bar).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.shopMiniIcon1);
        Glide.with(holder.shopMiniIcon2).load(ShopItem.getMiniIcon2()).placeholder(R.drawable.progess_bar).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.shopMiniIcon2);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShopBuy.class);
                intent.putExtra("whichT",PrefConfig.loadTableNameProductView(context) + "");
                intent.putExtra("id",ShopItem.getId() + "");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ShopChildViewHolder extends RecyclerView.ViewHolder {
        public TextView shopTitle, shopBuy, shopWebsite;
        public ImageView shopImage, shopMiniIcon1,shopMiniIcon2;


        public ShopChildViewHolder(@NonNull View itemView) {
            super(itemView);

            shopTitle = itemView.findViewById(R.id.shopTitle);
            shopBuy = itemView.findViewById(R.id.shopBuy);
            shopWebsite = itemView.findViewById(R.id.shopWebsite);
            shopImage = itemView.findViewById(R.id.shopImage);
            shopMiniIcon1 = itemView.findViewById(R.id.shopMiniIcon1);
            shopMiniIcon2 = itemView.findViewById(R.id.shopMiniIcon2);

        }
    }
}