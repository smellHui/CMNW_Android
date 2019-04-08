package com.tepia.cmnwsevice.view.detail.operationdetail.workingdetail;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.cmnwsevice.R;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/8 17:34
 * @修改人 ：
 * @修改时间 :       2019/4/8 17:34
 * @功能描述 :
 **/
@Route(path = "")
public class WorkingDetailFragment extends MVPBaseFragment<WorkingDetailContract.View, WorkingDetailPresenter> implements WorkingDetailContract.View {

    String orderId = "1";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_working_detail;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initRequestData() {
        mPresenter.getOrderWorkingDetail(orderId);
    }
}
