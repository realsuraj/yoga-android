package com.skroyal00000.dailyworkout.ExerciseListAdapter;

import java.io.Serializable;

public class HelperClass implements Serializable {
    String exercise_image,exercise_title,exercise_time,description;

    public HelperClass(String exercise_image, String exercise_title, String exercise_time) {
        this.exercise_image = exercise_image;
        this.exercise_title = exercise_title;
        this.exercise_time = exercise_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HelperClass() {

    }

    public String getExercise_image() {
        return exercise_image;
    }

    public void setExercise_image(String exercise_image) {
        this.exercise_image = exercise_image;
    }

    public String getExercise_title() {
        return exercise_title;
    }

    public void setExercise_title(String exercise_title) {
        this.exercise_title = exercise_title;
    }

    public String getExercise_time() {
        return exercise_time;
    }

    public void setExercise_time(String exercise_time) {
        this.exercise_time = exercise_time;
    }
}

