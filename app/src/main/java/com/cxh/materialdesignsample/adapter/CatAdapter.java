package com.cxh.materialdesignsample.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cxh.materialdesignsample.R;
import com.google.android.flexbox.FlexboxLayoutManager;

/**
 * @author Hai (haigod7[at]gmail[dot]com)
 *         2017/7/17
 */
public class CatAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    public CatAdapter() {
        super(R.layout.viewholder_cat);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {

        final ImageView imageView = helper.getView(R.id.imageview);

        imageView.setImageResource(item);

        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams) lp;
            flexboxLp.setFlexGrow(1.0f);//剩余空间将均匀分配到每个子项。
        }
    }
}
