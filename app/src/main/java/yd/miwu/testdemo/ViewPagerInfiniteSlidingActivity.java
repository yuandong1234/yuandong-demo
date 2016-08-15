package yd.miwu.testdemo;

import android.app.Activity;

import android.os.Bundle;
import android.view.Window;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import java.util.ArrayList;

import yd.miwu.R;
import yd.miwu.view.PicRollingDisplayView.PicRollingDisplayView;
import yd.miwu.view.PicRollingDisplayView.PositionIndicatorView;

public class ViewPagerInfiniteSlidingActivity extends Activity {

    @ViewInject(R.id.picRollingDisplayView)
    private PicRollingDisplayView picRollingDisplayView;
    @ViewInject(R.id.indicatorView)
    private PositionIndicatorView indicatorView;

    private ArrayList<TextPicBean> picList = new ArrayList<TextPicBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉状态栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_infinite_sliding);
        ViewUtils.inject(this);

        initBanners();//初始化引导图

        picRollingDisplayView.setPositionIndicatorView(indicatorView);
        picRollingDisplayView.setData(picList, true);
        indicatorView.setData(picList);
        picRollingDisplayView.startTimer();
        
    }





    private void  initBanners(){
        TextPicBean data1 = new TextPicBean();
        data1.picType = PicRollingDisplayView.IPicRollingDisplayViewBeanInterface.PIC_TYPE_RESOURCE;
        data1.picResourceId = R.mipmap.star_page_image1;
        picList.add(data1);
        TextPicBean data2 = new TextPicBean();
        data2.picType = PicRollingDisplayView.IPicRollingDisplayViewBeanInterface.PIC_TYPE_RESOURCE;
        data2.picResourceId = R.mipmap.star_page_image2;
        picList.add(data2);
        TextPicBean data3 = new TextPicBean();
        data3.picType = PicRollingDisplayView.IPicRollingDisplayViewBeanInterface.PIC_TYPE_RESOURCE;
        data3.picResourceId = R.mipmap.star_page_image3;
        picList.add(data3);
        TextPicBean data4 = new TextPicBean();
        data4.picType = PicRollingDisplayView.IPicRollingDisplayViewBeanInterface.PIC_TYPE_RESOURCE;
        data4.picResourceId = R.mipmap.star_page_image4;
        picList.add(data4);

    }

    class TextPicBean implements PicRollingDisplayView.IPicRollingDisplayViewBeanInterface {

        public String picUrl;
        public int picResourceId;
        public int picType = PicRollingDisplayView.IPicRollingDisplayViewBeanInterface.PIC_TYPE_RESOURCE;

        @Override
        public int getPicType() {
            return picType;
        }

        @Override
        public int getPicResourceId() {
            return picResourceId;
        }

        @Override
        public String getPicURL() {
            return picUrl;
        }

    }



}
