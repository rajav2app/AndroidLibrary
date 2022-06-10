package com.example.androidlibrary.app;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.androidlibrary.SessionManager;

public class MyApplication extends Application {
    SessionManager sessionManager;
    public static MyApplication instance;


    public void onCreate() {
        super.onCreate();
        instance = this;
        sessionManager = new SessionManager(this);
        if (sessionManager.isLightModeOn()) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
    }


    public static void setDarkLightTheme(int selectedDarkLightTheme) {

        if (selectedDarkLightTheme == 1) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);

        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
