package com.skroyal00000.dailyworkout.Home.ImageSlider;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.skroyal00000.dailyworkout.ExerciseDays;
import com.smarteist.autoimageslider.SliderViewAdapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.skroyal00000.dailyworkout.R;

import java.util.ArrayList;
import java.util.List;

public class ImageSliderAdapter extends SliderViewAdapter<ImageSliderAdapter.SliderAdapterViewHolder> {


    private final List<ImageSliderModel> mSliderItems;
    Context context;

    public ImageSliderAdapter(Context context, ArrayList<ImageSliderModel> sliderDataArrayList) {
        this.mSliderItems = sliderDataArrayList;
        this.context = context;
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_slider_image, null);
        return new SliderAdapterViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {

        final ImageSliderModel sliderItem = mSliderItems.get(position);



        Glide.with(viewHolder.itemView).load(sliderItem.getImgUrl()).fitCenter().
                placeholder(R.drawable.progess_bar).
                diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.imageViewBackground);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , ExerciseDays.class);
                intent.putExtra("JoinBtn",sliderItem.getTitle());
                context.startActivity(intent);
            }
        });
    }

    // this method will return
    // the count of our list.
    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.myimage);
            this.itemView = itemView;
        }
    }
}
