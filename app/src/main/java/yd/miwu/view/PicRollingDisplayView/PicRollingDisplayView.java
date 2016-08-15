package yd.miwu.view.PicRollingDisplayView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import java.util.ArrayList;

import yd.miwu.R;

/**
 * 图片轮播显示类，该类可以提供图片被点击和显示图片变化的监听，可以通过监听接口获取到被点击的图片对应的Object以及
 * 目前显示的是第几张图片的信息，
 * 另外，该类中可以添加 PositionIndicatorView 作为默认的positionIndicatorView ，添加方式有两种：
 * 一种是直接添加到本自定义view中，以一种是将positionIndicatorView作为外部View使其和本自定义View产生关联，
 * 来指示显示图片位置的状态
 * 如果通过startTimer（）启动了自动轮播，请在退出activity时调用stopTimer()来结束处理轮播消息的Handler
 * @author Alex
 *
 */
public class PicRollingDisplayView extends FrameLayout {

	private ArrayList<? extends IPicRollingDisplayViewBeanInterface> mItems;
	private ArrayList<View> mViews ;
	private int mWidth = -1;//控件显示宽度
	private int mHeight = -1;//控件显示高度
	private Drawable mImage;//默认图片
	private ViewPager mViewPage;
	private int mTimeGap = 3;//图片切换间隔时间 单位秒
	private PicRollingDisplayViewAdapter mAdapter;
	private Context mCtx;
	private IPicRollingDisplayViewOnItemClickListener mItemClickListener;
	private IPicRollingDisplayViewOnPageChangedListener mPageChangedListener;
	private PositionIndicatorView mPositionIndicatorView;
	private View mParentview ;
	private boolean needAddIndicatorView = false;
	private boolean isUnlimitedRollong = false;
	private TimerHandler mTimerHandler;

	private boolean canUnlimitRolling = true;//可以无限循环

	public PicRollingDisplayView(Context context,int widthPx,int heightPx) {
		super(context, null);
		this.mCtx = context;
		this.mWidth = widthPx;
		this.mHeight = heightPx;
		initDefault();
	}

