package yd.miwu.view.PicRollingDisplayView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;



import java.util.ArrayList;

import yd.miwu.R;

/**
 * 图片轮播类PicRollingDisplayView的位置指示自定义view
 * 本View需要结合PicRollingDisplayView一起使用用以显示PicRollingDisplayView中当前显示的图片的position
 * @author Alex
 *
 */
public class PositionIndicatorView extends LinearLayout implements PicRollingDisplayView.IPicRollingDisplayViewOnPageChangedListener {

	private ArrayList<? extends PicRollingDisplayView.IPicRollingDisplayViewBeanInterface> mItems;
	private Drawable mFocusDrawable;
	private Drawable mUnfocusDrawable;
	private int mGapPx = 30;
	private int mIndicatorWidthPx = 15;
	private int mIndicatorHeightPx = 15;
	private Context mCtx;
	/**
	 * 
	 * @param context
	 * @param focusStatusPicResourceId
	 * @param unfocusStatusPicResourceId
	 * @param gapPx 单位像素
	 * @throws GetResourceFailureException 
	 */
	public PositionIndicatorView(Context context , int focusStatusPicResourceId,
			int unfocusStatusPicResourceId , int gapPx , int indicatorWidth,int indicatorHeight) throws GetResourceFailureException {
		super(context, null);
		this.mCtx = context;
		if(gapPx > 0)
			this.mGapPx = gapPx;
		if(indicatorWidth > 0)
			this.mIndicatorWidthPx = indicatorWidth;
		if(indicatorHeight > 0)
			this.mIndicatorHeightPx = indicatorHeight;
		
		Bitmap bitmap =  BitmapFactory.decodeResource(context.getResources(), focusStatusPicResourceId);
		if(bitmap == null){
			throw new GetResourceFailureException("Can't get image resource for focusStatus in PositionIndicatorView");
		}
		mFocusDrawable = new BitmapDrawable(context.getResources(),bitmap);

		bitmap =  BitmapFactory.decodeResource(context.getResources(), unfocusStatusPicResourceId);
		if(bitmap == null)
			throw new GetResourceFailureException("Can't get image resource for unfocusStatus in PositionIndicatorView");
		mUnfocusDrawable = new BitmapDrawable(context.getResources(),bitmap);
		
	}

	public PositionIndicatorView(Context context, AttributeSet attrs) throws GetResourceFailureException {
		super(context, attrs);
		this.mCtx = context;
		initParams(context,attrs);
	}
	
	private void initParams(Context context, AttributeSet attrs) throws GetResourceFailureException{
		TypedArray t = getContext().obtainStyledAttributes(attrs,R.styleable.CustomPicRollingPositionIndicatorView);
		mFocusDrawable = t.getDrawable(R.styleable.CustomPicRollingPositionIndicatorView_selectedSrc);
		if(mFocusDrawable == null)
			throw new GetResourceFailureException("Can't get image resource for focusStatus in PositionIndicatorView");
		mUnfocusDrawable = t.getDrawable(R.styleable.CustomPicRollingPositionIndicatorView_defaultSrc);
		if(mUnfocusDrawable == null)
			throw new GetResourceFailureException("Can't get image resource for unfocusStatus in PositionIndicatorView");
		mGapPx = t.getDimensionPixelSize(R.styleable.CustomPicRollingPositionIndicatorView_gap, 30);
		mIndicatorWidthPx = t.getDimensionPixelSize(R.styleable.CustomPicRollingPositionIndicatorView_indicatorWidth, 15);
		mIndicatorHeightPx = t.getDimensionPixelSize(R.styleable.CustomPicRollingPositionIndicatorView_indicatorHeight, 15);
		
		t.recycle();
	}

	public void setData(ArrayList<? extends PicRollingDisplayView.IPicRollingDisplayViewBeanInterface> data){
		if(data == null)
			return;
		this.mItems = data;
		setViews();
	}

	private void setViews(){
		this.setOrientation(LinearLayout.HORIZONTAL);
		this.setGravity(Gravity.CENTER);
		/*LayoutParams parentParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		this.setLayoutParams(parentParams);*/
		if(mItems.size() > 1) {
			if(getChildCount() > 0){
				removeAllViews();
			}
			for (int i = 0; i < mItems.size(); i++) {
				ImageView imageView = new ImageView(mCtx);
				LayoutParams params = new LayoutParams(mIndicatorWidthPx, mIndicatorHeightPx);
				params.setMargins(mGapPx / 2, 0, mGapPx / 2, 0);
				if (i == 0)
					imageView.setImageDrawable(mFocusDrawable);
				else
					imageView.setImageDrawable(mUnfocusDrawable);
				addView(imageView, params);
			}
		}
	}

	public class GetResourceFailureException extends Exception {    
		public GetResourceFailureException(String message) {
			super(message);
		}
		public GetResourceFailureException()  {}

	}

	@Override
	public void pageChanged(int position) {
		for(int i = 0 ; i < getChildCount() ; i ++){
			if(i == position)
				((ImageView)getChildAt(i)).setImageDrawable(mFocusDrawable);
			else
				((ImageView)getChildAt(i)).setImageDrawable(mUnfocusDrawable);
		}
	}
}
