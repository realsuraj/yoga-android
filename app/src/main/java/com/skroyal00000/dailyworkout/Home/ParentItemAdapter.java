package com.skroyal00000.dailyworkout.Home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skroyal00000.dailyworkout.R;

import java.util.List;

public class ParentItemAdapter extends RecyclerView.Adapter<ParentItemAdapter.ParentViewHolder> {

    // An object of RecyclerView.RecycledViewPool
    // is created to share the Views
    // between the child and
    // the parent RecyclerViews
    private RecyclerView.RecycledViewPool
            viewPool
            = new RecyclerView
            .RecycledViewPool();
    private List<ParentItem> parentItemList;

    public void setParentItemList(List<ParentItem> parentItemList){
        this.parentItemList = parentItemList;
    }

    public ParentItemAdapter() {

    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup,
            int i)
    {

        // Here we inflate the corresponding
        // layout of the parent item
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(
                        R.layout.home_parent_item_view,
                        viewGroup, false);

        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ParentViewHolder parentViewHolder,
            int position)
    {

        ParentItem parentItem = parentItemList.get(position);
        parentViewHolder.ParentItemTitle.setText(parentItem.getParentItemTitle());

        parentViewHolder.ChildRecyclerView.setHasFixedSize(true);
        parentViewHolder.ChildRecyclerView.setLayoutManager(new GridLayoutManager(parentViewHolder.itemView.getContext() , 4));
        ChildItemAdapter childAdapter = new ChildItemAdapter();
        childAdapter.setChildItemList(parentItem.getChildItemList());
        parentViewHolder.ChildRecyclerView.setAdapter(childAdapter);
        childAdapter.notifyDataSetChanged();
    }


    @Override
    public int getItemCount()
    {

        if (parentItemList != null){
            return parentItemList.size();
        }else{
            return 0;
        }
    }

    // This class is to initialize
    // the Views present in
    // the parent RecyclerView
    class ParentViewHolder
            extends RecyclerView.ViewHolder {

        private TextView ParentItemTitle;
        private RecyclerView ChildRecyclerView;

        ParentViewHolder(final View itemView)
        {
            super(itemView);

            ParentItemTitle
                    = itemView
                    .findViewById(
                            R.id.mainTitle);
            ChildRecyclerView
                    = itemView
                    .findViewById(
                            R.id.child_recyclerview);
        }
    }
}
