package yd.miwu.testdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.limxing.xlistview.view.XListView;

import yd.miwu.R;

public class CustomRefreshListViewActivity extends Activity implements XListView.IXListViewListener {

    @ViewInject(R.id.listView)
    private XListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_refresh_list_view);
        ViewUtils.inject(this);
        listView.setPullRefreshEnable(true);
        listView.setPullLoadEnable(true);
        listView.setXListViewListener(this);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 30;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView view = new TextView(CustomRefreshListViewActivity.this);
                view.setText("候车员 ：" + position + "号");
                view.setHeight(100);
                return view;
            }
        });

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.stopRefresh();
            }
        }, 2000);

    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.stopLoadMore();
            }
        }, 2000);
    }



}
