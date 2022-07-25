package com.skroyal00000.dailyworkout.Home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.skroyal00000.dailyworkout.ProductPage.ProductView;
import com.skroyal00000.dailyworkout.R;
import com.skroyal00000.dailyworkout.ShopBuy;

import java.util.List;

public class ParentItemAdapter extends RecyclerView.Adapter<ParentItemAdapter.ParentViewHolder> {


    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<ParentItem> itemList;

    public ParentItemAdapter(List<ParentItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_parent_item_view, viewGroup, false);
        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ParentViewHolder parentViewHolder,
            int position) {
        ParentItem parentItem = itemList.get(position);

        parentViewHolder.ParentItemTitle.setText(parentItem.getTitle());

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                parentViewHolder.ChildRecyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false);

        layoutManager.setInitialPrefetchItemCount(parentItem.getChildItemList().size());

        ChildItemAdapter childItemAdapter = new ChildItemAdapter(parentViewHolder.itemView.getContext(), parentItem.getChildItemList());
        childItemAdapter.setWhichT(parentItem.getTitle());parentViewHolder.ChildRecyclerView.setLayoutManager(layoutManager);
        parentViewHolder.ChildRecyclerView.setAdapter(childItemAdapter);
        parentViewHolder.ChildRecyclerView.setRecycledViewPool(viewPool);

        parentViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(childItemAdapter.context, ProductView.class);

                intent.putExtra("whichT",parentItem.getTitle());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                childItemAdapter.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return itemList.size();
    }

    public class ParentViewHolder extends RecyclerView.ViewHolder {

        public TextView ParentItemTitle;
        public RecyclerView ChildRecyclerView;
        public RecyclerView.LayoutManager manager;

        public ParentViewHolder(final View itemView) {super(itemView);

            ParentItemTitle = itemView.findViewById(R.id.mainTitle);
            ChildRecyclerView = itemView.findViewById(R.id.child_recyclerview);
            manager = new LinearLayoutManager(itemView.getContext(), RecyclerView.HORIZONTAL, false);
            ChildRecyclerView.setLayoutManager(manager);
        }
    }

}