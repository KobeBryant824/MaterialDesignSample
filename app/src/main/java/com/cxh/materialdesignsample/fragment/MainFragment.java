package com.cxh.materialdesignsample.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.cxh.materialdesignsample.R;
import com.cxh.materialdesignsample.activity.LoginActivity;
import com.cxh.materialdesignsample.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static com.socks.library.KLog.I;

/**
 * Created by Hai (haigod7@gmail.com) on 2017/3/31 15:11.
 */
public class MainFragment extends Fragment{

    private Toolbar mToolbar;
    private FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle("首页");
        ((MainActivity) getActivity()).initDrawer(mToolbar);
        initTabLayout(view);
        inflateMenu();
        initSearchView();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "前往登录", Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getContext(), LoginActivity.class));
//                        getActivity().finish();
                    }
                }).show();
            }
        });
    }

    private void initTabLayout(View view) {
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(viewPager.getAdapter().getCount());
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);        //适合很多tab
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);      //tab均分,适合少的tab，默认
        //tab均分,适合少的tab,TabLayout.GRAVITY_CENTER，会居中
        //tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);// 铺满，默认
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        Fragment newfragment = new HomeFragment();
        Bundle data = new Bundle();
        data.putString("title", "科比门徒1");
        newfragment.setArguments(data);
        adapter.addFrag(newfragment, "科比门徒1");

        newfragment = new HomeFragment();
        data = new Bundle();
        data.putString("title", "科比门徒2");
        newfragment.setArguments(data);
        adapter.addFrag(newfragment, "科比门徒2");

        newfragment = new HomeFragment();
        data = new Bundle();
        data.putString("title", "科比门徒3");
        newfragment.setArguments(data);
        adapter.addFrag(newfragment, "科比门徒3");

        viewPager.setAdapter(adapter);
    }

    private void inflateMenu() {
        mToolbar.inflateMenu(R.menu.menu_home);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_about:
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/KobeBryant824"));
                        break;
                }
                return true;
            }
        });
    }

    private void initSearchView() {
        final SearchView searchView = (SearchView) mToolbar.getMenu().findItem(R.id.menu_search).getActionView();
        searchView.setQueryHint("搜索…");
//        searchView.setIconifiedByDefault(false);// 默认让他展开
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE); //搜索选项字段，默认是搜索，可以是：下一页、发送、完成等
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getActivity(), query,Toast.LENGTH_SHORT).show();
//                searchView.setIconifiedByDefault(true);
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String s) {
                // 查询服务器，展示列表
                return false;
            }
        });
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
