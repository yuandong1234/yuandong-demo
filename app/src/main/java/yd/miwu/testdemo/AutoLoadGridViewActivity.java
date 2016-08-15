package yd.miwu.testdemo;

import android.app.Activity;
import android.os.Bundle;

import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.lidroid.xutils.view.annotation.ViewInject;

import yd.miwu.R;

public class AutoLoadGridViewActivity extends Activity {

    @ViewInject(R.id.pullToRefreshGridView)
    private PullToRefreshGridView pullToRefreshGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_load_grid_view);


    }
}
