package yd.miwu.view.PicRollingDisplayView;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;


import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import yd.miwu.view.PicRollingDisplayView.PicRollingDisplayView.IPicRollingDisplayViewBeanInterface;
import yd.miwu.R;

public class PicRollingDisplayViewAdapter extends PagerAdapter {

    public static final String TAG = PicRollingDisplayViewAdapter.class.getSimpleName();

    private ArrayList<View> mViews = new ArrayList<View>();
    private ArrayList<? extends PicRollingDisplayView.IPicRollingDisplayViewBeanInterface> mData;
    private PicRollingDisplayView.IPicRollingDisplayViewOnItemClickListener mItemClickListener;
    private int mPagesCount = 0;
    //private NetPicUtil mBitmapUtil;
    private BitmapUtils mBitmapUtil;
    private boolean canUnlimitRolling;


    public void setItemOnClickListener(PicRollingDisplayView.IPicRollingDisplayViewOnItemClickListener listener) {
        this.mItemClickListener = listener;
    }


    public void setData(Context context, ArrayList<? extends PicRollingDisplayView.IPicRollingDisplayViewBeanInterface> data, boolean canUnlimitRolling) {
        //清理数据
        if (mViews.size() > 0) {
            mViews.clear();
        }
        /*if (this.options == null)
            this.options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.pic_loading)
                    .showImageForEmptyUri(R.mipmap.pic_load_failure)
                    .showImageOnFail(R.mipmap.pic_load_failure)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        this.mData = data;*/
//        this.mBitmapUtils = new NetPicUtils(context);
        if (mBitmapUtil == null) {
            mBitmapUtil = new BitmapUtils(context);
        }
        this.canUnlimitRolling = canUnlimitRolling;
        //当大于一个view时才无限循环，故要在头部添加一个和最后一个数据项对应的view
        if (canUnlimitRolling) {
            if (data.size() > 1) {
                final PicRollingDisplayView.IPicRollingDisplayViewBeanInterface temp = data.get(data.size() - 1);
                addViewsToList(context, temp);
            }
        }
        //添加说有data中对应的view到View数组
        for (final PicRollingDisplayView.IPicRollingDisplayViewBeanInterface temp : data) {
            addViewsToList(context, temp);
        }
        //当大于一个view时才无限循环，故要在尾部添加一个和第一个数据项对应的view
        if (canUnlimitRolling) {
            if (data.size() > 1) {
                final PicRollingDisplayView.IPicRollingDisplayViewBeanInterface temp = data.get(0);
                addViewsToList(context, temp);
            }
        }
        notifyDataSetChanged();
        Log.e(TAG, "pic size = " + mViews.size());
    }


    private void addViewsToList(Context context, final PicRollingDisplayView.IPicRollingDisplayViewBeanInterface data) {
        View view = LayoutInflater.from(context).inflate(R.layout.base_pic_rolling_display_default_view, null);
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(data);
                }
            }
        });

        ImageView image = (ImageView) view.findViewById(R.id.base_picrolling_view_default_imageview);
        if (data.getPicType() == IPicRollingDisplayViewBeanInterface.PIC_TYPE_RESOURCE) {
            image.setImageResource(data.getPicResourceId());
        } else {
//			LogUtils.e(TAG,"PIC URL="+data.getPicURL());
//            mBitmapUtils.display(image, data.getPicURL());
//            ImageLoader.getInstance().displayImage(data.getPicURL(), image, options);
            mBitmapUtil.display(image,data.getPicURL());

        }
        mViews.add(view);
    }

    @Override
    public int getCount() {
        mPagesCount = mViews == null ? 0 : mViews.size();
        return mPagesCount;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public void destroyItem(View container, int position, Object arg2) {
        ((ViewPager) container).removeView(mViews == null ? null : mViews.get(position));
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(mViews == null ? null : mViews.get(position));
        return mViews == null ? null : mViews.get(position);

    }

}

