package yd.miwu.testdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import yd.miwu.R;
import yd.miwu.adapter.MyAdapter;
import yd.miwu.util.Utils;

public class RecyclerViewActivity extends Activity {

    private static final String TAG=RecyclerViewActivity.class.getSimpleName();

    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    private MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ViewUtils.inject(this);
        initView();
    }
    private void initView() {
        //创建默认的线性LayoutManager
        layoutManager = new LinearLayoutManager(this);
        //水平list
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SpaceItemDecoration(this,5));
        //创建并设置Adapter
        adapter = new MyAdapter(this,urls);
        adapter.setOnRecyclerViewItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {

            @Override
            public void onItemClick(View view, Object data) {
                Toast.makeText(RecyclerViewActivity.this, "图片 ：" + disString((String) data), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private int mSpace ;
        private Context context;

        /**
         * @param space 传入的值，其单位视为dp
         */
        public SpaceItemDecoration(Context context,float space) {
            this.context=context;
            this.mSpace = Utils.dp2px(context, space);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int itemCount = adapter.getItemCount();
            int pos = parent.getChildAdapterPosition(view);
            Log.d(TAG, "itemCount>>" + itemCount + ";Position>>" + pos);

            outRect.left = 0;
            outRect.top = 0;
            outRect.bottom = 0;

            if (pos != (itemCount -1)) {
                outRect.right =  mSpace;
            } else {
                outRect.right = 0;
            }
        }
    }
    private String[] urls = {
            "http://wenwen.soso.com/p/20110611/20110611192409-2125459995.jpg",
            "http://img3.imgtn.bdimg.com/it/u=3578720581,3327250128&fm=21&gp=0.jpg",
            "http://awb.img.xmtbang.com/img/uploadnew/201506/06/38a69995275341dab59e1f4db839f7c5.jpg",
            "http://imgsrc.baidu.com/forum/w%3D580/sign=e94ecc411ad5ad6eaaf964e2b1ca39a3/1146e51f3a292df5dbab5b90bd315c6035a87377.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3740872691,1030483651&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1287502177,1270402009&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2588803474,3187558769&fm=21&gp=0.jpg",
            "http://img2.gomein.net.cn/image/bbcimg/production_image/nimg/20140312/16/8003168213/106120334_360.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2747963355,1053299901&fm=21&gp=0.jpg",
            "http://www.qzone.la/html/uploads/20140628/1722220.jpg",
            "http://img4.duitang.com/uploads/item/201601/24/20160124160025_AYXLs.jpeg",
            "http://imgsrc.baidu.com/forum/w%3D580/sign=ed7574a3d01373f0f53f6f97940e4b8b/d750915494eef01f061ff41de1fe9925bd317d3a.jpg"
    };

    private int disString (String str){
        int position=0;
        for(int i=0;i<urls.length;i++){
            if(str.equals(urls[i])){
                position=i;
            }
        }
        return position;
    }
}
