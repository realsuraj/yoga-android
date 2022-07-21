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
        childItemAdapter.setpTitle(parentItem.getTitle());parentViewHolder.ChildRecyclerView.setLayoutManager(layoutManager);
        parentViewHolder.ChildRecyclerView.setAdapter(childItemAdapter);
        parentViewHolder.ChildRecyclerView.setRecycledViewPool(viewPool);
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