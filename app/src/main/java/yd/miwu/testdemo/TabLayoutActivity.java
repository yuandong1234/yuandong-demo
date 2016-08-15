package yd.miwu.testdemo;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import yd.miwu.R;
import yd.miwu.fragment.TestFragment;

public class TabLayoutActivity extends FragmentActivity {

    @ViewInject(R.id.tabView)
    private TabLayout tabView;
    @ViewInject(R.id.viewpager)
    private ViewPager viewpager;

    private ArrayList<Fragment> fragments;
    private FragmentManager fragmentManager;
    private ArrayList<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        ViewUtils.inject(this);
        fragmentManager = getSupportFragmentManager();
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        addTitle();
        setTab();

    }

    private void addTitle() {
        titles.add("全部");
        titles.add("中国");
        titles.add("韩国");
        titles.add("美国");
        titles.add("法国");
        titles.add("日本");
        titles.add("英国");
        titles.add("意大利");
        titles.add("德国");
    }

    private void setTab() {

        for (String str : titles) {
            TestFragment fragment=new TestFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title",str);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(fragmentManager,fragments,titles);
        viewpager.setAdapter(adapter);
        tabView.setupWithViewPager(viewpager);

}


    //自定义adapter
    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> list;
        private ArrayList<String> titles;

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list, ArrayList<String> titles) {
            super(fm);
            this.list = list;
            this.titles = titles;
        }

        @Override

        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
