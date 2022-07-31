package com.skroyal00000.dailyworkout.Home.ImageSlider;

public class ImageSliderModel {
    private String imgUrl,title;
    // Constructor method.
    public ImageSliderModel(String imgUrl, String title) {
        this.imgUrl = imgUrl;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter method
    public String getImgUrl() {
        return imgUrl;
    }

    // Setter method
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
