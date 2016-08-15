package yd.miwu.testdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.io.File;

import yd.miwu.R;

public class DownFileActivity extends Activity {

    @ViewInject(R.id.startDownLoad)
    private TextView startDownLoad;
    @ViewInject(R.id.progressBar)
    private ProgressBar progressBar;

    private static final  int DOWNLOAD_SUCCEED=0;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String Apkpath=(String)msg.obj;
            switch (msg.what){
                case DOWNLOAD_SUCCEED:
                    installApk(Apkpath);
                    break;
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_file);
        ViewUtils.inject(this);
    }

    @OnClick({R.id.startDownLoad})
    private void click(View v){
        switch (v.getId()){
            case R.id.startDownLoad :
                downLoad();
                break;
        }
    }

    /**
     * 下载Apk
     */
    private void downLoad(){
        String url="http://meizhi.f3322.net:55580/uploadfiles/app-release.apk";
        String downPath= "/sdcard/yd/" + System.currentTimeMillis()+"lzfile.apk";
        HttpUtils http=new HttpUtils();
        http.download(url, downPath, true, true, new RequestCallBack<File>() {
            @Override
            public void onStart() {
                startDownLoad.setText("正在连接。。。");
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                Log.e("**************：", "正在下载 ：" +"current :"+ current+"   "+"total: "+total);
                super.onLoading(total, current, isUploading);
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setMax((int)total);
                progressBar.setProgress((int)current);

            }

            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {

                startDownLoad.setText("下载完毕: "+responseInfo.result.getPath());
                Message msg=new Message();
                msg.what=DOWNLOAD_SUCCEED;
                msg.obj=responseInfo.result.getPath();
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }
    /**
     *安装Apk
     */
    private void installApk(String str){

        Uri uri = Uri.fromFile(new File(str));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivity(intent);

    }


}
