package yd.miwu.testdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import yd.miwu.R;
import yd.miwu.util.CustomProgressDialog;
import yd.miwu.util.SecondCustomProgressDialog;

/**
 * ProgressBar实例
 */
public class ProgressBarActivity extends Activity {

    @ViewInject(R.id.container)
    private RelativeLayout container;
    @ViewInject(R.id.progressBar)
    private ProgressBar progressBar;
    @ViewInject(R.id.progressBar1)
    private ProgressBar progressBar1;
    @ViewInject(R.id.text1)
    private TextView text1;
    @ViewInject(R.id.text2)
    private TextView text2;
    @ViewInject(R.id.text3)
    private TextView text3;
    @ViewInject(R.id.text4)
    private TextView text4;
    @ViewInject(R.id.text5)
    private TextView text5;
    @ViewInject(R.id.text6)
    private TextView text6;

    private int startNumber = 0;
    private int endNumber = 100;
    private Handler handler = new Handler();
    private Runnable run = new Runnable() {
        @Override
        public void run() {
            progressBar.setProgress(startNumber);
            startNumber += 10;
            if (startNumber == endNumber) {
                handler.removeCallbacks(run);
                progressBar.setVisibility(View.GONE);
            }
            handler.postDelayed(run, 1000);
        }
    };

    private CustomProgressDialog Dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        ViewUtils.inject(this);


    }

    @OnClick({R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5,R.id.text6})
    private void click(View v) {
        switch (v.getId()) {
            case R.id.text1:
                //在xml 中添加progressBar
                style1();
                break;
            case R.id.text2:
                //在xml 中添加progressBar
                style2();
                break;
            case R.id.text3:
                //代码创建progressBar
                style3();
                break;
            case R.id.text4:
                //代码创建progressBarDialog
                style4();
                break;
            case R.id.text5:
                //自定义progressdialog
                style5();
                break;
            case R.id.text6:
                //自定义progressdialog
                style6();
                break;
        }
    }

    private void style1() {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setMax(endNumber);
        handler.postDelayed(run, 1000);
    }

    private void style2() {
        progressBar1.setVisibility(View.VISIBLE);
    }

    private void style3() {
        Log.e("*******************", "创建ProgressBar");
        FrameLayout rootContainer = (FrameLayout) findViewById(android.R.id.content);
        // 设置对其方式为：屏幕居中对其
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;

        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(lp);
//        // 自定义小菊花
//        if (customIndeterminateDrawable != null) {
//            progressBar.setIndeterminateDrawable(customIndeterminateDrawable);
//        }
        // 将菊花添加到FrameLayout中
        rootContainer.addView(progressBar);
    }

    private void style4() {
        final ProgressDialog mypDialog = new ProgressDialog(this);
        //实例化
        mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //设置进度条风格，风格为圆形，旋转的
        //mypDialog.setTitle("Google");
        //设置ProgressDialog 标题
        mypDialog.setMessage("快来休息喽");
        //设置ProgressDialog 提示信息
        //mypDialog.setIcon(R.mipmap.icon_miwu);
        //设置ProgressDialog 标题图标
//        mypDialog.setButton("Google", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                mypDialog.cancel();
//            }
//        });
        //设置ProgressDialog 的一个Button
        //mypDialog.setIndeterminate(false);
        //设置ProgressDialog 的进度条是否不明确
        mypDialog.setCancelable(true);
        //设置ProgressDialog 是否可以按退回按键取消
        mypDialog.show();
        //让ProgressDialog显示
    }


    private void style5() {
        if (Dialog != null) {
            Dialog.cancel();
        }
        Dialog = new CustomProgressDialog(this, "正在为你寻找美女。。。。");
        Dialog.show();
    }

    private void style6() {
        SecondCustomProgressDialog dialog=SecondCustomProgressDialog.createDialog(this);
        dialog.setMessage("亲，稍等哦。。。");
        dialog.show();
    }
}
