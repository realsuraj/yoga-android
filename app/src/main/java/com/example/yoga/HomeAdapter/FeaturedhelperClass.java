package com.example.yoga.HomeAdapter;

public class FeaturedhelperClass {
    int image ;
    String title,time,exercise_count;

    public FeaturedhelperClass(int image, String title, String time, String exercise_count) {
        this.image = image;
        this.title = title;
        this.time = time;
        this.exercise_count = exercise_count;
    }

    public int getImage() {
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
