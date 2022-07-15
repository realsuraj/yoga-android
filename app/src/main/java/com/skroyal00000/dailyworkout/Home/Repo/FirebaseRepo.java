package com.skroyal00000.dailyworkout.Home.Repo;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.skroyal00000.dailyworkout.Home.ChildItem;
import com.skroyal00000.dailyworkout.Home.ParentItem;

import java.util.ArrayList;
import java.util.List;

public class FirebaseRepo {
    private DatabaseReference databaseReference;
    private OnRealtimeDbTaskComplete onRealtimeDbTaskComplete;

    public FirebaseRepo(OnRealtimeDbTaskComplete onRealtimeDbTaskComplete){
        this.onRealtimeDbTaskComplete = onRealtimeDbTaskComplete;
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("HomeTab");
    }

    public void getAllData(){

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<ParentItem> parentItemList = new ArrayList<>();

                for (DataSnapshot ds : snapshot.getChildren()){
                    List<ChildItem> childItemList = new ArrayList<>();
                    ParentItem parentItem = new ParentItem();
                    parentItem.setParentItemTitle(ds.child("Title").getValue(String.class));

                    ChildItem childItem = new ChildItem();
                    childItem.setTitle(ds.child("childData").child("title").getValue(String.class));
                    childItem.setImage(ds.child("childData").child("image").getValue(String.class));
                    childItem.setMiniIcon1(ds.child("childData").child("miniIcon1").getValue(String.class));
                    childItem.setMiniIcon2(ds.child("childData").child("miniIcon2").getValue(String.class));
                    childItem.setMiniTitle1(ds.child("childData").child("miniTitle1").getValue(String.class));
                    childItem.setMiniTitle2(ds.child("childData").child("miniTitle2").getValue(String.class));

                    childItemList.add(childItem);

                    parentItem.setChildItemList(childItemList);
                    parentItemList.add(parentItem);


                }
                Log.d("TAG", "onDataChange: "+parentItemList);
                onRealtimeDbTaskComplete.onSuccess(parentItemList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onRealtimeDbTaskComplete.onFailure(error);
            }
        });
    }

    public interface OnRealtimeDbTaskComplete{
        void onSuccess(List<ParentItem> parentItemList);
        void onFailure(DatabaseError error);
    }
}