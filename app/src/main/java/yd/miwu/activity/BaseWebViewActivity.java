package yd.miwu.activity;

import android.app.Activity;
import android.graphics.Bitmap;

import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.event.OnClick;

import yd.miwu.R;

public class BaseWebViewActivity extends Activity implements View.OnClickListener {

    private RelativeLayout backFrame;
    private RelativeLayout closeFrame;
    private RelativeLayout refreshFrame;
    private TextView title;
    private WebView webView;
    private ProgressBar progressBar;

    private boolean blockNetworkImage = false;//是否阻塞图片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("***********", "父类onCreate执行了");
        setContentView(R.layout.activity_base_web_view);
        initView();
        initConfig();
        addProgressBar();
        setClient();
        setListener();
    }

    private void initView(){
        backFrame=(RelativeLayout)findViewById(R.id.backFrame);
        closeFrame=(RelativeLayout)findViewById(R.id.closeFrame);
        refreshFrame=(RelativeLayout)findViewById(R.id.refreshFrame);
        title=(TextView)findViewById(R.id.title);
        webView=(WebView)findViewById(R.id.webView);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
    }
    //配置webView参数
    private void initConfig() {
        Log.e("+++++++++++++++++","webView :"+webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        webSettings.supportMultipleWindows();//支持多窗口

        webSettings.setUseWideViewPort(true);//调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true);//缩放至屏幕的大小
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//支持内容重新布局

        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setDisplayZoomControls(false);//支持直接隐藏缩放控件

        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccess(true);//允许访问文件

        webSettings.setLoadsImagesAutomatically(true);//支持自动加载图片

        webSettings.setNeedInitialFocus(true);//当webview调用requestFocus时为webview设置节点

        if (Build.VERSION.SDK_INT >= 21) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        //TODO  待处理
//      //  1.提高渲染等级
//        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
//       // 2.首先阻塞图片，让图片不显示
//        webView.getSettings().setBlockNetworkImage(true);
//
//        blockNetworkImage=true;
//       // 页面加载好以后，在放开图片：
//        webView.getSettings().setBlockNetworkImage(false);


        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //webview中缓存

        //appcache缓存（h5缓存）
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheMaxSize(1024 * 1024 * 8);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        webSettings.setAppCachePath(appCachePath);
        webSettings.setAppCacheEnabled(true);


        //数据库缓存和DOM Storage
        webSettings.setDatabaseEnabled(true);
        webSettings.setGeolocationEnabled(true);
        String cacheDirPath = getCacheDir().getAbsolutePath() + "/webViewCache ";
        webSettings.setDatabasePath(cacheDirPath);

//
//
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        int mDensity = metrics.densityDpi;
//        Log.d("maomao", "densityDpi = " + mDensity);
//        if (mDensity == 240) {
//            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
//        } else if (mDensity == 160) {
//            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
//        } else if(mDensity == 120) {
//            webSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
//        }else if(mDensity == DisplayMetrics.DENSITY_XHIGH){
//            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
//        }else if (mDensity == DisplayMetrics.DENSITY_TV){
//            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
//        }else{
//            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
//        }
    }

    //添加进度条
    private void addProgressBar() {
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress < 100) {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }


    private void setClient() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showUrl(url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    private void setListener(){
        backFrame.setOnClickListener(this);
        closeFrame.setOnClickListener(this);
        refreshFrame.setOnClickListener(this);

    }

    protected void showUrl(String url) {
    }

    //加载网页
    protected void load(String url) {
        webView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            back();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * 系统自带返回键处理
     */
    private void back() {
        if (webView.canGoBack()) {
            webView.stopLoading();
            webView.goBack();
        } else {
            webView.stopLoading();
            onBackPressed();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backFrame:
                if(!webView.canGoBack()){
                    onBackPressed();
                }else{
                    webView.goBack();
                }
                break;
            case R.id.closeFrame:
                finish();
                break;
            case R.id.refreshFrame:
                webView.reload();
                break;
        }
    }
}
