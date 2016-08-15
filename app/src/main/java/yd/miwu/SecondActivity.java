package yd.miwu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import yd.miwu.testdemo.AutoLoadGridViewActivity;
import yd.miwu.testdemo.CustomRefreshListViewActivity;
import yd.miwu.testdemo.FrescoLoadImageActivity;
import yd.miwu.testdemo.PullToRefreshGridViewActivity;
import yd.miwu.testdemo.AutoLoadXlListViewActivity;
import yd.miwu.testdemo.DrawerLayoutActivtiy;
import yd.miwu.testdemo.MaterialRefreshLayoutActivity;
import yd.miwu.testdemo.CustomSliderActivity;
import yd.miwu.testdemo.GuideActivity;
import yd.miwu.testdemo.ViewPagerInfiniteSlidingActivity;
import yd.miwu.testdemo.ScrollViewPagerActivity;


public class SecondActivity extends AppCompatActivity {

    @ViewInject(R.id.Fresco)
    private TextView Fresco;
    @ViewInject(R.id.PullToRefreshGridView)
    private TextView pullToRefreshGridView;
    @ViewInject(R.id.PullToRefreshGridView2)
    private TextView PullToRefreshGridView2;
    @ViewInject(R.id.PullToRefreshListView)
    private TextView PullToRefreshListView;
    @ViewInject(R.id.AutoLoadXlListViewActivity)
    private TextView AutoLoadXlListViewActivity;
    @ViewInject(R.id. DrawerLayoutActivtiy)
    private TextView  DrawerLayoutActivtiy;
    @ViewInject(R.id. MaterialRefreshLayoutActivity)
    private TextView  MaterialRefreshLayoutActivity;
    @ViewInject(R.id. CustomSliderActivity)
    private TextView  CustomSliderActivity;
    @ViewInject(R.id. GuideActivity)
    private TextView  GuideActivity;
    @ViewInject(R.id. infiniteSliding)
    private TextView  infiniteSliding;
    @ViewInject(R.id. ScrollViewPagerActivity)
    private TextView  ScrollViewPagerActivity;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ViewUtils.inject(this);

    }

    @OnClick({R.id.Fresco,R.id.PullToRefreshGridView,R.id.PullToRefreshGridView2,R.id.PullToRefreshListView,R.id.AutoLoadXlListViewActivity,
            R.id. DrawerLayoutActivtiy,R.id. MaterialRefreshLayoutActivity,R.id. CustomSliderActivity,R.id. GuideActivity,R.id. infiniteSliding,R.id. ScrollViewPagerActivity})
    private void click(View v) {
        switch (v.getId()) {
            case R.id.Fresco:
                Intent intent = new Intent(this, FrescoLoadImageActivity.class);
                startActivity(intent);
                break;
            case R.id.PullToRefreshGridView:
                Intent intent1 = new Intent(this, PullToRefreshGridViewActivity.class);
                startActivity(intent1);
                break;
            case R.id.PullToRefreshGridView2:
                Intent intent2 = new Intent(this, AutoLoadGridViewActivity.class);
                startActivity(intent2);
                break;
            case R.id.PullToRefreshListView:
                Intent intent3 = new Intent(this, CustomRefreshListViewActivity.class);
                startActivity(intent3);
                break;
            case R.id.AutoLoadXlListViewActivity:
                Intent intent4 = new Intent(this, AutoLoadXlListViewActivity.class);
                startActivity(intent4);
                break;
            case R.id.DrawerLayoutActivtiy:
                Intent intent5 = new Intent(this, DrawerLayoutActivtiy.class);
                startActivity(intent5);
                break;
            case R.id.MaterialRefreshLayoutActivity:
                Intent intent6 = new Intent(this, MaterialRefreshLayoutActivity.class);
                startActivity(intent6);
                break;
            case R.id.CustomSliderActivity:
                Intent intent7 = new Intent(this, CustomSliderActivity.class);
                startActivity(intent7);
                break;
            case R.id.GuideActivity:
                Intent intent8 = new Intent(this, GuideActivity.class);
                startActivity(intent8);
                break;
            case R.id.infiniteSliding:
                Intent intent9 = new Intent(this, ViewPagerInfiniteSlidingActivity.class);
                startActivity(intent9);
                break;
            case R.id.ScrollViewPagerActivity:
                Intent intent10 = new Intent(this, ScrollViewPagerActivity.class);
                startActivity(intent10);
                break;
        }

    }
}
