package com.cxh.materialdesignsample.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cxh.materialdesignsample.R;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/7/13
 */
public class HomeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public static final String path1 = "http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-18-17882540_190116561497334_440657494176432128_n.jpg";
    public static final String path2 = "http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-16-17934400_1738549946443321_2924146161843437568_n.jpg";

    public HomeAdapter() {
        super(R.layout.list_item_home_content);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        final String title = item.split(",")[0];

        helper.setText(R.id.title, title);
        helper.setText(R.id.content, item.split(",")[1]);

        final ImageView showImage = helper.getView(R.id.showImage);

        if (helper.getAdapterPosition() % 2 == 0) {
            Glide.with(showImage.getContext()).load(path1).centerCrop().into(showImage);
        } else {
            Glide.with(showImage.getContext()).load(path2).centerCrop().into(showImage);
        }
    }
}
