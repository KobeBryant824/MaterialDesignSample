package com.cxh.materialdesignsample.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.cxh.materialdesignsample.R;
import com.cxh.materialdesignsample.activity.DetailActivity;

/**
 * Created by Hai (haigod7@gmail.com) on 2017/4/6 16:34.
 */
public class DataAdapter extends BaseAdapter<String> {
    private Activity mActivity;

    public DataAdapter(Activity activity) {
        super(activity);
        this.mActivity = activity;
    }

    @Override
    public int getLayoutId() {
        return R.layout.recyclerview_item;
    }

    @Override
    public void onBindItemHolder(BaseViewHolder holder, final int position) {
        final String title = mDataList.get(position).split(",")[0];

        holder.setText(R.id.title, title);
        holder.setText(R.id.content, mDataList.get(position).split(",")[1]);

        final ImageView showImage = holder.getView(R.id.showImage);
        if (position % 2 == 0) {
            showImage.setBackgroundResource(R.drawable.ic_head);
        } else {
            showImage.setBackgroundResource(R.drawable.ic_kobe);
        }

        holder.setOnClickListener(R.id.card_view, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailActivity.startActivity(mActivity, position, title, showImage);
            }
        });
    }
}
