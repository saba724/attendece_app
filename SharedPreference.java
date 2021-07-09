package com.example.attendence;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPreference {

    private static Context contextPref;
    private static SharedPreference sharePref = new SharedPreference();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_USN = "user_email";
    public static final String USER_INFO= "user_info";
    public static final String USER_CITY= "user_city";
    public static final String USER_MOBILE = "user_mobile";
    public static final String USER_OTP = "user_otp";

    private SharedPreference() {
    }

    //The context passed into the getInstance should be application level context.
    public static SharedPreference getInstance(Context context) {
        contextPref = context;
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return sharePref;
    }

    public void saveString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public Boolean getBooleanVal(String key) {
        return sharedPreferences.getBoolean(key, false);
    }


    public void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, true);
    }

    public void clearAll() {
        editor.clear();
        editor.commit();
    }

    public void saveInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 1);
    }

    public void logoutUser(Context context) {

        Intent i = new Intent(contextPref, emplylogin.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        contextPref.startActivity(i);

        editor.clear();
        editor.commit();

    }
}
