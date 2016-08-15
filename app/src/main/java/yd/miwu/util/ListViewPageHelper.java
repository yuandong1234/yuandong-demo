package yd.miwu.util;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.limxing.xlistview.view.XListView;

import java.util.ArrayList;

import yd.miwu.base.NetBaseActivity;

/**
 * Created by 256 on 2016/6/21.
 */
public class ListViewPageHelper<T> implements XListView.IXListViewListener {

    private Context ctx;
    private ArrayList<T> list;
    private int currentPage;
    private XListView listView;
    private boolean isRequestSuccess = true;
    private boolean canShowNoMoreDataTip = true;//是否可以显示没有更多
    private int totalNum;
    private boolean isActionRefresh = false;//是否可以进行刷新操作
    private int rowNumLeftToLoadMore = 6;
    private boolean canLoadMore = true;//是否可以加载更多
    private boolean isRefresh = false;

    public ListViewPageHelper(Context context, ArrayList<T> list, final XListView listView) {
        this.ctx = context;
        this.list = list;
        this.listView = listView;
        if (context instanceof NetBaseActivity) {
            ((NetBaseActivity) context).createDialog();
        }
        this.listView.setOnScrollListener(new XListView.OnXScrollListener() {
            @Override
            public void onXScrolling(View view) {

            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.e("******","onScroll()....");
                if (totalItemCount >= totalNum && firstVisibleItem + visibleItemCount == totalItemCount && totalNum > 0 && canShowNoMoreDataTip) {
                    if (visibleItemCount < totalNum) {
                        canShowNoMoreDataTip = false;
                        Toast.makeText(ctx, "没有更多了", Toast.LENGTH_SHORT).show();
                    }
                }

                if (firstVisibleItem < totalItemCount - rowNumLeftToLoadMore) {
                    canShowNoMoreDataTip = true;
                }
                if (firstVisibleItem + visibleItemCount >= totalItemCount - rowNumLeftToLoadMore) {

                    if (canLoadMore) {
                        canLoadMore=false;
                        onLoadMore();
                    }
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        Log.e("*********","执行了onRefresh()方法。。。。");
        isActionRefresh = true;
        currentPage = 1;
        setRefresh(true);
        onPage(currentPage);
    }

    @Override
    public void onLoadMore() {
        listView.setPullRefreshEnable(false);
        listView.setPullLoadEnable(false);
        setRefresh(false);

        if (isRequestSuccess)
            onPage(++currentPage);
        else
            onPage(currentPage);

    }

    public void clearData() {
        list.clear();
    }


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

    public void onFinishPage(ArrayList<T> olist, BaseAdapter adapter, int totalRowNum, boolean requestSuccess) {
        isRequestSuccess = requestSuccess;
        if (isRequestSuccess) {
            this.totalNum = totalRowNum;
        }
        onStopPage(listView);
        if (olist.size() > 0) {
            this.list.addAll(olist);
            if (list.size() >= totalRowNum) {
                canLoadMore = false;
                listView.setPullLoadEnable(false);//底部加载更多不显示
            } else {
                canLoadMore = true;
                listView.setPullLoadEnable(true);//底部加载更多显示
            }
        } else {
            listView.setPullLoadEnable(false);
        }


        adapter.notifyDataSetChanged();
        if (ctx instanceof NetBaseActivity && ((NetBaseActivity) ctx).dialog.isShowing()) {
            ((NetBaseActivity) ctx).dialog.dismiss();
        }

        /*if(this.list.size() >= totalRowNum && totalRowNum > 0){
            LogUtils.e("PageHelper","***************list.size() >= totalRowNum**************");
            TextView footerView = new TextView(mCtx);
            footerView.setBackgroundColor(mCtx.getResources().getColor(R.color.colorFFFFFF));
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtil.Dp2Px(mCtx,50));
            footerView.setLayoutParams(lp);
            listview.addFooterView(footerView);
        }*/
    }

    public void onStopPage(XListView lv) {
        if (isActionRefresh) {
            isActionRefresh = false;
            clearData();
        }
        if (isRefresh) {
            Log.e("**********","isRefresh :"+isRefresh);
            lv.stopRefresh();
        } else {
            lv.setPullRefreshEnable(true);
        }
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setRefresh(boolean isRefresh) {
        this.isRefresh = isRefresh;
    }
}
