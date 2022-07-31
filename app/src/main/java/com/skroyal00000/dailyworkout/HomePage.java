package com.skroyal00000.dailyworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.skroyal00000.dailyworkout.Detail.Detail_intro;
import com.skroyal00000.dailyworkout.Home.ImageSlider.ImageSliderAdapter;
import com.skroyal00000.dailyworkout.Home.ImageSlider.ImageSliderModel;
import com.skroyal00000.dailyworkout.ProductPage.ProductView;
import com.smarteist.autoimageslider.SliderView;
import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    ImageView settingImageview,custom_btn, imgWorkout,imgTrainer, imgShop, imgGym;
    CardView cardWorkout,cardTrainer, cardGym, cardShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
//**********************************************************************************************************************************
//                                           image slider
//**********************************************************************************************************************************

        String url1 = "https://images.pexels.com/photos/3253501/pexels-photo-3253501.jpeg?auto=compress&cs=tinysrgb&w=600";
        String url2 = "https://images.pexels.com/photos/2105493/pexels-photo-2105493.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1";
        String url3 = "https://images.pexels.com/photos/1552103/pexels-photo-1552103.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1";
        ArrayList<ImageSliderModel> sliderDataArrayList = new ArrayList<>();
        SliderView sliderView = findViewById(R.id.imageSlider);

        sliderDataArrayList.add(new ImageSliderModel(url1,"beginner"));
        sliderDataArrayList.add(new ImageSliderModel(url2,"intermediate"));
        sliderDataArrayList.add(new ImageSliderModel(url3,"advanced"));

        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(this, sliderDataArrayList);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(imageSliderAdapter);

        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();


//**********************************************************************************************************************************
//                                            finding data
//**********************************************************************************************************************************
        cardShop = findViewById(R.id.cardClothes);
        cardGym = findViewById(R.id.cardSupplements);
        cardTrainer = findViewById(R.id.cardTrainer);
        cardWorkout = findViewById(R.id.cardWorkout);

        settingImageview = (ImageView)  findViewById(R.id.setting_imageview);
        custom_btn = findViewById(R.id.create_custom_plan_btn);
        imgShop = findViewById(R.id.imgShop);
        imgGym = findViewById(R.id.imgGym);
        imgTrainer = findViewById(R.id.imgTrainer);
        imgWorkout = findViewById(R.id.imgWorkout);

//**********************************************************************************************************************************
//                                               setting images to cardView
//**********************************************************************************************************************************

        String urlClothes = "https://i.pinimg.com/736x/7f/d6/11/7fd611e6e4cc411f380153fa73cccee5.jpg";
        String urlSupplement = "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/gettyimages-1227050586.jpg?crop=0.668xw:1.00xh;0.167xw,0&resize=640:*";
        String urlTrainer = "https://www.mensjournal.com/wp-content/uploads/mf/_main2_trainer2.jpg?quality=86&strip=all";
        String urlWorkout = "https://images.pexels.com/photos/1552106/pexels-photo-1552106.jpeg?auto=compress&cs=tinysrgb&w=600";


        Glide.with(HomePage.this).load(urlClothes).fitCenter().
                placeholder(R.drawable.progess_bar).
                diskCacheStrategy(DiskCacheStrategy.ALL).into(imgShop);

        Glide.with(HomePage.this).load(urlSupplement).fitCenter().
                placeholder(R.drawable.progess_bar).
                diskCacheStrategy(DiskCacheStrategy.ALL).into(imgGym);

        Glide.with(HomePage.this).load(urlTrainer).fitCenter().
                placeholder(R.drawable.progess_bar).
                diskCacheStrategy(DiskCacheStrategy.ALL).into(imgTrainer);

        Glide.with(HomePage.this).load(urlWorkout).fitCenter().
                placeholder(R.drawable.progess_bar).
                diskCacheStrategy(DiskCacheStrategy.ALL).into(imgWorkout);

       JoinFunc();
        SettingImageButton();
        custombtnPressed();


    }


//**********************************************************************************************************************************
//                                             custom button
//**********************************************************************************************************************************

    private void custombtnPressed() {
        custom_btn.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, Detail_intro.class);
            intent.putExtra("customPressed","Custom_pressed");
            startActivity(intent);
        });
    }
//**********************************************************************************************************************************
//                                             setting btn
//**********************************************************************************************************************************

    private void SettingImageButton() {
        settingImageview.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this,Setting.class);
            startActivity(intent);
        });
    }


//**********************************************************************************************************************************
//                                            intent for join
//**********************************************************************************************************************************

    private void JoinFunc() {
        cardWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, ProductView.class);
                intent.putExtra("whichT","Daily Workout");
                startActivity(intent);
            }
        });

        cardTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, ProductView.class);
                intent.putExtra("whichT","Trainer");
                startActivity(intent);
            }
        });

        cardShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, ProductView.class);
                intent.putExtra("whichT","Clothes");
                startActivity(intent);
            }
        });

        cardGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, ProductView.class);
                intent.putExtra("whichT","Gym");
                startActivity(intent);
            }
        });
    }

}