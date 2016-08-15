package yd.miwu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.lidroid.xutils.BitmapUtils;

import yd.miwu.R;

/**
 * Created by Administrator on 2016/7/12 0012.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private LayoutInflater inflater;
    private String[] urls;
    private BitmapUtils bitmapUtils;
    private OnRecyclerViewItemClickListener listener;

    public MyAdapter(Context context, String[] urls) {
        this.context = context;
        this.urls = urls;
        this.inflater = LayoutInflater.from(context);
        this.bitmapUtils = new BitmapUtils(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_image_view, parent, false);
        view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        bitmapUtils.display(holder.img, urls[position]);
        holder.itemView.setTag(urls[position]);
    }

    @Override
    public int getItemCount() {
        return urls.length;
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(v,v.getTag());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.image);
        }

    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, Object data);
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }
}
