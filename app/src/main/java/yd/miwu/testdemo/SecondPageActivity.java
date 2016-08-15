package yd.miwu.testdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yd.miwu.R;

public class SecondPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

    }

    /**
     * 返回键处理
     */

    @Override
    public void onBackPressed() {

        Intent intent=new Intent();
        intent.setAction(MainGroupActivity.ACTION_TAB_CHANGE);
        intent.putExtra("tabId", R.id.menu_one);
        sendBroadcast(intent);
    }
}
