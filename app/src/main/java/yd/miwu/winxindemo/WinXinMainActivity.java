package yd.miwu.winxindemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.io.File;
import java.util.ArrayList;

import yd.miwu.R;
import yd.miwu.util.Utils;
import yd.miwu.view.TakePhotoPopWindow;

public class WinXinMainActivity extends Activity {

    private final static String TAG = WinXinMainActivity.class.getSimpleName();

    private static final int TAKE_PICTURE = 10010;//拍照
    private static final int PICK_PICTURE = 10086;//拍照
    public static final int MAX_IMAGE_SIZE = 9;//可以添加的最大图片数量
    public static final String EXTRA_CAN_ADD_IMAGE_SIZE = "can_add_image_size";

    @ViewInject(R.id.send)
    private TextView send;
    @ViewInject(R.id.input)
    private EditText input;
    @ViewInject(R.id.gridview)
    private GridView gridview;
    @ViewInject(R.id.mainFrame)
    private RelativeLayout mainFrame;

    private ArrayList<ImageBean> imageList = new ArrayList<>();
    private MainImageAdapter adapter;
    private TakePhotoPopWindow popWindow;
    private String path;

    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_xin_main);
        ViewUtils.inject(this);
        initView();
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ImageChooseActivity.PHOTO_PICK_SUCCESS);
        this.registerReceiver(receiver, filter);

    }

    private void initView() {
        gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new MainImageAdapter(imageList, this);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == imageList.size()) {
                    //TODO 弹出弹框
                    popWindow = new TakePhotoPopWindow(WinXinMainActivity.this, itemsOnClick);
                    popWindow.showAtLocation(WinXinMainActivity.this.findViewById(R.id.mainFrame), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                }

            }
        });
    }


    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.takePhoto:
                    popWindow.dismiss();
                    //TODO 拍照
                    takePhoto();
                    break;
                case R.id.pickPhoto:
                    popWindow.dismiss();
                    //TODO 从相册选择
                    pickPhoto();
                    break;
                case R.id.cancel:
                    popWindow.dismiss();
                    break;
            }
        }
    };

    private void takePhoto() {
        // 拍照
        String imageFilePath = Utils.getRootFilePath() + "/image/" + System.currentTimeMillis() + ".jpg";
        File temp = new File(imageFilePath);
        if (!temp.exists()) {
            File vDirPath = temp.getParentFile();
            vDirPath.mkdirs();
        } else {
            if (temp.exists()) {
                temp.delete();
            }
        }
        path = temp.getPath();
        Uri imageFileUri = Uri.fromFile(temp);//获取文件的Uri
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//跳转到相机Activity
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);//告诉相机拍摄完毕输出图片到指定的Uri
        startActivityForResult(intent, TAKE_PICTURE);
    }


    private void pickPhoto() {
        //从相册中选择
        Intent intent = new Intent(this, PhotoAlbumsActivity.class);
        intent.putExtra(EXTRA_CAN_ADD_IMAGE_SIZE, getAvailableSize());
        startActivity(intent);
    }

    private int getAvailableSize() {
        int availSize = MAX_IMAGE_SIZE - imageList.size();

        if (availSize >= 0) {
            return availSize;
        }
        return 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.send})
    private void click(View view) {
        switch (view.getId()) {
            case R.id.send:
                Snackbar.make(mainFrame, "上传图片...", Snackbar.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE:
                    ImageBean bean = new ImageBean();
                    bean.sourcePath = path;
                    imageList.add(bean);
                    break;
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 自定义广播，用于接收相册中的选中的图片
     */
    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case ImageChooseActivity.PHOTO_PICK_SUCCESS:
                    ArrayList<ImageBean> Images=(ArrayList<ImageBean>)intent.getSerializableExtra(ImageChooseActivity.IMAGE_PICK_LIST);
                    Log.e("***选中的图片集合",Images.toString());
                    if(Images!=null){
                        imageList.addAll(Images) ;
                        adapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(receiver);
    }
}
