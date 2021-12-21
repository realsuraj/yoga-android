package com.example.yoga;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefConfig {

    private static final String MY_PREFERENCE_NAME = "com.example.yoga.shared_preference";
    public static final String PREF_SETTING_REST = "pref_setting_rest";
    public static final String PREF_SETTING_COUNTDOWN = "pref_setting_counter";

    private  static final String PREF_GENDER = "pref_setting_gender";
    private  static final String PREF_LEVEL = "pref_setting_level";
    private static final String PREF_DAYS = "pref_days";

    private static final String PREF_ISDAYPAGE = "pref_isDayPage";

    public static void saveSettingSetsCountInPref(Context context, int total){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_SETTING_REST,total);
        editor.apply();
    };

    public static void saveSettingCountdownTimeInPref(Context context, int total){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_SETTING_COUNTDOWN,total);
        editor.apply();
    };


    public static void saveGender(Context context, String gender){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_GENDER,gender);
        editor.apply();
    };
    public static String loadGender(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return  sharedPreferences.getString(PREF_GENDER,null);
    }

 public static void saveLevel(Context context, String level){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_LEVEL,level);
        editor.apply();
    };
    public static String loadLevel(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return  sharedPreferences.getString(PREF_LEVEL,null);
    }

    public static int loadSettingSetsCount(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(PREF_SETTING_REST,3);
    }


    public static int loadSettingCountDown(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(PREF_SETTING_COUNTDOWN,30);
    }
    public static void saveDays(Context context, int Days){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_DAYS,Days);
        editor.apply();
    };

    public static int loadDays(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(PREF_DAYS,0);
    }

      public static void saveIsDayPage(Context context, boolean isDayspage){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PREF_ISDAYPAGE,isDayspage);
        editor.apply();
    };

    public static boolean loadIsDayPage(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(PREF_ISDAYPAGE,false);
    }




    public static void removeDataFromPref(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(PREF_SETTING_REST);
        editor.apply();
    }


  }

