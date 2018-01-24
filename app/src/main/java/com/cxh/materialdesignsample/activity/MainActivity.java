package com.cxh.materialdesignsample.activity;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.cxh.materialdesignsample.Constant;
import com.cxh.materialdesignsample.R;
import com.cxh.materialdesignsample.fragment.MainFragment;
import com.cxh.materialdesignsample.fragment.OtherWidgetFragment;
import com.cxh.materialdesignsample.fragment.PaletteFragment;
import com.cxh.materialdesignsample.fragment.PercentFragment;
import com.cxh.materialdesignsample.service.SendMsgService;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String RESULT_KEY = "remote_input";
    public static final int NOTIFICATION_ID = 1000;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        StatusBarCompat.compat(this, Color.parseColor("#3F51B5"));
//        StatusBarCompat.compat(this);

        setExitTransition();
        setReenterTransition();

        // Theme.AppCompat.Light.DarkActionBar自带的actionbar
//        getSupportActionBar().setDisplayShowHomeEnabled(true);//使左上自定义图标是否显示,
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//给左上角图标的左边加上一个返回的图标
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        switchContent(new MainFragment());

//        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification notification = buildNotification();
//        nm.notify(NOTIFICATION_ID, notification);


    }

    private Notification buildNotification() {
        RemoteInput remoteInput = new RemoteInput.Builder(RESULT_KEY)
                .setLabel("回复这条消息")
                .build();

        // 创建pendingintent, 当发送时调用什么
        Intent intent = new Intent(this, SendMsgService.class);
        PendingIntent pi = PendingIntent.getService(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 创建快捷回复 Action
        NotificationCompat.Action act = new NotificationCompat.Action.Builder(R.mipmap.ic_launcher, "回复", pi)
                .addRemoteInput(remoteInput).build();

        // 创建notification
        // 使用设置优先级的方式创建悬浮通知，则会自动消失
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("请问是否需要信用卡?")
                .setContentText("您好，我是XX银行的XX经理， 请问你需要办理信用卡吗？")
                .setColor(Color.CYAN)
                .setPriority(Notification.PRIORITY_MAX) // 设置优先级为Max，则为悬浮通知
                .addAction(act) // 设置回复action
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL) // 想要悬浮出来， 这里必须要设置
                .setCategory(Notification.CATEGORY_MESSAGE);

        return builder.build();
    }

    /**
     * 第二个activity进入时的动画；第一个activity退出时的动画；
     * overridePendingTransition(R.id.enter,R.id.zoom_exit):它必需紧挨着startActivity()或者finish()函数之后调用"
     */
    /**
     * 当A startB时，使A中的View退出场景的transition
     */
    private void setExitTransition() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide visibility = new Slide(Gravity.LEFT);
            visibility.setDuration(200);
            visibility.setInterpolator(new AccelerateDecelerateInterpolator());
            getWindow().setExitTransition(visibility);
        }
    }

    /**
     * 当B 返回A时，使A中的View进入场景的transition
     */
    private void setReenterTransition() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Explode visibility = new Explode(); // 爆裂效果
            visibility.setDuration(200);
            visibility.setInterpolator(new AccelerateDecelerateInterpolator());
            getWindow().setReenterTransition(visibility);
        }
    }


    public void initDrawer(Toolbar toolbar) {
        if (toolbar != null) {
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                }
            };
            mDrawerToggle.syncState();
            mDrawerLayout.addDrawerListener(mDrawerToggle);
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            switchContent(new MainFragment());

        } else if (id == R.id.nav_other_widget) {
            switchContent(new OtherWidgetFragment());

        } else if (id == R.id.nav_palette) {
            switchContent(new PaletteFragment());

        }  else if (id == R.id.nav_skin) {
            SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();

            boolean isNight = !sharedPreferences.getBoolean(Constant.ISNIGHT, false);
            edit.putBoolean(Constant.ISNIGHT, isNight).commit();

            if (isNight)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            recreate();
        } else if (id == R.id.nav_percent){
            switchContent(new PercentFragment());
        }
        return true;
    }

    protected void switchContent(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentLayout, fragment).commit();
//        invalidateOptionsMenu();
    }

}
