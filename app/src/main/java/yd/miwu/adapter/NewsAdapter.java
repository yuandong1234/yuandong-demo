package yd.miwu.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import yd.miwu.R;
import yd.miwu.activity.WebViewActivity;
import yd.miwu.mode.NewsListBean;
import yd.miwu.testdemo.ImageViewPagerActivity;

/**
 * Created by 256 on 2016/8/8.
 */
public class NewsAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<NewsListBean>list;

    public NewsAdapter(Context context, ArrayList<NewsListBean> list) {
        this.context = context;
        this.list = list;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if(convertView==null){
            vh=new ViewHolder();
            convertView=inflater.inflate(R.layout.item_news,null);
            ViewUtils.inject(vh,convertView);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder)convertView.getTag();
        }

        final NewsListBean bean=list.get(position);
        vh.title.setText(bean.title);
        vh.authorName.setText(bean.author_name);
        vh.date.setText(bean.date);
        Picasso.with(context).load(bean.thumbnail_pic_s).placeholder(new ColorDrawable(Color.parseColor("#f5f5f5"))).into(vh.imageView);

        vh.frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("url", bean.url);
                context.startActivity(intent);
            }
        });
        vh.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> urlList=new ArrayList<String>();
                urlList.add(bean.thumbnail_pic_s03);
                Intent intent=new Intent(context, ImageViewPagerActivity.class);
                intent.putExtra(ImageViewPagerActivity.EXTRA_IMAGE_INDEX, 1);
                intent.putExtra(ImageViewPagerActivity.EXTRA_IMAGE_URLS, urlList);
                context.startActivity(intent);
            }
        });


        return convertView;
    }

    class ViewHolder{
        @ViewInject(R.id.title)
        private TextView title;
        @ViewInject(R.id.authorName)
        private TextView authorName;
        @ViewInject(R.id.date)
        private TextView date;
        @ViewInject(R.id.imageView)
        private ImageView imageView;
        @ViewInject(R.id.frame)
        private LinearLayout frame;

    }
}
