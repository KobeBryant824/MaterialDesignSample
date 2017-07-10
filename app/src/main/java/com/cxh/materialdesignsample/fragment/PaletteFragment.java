package com.cxh.materialdesignsample.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.cxh.materialdesignsample.R;
import com.cxh.materialdesignsample.activity.MainActivity;
import com.cxh.materialdesignsample.adapter.PaletteViewPagerAdapter;

/**
 * Created by Hai (haigod7@gmail.com) on 2017/4/11 14:42.
 */
public class PaletteFragment extends Fragment{
    private Toolbar toolbar;
    private TabLayout toolbar_tab;
    private ViewPager main_vp_container;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_palette, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((MainActivity) getActivity()).initDrawer(toolbar);

        toolbar_tab = (TabLayout) view.findViewById(R.id.toolbar_tab);
        main_vp_container = (ViewPager) view.findViewById(R.id.main_vp_container);

        PaletteViewPagerAdapter vpAdapter = new PaletteViewPagerAdapter(getChildFragmentManager());
        main_vp_container.setAdapter(vpAdapter);
        toolbar_tab.setupWithViewPager(main_vp_container);
        changeTopBgColor(0);
        main_vp_container.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTopBgColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 根据Palette提取的颜色，修改tab和toolbar以及状态栏的颜色
     */
    private void changeTopBgColor(int position) {
        // 用来提取颜色的Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), PaletteContentFragment.getBackgroundBitmapPosition(position));
        // Palette的部分
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                //获取到充满活力的这种色调
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                //根据调色板Palette获取到图片中的颜色设置到toolbar和tab中背景，标题等，使整个UI界面颜色统一
                toolbar_tab.setBackgroundColor(vibrant.getRgb());
                toolbar_tab.setSelectedTabIndicatorColor(colorBurn(vibrant.getRgb()));
                toolbar.setBackgroundColor(vibrant.getRgb());

                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    Window window = getActivity().getWindow();
                    window.setStatusBarColor(colorBurn(vibrant.getRgb()));
                    window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
                }
            }
        });
    }

    /**
     * 颜色加深处理
     *
     * @param RGBValues RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     *                  Android中我们一般使用它的16进制，
     *                  例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     *                  red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     *                  所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
     * @return
     */
    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }
}
