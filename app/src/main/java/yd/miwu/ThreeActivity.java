package yd.miwu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import yd.miwu.testdemo.AytuScrollTextViewActivity;
import yd.miwu.testdemo.BannerNoticeActivity;
import yd.miwu.testdemo.BannerViewActivity;
import yd.miwu.testdemo.ClearEditTextActivity;
import yd.miwu.testdemo.ClickAbleTextViewActivity;
import yd.miwu.testdemo.CropActivity;
import yd.miwu.testdemo.MainGroupActivity;
import yd.miwu.testdemo.NineLayoutActivity;
import yd.miwu.testdemo.OkHttpActivity;
import yd.miwu.testdemo.PickViewActivity;
import yd.miwu.testdemo.ReBoundActivity;
import yd.miwu.testdemo.RecyclerViewActivity;
import yd.miwu.testdemo.YDScrollViewViewPagerActivity;
import yd.miwu.tubatu.TubaTuActivity;
import yd.miwu.winxindemo.WinXinMainActivity;

public class ThreeActivity extends Activity {
    @ViewInject(R.id.YDScrollViewViewPagerActivity)
    private TextView YDScrollViewViewPagerActivity;
    @ViewInject(R.id.NineLayout)
    private TextView NineLayout;
    @ViewInject(R.id.ClearEditText)
    private TextView ClearEditText;
    @ViewInject(R.id.WeiXinActivity)
    private TextView WeiXinActivity;
    @ViewInject(R.id.pickViewActivity)
    private TextView pickViewActivity;
    @ViewInject(R.id.AutoTextView)
    private TextView AutoTextView;
    @ViewInject(R.id.clickAbleTextView)
    private TextView clickAbleTextView;
    @ViewInject(R.id.BannerView)
    private TextView BannerView;
    @ViewInject(R.id.BannerNotice)
    private TextView BannerNotice;
    @ViewInject(R.id.recyclerView)
    private TextView recyclerView;
    @ViewInject(R.id.cropView)
    private TextView cropView;
    @ViewInject(R.id.customViewPager)
    private TextView customViewPager;
    @ViewInject(R.id.mainGroupView)
    private TextView mainGroupView;
    @ViewInject(R.id.reboundView)
    private TextView reboundView;
    @ViewInject(R.id.OkHttp)
    private TextView OkHttp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        ViewUtils.inject(this);
    }


    @OnClick({R.id.YDScrollViewViewPagerActivity, R.id.NineLayout, R.id.ClearEditText, R.id.WeiXinActivity, R.id.pickViewActivity, R.id.AutoTextView,
            R.id.clickAbleTextView, R.id.BannerView, R.id.BannerNotice, R.id.recyclerView, R.id.cropView, R.id.customViewPager, R.id.mainGroupView
            , R.id.reboundView,R.id.OkHttp})
    private void click(View v) {
        switch (v.getId()) {
            case R.id.YDScrollViewViewPagerActivity:
                Intent intent = new Intent(ThreeActivity.this, YDScrollViewViewPagerActivity.class);
                startActivity(intent);
                break;
            case R.id.NineLayout:
                Intent intent1 = new Intent(ThreeActivity.this, NineLayoutActivity.class);
                startActivity(intent1);
                break;
            case R.id.ClearEditText:
                Intent intent2 = new Intent(ThreeActivity.this, ClearEditTextActivity.class);
                startActivity(intent2);
                break;
            case R.id.WeiXinActivity:
                Intent intent3 = new Intent(ThreeActivity.this, WinXinMainActivity.class);
                startActivity(intent3);
                break;
            case R.id.pickViewActivity:
                Intent intent4 = new Intent(ThreeActivity.this, PickViewActivity.class);
                startActivity(intent4);
                break;
            case R.id.AutoTextView:
                Intent intent5 = new Intent(ThreeActivity.this, AytuScrollTextViewActivity.class);
                startActivity(intent5);
                break;
            case R.id.clickAbleTextView:
                Intent intent6 = new Intent(ThreeActivity.this, ClickAbleTextViewActivity.class);
                startActivity(intent6);
                break;
            case R.id.BannerView:
                Intent intent7 = new Intent(ThreeActivity.this, BannerViewActivity.class);
                startActivity(intent7);
                break;
            case R.id.BannerNotice:
                Intent intent8 = new Intent(ThreeActivity.this, BannerNoticeActivity.class);
                startActivity(intent8);
                break;
            case R.id.recyclerView:
                Intent intent9 = new Intent(ThreeActivity.this, RecyclerViewActivity.class);
                startActivity(intent9);
                break;
            case R.id.cropView:
                Intent intent10 = new Intent(ThreeActivity.this, CropActivity.class);
                startActivity(intent10);
                break;
            case R.id.customViewPager:
                Intent intent11 = new Intent(ThreeActivity.this, TubaTuActivity.class);
                startActivity(intent11);
                break;
            case R.id.mainGroupView:
                Intent intent12 = new Intent(ThreeActivity.this, MainGroupActivity.class);
                startActivity(intent12);
                break;
            case R.id.reboundView:
                Intent intent13 = new Intent(ThreeActivity.this, ReBoundActivity.class);
                startActivity(intent13);
                break;
            case R.id.OkHttp:
                Intent intent14 = new Intent(ThreeActivity.this, OkHttpActivity.class);
                startActivity(intent14);
                break;
        }
    }
}
