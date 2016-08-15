package yd.miwu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 256 on 2016/7/7.
 */
public class AutoTextView extends TextView {

    public AutoTextView(Context context) {
        super(context);

    }

    public AutoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean isFocused() {
        // 要显示该跑马灯，view必须要获得焦点，且单行显示，只有在取得焦点的情况下跑马灯才会显示
        return true;
    }

}
