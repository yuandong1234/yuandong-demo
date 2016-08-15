package yd.miwu.testdemo;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;



import yd.miwu.R;
import yd.miwu.view.GrlleryScrollView.YDGallery;

public class YDScrollViewViewPagerActivity extends Activity {


    @ViewInject(R.id.adgallery)
    private YDGallery adgallery;
    @ViewInject(R.id.ovalLayout)
    private LinearLayout ovalLayout;


  // 广告控件
    // 圆点容器
    /** 图片id的数组,本地测试用 */
    private int[] imageId = new int[] { R.mipmap.star_page_image1, R.mipmap.star_page_image2,
            R.mipmap.star_page_image3, R.mipmap.star_page_image4 };
    /** 图片网络路径数组 */
    private String[] mris = {

            "http://img.my.csdn.net/uploads/201312/14/1386989803_3335.PNG",
            "http://img.my.csdn.net/uploads/201312/14/1386989613_6900.jpg",
            "http://img.my.csdn.net/uploads/201312/14/1386989802_7236.PNG"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ydscroll_view_view_pager);
        ViewUtils.inject(this);
        adgallery.start(this, mris, imageId, 3000, ovalLayout,
                R.drawable.cricle_red, R.drawable.cricle_white);
        adgallery.setMyOnItemClickListener(new YDGallery.MyOnItemClickListener() {
            public void onItemClick(int curIndex) {
                Toast.makeText(YDScrollViewViewPagerActivity.this, "点击的图片下标为:" + curIndex,
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        adgallery.startTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        adgallery.stopTimer();
    }
}
