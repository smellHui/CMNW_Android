package com.tepia.cmdbsevice.view.alarmstatistics;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SegmentTabLayout;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupPosition;
import com.tepia.base.mvp.BaseListActivity;
import com.tepia.base.utils.ToastUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.SelectEventPopView;
import com.tepia.cmnwsevice.model.order.OrderBean;
import com.tepia.cmnwsevice.view.main.OrderPresenter;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:报警统计
 */
public class AlarmStatisticsActivity extends BaseListActivity<OrderBean> {

    private String[] mTitles = {"日", "周", "月", "年"};
    private OrderPresenter orderPresenter;
    private SelectEventPopView selectEventPopView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_alarm_statistics;
    }

    @Override
    public void initView() {
        super.initView();
        setCenterTitle("报警统计");
        showBack();
        setRightImgEvent(R.mipmap.img_agrc_service, (v) -> {
            new XPopup.Builder(getContext())
                    .popupPosition(PopupPosition.Right)//右边
                    .hasStatusBarShadow(true) //启用状态栏阴影
                    .asCustom(selectEventPopView)
                    .show();
        });
        selectEventPopView = new SelectEventPopView(getContext());
        SegmentTabLayout tabLayout_1 = findViewById(R.id.tl_1);
        tabLayout_1.setTabData(mTitles);
        orderPresenter = new OrderPresenter(1, this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {
        orderPresenter.querylist(getPage(), 20);
    }

    @Override
    public BaseQuickAdapter getBaseQuickAdapter() {
        return new AlermStatisAdapter();
    }

    @Override
    public void setOnItemChildClickListener(BaseQuickAdapter adapter, View view, int position) {
        int i = view.getId();
        if (i == R.id.btn_look) {
            new XPopup.Builder(getContext())
//                        .enableDrag(false)
                    .asBottomList(null, new String[]{"提升井", "风机"},
                            (position1, text) -> ToastUtils.shortToast("click " + text))
                    .show();
        }
    }
}
