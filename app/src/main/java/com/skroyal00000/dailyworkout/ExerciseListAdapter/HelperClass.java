package com.skroyal00000.dailyworkout.ExerciseListAdapter;

public class HelperClass {
    String exercise_image,exercise_title,exercise_time;

    public HelperClass(String exercise_image, String exercise_title, String exercise_time) {
        this.exercise_image = exercise_image;
        this.exercise_title = exercise_title;
        this.exercise_time = exercise_time;
    }
//    setter
    public String getExercise_image() {
        return exercise_image;
    }

    public String getExercise_title() {
        return exercise_title;
    }

    public String getExercise_time() {
        return exercise_time;
    }
}

