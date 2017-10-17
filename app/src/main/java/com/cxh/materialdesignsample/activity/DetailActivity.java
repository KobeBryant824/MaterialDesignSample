package com.cxh.materialdesignsample.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cxh.materialdesignsample.Constants;
import com.cxh.materialdesignsample.R;

import static com.cxh.materialdesignsample.adapter.HomeAdapter.path1;
import static com.cxh.materialdesignsample.adapter.HomeAdapter.path2;

public class DetailActivity extends BaseActivity {
    private static final String ACTION_CODE = "ilovekobebryant";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_detail);

        super.onCreate(savedInstanceState);

//        StatusBarCompat.compat(this, Color.parseColor("#3F51B5"));
//        StatusBarCompat.compat(this);

        setEnterTransition();
        setReturnTransition();

        // 自定义toolbar里标题
        String title = getIntent().getStringExtra("title");

        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // ToolBar的标题，当CollapsingToolbarLayout全屏没有折叠时，title显示的是大字体，在折叠的过程中，title不断变小到一定大小的效果。你可以调用setTitle(CharSequence)方法设置title。
        collapsingToolbar.setTitle(title);

        ((AppBarLayout) findViewById(R.id.appbar)).addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                int height = appBarLayout.getHeight() - getSupportActionBar().getHeight() - ViewUtils.getStatusBarHeight(DetailActivity.this); // 状态栏透明
                int height = appBarLayout.getHeight() - getSupportActionBar().getHeight();
                int alpha = 255 * (0 - verticalOffset) / height;
                collapsingToolbar.setExpandedTitleColor(Color.argb(0, 255, 255, 255));
                // 下面这句取决于 android:theme="@style/AppTheme.AppBarOverlay" 设置给哪个控件
//                collapsingToolbar.setCollapsedTitleTextColor(Color.argb(alpha, 255, 255, 255));
            }
        });

        int position = this.getIntent().getIntExtra("position", 0);
        final ImageView backdrop = (ImageView) findViewById(R.id.backdrop);
        //设置过渡动画，或者XML android:transitionName=""
//        ViewCompat.setTransitionName(titleTv, Constants.TRANSITION_TITLE);
        ViewCompat.setTransitionName(backdrop, Constants.TRANSITION_PIC);

        if (position % 2 == 0) {
            Glide.with(this).load(path1).into(backdrop);

        } else {
            Glide.with(this).load(path2).into(backdrop);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        String action = getIntent().getAction();
        if (action != null && action.equals(ACTION_CODE)) {
            Snackbar.make(fab, action, Snackbar.LENGTH_SHORT).show();
        }
    }

    /**
     * 当A startB时，使B中的View进入场景的transition
     */
    private void setEnterTransition() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade visibility = new Fade();
            // This view will not be affected by enter transition animation
            visibility.excludeTarget(R.id.fab, true);
            visibility.setDuration(200);
            visibility.setInterpolator(new AccelerateDecelerateInterpolator());
            getWindow().setEnterTransition(visibility);
        }
    }

    /**
     * 当B 返回A时，使B中的View退出场景的transition
     */
    private void setReturnTransition() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide visibility = new Slide(Gravity.RIGHT);
            visibility.setDuration(200);
            visibility.setInterpolator(new AccelerateDecelerateInterpolator());
            getWindow().setReturnTransition(visibility);
        }
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
                .makeSceneTransitionAnimation(activity, showImage, Constants.TRANSITION_PIC);
        ActivityCompat.startActivity(activity, intent, options.toBundle());


        // 多个共享元素， CollapsingToolbarLayout的标题共享有bug，换成toolbar的title
//                Intent intent = new Intent();
//                intent.setClass(mActivity, DetailActivity.class);
//                intent.putExtra("title", title);
//                intent.putExtra("position", position);
//
//                Pair<View, String> pair1 = new Pair<>((View)holder.title, Constants.TRANSITION_TITLE);
//                Pair<View, String> pair2 = new Pair<>((View)holder.showImage, Constants.TRANSITION_PIC);
//                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, pair1, pair2);
//
//                ActivityCompat.startActivity(mActivity, intent, activityOptionsCompat.toBundle());
    }
}
