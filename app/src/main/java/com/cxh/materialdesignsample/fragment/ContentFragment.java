package com.cxh.materialdesignsample.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxh.materialdesignsample.R;
import com.cxh.materialdesignsample.adapter.DataAdapter;
import com.cxh.materialdesignsample.utils.NetworkUtils;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import java.util.ArrayList;
import java.util.List;


public class ContentFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener{
    private static final int TOTAL_COUNTER = 5;//如果服务器没有返回总数据或者总页数，这里设置为最大值比如10000，什么时候没有数据了根据接口返回判断
    /**
     * 每一页展示多少条数据
     */
    private static final int REQUEST_COUNT = 8;

    private String mTitle;
    private Handler handler;
    private Runnable runnable;

    private int page = 1;

    private LRecyclerView mRecyclerView;
    private DataAdapter mDataAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTitle = this.getArguments().getString("title");

        mRecyclerView = (LRecyclerView) view.findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
//        mRecyclerView.setArrowImageView(R.drawable.ic_head);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);

        //设置头部加载颜色
        mRecyclerView.setHeaderViewColor(R.color.colorAccent, R.color.dark, android.R.color.transparent);
        //设置底部加载颜色
        mRecyclerView.setFooterViewColor(R.color.colorAccent, R.color.dark, android.R.color.transparent);
        //设置底部加载文字提示
        mRecyclerView.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");

        mDataAdapter = new DataAdapter(getActivity());
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mDataAdapter);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);

        mRecyclerView.setOnRefreshListener(this);
        mRecyclerView.setOnLoadMoreListener(this);

        mRecyclerView.refresh();
    }

    private void setList() {
        runnable = new Runnable() {
            @Override
            public void run() {
                //模拟一下网络请求失败的情况
                if (NetworkUtils.isNetAvailable(getContext())) {
                    int start = REQUEST_COUNT * (page - 1);
                    List<String> mDataList = new ArrayList<>();
                    for (int i = start; i < page * REQUEST_COUNT; i++) {
                        mDataList.add(mTitle + "," + getActivity().getString(R.string.app_name) + i);
                    }
                    mDataAdapter.addAll(mDataList);
                    mRecyclerView.refreshComplete(REQUEST_COUNT);
                } else {
                    mRecyclerView.refreshComplete(REQUEST_COUNT);
                    mLRecyclerViewAdapter.notifyDataSetChanged();

                    mRecyclerView.setOnNetWorkErrorListener(new OnNetWorkErrorListener() {
                        @Override
                        public void reload() {
                            setList();
                        }
                    });
                }


            }
        };
        handler = new Handler();
        handler.postDelayed(runnable, 500);
    }

    @Override
    public void onRefresh() {
        mDataAdapter.clear();
        mLRecyclerViewAdapter.notifyDataSetChanged();
        page = 1;
        setList();

    }

    @Override
    public void onLoadMore() {
        page++;
        if (page < TOTAL_COUNTER) {
            setList();
        } else {
            mRecyclerView.setNoMore(true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null)
            handler.removeCallbacks(runnable);
    }


}
