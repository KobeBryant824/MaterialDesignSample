package com.cxh.materialdesignsample.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;

import com.cxh.materialdesignsample.AppConstants;
import com.cxh.materialdesignsample.BaseActivity;
import com.cxh.materialdesignsample.R;

public class DetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupToolbar("");
        String title = getIntent().getStringExtra("title");
//        TextView titleTv = (TextView) findViewById(R.id.title_tv);
//        titleTv.setText(title);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // ToolBar的标题，当CollapsingToolbarLayout全屏没有折叠时，title显示的是大字体，在折叠的过程中，title不断变小到一定大小的效果。你可以调用setTitle(CharSequence)方法设置title。
        collapsingToolbar.setTitle(title);

        int position = this.getIntent().getIntExtra("position", 0);
        ImageView backdrop = (ImageView) findViewById(R.id.backdrop);
        //设置过渡动画，或者XML android:transitionName=""
//        ViewCompat.setTransitionName(titleTv, AppConstants.TRANSITION_TITLE);
        ViewCompat.setTransitionName(backdrop, AppConstants.TRANSITION_PIC);

        if (position % 2 == 0) {
            backdrop.setBackgroundResource(R.drawable.ic_head);
            // 居然不能加载网络图片
//            Glide
//                    .with(this)
//                    .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491395116513&di=086fb70e4d8f885a99d4d885ea764f22&imgtype=0&src=http%3A%2F%2Fimgmini.eastday.com%2Fmobile%2F20160426045203_a9988af49be1b2286e813a02b0361ab7_1.jpeg")
//                    .centerCrop()
//                    .crossFade()
//                    .into(backdrop);
        } else {
            backdrop.setBackgroundResource(R.drawable.ic_kobe);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }

    /**
     * @param showImage 共享的元素
     */
    public static void startActivity(Activity activity, int position, String title, ImageView showImage) {
        Intent intent = new Intent();
        intent.setClass(activity, DetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("position", position);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, showImage, AppConstants.TRANSITION_PIC);
        ActivityCompat.startActivity(activity, intent, options.toBundle());


        // 多个共享元素， CollapsingToolbarLayout的标题共享有bug，换成toolbar的title
//                Intent intent = new Intent();
//                intent.setClass(mActivity, DetailActivity.class);
//                intent.putExtra("title", title);
//                intent.putExtra("position", position);
//
//                Pair<View, String> pair1 = new Pair<>((View)holder.title, AppConstants.TRANSITION_TITLE);
//                Pair<View, String> pair2 = new Pair<>((View)holder.showImage, AppConstants.TRANSITION_PIC);
//                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, pair1, pair2);
//
//                ActivityCompat.startActivity(mActivity, intent, activityOptionsCompat.toBundle());
    }
}
