package com.skroyal00000.dailyworkout.ProductPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skroyal00000.dailyworkout.ProductPage.Model.ShopChildItem;
import com.skroyal00000.dailyworkout.ProductPage.Model.ShopParentItem;
import com.skroyal00000.dailyworkout.ProductPage.ViewHolder.ShopChildViewHolder;
import com.skroyal00000.dailyworkout.ProductPage.ViewHolder.ShopParentViewHolder;
import com.skroyal00000.dailyworkout.R;

public class ProductView extends AppCompatActivity{

    DatabaseReference reference;
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<ShopChildItem,ShopChildViewHolder> adapter2;
    FirebaseRecyclerAdapter<ShopParentItem, ShopParentViewHolder> adapter;
    RecyclerView.LayoutManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        // firebase
        reference = FirebaseDatabase.getInstance().getReference("cloth");

        recyclerView = findViewById(R.id.shopPRecyclerView);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        FirebaseRecyclerOptions<ShopParentItem> options = new FirebaseRecyclerOptions.Builder<ShopParentItem>()
                .setQuery(reference,ShopParentItem.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<ShopParentItem, ShopParentViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ShopParentViewHolder holder, int position, @NonNull ShopParentItem model) {
                holder.parentTitle.setText(model.getTitle());

                FirebaseRecyclerOptions<ShopChildItem> options2 = new FirebaseRecyclerOptions.Builder<ShopChildItem>()
                        .setQuery(reference.child(model.getId()).child("childData"),ShopChildItem.class)
                        .build();

                adapter2 = new FirebaseRecyclerAdapter<ShopChildItem, ShopChildViewHolder>(options2) {
                    @Override
                    protected void onBindViewHolder(@NonNull ShopChildViewHolder holder2, int position, @NonNull ShopChildItem model2) {
                        holder2.shopTitle.setText(model2.getTitle());
                        Glide.with(holder2.shopImage).load(model2.getImage()).placeholder(R.drawable.progess_bar).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder2.shopImage);
                        holder2.shopBuy.setText(model2.getBuy());
                        holder2.shopWebsite.setText(model2.getWebsite());
                    }

                    @NonNull
                    @Override
                    public ShopChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view2 = LayoutInflater.from(getBaseContext()).inflate(R.layout.shop_page_card,parent,false);
                        return new ShopChildViewHolder(view2);
                    }
                };
                adapter2.startListening();
                adapter2.notifyDataSetChanged();
                holder.parentRecycler.setAdapter(adapter2);
            }

            @NonNull
            @Override
            public ShopParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.shop_parent,parent,false);
                return new ShopParentViewHolder(view);
            }
        };
        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}