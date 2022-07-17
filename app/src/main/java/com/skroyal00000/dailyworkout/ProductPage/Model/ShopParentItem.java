package com.skroyal00000.dailyworkout.ProductPage.Model;

//name of firebase parent and model parent will be same
public class ShopParentItem {
    String title, id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ShopParentItem() {
    }

    public ShopParentItem(String title, String id) {
        this.title = title;
        this.id = id;
    }
}
