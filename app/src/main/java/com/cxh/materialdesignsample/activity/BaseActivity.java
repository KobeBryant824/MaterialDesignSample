package com.cxh.materialdesignsample.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.cxh.materialdesignsample.R;


public class BaseActivity extends AppCompatActivity {

    public Toolbar setupToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return toolbar;

    }

    // ToolBar返回回调
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
