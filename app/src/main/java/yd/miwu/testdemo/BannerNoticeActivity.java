package yd.miwu.testdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import yd.miwu.R;
import yd.miwu.activity.WebViewActivity;

public class BannerNoticeActivity extends Activity {

    private static final String TAG = BannerNoticeActivity.class.getSimpleName();

    @ViewInject(R.id.main_notice)
    private FrameLayout main_notice;

    ArrayList<String> titleList= new ArrayList<String>();
    ArrayList<String> linkUrlArray= new ArrayList<String>();
    private LinearLayout notice_parent_ll;
    private ViewFlipper notice_vf;
    private int mCurrPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_notice);
        ViewUtils.inject(this);
        initData();
        initRollNotice();
    }


    private void initData(){
        titleList.add("常见Android进阶笔试题");
        titleList.add("GridView之仿支付宝钱包首页");
        titleList.add("仿手机QQ网络状态条的显示与消失 ");
        titleList.add("Android循环滚动广告条的完美实现 ");

        linkUrlArray.add("http://blog.csdn.net/finddreams/article/details/44301359");
        linkUrlArray.add("http://blog.csdn.net/finddreams/article/details/43486527");
        linkUrlArray.add("http://blog.csdn.net/finddreams/article/details/44648121");
        linkUrlArray.add("http://blog.csdn.net/finddreams/article/details/44619589");
    }
    private void initRollNotice(){
        notice_parent_ll = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_notice, null);
        notice_vf = ((ViewFlipper) this.notice_parent_ll.findViewById(R.id.homepage_notice_vf));
        main_notice.addView(notice_parent_ll);


        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        moveNext();
                        Log.d("Task", "下一个");
                    }
                });

            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 4000);
    }

    private void moveNext() {
        setView(this.mCurrPos, this.mCurrPos + 1);
        this.notice_vf.setInAnimation(this, R.anim.in_bottomtop);
        this.notice_vf.setOutAnimation(this, R.anim.out_bottomtop);
        this.notice_vf.showNext();
    }

    private void setView(final int curr, int next) {
        View noticeView = getLayoutInflater().inflate(R.layout.notice_item, null);
        TextView notice_tv = (TextView) noticeView.findViewById(R.id.notice_tv);
        if ((curr < next) && (next > (titleList.size() - 1))) {
            next = 0;
        } else if ((curr > next) && (next < 0)) {
            next = titleList.size() - 1;
        }
        notice_tv.setText(titleList.get(curr));
        notice_tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Bundle bundle = new Bundle();
                bundle.putString("url", linkUrlArray.get(curr));
                Intent intent = new Intent(BannerNoticeActivity.this, WebViewActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        if (notice_vf.getChildCount() > 1) {
            notice_vf.removeViewAt(0);
        }
        notice_vf.addView(noticeView, notice_vf.getChildCount());
        mCurrPos = next;

    }

}
