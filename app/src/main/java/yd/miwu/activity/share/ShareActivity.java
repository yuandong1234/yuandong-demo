package yd.miwu.activity.share;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import yd.miwu.R;

public class ShareActivity extends BaseShareActivity {

    @ViewInject(R.id.shareInput)
    private EditText shareInput;
    @ViewInject(R.id.weixin_pyq)
    private LinearLayout weixin_pyq;
    @ViewInject(R.id.weixin_hy)
    private LinearLayout weixin_hy;
    @ViewInject(R.id.qq_hy)
    private LinearLayout qq_hy;
    @ViewInject(R.id.qq_kj)
    private LinearLayout qq_kj;
    @ViewInject(R.id.cancel)
    private TextView cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ViewUtils.inject(this);

    }

    // 待处理
    @OnClick({R.id.weixin_pyq, R.id.weixin_hy, R.id.qq_hy, R.id.qq_kj, R.id.cancel})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.weixin_pyq:
                shareToPlatform(SHARE_MEDIA.WEIXIN_CIRCLE);
                break;
            case R.id.weixin_hy:
                shareToPlatform(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.qq_hy:
                shareToPlatform(SHARE_MEDIA.QQ);
                break;
            case R.id.qq_kj:
                shareToPlatform(SHARE_MEDIA.QZONE);
                break;
            case R.id.cancel:
                finish();
                break;
            default:
                break;
        }
    }

}
