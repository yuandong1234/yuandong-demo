package yd.miwu.testdemo;

import android.app.Activity;
import android.os.Bundle;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import yd.miwu.R;
import yd.miwu.adapter.ImagePagerAdapter;
import yd.miwu.view.bannerview.CircleFlowIndicator;
import yd.miwu.view.bannerview.ViewFlow;

public class BannerViewActivity extends Activity {

    private final static String TAG = BannerViewActivity.class.getSimpleName();

    @ViewInject(R.id.viewFlow)
    private ViewFlow viewFlow;
    @ViewInject(R.id.indicator)
    private CircleFlowIndicator indicator;

    private ArrayList<String> imageUrlList = new ArrayList<String>();
    ArrayList<String> linkUrlArray= new ArrayList<String>();
    ArrayList<String> titleList= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_view);
        ViewUtils.inject(this);
        initData();

        initBanner();
    }


    private void initData() {
        imageUrlList.add("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg");
        imageUrlList.add("http://g.hiphotos.baidu.com/image/pic/item/6159252dd42a2834da6660c459b5c9ea14cebf39.jpg");
        imageUrlList.add("http://d.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd976427f6c3901213fb80e911c.jpg");
        imageUrlList.add("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg");

        linkUrlArray.add("http://blog.csdn.net/finddreams/article/details/44301359");
        linkUrlArray.add("http://blog.csdn.net/finddreams/article/details/43486527");
        linkUrlArray.add("http://blog.csdn.net/finddreams/article/details/44648121");
        linkUrlArray.add("http://blog.csdn.net/finddreams/article/details/44619589");

        titleList.add("常见Android进阶笔试题");
        titleList.add("GridView之仿支付宝钱包首页");
        titleList.add("仿手机QQ网络状态条的显示与消失 ");
        titleList.add("Android循环滚动广告条的完美实现 ");
    }

    private void initBanner() {
        viewFlow.setAdapter(new ImagePagerAdapter(this, imageUrlList, linkUrlArray).setInfiniteLoop(true));
        viewFlow.setmSideBuffer(imageUrlList.size()); // 实际图片张数，// 我的ImageAdapter实际图片张数为3

        viewFlow.setFlowIndicator(indicator);
        viewFlow.setTimeSpan(4500);
        viewFlow.setSelection(imageUrlList.size() * 1000); // 设置初始位置
        viewFlow.startAutoFlowTimer(); // 启动自动播放
    }
}
