package yd.miwu.testdemo;

import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import yd.miwu.R;
import yd.miwu.view.CustomScrollView;

public class ScrollViewAlpraActivity extends AppCompatActivity {

    @ViewInject(R.id.scrollView)
    private CustomScrollView scrollView;
    @ViewInject(R.id.titleBar)
    private RelativeLayout titleBar;
    @ViewInject(R.id.title)
    private TextView title;

    private int height;
    private boolean isFirst=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_alpra);
        ViewUtils.inject(this);
        //获得屏幕的宽高
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        height = dm.heightPixels;//高度

        titleBar.getBackground().setAlpha(0);
        int color = getResources().getColor(R.color.backgroundWhite);
        color = ColorUtils.setAlphaComponent(color, 0);
        title.setTextColor(color);

        scrollView.setScrollViewListener(new CustomScrollView.ScrollViewListener() {

            @Override
            public void onScrollChanged(View scrollView, int x, int y, int oldx, int oldy) {
                if(y>=height/2){
                    titleBar.getBackground().setAlpha(255);
                    title.setTextColor(getResources().getColor(R.color.backgroundWhite));
                }else if(y<=0){
                    titleBar.getBackground().setAlpha(0);
                    int color = getResources().getColor(R.color.backgroundWhite);
                    color = ColorUtils.setAlphaComponent(color, 0);
                    title.setTextColor(color);

                }else{
                    int alpha = 255*2 * y /(height) ;
                    titleBar.getBackground().setAlpha(alpha);
                    int color = getResources().getColor(R.color.backgroundWhite);
                    color = ColorUtils.setAlphaComponent(color, alpha);
                    title.setTextColor(color);

                }
            }
        });

    }
}
