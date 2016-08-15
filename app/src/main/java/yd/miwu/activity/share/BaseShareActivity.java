package yd.miwu.activity.share;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import yd.miwu.R;

public class BaseShareActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_share);
    }
    //分享到不同的平台
    protected void shareToPlatform(SHARE_MEDIA str) {
        String url = "www.geihui.com";
        UMImage image = new UMImage(this, "http://img2.geihui.com/attachments/newupload4/month_1412/03/141203104926d91c0cb16da6c8_459020.jpg.thumb.jpg");
        if(str==SHARE_MEDIA.WEIXIN){
            new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(umShareListener)
                    .withText("袁栋的友盟测试")
                    .withMedia(image)
                    .withTargetUrl(url)
                    .share();
        }else if(str==SHARE_MEDIA.WEIXIN_CIRCLE){
            new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(umShareListener)
                    .withText("袁栋的友盟测试")
                    .withMedia(image)
                    .withTargetUrl(url)
                    .share();
        }else if(str==SHARE_MEDIA.QQ){
            new ShareAction(this).setPlatform(SHARE_MEDIA.QQ).setCallback(umShareListener)
                    .withText("袁栋的友盟测试")
                    .withMedia(image)
                    .withTargetUrl(url)
                    .share();
        }else if(str==SHARE_MEDIA.QZONE){
            new ShareAction(this).setPlatform(SHARE_MEDIA.QZONE).setCallback(umShareListener)
                    .withText("袁栋的友盟测试")
                    .withMedia(image)
                    .withTargetUrl(url)
                    .share();
        }


    }

    //;
    public UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(BaseShareActivity.this, platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(BaseShareActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(BaseShareActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(BaseShareActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
