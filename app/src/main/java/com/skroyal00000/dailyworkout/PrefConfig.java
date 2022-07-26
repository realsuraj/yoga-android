package com.skroyal00000.dailyworkout;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefConfig {

    private static final String MY_PREFERENCE_NAME = "com.example.yoga.shared_preference";
    public static final String PREF_SETTING_REST = "pref_setting_rest";
    public static final String PREF_SETTING_COUNTDOWN = "pref_setting_counter";

    private  static final String PREF_GENDER = "pref_setting_gender";
    private  static final String PREF_LEVEL = "pref_setting_level";
    private static final String PREF_SOUND_ON_OFF = "pref_sound_onn_off";

    private static final String PREF_MUSIC_ON_OFF = "pref_is_music_on_off";
    private static final String PREF_USER_NAME = "pref_username";

    private static final String PREF_WHICH_TABLE_PRODUCT_VIEW = "pref_which_table";

    public static void saveTableNameProductView(Context context, String userName){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_WHICH_TABLE_PRODUCT_VIEW,userName);
        editor.apply();
    };

    public static String loadTableNameProductView(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return  sharedPreferences.getString(PREF_WHICH_TABLE_PRODUCT_VIEW,null);
    };


    public static void saveSettingSetsCountInPref(Context context, int total){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_SETTING_REST,total);
        editor.apply();
    };

    public static void saveUserName(Context context, String userName){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_USER_NAME,userName);
        editor.apply();
    };

    public static String loadUsername(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return  sharedPreferences.getString(PREF_USER_NAME,null);
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
    public static void saveIsSoundOn(Context context, boolean isSoundOn){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PREF_SOUND_ON_OFF,isSoundOn);
        editor.apply();
    };

    public static boolean loadIsSoundOn(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(PREF_SOUND_ON_OFF,false);
    }

      public static void saveIsMusicOn(Context context, boolean IsMusicOn){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PREF_MUSIC_ON_OFF,IsMusicOn);
        editor.apply();
    };

    public static boolean loadIsMusicOn(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(PREF_MUSIC_ON_OFF,false);
    }




    public static void removeDataFromPref(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(PREF_SETTING_REST);
        editor.apply();
    }


  }

