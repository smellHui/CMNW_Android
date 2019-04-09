package com.tepia.base.mvp;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.R;
import com.tepia.base.model.PageBean;
import com.tepia.base.view.WrapLayoutManager;
import com.tepia.base.view.dialog.permissiondialog.Px2dpUtils;

import java.util.List;

/**
 * Author:xch
 * Date:2019/4/4
 * Do:
 */
public abstract class BaseListFragment<K> extends BaseCommonFragment
        implements NetListListener<PageBean<K>> {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rv;

    protected int page = 1;

    private BaseQuickAdapter baseQuickAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_list;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView(View view) {
        swipeRefreshLayout = findView(R.id.layout_swipe_refresh);
        rv = findView(R.id.rv);

        if (swipeRefreshLayout == null)
            throw new RuntimeException("not found swipeRefreshLayout");
        if (rv == null)
            throw new RuntimeException("not found RecyclerView");

        setVerModel();
        swipeRefreshLayout.setOnRefreshListener(this::refresh);
    }

    public void refresh() {
        swipeRefreshLayout.setRefreshing(true);
        page = 1;
        getList().clear();
        initRequestData();
    }

    public int getPage() {
        return page;
    }

    protected abstract void initRequestData();

    private void setVerModel() {
        baseQuickAdapter = getBaseQuickAdapter();
        baseQuickAdapter.setEnableLoadMore(true);
        baseQuickAdapter.setOnLoadMoreListener(this::initRequestData, rv);
        rv.setLayoutManager(new WrapLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        int zeroPx = Px2dpUtils.dip2px(getContext(), 0);
        rv.setPadding(zeroPx, zeroPx, zeroPx, zeroPx);
        rv.setAdapter(baseQuickAdapter);
        baseQuickAdapter.setEmptyView(R.layout.view_empty_list_view);
        baseQuickAdapter.setOnItemClickListener(this::setOnItemClickListener);
    }

    public List<K> getList() {
        return baseQuickAdapter.getData();
    }

    public abstract BaseQuickAdapter getBaseQuickAdapter();

    public void setOnItemClickListener(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void success(PageBean<K> k) {
        if (k != null) {
            List<K> list = k.getResult();
            if (list != null) {
                if (page == 1 && list.isEmpty()){
                    getList().clear();
                }else {
                    getList().addAll(list);
                }
                if (list.size() == 20) {
                    page++;
                    baseQuickAdapter.loadMoreComplete();
                } else {
                    baseQuickAdapter.loadMoreEnd();
                }
            } else {
                baseQuickAdapter.loadMoreEnd();
            }
        } else {
            baseQuickAdapter.loadMoreEnd();
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void error() {
        baseQuickAdapter.loadMoreFail();
        swipeRefreshLayout.setRefreshing(false);
    }
}
