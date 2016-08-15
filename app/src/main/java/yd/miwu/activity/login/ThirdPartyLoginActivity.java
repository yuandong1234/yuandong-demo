package yd.miwu.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lidroid.xutils.util.LogUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import yd.miwu.R;

/**
 * 第三方登录 授权或取消授权
 */
public class ThirdPartyLoginActivity extends Activity {
    private UMShareAPI mShareAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_party_login);
        mShareAPI = UMShareAPI.get(this);
    }

    //TODO 待处理
    /**
     * 授权处理操作
     *
     * @param str
     */
    protected void DoOauthVerify(SHARE_MEDIA str) {
        SHARE_MEDIA platform = null;
        if (str == SHARE_MEDIA.QQ) {
            //QQ登录
            platform = SHARE_MEDIA.QQ;
        } else if (str == SHARE_MEDIA.SINA) {
            //新浪登录
            platform = SHARE_MEDIA.SINA;
        } else if (str == SHARE_MEDIA.WEIXIN) {
            //微信登录
            platform = SHARE_MEDIA.WEIXIN;
        }
        mShareAPI.deleteOauth(this, platform, umAuthListener);
    }

    /**
     * 取消授权处理操作
     */
    protected void CancelOauthVerify(SHARE_MEDIA str) {
        SHARE_MEDIA platform = null;
        if (str == SHARE_MEDIA.QQ) {
            //QQ登录
            platform = SHARE_MEDIA.QQ;
        } else if (str == SHARE_MEDIA.SINA) {
            //新浪登录
            platform = SHARE_MEDIA.SINA;
        } else if (str == SHARE_MEDIA.WEIXIN) {
            //微信登录
            platform = SHARE_MEDIA.WEIXIN;
        }
        mShareAPI.deleteOauth(this, platform, umdelAuthListener);
    }

    /**
     * 获得等三方用户信息
     */
    protected void getUserInfoFromThirdPartyInfo(SHARE_MEDIA str) {
        SHARE_MEDIA platform = null;
        if (str == SHARE_MEDIA.QQ) {
            //QQ登录
            platform = SHARE_MEDIA.QQ;
        } else if (str == SHARE_MEDIA.SINA) {
            //新浪登录
            platform = SHARE_MEDIA.SINA;
        } else if (str == SHARE_MEDIA.WEIXIN) {
            //微信登录
            platform = SHARE_MEDIA.WEIXIN;
        }
        mShareAPI.getPlatformInfo(this, platform, umPlatformInfoListener);
    }

    //授权监听事件
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            //TODO 授权成后获得第三方的用户信息
            getUserInfoFromThirdPartyInfo(platform);

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    //取消授权监听事件
    private UMAuthListener umdelAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "delete Authorize succeed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "delete Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "delete Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    //获得第三方用户的信息info
    private UMAuthListener umPlatformInfoListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            //TODO 得到第三方用户的信息 data
            if (data != null) {
                Log.d("auth callbacl", "getting data");
                Log.e("USERINFO","USERINFO : "+data.toString());
                Toast.makeText(getApplicationContext(), data.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "get fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "get cancel", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }
}
