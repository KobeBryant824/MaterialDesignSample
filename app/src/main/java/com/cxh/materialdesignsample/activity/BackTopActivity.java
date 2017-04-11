/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cxh.materialdesignsample.activity;

import android.os.Bundle;
import android.support.design.widget.BasicBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.cxh.materialdesignsample.R;
import com.cxh.materialdesignsample.adapter.BehaviorRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created on 2016/7/14.
 *
 * @author Yan Zhenjie.
 */
public class BackTopActivity extends AppCompatActivity {

    private FloatingActionButton FAB;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    // 如果代码看不懂，本例详解博客：http://blog.csdn.net/yanzhenjie1003/article/details/51941288

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_top_behavior);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("我是第" + i + "个");
        }
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        BehaviorRecyclerAdapter adapter = new BehaviorRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);

        FAB = (FloatingActionButton) findViewById(R.id.fab);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutManager.scrollToPosition(0);
                hideFAB();
            }
        });
    }

    private boolean isInitializeFAB = false;

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (!isInitializeFAB) {
//            isInitializeFAB = true;
//            hideFAB();
//        }
//    }

    private void hideFAB() {
        FAB.postDelayed(new Runnable() {
            @Override
            public void run() {
                BasicBehavior.scaleHide(FAB, BasicBehavior.DEFAULT_OUT_ANIMATOR_LISTENER);
            }
        }, 500);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
