package yd.miwu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.widget.ScrollView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import yd.miwu.R;
import yd.miwu.base.BaseActivity;

public class FirstActivity extends BaseActivity {


    @ViewInject(R.id.ScrollView)
    private PullToRefreshScrollView ScrollView;

    private Handler handler=new Handler();
    private Runnable run=new Runnable() {
        @Override
        public void run() {
            //ScrollView.setMode(PullToRefreshBase.Mode.DISABLED);
            ScrollView.onRefreshComplete();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ViewUtils.inject(this);
        ScrollView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        ScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<android.widget.ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                //refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

                Toast.makeText(FirstActivity.this,"刷新一下哦，客官莫急",Toast.LENGTH_SHORT).show();
                handler.postDelayed(run,5000);

            }
        });

    }

}
