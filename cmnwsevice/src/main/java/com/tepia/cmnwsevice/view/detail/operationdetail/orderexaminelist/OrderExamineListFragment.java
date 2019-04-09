package com.tepia.cmnwsevice.view.detail.operationdetail.orderexaminelist;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Adapter;

import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.view.EmptyLayoutUtil;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.databinding.FragmentExamineListDetailBinding;
import com.tepia.cmnwsevice.model.order.ExamineBean;

import java.util.List;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/8 19:40
 * @修改人 ：
 * @修改时间 :       2019/4/8 19:40
 * @功能描述 :
 **/

public class OrderExamineListFragment extends MVPBaseFragment<OrderExamineListContract.View, OrderExamineListPresenter> implements OrderExamineListContract.View {

    public String orderId;
    private FragmentExamineListDetailBinding mBinding;
    private AdapterExamineList adapterExamineList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_examine_list_detail;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        mBinding = DataBindingUtil.bind(view);
        initListView();
    }

    private void initListView() {
        mBinding.rvExamine.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterExamineList = new AdapterExamineList(R.layout.lv_examine_list, null);
        mBinding.rvExamine.setAdapter(adapterExamineList);
        mBinding.srlContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getOrderExamineList(orderId);
                mBinding.srlContainer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mBinding.srlContainer.setRefreshing(false);
                    }
                }, 1500);
            }
        });
    }

    @Override
    protected void initRequestData() {
        mPresenter.getOrderExamineList(orderId);
    }

    @Override
    public void getOrderExamineListSuccess(List<ExamineBean> data) {
        if (CollectionsUtil.isEmpty(data)) {
            adapterExamineList.setEmptyView(EmptyLayoutUtil.getView("您暂时没有任何审核记录哦！"));
        } else {
            adapterExamineList.setNewData(data);
        }
    }

}
