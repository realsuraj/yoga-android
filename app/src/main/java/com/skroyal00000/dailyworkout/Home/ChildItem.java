package com.skroyal00000.dailyworkout.Home;

public class ChildItem {

        String image, miniIcon1, miniIcon2,miniTitle1,miniTitle2,title;

    public ChildItem() {
    }

    public ChildItem(String title,  String image, String miniTitle1, String miniTitle2, String miniIcon1,String miniIcon2) {
        this.image = image;
        this.title = title;
        this.miniTitle1 = miniTitle1;
        this.miniTitle2 = miniTitle2;
        this.miniIcon1 = miniIcon1;
        this.miniIcon2 = miniIcon2;
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

    public String getMiniTitle1() {
        return miniTitle1;
    }

    public void setMiniTitle1(String miniTitle1) {
        this.miniTitle1 = miniTitle1;
    }

    public String getMiniTitle2() {
        return miniTitle2;
    }

    public void setMiniTitle2(String miniTitle2) {
        this.miniTitle2 = miniTitle2;
    }

    public String getMiniIcon1() {
        return miniIcon1;
    }

    public void setMiniIcon1(String miniIcon1) {
        this.miniIcon1 = miniIcon1;
    }

    public String getMiniIcon2() {
        return miniIcon2;
    }

    public void setMiniIcon2(String miniIcon2) {
        this.miniIcon2 = miniIcon2;
    }
}




