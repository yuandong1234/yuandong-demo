package yd.miwu.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;


import java.util.List;

import yd.miwu.R;

public class WebViewActivity extends BaseWebViewActivity {
    private String url="https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm?spm=a21bo.50862.1997525045.2.cHC5Vh";
    private StringBuffer cookieString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String url1=getIntent().getStringExtra("url");
        if(!TextUtils.isEmpty(url1)){
            url=url1;
        }


        Log.e("***********", "子类onCreate执行了");
        Log.e("***********","url :"+url);

        new Thread(){
            @Override
            public void run() {
                super.run();
                //  清除cookies
                removeCookie(WebViewActivity.this);

                HttpPost httpRequest = new HttpPost(url);
                DefaultHttpClient client = new DefaultHttpClient();
                try {
                    client.execute(httpRequest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 取得Cookie
                CookieStore mCookieStore = client.getCookieStore();
                List<Cookie> cookies = mCookieStore.getCookies();
                cookieString=new StringBuffer();
                if(cookies.isEmpty()){
                    System.out.println("Cookies为空");
                }else{
                    //保存cookies
                    for(Cookie c:cookies){
                        cookieString.append(c.getName() + "=" + c.getValue() + "; domain=" + c.getDomain() + ";");
                    }
                    Log.e("cookies*************","cookies= "+cookieString.toString());
                    //同步cookies
                    synCookies(WebViewActivity.this,url,cookieString.toString());
                }
                WebViewActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //加载网页
                        load(url);
                    }
                });
            }
        }.start();
    }

    //得到加载的Url
    @Override
    protected void showUrl(String url) {
        super.showUrl(url);

    }
    //同步cookies
    public static void synCookies(Context context, String url,String cookies) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(url, cookies);
        CookieSyncManager.getInstance().sync();
    }

    //清除cookies
    private void removeCookie(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

}
