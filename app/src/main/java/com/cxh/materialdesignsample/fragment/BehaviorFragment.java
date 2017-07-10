package com.cxh.materialdesignsample.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxh.materialdesignsample.R;
import com.cxh.materialdesignsample.activity.BackTopActivity;
import com.cxh.materialdesignsample.activity.BottomSheetBehaviorActivity;
import com.cxh.materialdesignsample.activity.DefineBehaviorActivity;
import com.cxh.materialdesignsample.activity.MainActivity;
import com.cxh.materialdesignsample.activity.SwipeDismissBehaviorActivity;
import com.cxh.materialdesignsample.activity.ZhihuActivity;

/**
 * Created by Hai (haigod7@gmail.com) on 2017/4/10 14:34.
 */
public class BehaviorFragment extends Fragment implements View.OnClickListener {
    private Toolbar mToolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_behavior, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle("自定义Behavior");
        ((MainActivity) getActivity()).initDrawer(mToolbar);

        view.findViewById(R.id.btn_back_top).setOnClickListener(this);
        view.findViewById(R.id.btn_zhihu).setOnClickListener(this);
        view.findViewById(R.id.btn_bottom_sheet).setOnClickListener(this);
        view.findViewById(R.id.btn_swipe_dismiss).setOnClickListener(this);
        view.findViewById(R.id.btn_define_behavior).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back_top) {// 回到顶部按钮动画。
            startActivity(new Intent(getContext(), BackTopActivity.class));
        } else if (v.getId() == R.id.btn_zhihu) {// 仿知乎首页隐藏按钮动画。
            startActivity(new Intent(getContext(), ZhihuActivity.class));
        } else if (v.getId() == R.id.btn_bottom_sheet) {// 底部覆盖。
            startActivity(new Intent(getContext(), BottomSheetBehaviorActivity.class));
        } else if (v.getId() == R.id.btn_swipe_dismiss) {// 滑动删除。
            startActivity(new Intent(getContext(), SwipeDismissBehaviorActivity.class));
        } else if (v.getId() == R.id.btn_define_behavior) {
            startActivity(new Intent(getContext(), DefineBehaviorActivity.class));
        }
    }
}