	public PicRollingDisplayView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mCtx = context;
		initParams(context, attrs);
	}

	/**
	 * 设置需要显示的图片资源列表。
	 * 在PicRollingDisplayView中会调用IPicRollingDisplayViewBeanInterface中的接口来获取图片信息；
	 * 如果PicRollingDisplayViewAdapter mAdapter已经实例化，
	 * 并且IPicRollingDisplayViewOnItemClickListener mItemClickListener不为null就为mAdapter设置mItemClickListener
	 * @param data
	 */
	public void setData(ArrayList<? extends IPicRollingDisplayViewBeanInterface> data ,boolean canUnlimitRolling){
		this.canUnlimitRolling = canUnlimitRolling;
		if(data == null)
			return;
		this.mItems = data;
		if(data.size() > 1)
			isUnlimitedRollong = true;
		resetViews();
		mAdapter.setData(mCtx,data,canUnlimitRolling);
		//当无限循环时需要展示第二个图片view,并同时启动计时器
		if(canUnlimitRolling) {
			if (isUnlimitedRollong) {
				mViewPage.setCurrentItem(1, false);
			}
		}else{
			mViewPage.setCurrentItem(0, false);
		}
		
		if(this.mItemClickListener != null)
			mAdapter.setItemOnClickListener(this.mItemClickListener);
	}

	/**
	 * 设置PositionIndicatorView 使外部的PositionIndicatorView和PicRollingDisplayView产生联系，
	 * 使PositionIndicatorView可以根据PicRollingDisplayView的变化进行指示的变化
	 * @param listener
	 */
	public void setPositionIndicatorView(PositionIndicatorView listener){
		needAddIndicatorView = false;
		mPositionIndicatorView = listener;
	}

	/**
	 * 将PositionIndicatorView listener作为子View添加到当前布局中，
	 * 并使PositionIndicatorView和PicRollingDisplayView产生联系，
	 * 使PositionIndicatorView可以根据PicRollingDisplayView的变化进行指示的变化
	 * @param listener
	 */
	public void setPositionIndicatorViewAsChildView(PositionIndicatorView listener){
		mPositionIndicatorView = listener;
		needAddIndicatorView = true;
	}

	/**
	 * 设置不同page内容被点击的监听器
	 * @param listener
	 */
	public void setItemOnClickListener(IPicRollingDisplayViewOnItemClickListener listener){
		this.mItemClickListener = listener;
		if(mAdapter != null)
			mAdapter.setItemOnClickListener(this.mItemClickListener);
	}

	/**
	 * 设置page滑动变化的监听器
	 * @param listener
	 */
	public void setOnPageChangedListener(IPicRollingDisplayViewOnPageChangedListener listener){
		this.mPageChangedListener = listener;
	}


	private void initParams(Context context, AttributeSet attrs){
		TypedArray t = getContext().obtainStyledAttributes(attrs, R.styleable.CustomPicRollingDisplayView);
		mImage = t.getDrawable(R.styleable.CustomPicRollingDisplayView_defaultPicSrc);
		mTimeGap = t.getInt(R.styleable.CustomPicRollingDisplayView_timeGap, 3);
		t.recycle();
		initDefault();
	}


	/**
	 * 初始化只显示一张ImageView的view并添加到挡圈布局进行显示
	 */
	private void initDefault(){
		this.setBackgroundColor(mCtx.getResources().getColor(android.R.color.darker_gray));
		View view = LayoutInflater.from(mCtx).inflate(R.layout.base_pic_rolling_display_default_view, null);
		ImageView defaultView = (ImageView) view.findViewById(R.id.base_picrolling_view_default_imageview);
		if(mImage != null)
			defaultView.setImageDrawable(mImage);
		if(mWidth > 0 && mHeight > 0)
			this.addView(view,mWidth,mHeight);
		else
			this.addView(view);
		ViewTreeObserver vto = this.getViewTreeObserver(); 
		vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
			public boolean onPreDraw() {
				getViewTreeObserver().removeOnPreDrawListener(this);
				int height = getMeasuredHeight();
				int width = getMeasuredWidth();
				if (height > 0 && width > 0) {
					mWidth = width;
					mHeight = height;
				}
				return true;
			}
		}); 	
	}

	/**
	 * 初始化含有viewPager的View添加到当前布局，并且设置PicRollingDisplayViewAdapter
	 * 和IPicRollingDisplayViewOnPageChangedListener，
	 * 同事判断是否需要将PositionIndicatorView添加到当前布局中并作相应添加的操作
	 */
	private void resetViews(){
		mParentview =  LayoutInflater.from(mCtx).inflate(R.layout.base_pic_rolling_display_view, null);
		mViewPage = (ViewPager) mParentview.findViewById(R.id.base_pic_rolling_display_veiwpager);
		this.removeAllViews();
		this.addView(mParentview,mWidth,mHeight);
		if(mPageChangedListener != null)
			mPageChangedListener.pageChanged(1);
		mViewPage.setAdapter(getGuidePageAdapter());
		
		mViewPage.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				int realPosition = position;
				if(canUnlimitRolling) {
					if (isUnlimitedRollong) {
						if (position == mItems.size() + 1) {
							realPosition = 0;
							positionMoveHandler.sendEmptyMessageDelayed(0, 1000);
//						mViewPage.setCurrentItem(1, false);
						} else if (position == 0) {
							realPosition = mItems.size() - 1;
							positionMoveHandler.sendEmptyMessageDelayed(1, 1000);
//						mViewPage.setCurrentItem(mItems.size(), false);
						} else
							realPosition = position - 1;
					}
				}
				if(mPageChangedListener != null)
					mPageChangedListener.pageChanged(realPosition);
				if(mPositionIndicatorView != null)
					mPositionIndicatorView.pageChanged(realPosition);

				//重新计时
				stopTimer();
				startTimer();

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		if(needAddIndicatorView){
			RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			params.setMargins(0, 10, 0, 10);
			params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE); 
			params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE); 
			mPositionIndicatorView.setLayoutParams(params);
			((ViewGroup) mParentview).addView(mPositionIndicatorView);
			((ViewGroup) mParentview).bringChildToFront(mPositionIndicatorView);
		}
	}

	private PicRollingDisplayViewAdapter getGuidePageAdapter(){
		if(mAdapter == null)
			mAdapter = new PicRollingDisplayViewAdapter();
		return mAdapter;
	}


	/**
	 * 使用PicRollingDisplayView时 pojo需要实现的接口，在PicRollingDisplayView中会调用接口来获取图片信息
	 * @author Alex
	 *
	 */
	public interface IPicRollingDisplayViewBeanInterface{
		public static final int PIC_TYPE_URL = 1;
		public static final int PIC_TYPE_RESOURCE = 2;

		public abstract int getPicType();
		public abstract int getPicResourceId();
		public abstract String getPicURL();
	}

	/**
	 * 滚动内容中Item被点击的监听器
	 * @author Alex
	 *
	 */
	public interface IPicRollingDisplayViewOnItemClickListener{
		public abstract void onItemClick(IPicRollingDisplayViewBeanInterface item);
	}

	/**
	 * 滚动内容中显示内容变化监听器
	 * @author Alex
	 *
	 */
	public interface IPicRollingDisplayViewOnPageChangedListener{
		public abstract void pageChanged(int position);
	}
	
	
	class TimerHandler extends Handler{

		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 0){
				int currentPosition = mViewPage.getCurrentItem();
				mViewPage.setCurrentItem(currentPosition+1);
				mTimerHandler.sendEmptyMessageDelayed(0, mTimeGap * 1000);
			}
			super.handleMessage(msg);
		}
		
	}
	
	/**
	 * 启动计时器来完成自动轮播
	 */
	public void startTimer(){
		if(mTimerHandler == null)
			mTimerHandler = new TimerHandler();
		mTimerHandler.sendEmptyMessageDelayed(0, mTimeGap * 1000);
	}
	
	/**
	 * 关闭计时器来完成自动轮播
	 */
	public void stopTimer(){
		if(mTimerHandler != null){
			mTimerHandler.removeMessages(0);
		}
	}


	private PositionMoveHandler positionMoveHandler = new PositionMoveHandler();
	class PositionMoveHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 1){
				mViewPage.setCurrentItem(mItems.size(), false);
			}else if(msg.what == 0){
				mViewPage.setCurrentItem(1, false);
			}
		}
	}

	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		super.onVisibilityChanged(changedView, visibility);
		Log.e("afdsadsfasdfa", "************onVisibilityChanged(" + visibility + ")");
		if(visibility == INVISIBLE){
			if(mTimerHandler != null){
				mTimerHandler.removeMessages(0);
			}
		}
		if(visibility == VISIBLE){
			if(mTimerHandler != null){
				mTimerHandler.removeMessages(0);
				mTimerHandler.sendEmptyMessageDelayed(0, mTimeGap * 1000);
			}
		}
	}
}
