package yd.miwu.activity.login;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.umeng.socialize.bean.SHARE_MEDIA;

import yd.miwu.R;

public class LoginActivity extends ThirdPartyLoginActivity {

    @ViewInject(R.id.login_qq)
    private ImageView login_qq;
    @ViewInject(R.id.login_weixin)
    private ImageView login_weixin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);
    }

    @OnClick({R.id.login_qq, R.id.login_weixin})
    private void onclick(View view) {
        switch (view.getId()) {
            case R.id.login_qq:
                break;
            case R.id.login_weixin:
                DoOauthVerify(SHARE_MEDIA.WEIXIN);
                break;
            default:
                break;
        }

    }


}
