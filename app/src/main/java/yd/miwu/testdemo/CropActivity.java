package yd.miwu.testdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.oginotihiro.cropview.CropUtil;
import com.oginotihiro.cropview.CropView;

import java.io.File;

import yd.miwu.R;

public class CropActivity extends Activity {

    private static final String TAG=CropActivity.class.getSimpleName();
    public static final int REQUEST_PICK = 9162;


    @ViewInject(R.id.pick)
    private RelativeLayout pick;
    @ViewInject(R.id.cropView)
    private CropView cropView;
    @ViewInject(R.id.iv_picked)
    private ImageView iv_picked;
    @ViewInject(R.id.cancel)
    private TextView cancel;
    @ViewInject(R.id.done)
    private TextView done;
    @ViewInject(R.id.menuItem)
    private LinearLayout menuItem;

    private Bitmap croppedBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);
        ViewUtils.inject(this);



    }

    @OnClick({R.id.pick,R.id.cancel,R.id.done})
    private void click(View v){
        switch (v.getId()){
            case R.id.pick:
                openLocalPhotoAlbum();
                break;
            case R.id.cancel:
                resetView();
                break;
            case R.id.done:
                pickPicture();
                break;

            default:
                break;
        }

    }

    //打开本地相册
    private void openLocalPhotoAlbum(){
        resetView();
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT).setType("image/*");
        startActivityForResult(intent, REQUEST_PICK);
    }

    //reset view
    private void resetView(){
        cropView.setVisibility(View.GONE);
        iv_picked.setVisibility(View.GONE);
        iv_picked.setImageBitmap(null);
        menuItem.setVisibility(View.GONE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode==RESULT_OK){
            switch (requestCode){
                case REQUEST_PICK:
                    Uri source = data.getData();
                    cropView.setVisibility(View.VISIBLE);
                    menuItem.setVisibility(View.VISIBLE);
                    cropView.of(source).asSquare().initialize(CropActivity.this);
                    break;
            }

        }
    }

    private void pickPicture(){

        final ProgressDialog dialog = ProgressDialog.show(CropActivity.this, null, "Please wait…", true, false);
        cropView.setVisibility(View.GONE);
        menuItem.setVisibility(View.GONE);
        iv_picked.setVisibility(View.VISIBLE);

        new Thread() {
            public void run() {
                croppedBitmap = cropView.getOutput();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iv_picked.setImageBitmap(croppedBitmap);
                    }
                });

                Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
                CropUtil.saveOutput(CropActivity.this, destination, croppedBitmap, 100);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                });
            }
        }.start();
    }
}
