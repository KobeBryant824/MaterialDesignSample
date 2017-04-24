package com.cxh.materialdesignsample.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cxh.materialdesignsample.R;
import com.cxh.materialdesignsample.activity.DetailActivity;

/**
 * Created by Hai (haigod7@gmail.com) on 2017/4/6 16:34.
 */
public class HomeContentAdapter extends BaseAdapter<String> {
    public static final String path1 = "http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-18-17882540_190116561497334_440657494176432128_n.jpg";
    public static final String path2 = "http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-16-17934400_1738549946443321_2924146161843437568_n.jpg";
    private Activity mActivity;

    public HomeContentAdapter(Activity activity) {
        super(activity);
        this.mActivity = activity;
    }

//    @Override
    public int getLayoutId() {
        return R.layout.list_item_home_content;
    }

    @Override
    public void onBindItemHolder(BaseViewHolder holder, final int position) {
        final String title = mDataList.get(position).split(",")[0];

        holder.setText(R.id.title, title);
        holder.setText(R.id.content, mDataList.get(position).split(",")[1]);

        final ImageView showImage = holder.getView(R.id.showImage);

        if (position % 2 == 0) {
            Glide.with(mActivity).load(path1).centerCrop().into(showImage);
        } else {
            Glide.with(mActivity).load(path2).centerCrop().into(showImage);
        }

        holder.setOnClickListener(R.id.card_view, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailActivity.startActivity(mActivity, position, title, showImage);
            }
        });
    }

}
