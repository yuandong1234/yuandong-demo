package yd.miwu.base;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import yd.miwu.R;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initUmeng();
    }

    protected void initUmeng(){
        //开启兔推送
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.enable(new IUmengRegisterCallback() {
            @Override
            public void onRegistered(final String s) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("device_token", "device_token==: =" + s);
                    }
                });
            }
        });
        //获取设备的device_token
        //String device_token = UmengRegistrar.getRegistrationId(this);
        //启动应用数据
        mPushAgent.onAppStart();
    }
}
