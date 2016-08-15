package yd.miwu.testdemo;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import yd.miwu.R;

public class GuideActivity extends Activity {

    @ViewInject(R.id.viewPager)
    private ViewPager viewPager;
    @ViewInject(R.id.pointContainer)
    private LinearLayout pointContainer;

    private ArrayList<View> banners=new ArrayList<>();
    private ArrayList<ImageView> points=new ArrayList<>();
    private int Current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉状态栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ViewUtils.inject(this);

        initBanners();//初始化引导图

        initPoint();//初始化焦点

        viewPager.setAdapter(new MyPagerAdapter());  // 设置适配器

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Current = position;
                Toast.makeText(GuideActivity.this, "position =" + position + "  " + "Current =" + Current, Toast.LENGTH_SHORT).show();

                for (ImageView point : points) {
                    point.setEnabled(false);
                }
                points.get(position).setEnabled(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void  initBanners(){

        ImageView img = new ImageView(this);
        LinearLayout.LayoutParams params =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        img.setLayoutParams(params);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.setImageResource(R.mipmap.star_page_image1);
        banners.add(img);
        ImageView img1 = new ImageView(this);
        LinearLayout.LayoutParams params1 =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        img1.setLayoutParams(params1);
        img1.setScaleType(ImageView.ScaleType.FIT_XY);
        img1.setImageResource(R.mipmap.star_page_image2);
        banners.add(img1);
        ImageView img2 = new ImageView(this);
        LinearLayout.LayoutParams params2 =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        img2.setLayoutParams(params2);
        img2.setScaleType(ImageView.ScaleType.FIT_XY);
        img2.setImageResource(R.mipmap.star_page_image3);
        banners.add(img2);
        ImageView img3= new ImageView(this);
        LinearLayout.LayoutParams params3 =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        img3.setLayoutParams(params3);
        img3.setScaleType(ImageView.ScaleType.FIT_XY);
        img3.setImageResource(R.mipmap.star_page_image4);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GuideActivity.this, "开启体验之旅。。。。", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                     finish();
                    }
                }, 2000);
            }
        });
        banners.add(img3);
    }

    private void initPoint(){
        for(int i=0;i<banners.size();i++){
            ImageView image=new ImageView(this);
            image.setImageResource(R.drawable.points_selector);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(20,20);
            params.rightMargin=10;
            image.setLayoutParams(params);
            pointContainer.addView(image);
            if(i==0){
                image.setEnabled(true);
            }else{
                image.setEnabled(false);
            }
            points.add(image);
        }
    }


    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(banners.get(position));
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(banners.get(position));
            return banners.get(position);
        }
        @Override
        public int getCount() {
            return banners.size();
        }
        @Override
        public boolean isViewFromObject(View arg, Object arg1) {
            return arg == arg1;
        }
    }

}
