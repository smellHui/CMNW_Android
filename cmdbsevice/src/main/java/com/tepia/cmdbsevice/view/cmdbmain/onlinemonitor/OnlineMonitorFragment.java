package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;

import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.databinding.FragmentOnlineMonitorBinding;
import com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.onlinemonitormap.OnlineMonitorMapFragment;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/15 14:44
 * @修改人 ：
 * @修改时间 :       2019/4/15 14:44
 * @功能描述 :        tab-在线监测
 **/
public class OnlineMonitorFragment extends MVPBaseFragment<OnlineMonitorContract.View, OnlineMonitorPresenter> implements OnlineMonitorContract.View {

    private FragmentOnlineMonitorBinding mBinding;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_online_monitor;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        mBinding = DataBindingUtil.bind(view);
        initMapFragment();
        initLisitener();
    }

    private void initMapFragment() {
        FragmentTransaction ft2 = getChildFragmentManager().beginTransaction();
        ft2.replace(R.id.fl_map_container, new OnlineMonitorMapFragment());
        ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft2.addToBackStack(null);
        ft2.commit();

    }

    private void initLisitener() {
        mBinding.loMapOptLayer.loMapLayersSelect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                /** 打开右侧 图层选择 视图*/
                mBinding.drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
    }

    @Override
    protected void initRequestData() {
        mPresenter.getStationList();
    }
}
