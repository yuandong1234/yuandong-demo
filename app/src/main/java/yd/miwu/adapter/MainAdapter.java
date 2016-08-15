package yd.miwu.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.w4lle.library.NineGridAdapter;
import com.w4lle.library.NineGridlayout;

import java.util.ArrayList;
import java.util.List;

import yd.miwu.R;
import yd.miwu.mode.Image;
import yd.miwu.testdemo.ImageViewPagerActivity;

public class MainAdapter extends BaseAdapter {
    private Context context;
    private List<List<Image>> datalist;
    private NineGridAdapter adapter;

    public MainAdapter(Context context, List<List<Image>> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        List<Image> itemList = datalist.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_ninegridlayout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivMore = (NineGridlayout) convertView.findViewById(R.id.iv_ngrid_layout);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (itemList.isEmpty() || itemList.size()==0) {
            viewHolder.ivMore.setVisibility(View.GONE);
        } else {
            viewHolder.ivMore.setVisibility(View.VISIBLE);
            handlerOneImage(viewHolder, itemList);
        }

        return convertView;
    }

    private void handlerOneImage(ViewHolder viewHolder,  List<Image> image) {
      final   ArrayList<String> urlList=new ArrayList<>();
        //把图片的url 放在一个集合
        for(Image temp:image){
            urlList.add(temp.getUrl());
        }
        adapter = new Adapter(context, image);
        viewHolder.ivMore.setAdapter(adapter);
        viewHolder.ivMore.setOnItemClickListerner(new NineGridlayout.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, int position) {
                //do some thing
                //Toast.makeText(context,"onItemClick : " + position, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, ImageViewPagerActivity.class);
                intent.putExtra(ImageViewPagerActivity.EXTRA_IMAGE_INDEX, position);
                intent.putExtra(ImageViewPagerActivity.EXTRA_IMAGE_URLS, urlList);
                context.startActivity(intent);

            }
        });
    }


    class ViewHolder {
        public NineGridlayout ivMore;
    }

    class Adapter extends NineGridAdapter {

        public Adapter(Context context, List list) {
            super(context, list);
        }

        @Override
        public int getCount() {
            return (list == null) ? 0 : list.size();
        }

        @Override
        public String getUrl(int position) {
            return getItem(position) == null ? null : ((Image)getItem(position)).getUrl();
        }

        @Override
        public Object getItem(int position) {
            return (list == null) ? null : list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int i, View view) {
            ImageView iv = null;
            if (view != null && view instanceof ImageView) {
                iv = (ImageView) view;
            } else {
                iv = new ImageView(context);
            }
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setBackgroundColor(context.getResources().getColor((android.R.color.transparent)));
            String url = getUrl(i);
            Picasso.with(context).load(getUrl(i)).placeholder(new ColorDrawable(Color.parseColor("#f5f5f5"))).into(iv);
            if (!TextUtils.isEmpty(url)) {
                iv.setTag(url);
            }
            return iv;
        }
    }
}
