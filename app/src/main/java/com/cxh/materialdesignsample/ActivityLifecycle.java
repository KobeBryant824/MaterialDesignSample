package com.cxh.materialdesignsample;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/7/17
 */
public class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (activity instanceof AppCompatActivity) {
            Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
            ((AppCompatActivity) activity).setSupportActionBar(toolbar);
            if (((AppCompatActivity) activity).getSupportActionBar() != null)
                ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
