package yd.miwu.testdemo;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import yd.miwu.R;

public class MainGroupActivity extends ActivityGroup {

    private static final String TAG = MainGroupActivity.class.getSimpleName();
    public static final String ACTION_TAB_CHANGE = "action_tab_change";

    @ViewInject(R.id.container)
    private LinearLayout container;
    @ViewInject(R.id.menu_one)
    private RelativeLayout menu_one;
    @ViewInject(R.id.menu_two)
    private RelativeLayout menu_two;
    @ViewInject(R.id.menu_three)
    private RelativeLayout menu_three;
    @ViewInject(R.id.menu_thrid)
    private RelativeLayout menu_thrid;
    @ViewInject(R.id.menu_fifth)
    private RelativeLayout menu_fifth;

    @ViewInject(R.id.pic_one)
    private ImageView pic_one;
    @ViewInject(R.id.pic_two)
    private ImageView pic_two;
    @ViewInject(R.id.pic_three)
    private ImageView pic_three;
    @ViewInject(R.id.pic_thrid)
    private ImageView pic_thrid;
    @ViewInject(R.id.pic_fifth)
    private ImageView pic_fifth;


    private ActivityGroup group;
    private TabReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_group);
        ViewUtils.inject(this);
        group = this;
        showView(FirstPageActivity.class);
        pic_one.setImageResource(R.mipmap.icon_home_selected);

        //注册广播
        receiver=new TabReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction(ACTION_TAB_CHANGE);
        registerReceiver(receiver, filter);

    }

    @OnClick({R.id.menu_one, R.id.menu_two, R.id.menu_three, R.id.menu_thrid, R.id.menu_fifth})
    private void click(View view) {
        changeTabById(view.getId());
    }

    private void showView(Class<? extends Activity> clazz) {
        String str = clazz.getName();
        container.removeAllViews();
        View view = getTabView(str, clazz);
        container.addView(view);

    }

    private View getTabView(String id, Class<? extends Activity> clazz) {
        Intent intent = new Intent(MainGroupActivity.this, clazz);
        View view = getLocalActivityManager().startActivity(id, intent).getDecorView();
        return view;
    }


    private void changeTabById(int id) {
        switch (id) {
            case R.id.menu_one:
                showView(FirstPageActivity.class);
                pic_one.setImageResource(R.mipmap.icon_home_selected);
                pic_two.setImageResource(R.mipmap.icon_superrebate);
                pic_three.setImageResource(R.mipmap.icon_mallrebate);
                pic_thrid.setImageResource(R.mipmap.icon_tobaorebate);
                pic_fifth.setImageResource(R.mipmap.icon_personal);
                break;
            case R.id.menu_two:
                showView(SecondPageActivity.class);
                pic_one.setImageResource(R.mipmap.icon_home);
                pic_two.setImageResource(R.mipmap.icon_superrebate_selected);
                pic_three.setImageResource(R.mipmap.icon_mallrebate);
                pic_thrid.setImageResource(R.mipmap.icon_tobaorebate);
                pic_fifth.setImageResource(R.mipmap.icon_personal);
                break;
            case R.id.menu_three:
                showView(ThridPageActivity.class);
                pic_one.setImageResource(R.mipmap.icon_home);
                pic_two.setImageResource(R.mipmap.icon_superrebate);
                pic_three.setImageResource(R.mipmap.icon_mallrebate_selected);
                pic_thrid.setImageResource(R.mipmap.icon_tobaorebate);
                pic_fifth.setImageResource(R.mipmap.icon_personal);
                break;
            case R.id.menu_thrid:
                showView(ForthPageActivity.class);
                pic_one.setImageResource(R.mipmap.icon_home);
                pic_two.setImageResource(R.mipmap.icon_superrebate);
                pic_three.setImageResource(R.mipmap.icon_mallrebate);
                pic_thrid.setImageResource(R.mipmap.icon_tobaorebate_selected);
                pic_fifth.setImageResource(R.mipmap.icon_personal);
                break;
            case R.id.menu_fifth:
                showView(FifthPageActivity.class);
                pic_one.setImageResource(R.mipmap.icon_home);
                pic_two.setImageResource(R.mipmap.icon_superrebate);
                pic_three.setImageResource(R.mipmap.icon_mallrebate);
                pic_thrid.setImageResource(R.mipmap.icon_tobaorebate);
                pic_fifth.setImageResource(R.mipmap.icon_personal_selected);
                break;
            default:
                break;
        }
    }

    /*
    * 接收广播，用于改变tab的状态
    */
    class TabReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ACTION_TAB_CHANGE)) {
                int id = intent.getIntExtra("tabId", -1);
                if(id!=-1){
                    changeTabById(id);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);

    }

    @Override
    public void onBackPressed() {
        group.getLocalActivityManager().getCurrentActivity().onBackPressed();
    }
}
