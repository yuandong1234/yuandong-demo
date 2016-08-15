package yd.miwu.winxindemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import yd.miwu.R;

public class PhotoAlbumsActivity extends Activity {
    private final static String TAG = WinXinMainActivity.class.getSimpleName();
    public static final String EXTRA_IMAGE_LIST = "image_list"; //相册中图片对象集合
    public static final String EXTRA_BUCKET_NAME = "buck_name";//相册名称

    @ViewInject(R.id.cancel)
    private TextView cancel;
    @ViewInject(R.id.listView)
    private ListView listView;

    private ImageFetcher mHelper;
    private List<ImageBucket> mDataList = new ArrayList<>();
    private int availableSize;
    private ImageBucketAdapter adapter;
    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_albums);
        ViewUtils.inject(this);
        mHelper = ImageFetcher.getInstance(getApplicationContext());
        initData();
        initView();

        receiver=new MyReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction(ImageChooseActivity.PHOTO_PICK_SUCCESS);
        this.registerReceiver(receiver,filter);

    }

    private void initData() {
        mDataList = mHelper.getImagesBucketList(false);
        availableSize = getIntent().getIntExtra(WinXinMainActivity.EXTRA_CAN_ADD_IMAGE_SIZE, WinXinMainActivity.MAX_IMAGE_SIZE);
    }

    private void initView() {
        adapter=new ImageBucketAdapter(this,mDataList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectOne(position);
                Intent intent=new Intent(PhotoAlbumsActivity.this,ImageChooseActivity.class);
                intent.putExtra(EXTRA_IMAGE_LIST, (Serializable) mDataList.get(position).imageList);
                intent.putExtra(EXTRA_BUCKET_NAME, mDataList.get(position).bucketName);
                intent.putExtra(WinXinMainActivity.EXTRA_CAN_ADD_IMAGE_SIZE, availableSize);
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.cancel})
    private void click(View view){
        switch (view.getId()) {
            case R.id.cancel:
                finish();
                break;
        }
    }

    private void selectOne(int position)
    {
        int size = mDataList.size();
        for (int i = 0; i != size; i++)
        {
            if (i == position) {
                mDataList.get(i).selected = true;
            }else{
                mDataList.get(i).selected = false;
            }
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 自定义广播，在完成相册选择的时候关闭当前Activity
     */
    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case ImageChooseActivity.PHOTO_PICK_SUCCESS:
                    finish();
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(receiver);
        Log.e(TAG,"PhotoAlbumsActivity销毁了。。。");
    }
}
