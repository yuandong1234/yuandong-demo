package yd.miwu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import yd.miwu.activity.FirstActivity;
import yd.miwu.activity.WebViewActivity;
import yd.miwu.activity.login.LoginActivity;
import yd.miwu.activity.share.ShareActivity;

import yd.miwu.base.BaseActivity;
import yd.miwu.testdemo.CardViewActivity;
import yd.miwu.testdemo.DownFileActivity;
import yd.miwu.testdemo.ProgressBarActivity;
import yd.miwu.testdemo.TabLayoutActivity;
import yd.miwu.testdemo.SwipeRefreshLayoutActivity;
import yd.miwu.testdemo.HeaderActionBarActivity;
import yd.miwu.testdemo.ScrollViewAlpraActivity;

public class MainActivity extends BaseActivity {

    @ViewInject(R.id.loginActivity)
    private TextView loginActivity;
    @ViewInject(R.id.shareActivity)
    private TextView shareActivity;
    @ViewInject(R.id.scrollViewActivity)
    private TextView scrollViewActivity;
    @ViewInject(R.id.WebViewActivity)
    private TextView WebViewActivity;
    @ViewInject(R.id.TabLayoutActivity)
    private TextView TabLayoutActivity;
    @ViewInject(R.id.DownFileActivity)
    private TextView DownFileActivity;
    @ViewInject(R.id.progressBarActivity)
    private TextView progressBarActivity;
    @ViewInject(R.id.SwipeRefreshLayout)
    private TextView SwipeRefreshLayoutActivity;
    @ViewInject(R.id.CardView)
    private TextView CardView;
    @ViewInject(R.id.HeaderActionBarActivity)
    private TextView HeaderActionBarActivity;
    @ViewInject(R.id.ScrollViewAlpraActivity)
    private TextView ScrollViewAlpraActivity;

    @ViewInject(R.id.secondPage)
    private TextView secondPage;
    @ViewInject(R.id.threePage)
    private TextView threePage;
    @ViewInject(R.id.thrithPage)
    private TextView thrithPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
    }


    @OnClick({R.id.loginActivity,R.id.shareActivity,R.id.scrollViewActivity,R.id.WebViewActivity,R.id.TabLayoutActivity,R.id.DownFileActivity,
            R.id.progressBarActivity,R.id.SwipeRefreshLayout,R.id.CardView,R.id.HeaderActionBarActivity,
            R.id.ScrollViewAlpraActivity,R.id.secondPage,R.id.threePage,R.id.thrithPage})
    private void onclick(View view){

        switch (view.getId()){
            case R.id.loginActivity:
                Intent intent=new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.shareActivity:
                Intent intent1=new Intent(this, ShareActivity.class);
                startActivity(intent1);
                break;
            case R.id.scrollViewActivity:
                Intent intent2=new Intent(this, FirstActivity.class);
                startActivity(intent2);
                break;
            case R.id.WebViewActivity:
                Intent intent3=new Intent(this, WebViewActivity.class);
                startActivity(intent3);
                break;
            case  R.id.TabLayoutActivity:
                Intent intent4=new Intent(this, TabLayoutActivity.class);
                startActivity(intent4);
                break;
            case  R.id.DownFileActivity:
                Intent intent5=new Intent(this, DownFileActivity.class);
                startActivity(intent5);
                break;
            case  R.id.progressBarActivity:
                Intent intent6=new Intent(this, ProgressBarActivity.class);
                startActivity(intent6);
                break;
            case  R.id.SwipeRefreshLayout:
                Intent intent7=new Intent(this, SwipeRefreshLayoutActivity.class);
                startActivity(intent7);
                break;
            case  R.id.CardView:
                Intent intent8=new Intent(this, CardViewActivity.class);
                startActivity(intent8);
                break;
            case  R.id.HeaderActionBarActivity:
                Intent intent9=new Intent(this, HeaderActionBarActivity.class);
                startActivity(intent9);
                break;
            case  R.id.ScrollViewAlpraActivity:
                Intent intent10=new Intent(this, ScrollViewAlpraActivity.class);
                startActivity(intent10);
                break;
            case  R.id.secondPage:
                Intent intent11=new Intent(this, SecondActivity.class);
                startActivity(intent11);
                break;
            case  R.id.threePage:
                Intent intent12=new Intent(this, ThreeActivity.class);
                startActivity(intent12);
                break;
            case  R.id.thrithPage:
                Intent intent13=new Intent(this, ThreeActivity.class);
                startActivity(intent13);
                break;

            default:
                break;



        }
    }

}
