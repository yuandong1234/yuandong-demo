package yd.miwu.util;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;

import yd.miwu.R;
import yd.miwu.base.IBaseInterface;
import yd.miwu.base.NetBaseActivity;

/**
 * Created by 256 on 2016/6/8.
 */
public class PageGridViewHelper<T> implements IBaseInterface {

    private Context ctx;
    private ArrayList<T> dataList;
    private PullToRefreshGridView mGridView;
    private int totalRowNum;//全部item 个数
    private int currentPage;//当前分页页数

    private boolean isActionRefresh = false;//是否可以下拉刷新(默认不可以)
    private boolean canLoadMore = true;//是否可以加载更多
    private boolean isRequestSuccess = true;//数据是否请求成功
    private boolean canShowNoMoreDataTip = true;//是否显示“没有更多”
    private int rowNumLeftToLoadMore=6;//加载的item还剩6条时，加载下一页

    public PageGridViewHelper( Context context, ArrayList<T> dataList, PullToRefreshGridView pullToRefreshGridView) {
        super();
        this.ctx = context;
        this.dataList = dataList;
        Log.e("初始化dataList","dataList"+dataList.toString());
        this.mGridView = pullToRefreshGridView;
        this.mGridView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        this.mGridView.setOnScrollListener(new OnXScrollListener() {

            @Override
            public void onXScrolling(View view) {

            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (totalItemCount >= totalRowNum && firstVisibleItem + visibleItemCount == totalItemCount && totalRowNum > 0 && canShowNoMoreDataTip) {
                    if (visibleItemCount < totalRowNum) {
                        canShowNoMoreDataTip = false;
                        Toast.makeText(ctx, "没有更多了", Toast.LENGTH_SHORT).show();
                    }
                }

                if (firstVisibleItem < totalItemCount - rowNumLeftToLoadMore * mGridView.getRefreshableView().getNumColumns()) {
                    //如果可见Item的第一个位置小于GridView 的总数减6时
                    canShowNoMoreDataTip = true;
                }

                if (firstVisibleItem + visibleItemCount >= totalItemCount - rowNumLeftToLoadMore * mGridView.getRefreshableView().getNumColumns()) {
                    if (canLoadMore) {
                        canLoadMore = false;
                        onLoadMore();
                    }
                }

            }
        });
        this.mGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                //TODO 下拉刷新
                onRefresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                //TODO 上拉加载
               // onLoadMore();

            }
        });

        if (context instanceof NetBaseActivity) {//创建dialog
            ((NetBaseActivity) context).createDialog();
        }

    }

    /**
     * 刷新数据
     */
    @Override
    public void onRefresh() {
        currentPage = 1;
        isActionRefresh = true;
        onPage(currentPage);

    }

    /**
     * 加载更多数据
     */
    @Override
    public void onLoadMore() {

        mGridView.onRefreshComplete();
        if (isRequestSuccess) {
            onPage(++currentPage);
        } else {
            onPage(currentPage);
        }
    }

    /**
     * @param page 加载的页数
     */
    protected void onPage(int page) {
        if (!isActionRefresh && page == 1) {
            if (ctx instanceof NetBaseActivity) {
                ((NetBaseActivity) ctx).dialog.show();
            }
        }
        if (page == 1) {
            isActionRefresh = true;
        }
    }

    /**
     * 清除数据
     */

    public void clearData() {
        if(dataList.size()>0){
            Log.e("dataList************","dataList: "+dataList.toString());
            dataList.clear();
        }
    }


    public void onFinishPage(ArrayList<T> list, BaseAdapter adapter, int totalRowNum, boolean RequestSuccess) {
        Log.e("*******","执行了onFinishPage（）。。。");
        Log.e("+*+*+*+","list :"+list.toString());
        this.isRequestSuccess = RequestSuccess;
        if (isRequestSuccess) {
            this.totalRowNum = totalRowNum;
        }
        Log.e("list.size()", "List.size()_1: " + list.size());
        stopPage(mGridView);
        Log.e("list.size()", "List.size()_2: " + list.size());
        if (list.size() > 0) {
            this.dataList.addAll(list);
            Log.e("++++++++++++++++++++","dataList:"+dataList.toString());
            if (dataList.size() >= totalRowNum) {
                canLoadMore = false;
            } else {
                canLoadMore = true;
            }
        } else {
            canLoadMore = false;
        }
        adapter.notifyDataSetChanged();
        if (ctx instanceof NetBaseActivity && ((NetBaseActivity) ctx).dialog.isShowing()) {
            ((NetBaseActivity) ctx).dialog.dismiss();
        }
    }

    public void stopPage(PullToRefreshGridView gridView) {

        Log.e("********", "currentPage :" + currentPage);
        if (currentPage == 1) {
            clearData();
            gridView.onRefreshComplete();
        }

    }

    public interface OnXScrollListener extends AbsListView.OnScrollListener {
        public void onXScrolling(View view);
    }
}
