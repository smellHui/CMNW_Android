package com.tepia.base.mvp;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.R;
import com.tepia.base.view.dialog.permissiondialog.Px2dpUtils;

import java.util.List;

/**
 * Author:xch
 * Date:2019/4/4
 * Do:
 */
public abstract class BaseListFragment<V extends BaseView, T extends BasePresenterImpl<V>> extends MVPBaseFragment<V, T> {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rv;

    protected int page = 1;

    private Context ctx;
    private BaseQuickAdapter baseQuickAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_list;
    }

    @Override
    protected void initData() {
        ctx = getContext();
    }

    @Override
    protected void initView(View view) {
        swipeRefreshLayout = findView(R.id.layout_swipe_refresh);
        rv = findView(R.id.rv);

        setVerModel();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            page = 1;
            getList().clear();
            initRequestData();
        });

    }

    protected abstract void initRequestData();

    private void setVerModel() {
        baseQuickAdapter = getBaseQuickAdapter();
        baseQuickAdapter.setEnableLoadMore(true);
        baseQuickAdapter.setOnLoadMoreListener(this::initRequestData, rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        int zeroPx = Px2dpUtils.dip2px(ctx, 0);
        rv.setPadding(zeroPx, zeroPx, zeroPx, zeroPx);
        rv.setAdapter(baseQuickAdapter);

        baseQuickAdapter.setOnItemClickListener(this::setOnItemClickListener);
    }

    public List<T> getList() {
        return baseQuickAdapter.getData();
    }

    public abstract BaseQuickAdapter getBaseQuickAdapter();

    public void setOnItemClickListener(BaseQuickAdapter adapter, View view, int position) {

    }
}
