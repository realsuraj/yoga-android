package com.skroyal00000.dailyworkout.HomeAdapter;

public class FeaturedhelperClass {
    String image, title,time,exercise_count;

    public FeaturedhelperClass(String image, String title, String time, String exercise_count) {
        this.image = image;
        this.title = title;
        this.time = time;
        this.exercise_count = exercise_count;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getExercise_count() {
        return exercise_count;
    }
}
