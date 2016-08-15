/*
 * Copyright 2014 trinea.cn All right reserved. This software is the confidential and proprietary information of
 * trinea.cn ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with trinea.cn.
 */
package yd.miwu.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import yd.miwu.R;
import yd.miwu.activity.WebViewActivity;

/**
 * @author http://blog.csdn.net/finddreams
 * @Description: 图片适配器
 */
public class ImagePagerAdapter extends BaseAdapter {

    private Context context;
    private List<String> imageIdList;
    private List<String> linkUrlArray;
    private List<String> urlTitlesList;
    private int size;
    private boolean isInfiniteLoop;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    public ImagePagerAdapter(Context context, List<String> imageIdList,
                             List<String> urllist) {
        this.context = context;
        this.imageIdList = imageIdList;
        if (imageIdList != null) {
            this.size = imageIdList.size();
        }
        this.linkUrlArray = urllist;
        this.urlTitlesList = urlTitlesList;
        isInfiniteLoop = false;
        // 初始化imageLoader 否则会报错
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.ic_launcher) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                .build();

    }

    @Override
    public int getCount() {
        // Infinite loop
        return isInfiniteLoop ? Integer.MAX_VALUE : imageIdList.size();
    }

    /**
     * get really position
     *
     * @param position
     * @return
     */
    private int getPosition(int position) {
        return isInfiniteLoop ? position % size : position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup container) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = holder.imageView = new ImageView(context);
            holder.imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        imageLoader.displayImage(imageIdList.get(getPosition(position)), holder.imageView, options);

        holder.imageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String url = linkUrlArray.get(ImagePagerAdapter.this.getPosition(position));
                /*
				 * if (TextUtils.isEmpty(url)) {
				 * holder.imageView.setEnabled(false); return; }
				 */
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtras(bundle);

                context.startActivity(intent);
                Toast.makeText(context, "点击了第" + getPosition(position) + "美女", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    private static class ViewHolder {

        ImageView imageView;
    }

    /**
     * @return the isInfiniteLoop
     */
    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    /**
     * @param isInfiniteLoop the isInfiniteLoop to set
     */
    public ImagePagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }

    @Override
    public Object getItem(int arg0) {
        return imageIdList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

}
