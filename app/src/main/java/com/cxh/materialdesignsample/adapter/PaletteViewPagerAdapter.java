package com.cxh.materialdesignsample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cxh.materialdesignsample.fragment.PaletteContentFragment;


public class PaletteViewPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"主页", "分享", "收藏", "关注", "微博"};

    public PaletteViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return PaletteContentFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}