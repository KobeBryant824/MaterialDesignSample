package com.cxh.materialdesignsample.activity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.cxh.materialdesignsample.AppConstants;
import com.cxh.materialdesignsample.R;
import com.cxh.materialdesignsample.fragment.HomeFragment;
import com.cxh.materialdesignsample.fragment.OtherWidgetFragment;
import com.cxh.materialdesignsample.fragment.BehaviorFragment;
import com.cxh.materialdesignsample.fragment.PaletteFragment;
import com.cxh.materialdesignsample.fragment.PercentFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setExitTransition();
        setReenterTransition();

        // Theme.AppCompat.Light.DarkActionBar自带的actionbar
//        getSupportActionBar().setDisplayShowHomeEnabled(true);//使左上自定义图标是否显示,
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//给左上角图标的左边加上一个返回的图标
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        switchContent(new HomeFragment());
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
            visibility.setDuration(500);
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
            visibility.setDuration(500);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            switchContent(new HomeFragment());

        } else if (id == R.id.nav_other_widget) {
            switchContent(new OtherWidgetFragment());

        } else if (id == R.id.nav_palette) {
            switchContent(new PaletteFragment());

        } else if (id == R.id.nav_behavior) {
            switchContent(new BehaviorFragment());

        } else if (id == R.id.nav_skin) {
            SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();

            boolean isNight = !sharedPreferences.getBoolean(AppConstants.ISNIGHT, false);
            edit.putBoolean(AppConstants.ISNIGHT, isNight).commit();

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
