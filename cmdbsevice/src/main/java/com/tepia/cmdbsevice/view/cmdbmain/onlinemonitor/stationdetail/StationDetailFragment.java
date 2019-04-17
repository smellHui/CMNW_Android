package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationdetail;


import android.view.View;

import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.cmdbsevice.R;

/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/4/16 16:52
 * @修改人         ：
 * @修改时间       :       2019/4/16 16:52
 * @功能描述       :
 **/

public class StationDetailFragment extends MVPBaseFragment<StationDetailContract.View, StationDetailPresenter> implements StationDetailContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_station_detail;
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
