package com.cxh.materialdesignsample.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cxh.materialdesignsample.R;
import com.cxh.materialdesignsample.activity.DetailActivity;
import com.cxh.materialdesignsample.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {

    private static final int TOTAL_COUNT = 12;
    private static final int PAGE_COUNT = 5;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Handler mHandler;

    private String mTitle;
    private int mPage;
    private boolean isErr;

    private HomeAdapter mHomeAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_content, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHandler = new Handler();
        mTitle = this.getArguments().getString("title");

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mHomeAdapter = new HomeAdapter();
        mHomeAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mHomeAdapter.isFirstOnly(false);
        mHomeAdapter.setOnLoadMoreListener(this, mRecyclerView);

        mRecyclerView.setAdapter(mHomeAdapter);
        mHomeAdapter.setOnItemClickListener(this);

        mSwipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mPage = 1;

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int start = PAGE_COUNT * (mPage - 1);
                List<String> data = new ArrayList<>();
                for (int i = start; i < mPage * PAGE_COUNT; i++) {
                    data.add(mTitle + "," + getActivity().getString(R.string.app_name) + i);
                }
//                mHomeAdapter.replaceData(data);
                mHomeAdapter.setNewData(data);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    private int lastVisibleItemPosition;

    @Override
    public void onLoadMoreRequested() {
        lastVisibleItemPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findLastVisibleItemPosition();

        int currentCount = mHomeAdapter.getData().size();

        if (currentCount < PAGE_COUNT) {
            mHomeAdapter.loadMoreEnd(true);
        } else {
            if (currentCount <= TOTAL_COUNT) {
                if (isErr){
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mPage++;
                            int start = PAGE_COUNT * (mPage - 1);
                            List<String> data = new ArrayList<>();
                            for (int i = start; i < mPage * PAGE_COUNT; i++) {
                                data.add(mTitle + "," + getActivity().getString(R.string.app_name) + i);
                            }
                            mHomeAdapter.addData(data);
                            mHomeAdapter.loadMoreComplete();
                        }
                    }, 1000);
                } else {
                    isErr = true;
                    mHomeAdapter.loadMoreFail();
                }
            } else {
                mHomeAdapter.loadMoreEnd(true);
            }
        }

        mRecyclerView.scrollToPosition(lastVisibleItemPosition);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String item = ((String) adapter.getItem(position)).split(",")[0];


        DetailActivity.startActivity(getActivity(), position, item, (ImageView) adapter.getViewByPosition(position, R.id.showImage));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHandler.removeCallbacksAndMessages(null);
    }
}
