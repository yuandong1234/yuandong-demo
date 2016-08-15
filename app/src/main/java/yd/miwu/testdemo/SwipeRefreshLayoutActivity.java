package yd.miwu.testdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import yd.miwu.R;


public class SwipeRefreshLayoutActivity extends Activity {

    @ViewInject(R.id.swipeLayout)
    private SwipeRefreshLayout swipeLayout;
    @ViewInject(R.id.myList)
    private ListView listView;

    private List<String> list;
    private ArrayAdapter<String> adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    swipeLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();


                    if (list.size() == 30) {
                        swipeLayout.setEnabled(false);
                    }
                    break;
            }
        }
    };
    private Runnable run=new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                list.add(m + "路公交车有 " + i + " 人");
            }
            m++;
            handler.sendEmptyMessage(1);

        }
    };
    int m = 1;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);
        ViewUtils.inject(this);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        swipeLayout.setColorSchemeResources(R.color.miwuBaseTitleBarColor, R.color.miwuBaseTitleBarColor, R.color.miwuBaseTitleBarColor, R.color.miwuBaseTitleBarColor);
        swipeLayout.setSize(SwipeRefreshLayout.LARGE);
        swipeLayout.setProgressViewOffset(true, 0, 100);
        swipeLayout.setProgressBackgroundColor(R.color.miwuBaseBackGroundColor);
        swipeLayout.setProgressViewEndTarget(true, 100);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e("**************", "执行了onRefresh()方法。。。");
                //刷新
                handler.postDelayed(run,5000);
            }
        });
    }
}
