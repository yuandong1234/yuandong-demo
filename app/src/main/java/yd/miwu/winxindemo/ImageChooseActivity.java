package yd.miwu.winxindemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.umeng.message.proguard.s;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import yd.miwu.R;

public class ImageChooseActivity extends Activity {
    private final static String TAG = ImageChooseActivity.class.getSimpleName();
    public final static String PHOTO_PICK_SUCCESS = "photo_pick_success";
    public final static String IMAGE_PICK_LIST = "image_pick_list";

    @ViewInject(R.id.cancel)
    private TextView cancel;
    @ViewInject(R.id.albumsTitle)
    private TextView albumsTitle;
    @ViewInject(R.id.gridView)
    private GridView gridView;
    @ViewInject(R.id.finish_btn)
    private Button finish;

    private int availableSize;
    private ImageGridAdapter mAdapter;
    private HashMap<String, ImageBean> selectedImgs = new HashMap<String, ImageBean>();
    private List<ImageBean> mDataList;
    private String mBucketName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_choose);
        ViewUtils.inject(this);

        mDataList = (List<ImageBean>) getIntent().getSerializableExtra(PhotoAlbumsActivity.EXTRA_IMAGE_LIST);
        if (mDataList == null) {
            mDataList = new ArrayList<ImageBean>();
        }
        mBucketName = getIntent().getStringExtra(PhotoAlbumsActivity.EXTRA_BUCKET_NAME);
        if (TextUtils.isEmpty(mBucketName)) {
            mBucketName = "请选择";
        }
        availableSize = getIntent().getIntExtra(WinXinMainActivity.EXTRA_CAN_ADD_IMAGE_SIZE, WinXinMainActivity.MAX_IMAGE_SIZE);

        initView();
        initListener();
    }

    private void initView() {
        albumsTitle.setText(mBucketName);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mAdapter = new ImageGridAdapter(this, mDataList);
        gridView.setAdapter(mAdapter);
        finish.setText("完成" + "(" + selectedImgs.size() + "/" + availableSize + ")");
    }

    private void initListener() {

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImageBean item = mDataList.get(position);
                if (item.isSelected) {
                    item.isSelected = false;
                    selectedImgs.remove(item.imageId);
                } else {
                    if (selectedImgs.size() >= availableSize) {
                        Toast.makeText(ImageChooseActivity.this, "最多选择" + availableSize + "张图片", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    item.isSelected = true;
                    selectedImgs.put(item.imageId, item);
                }
                finish.setText("完成" + "(" + selectedImgs.size() + "/" + availableSize + ")");
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    @OnClick({R.id.cancel, R.id.finish_btn})
    private void click(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                finish();
                break;
            case R.id.finish_btn:
                //TODO 待处理
               ArrayList<ImageBean> images= new ArrayList<ImageBean>(selectedImgs.values());
                Log.e(TAG,images.toString());
                Intent intent = new Intent();
                intent.setAction(PHOTO_PICK_SUCCESS);
                intent.putExtra(IMAGE_PICK_LIST, images);
                this.sendBroadcast(intent);
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "ImageChooseActivity销毁了。。。");
    }
}
