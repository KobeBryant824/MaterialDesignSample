package com.cxh.materialdesignsample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.cxh.materialdesignsample.R;
import com.cxh.materialdesignsample.adapter.CatAdapter;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/7/14
 */
public class FlexboxLayoutActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Integer> mCatList;

    {
        mCatList = new ArrayList<>();
        mCatList.add(R.drawable.cat_1);
        mCatList.add(R.drawable.cat_2);
        mCatList.add(R.drawable.cat_3);
        mCatList.add(R.drawable.cat_4);
        mCatList.add(R.drawable.cat_5);
        mCatList.add(R.drawable.cat_6);
        mCatList.add(R.drawable.cat_7);
        mCatList.add(R.drawable.cat_8);
        mCatList.add(R.drawable.cat_9);
        mCatList.add(R.drawable.cat_10);
        mCatList.add(R.drawable.cat_11);
        mCatList.add(R.drawable.cat_12);
        mCatList.add(R.drawable.cat_13);
        mCatList.add(R.drawable.cat_14);
        mCatList.add(R.drawable.cat_15);
        mCatList.add(R.drawable.cat_16);
        mCatList.add(R.drawable.cat_17);
        mCatList.add(R.drawable.cat_18);
        mCatList.add(R.drawable.cat_19);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_flexboxlayout);
        setTitle("FlexboxLayout");

        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);

        layoutManager.setFlexWrap(FlexWrap.WRAP);//控制单行和多行，以及副轴的方向
        layoutManager.setFlexDirection(FlexDirection.ROW);//子元素的排列方向，决定主轴和副轴的方向
        layoutManager.setAlignItems(AlignItems.STRETCH);//控制沿副轴对齐(单行起作用)
        layoutManager.setJustifyContent(JustifyContent.CENTER);//控制沿主轴对齐
        mRecyclerView.setLayoutManager(layoutManager);

        CatAdapter catAdapter = new CatAdapter();
        catAdapter.setNewData(mCatList);
        mRecyclerView.setAdapter(catAdapter);

    }
}
