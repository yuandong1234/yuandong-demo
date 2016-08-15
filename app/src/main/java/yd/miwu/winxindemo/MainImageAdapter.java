package yd.miwu.winxindemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import yd.miwu.R;

/**
 * Created by 256 on 2016/7/4.
 */
public class MainImageAdapter extends BaseAdapter {

    private ArrayList<ImageBean> list;
    private Context context;
    private LayoutInflater inflater;

    public MainImageAdapter(ArrayList<ImageBean> list, Context context) {
        this.list = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (list.size() == 0) {
            return 1;
        } else if (list.size() == 9) {
            return list.size();
        } else if (list.size() < 9) {
            return list.size()+1;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(position==list.size()){
            return null;
        }
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.item_publish,null);
        ImageView imageIv = (ImageView) convertView.findViewById(R.id.item_grid_image);
        if(list!=null){
            if(position==list.size()){
                return convertView;
            }
            ImageBean bean = list.get(position);
            ImageDisplayer.getInstance(context).displayBmp(imageIv,bean.thumbnailPath, bean.sourcePath);
        }
        return convertView;
    }
}
