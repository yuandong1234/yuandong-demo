package yd.miwu.tubatu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import yd.miwu.R;
import yd.miwu.tubatu.adapter.RecyclingPagerAdapter;
import yd.miwu.util.Utils;

public class TubaTuActivity extends AppCompatActivity {

    @ViewInject(R.id.viewpager)
    private ClipViewPager viewpager;
    @ViewInject(R.id.page_container)
    private RelativeLayout container;

    private TubatuAdapter mPagerAdapter;
    private ArrayList<View> views;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuba_tu);
        ViewUtils.inject(this);
        initView();
        viewpager.setPageTransformer(true, new ScalePageTransformer());
        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewpager.dispatchTouchEvent(event);
            }
        });
        mPagerAdapter = new TubatuAdapter(this,views);
        viewpager.setAdapter(mPagerAdapter);

        //initData();
    }


//    private void initData() {
//        List<Integer> list = new ArrayList<>();
//        list.add(R.drawable.style_xiandai);
//        list.add(R.drawable.style_jianyue);
//        list.add(R.drawable.style_oushi);
//        list.add(R.drawable.style_zhongshi);
//        list.add(R.drawable.style_meishi);
//        list.add(R.drawable.style_dzh);
//        list.add(R.drawable.style_dny);
//        list.add(R.drawable.style_rishi);
//
//        //设置OffscreenPageLimit
//        viewpager.setOffscreenPageLimit(list.size());
//        mPagerAdapter.addAll(list);
//    }

    private void initView(){
       views=new ArrayList<>();
        View view1=View.inflate(this,R.layout.activity_item_viewpager_one,null);
        views.add(view1);
        View view2=View.inflate(this,R.layout.activity_item_viewpager_two,null);
        views.add(view2);
        View view3=View.inflate(this,R.layout.activity_item_viewpager_three,null);
        views.add(view3);
        View view4=View.inflate(this,R.layout.activity_item_viewpager_four,null);
        views.add(view4);

        //设置OffscreenPageLimit
        viewpager.setOffscreenPageLimit(views.size());
        //设置item之间的间距
        viewpager.setPageMargin(Utils.dp2px(this,25));
        //mPagerAdapter.addAll(list);
    }

    /**
     *自定义adapter
     */
    private  class TubatuAdapter extends RecyclingPagerAdapter {

        private final List<Integer> mList;
        private ArrayList<View> list;
        private final Context mContext;

        public TubatuAdapter(Context context,ArrayList<View> views) {
            mList = new ArrayList<>();
            mContext = context;
            this.list=views;
        }

        public void addAll(List<Integer> list) {
            mList.addAll(list);
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
//            ImageView imageView = null;
//            if (convertView == null) {
//                imageView = new ImageView(mContext);
//            } else {
//                imageView = (ImageView) convertView;
//            }
//            imageView.setTag(position);
//            imageView.setImageResource(mList.get(position));
            return list.get(position);
        }

        @Override
        public int getCount() {
           // return mList.size();
            return list.size();
        }
    }

}
