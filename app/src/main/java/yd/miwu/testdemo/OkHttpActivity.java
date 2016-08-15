package yd.miwu.testdemo;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import yd.miwu.R;
import yd.miwu.adapter.NewsAdapter;
import yd.miwu.mode.BaseBean;
import yd.miwu.mode.NewsListBean;
import yd.miwu.okhttp.CacheType;
import yd.miwu.okhttp.Callback;
import yd.miwu.okhttp.JsonCallback;
import yd.miwu.okhttp.OKHttpUtils;

public class OkHttpActivity extends Activity {

    private static final  String TAG=OkHttpActivity.class.getSimpleName();

    private final static int  LOAD_DATA_SUCCESS=0;

    @ViewInject(R.id.listView)
    private ListView  listView;
    @ViewInject(R.id.Frame)
    private RelativeLayout Frame;


    private String url="http://v.juhe.cn/toutiao/index";//聚合数据接口
    private String  key="aa47561558f285fee99f1943c7b844fb";//聚合数据key

    private RequestBody body;
    private OKHttpUtils okHttpUtils;

    private int  cacheType;

    private BaseBean bean;
    private ArrayList<NewsListBean> list;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case LOAD_DATA_SUCCESS:
                    initData();
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ViewUtils.inject(this);
        okHttpUtils = new OKHttpUtils.Builder(this).build();

        loadData();
    }


    private  void  loadData(){
        body= new FormBody.Builder()
                .add("type", "top")
                .add("key", key)
                .build();

        Request request = new Request.Builder().url(url).post(body).build();
        okHttpUtils.request(request, CacheType.ONLY_NETWORK, new JsonCallback<String>() {

            @Override
            public void onFailure(Call call, Exception e) {

            }

            @Override
            public void onResponse(Call call, String response) {
                Logger.json(response);

                Gson gson = new Gson();
                bean = gson.fromJson(response, BaseBean.class);
                //Snackbar.make(Frame,"reason :"+bean.reason,Snackbar.LENGTH_SHORT).show();
                if (bean != null) {
                    list=bean.result.data;
                    Message msg=new Message();
                    msg.what= LOAD_DATA_SUCCESS;
                    handler.sendMessage(msg);
                }
            }


        });
    }


    private void initData(){
        NewsAdapter adapter=new NewsAdapter(this,list);
        listView.setAdapter(adapter);
    }
}
