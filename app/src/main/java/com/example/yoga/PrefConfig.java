package com.example.yoga;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefConfig {

    private static final String MY_PREFERENCE_NAME = "com.example.yoga.shared_preference";
    public static final String PREF_SETTING_REST = "pref_setting_rest";
    public static final String PREF_SETTING_COUNTDOWN = "pref_setting_counter";

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

    public static int loadSettingSetsCount(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(PREF_SETTING_REST,3);
    }


    public static int loadSettingCountDown(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(PREF_SETTING_COUNTDOWN,30);
    }

    public static void removeDataFromPref(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(PREF_SETTING_REST);
    }


    public static void registerPref(Context context, SharedPreferences.OnSharedPreferenceChangeListener listener) {
        SharedPreferences preferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        preferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public static void unregisterPref(Context context, SharedPreferences.OnSharedPreferenceChangeListener listener) {
        SharedPreferences preferences = context.getSharedPreferences(MY_PREFERENCE_NAME,Context.MODE_PRIVATE);
        preferences.unregisterOnSharedPreferenceChangeListener(listener);
    }
}

