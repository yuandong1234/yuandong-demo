package yd.miwu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by 256 on 2016/6/7.
 */
public class CustomScrollView extends ScrollView {
    private ScrollViewListener listener;
    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        listener.onScrollChanged(this, l, t, oldl, oldt);
    }

    //是否要其弹性滑动
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
                                   int scrollY, int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        // 弹性滑动关键则是maxOverScrollX， 以及maxOverScrollY，
        // 一般默认值都是0，需要弹性时，更改其值即可
        // 即就是，为零则不会发生弹性，不为零（>0,负数未测试）则会滑动到其值的位置
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
                scrollRangeY, 0, 0, isTouchEvent);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener){
        this.listener=scrollViewListener;
    }

    public interface  ScrollViewListener{

        void onScrollChanged(View scrollView, int x, int y, int oldx, int oldy);
    }
}
