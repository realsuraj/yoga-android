package com.example.yoga.ExerciseListAdapter;

public class HelperClass {
    int exercise_image;
    String exercise_title,exercise_time;

    public HelperClass(int exercise_image, String exercise_title, String exercise_time) {
        this.exercise_image = exercise_image;
        this.exercise_title = exercise_title;
        this.exercise_time = exercise_time;
    }

//    setter
    public int getExercise_image() {
        return exercise_image;
    }

    public String getExercise_title() {
        return exercise_title;
    }

    public String getExercise_time() {
        return exercise_time;
    }
}
