package com.cxh.materialdesignsample;

import android.app.Application;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatDelegate;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        boolean isNight = sharedPreferences.getBoolean(Constants.ISNIGHT, false);
        if (isNight) {
            //使用夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            //不使用夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}