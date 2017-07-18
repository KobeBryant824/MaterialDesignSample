package com.cxh.materialdesignsample.activity;

import android.support.v7.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {

    // ToolBar返回回调
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
