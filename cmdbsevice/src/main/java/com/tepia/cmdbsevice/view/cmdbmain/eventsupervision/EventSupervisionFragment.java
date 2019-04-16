package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision;


import android.view.View;

import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.cmdbsevice.R;

/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/4/15 14:43
 * @修改人         ：
 * @修改时间       :       2019/4/15 14:43
 * @功能描述       :        tab-事件督办
 **/

public class EventSupervisionFragment extends MVPBaseFragment<EventSupervisionContract.View, EventSupervisionPresenter> implements EventSupervisionContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_event_supervision;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initRequestData() {
        mPresenter.topTotal();
    }
}
