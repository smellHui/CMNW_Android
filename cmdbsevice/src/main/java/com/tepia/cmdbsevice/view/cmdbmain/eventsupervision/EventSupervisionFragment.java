package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision;


import android.view.View;

import com.flyco.tablayout.SegmentTabLayout;
import com.tepia.base.mvp.BaseCommonFragment;
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

public class EventSupervisionFragment extends BaseCommonFragment {

    private String[] mTitles = {"日", "周", "月", "年"};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_event_supervision;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
           setCenterTitle("事件督办");
        SegmentTabLayout tabLayout_1 = findView(R.id.tl_1);
        tabLayout_1.setTabData(mTitles);
    }

    @Override
    protected void initRequestData() {

    }
}
