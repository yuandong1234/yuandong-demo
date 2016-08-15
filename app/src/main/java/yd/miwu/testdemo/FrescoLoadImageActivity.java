package yd.miwu.testdemo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import yd.miwu.R;

public class FrescoLoadImageActivity extends Activity {
    @ViewInject(R.id.my_image_view)
    private SimpleDraweeView my_image_view;
    private String url="http://c.hiphotos.baidu.com/image/pic/item/43a7d933c895d143bf16062771f082025aaf0755.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_fresco_load_image);
        ViewUtils.inject(this);
        Uri uri=Uri.parse(url);
        my_image_view.setImageURI(uri);

    }
}
