package yd.miwu.testdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import yd.miwu.R;

public class ReBoundActivity extends Activity {

    @ViewInject(R.id.iv_pic)
    private ImageView iv_pic;
    @ViewInject(R.id.btn_click)
    private Button btn_click;

    private Spring spring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_bound);
        ViewUtils.inject(this);

        initView();
        btn_click.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        spring.setEndValue(1.0f);
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        spring.setEndValue(0.0f);
                        break;
                }

                return true;
            }
        });


    }
    private void initView(){

        SpringSystem springSystem = SpringSystem.create();
         spring = springSystem.createSpring();
        spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(100, 1));
        spring.addListener(new SimpleSpringListener() {

            @Override
            public void onSpringUpdate(Spring spring) {

                float value = (float) spring.getCurrentValue();
                float scale = 1f - (value * 0.5f);
                iv_pic.setScaleX(scale);
                iv_pic.setScaleY(scale);
            }
        });

    }



}
