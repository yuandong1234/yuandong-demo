package yd.miwu.winxindemo;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import yd.miwu.R;

public class ImageGridAdapter extends BaseAdapter
{
	private Context mContext;
	private List<ImageBean> mDataList;

	public ImageGridAdapter(Context context, List<ImageBean> dataList)
	{
		this.mContext = context;
		this.mDataList = dataList;
	}

	@Override
	public int getCount()
	{
		return mDataList == null ? 0 : mDataList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mDataList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final ViewHolder mHolder;

		if (convertView == null)
		{
			convertView = View
					.inflate(mContext, R.layout.item_image_list, null);
			mHolder = new ViewHolder();
			mHolder.imageIv = (ImageView) convertView.findViewById(R.id.image);
			mHolder.selectedIv = (ImageView) convertView
					.findViewById(R.id.selected_tag);
			mHolder.selectedBgTv = (TextView) convertView
					.findViewById(R.id.image_selected_bg);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}

		final ImageBean item = mDataList.get(position);

		ImageDisplayer.getInstance(mContext).displayBmp(mHolder.imageIv, item.thumbnailPath, item.sourcePath);

		if (item.isSelected) {
			//mHolder.selectedIv.setImageDrawable(mContext.getResources().getDrawable(R.drawable.tag_selected));
			mHolder.selectedIv.setImageResource(R.mipmap.select);
			mHolder.selectedIv.setVisibility(View.VISIBLE);
			mHolder.selectedBgTv.setBackgroundResource(R.drawable.image_selected);
		} else {
			mHolder.selectedIv.setImageDrawable(null);
			mHolder.selectedIv.setVisibility(View.GONE);
			mHolder.selectedBgTv.setBackgroundResource(R.color.miwuBaseBackGroundColor);
		}

		return convertView;
	}

	static class ViewHolder
	{
		private ImageView imageIv;
		private ImageView selectedIv;
		private TextView selectedBgTv;
	}

}
