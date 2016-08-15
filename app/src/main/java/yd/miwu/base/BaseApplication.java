package yd.miwu.base;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;

import yd.miwu.R;

/**
 * Created by 256 on 2016/5/19.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initConfig();
        initImageViewLoad();
    }


    private void initConfig() {
        //QQ和Qzone appid appkey  1105413062 NXLiMoYrgAWi4J6O
        PlatformConfig.setQQZone("1105413062", "NXLiMoYrgAWi4J6O");
               //微信 appid appsecret
        PlatformConfig.setWeixin("wx943e910fd1803f35", "d0d091d1c56a79a2a0dfabb4870cb23e");
    }
    // {
//        //微信 appid appsecret
//        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
//
//        //新浪微博 appkey appsecret
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
//
    // QQ和Qzone appid appkey
    //     PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//
//        //支付宝 appid
//        PlatformConfig.setAlipay("2015111700822536");
//
//        //易信 appkey
//        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
//
//        //Twitter appid appkey
//        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
//
//        //Pinterest appid
//        PlatformConfig.setPinterest("1439206");
//
//        //来往 appid appkey
//        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
//
    //}
    private void initImageViewLoad(){
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder() //
                .showImageForEmptyUri(R.mipmap.ic_launcher) //
                .showImageOnFail(R.mipmap.ic_launcher) //
                .cacheInMemory(true) //
                .cacheOnDisk(true) //
                .build();//
        ImageLoaderConfiguration config = new ImageLoaderConfiguration//
                .Builder(getApplicationContext())//
                .defaultDisplayImageOptions(defaultOptions)//
                .discCacheSize(50 * 1024 * 1024)//
                .discCacheFileCount(100)// 缓存一百张图片
                .writeDebugLogs()//
                .build();//
        ImageLoader.getInstance().init(config);
    }


}
