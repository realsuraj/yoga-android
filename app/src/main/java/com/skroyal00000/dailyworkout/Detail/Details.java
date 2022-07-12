package com.skroyal00000.dailyworkout.Detail;

public class Details {
   String userName, userEmail,userPhoneNo,userAge,userLocation,userHeight,userWeight,userGender,userLevel;

    public Details() {
    }

    public Details(String userName, String userEmail, String userPhoneNo, String userAge, String userLocation, String userHeight, String userWeight, String userGender, String userLevel) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhoneNo = userPhoneNo;
        this.userAge = userAge;
        this.userLocation = userLocation;
        this.userHeight = userHeight;
        this.userWeight = userWeight;
        this.userGender = userGender;
        this.userLevel = userLevel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    public void setUserPhoneNo(String userPhoneNo) {
        this.userPhoneNo = userPhoneNo;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(String userHeight) {
        this.userHeight = userHeight;
    }

    public String getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(String userWeight) {
        this.userWeight = userWeight;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }
}
