package yd.miwu.testdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import yd.miwu.R;

public class MaterialRefreshLayoutActivity extends Activity {

    @ViewInject(R.id.refresh)
    private MaterialRefreshLayout materialRefreshLayout;
    @ViewInject(R.id.recyclerview)
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_refresh_layout);
        ViewUtils.inject(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        //可以在xml中配置 ,也可以用代码设置属性
        materialRefreshLayout.setWaveShow(true);
        materialRefreshLayout.setWaveColor(Color.parseColor("#6006BF00"));
        materialRefreshLayout.setLoadMore(true);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        materialRefreshLayout.finishRefresh();

                    }
                }, 3000);
            }


            @Override
            public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {
                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        materialRefreshLayout.finishRefreshLoadMore();
                        Snackbar.make(materialRefreshLayout,"加载更多完成。。。",Snackbar.LENGTH_SHORT).show();

                    }
                }, 3000);

            }

            @Override
            public void onfinish() {
                Snackbar.make(materialRefreshLayout,"刷新完毕。。。",Snackbar.LENGTH_SHORT).show();
            }
        });

        initData();

    }


    private void  initData(){
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * RecyclerView 自定义 adapter
     */
    class SimpleStringRecyclerViewAdapter extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {


        public class ViewHolder extends RecyclerView.ViewHolder {

            public final ImageView mImageView;

            public ViewHolder(View view) {
                super(view);
                mImageView = (ImageView) view.findViewById(R.id.avatar);
            }


        }



        public SimpleStringRecyclerViewAdapter(Context context) {
            super();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            if (position == 0) {
                holder.mImageView.setImageResource(R.mipmap.a6);
            } else if (position == 1) {
                holder.mImageView.setImageResource(R.mipmap.a5);
            }else if(position == 2){
                holder.mImageView.setImageResource(R.mipmap.dd);
            }else if(position == 3){
                holder.mImageView.setImageResource(R.mipmap.a6);
            }

        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }
}
