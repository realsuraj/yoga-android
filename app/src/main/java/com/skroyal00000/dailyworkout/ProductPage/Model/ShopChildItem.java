package com.skroyal00000.dailyworkout.ProductPage.Model;

public class ShopChildItem {

    String buy, image,title ,website;

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public ShopChildItem() {
    }

    public ShopChildItem(String buy, String image, String title, String website) {
        this.buy = buy;
        this.image = image;
        this.title = title;
        this.website = website;
    }
}