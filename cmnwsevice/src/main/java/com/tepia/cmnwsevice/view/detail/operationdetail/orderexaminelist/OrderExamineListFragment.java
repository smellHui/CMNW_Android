package com.tepia.cmnwsevice.view.detail.operationdetail.orderexaminelist;


import android.view.View;

import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.cmnwsevice.R;

/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/4/8 19:40
 * @修改人         ：
 * @修改时间       :       2019/4/8 19:40
 * @功能描述       :
 **/

public class OrderExamineListFragment extends MVPBaseFragment<OrderExamineListContract.View, OrderExamineListPresenter> implements OrderExamineListContract.View {

    public String orderId;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_examine_list_detail;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initRequestData() {

    }
}
