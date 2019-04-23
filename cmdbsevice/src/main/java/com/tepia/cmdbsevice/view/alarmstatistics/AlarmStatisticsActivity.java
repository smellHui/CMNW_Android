package com.tepia.cmdbsevice.view.alarmstatistics;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SegmentTabLayout;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupPosition;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.mvp.BaseListActivity;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.WrapLayoutManager;
import com.tepia.base.view.dialog.permissiondialog.Px2dpUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.EventManager;
import com.tepia.cmdbsevice.model.event.TopTotalBean;
import com.tepia.cmdbsevice.model.event.WarnBean;
import com.tepia.cmdbsevice.view.alarmstatistics.view.DataTopView;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.adapter.CityCountAdapter;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.SelectEventPopView;
import com.tepia.cmnwsevice.model.order.OrderBean;
import com.tepia.cmnwsevice.view.main.OrderPresenter;

import java.util.List;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:报警统计
 */
public class AlarmStatisticsActivity extends BaseActivity {

    private RecyclerView rv;
    private AlermStatisAdapter alermStatisAdapter;
    private DataTopView dataTopView;
    private List<WarnBean> warnBeans;
    private SelectEventPopView selectEventPopView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_alarm_statistics;
    }

    @Override
    public void initView() {
        setCenterTitle("报警统计");
        showBack();
        setRightImgEvent(R.mipmap.img_agrc_service, (v) -> {
            new XPopup.Builder(getContext())
                    .popupPosition(PopupPosition.Right)//右边
                    .hasStatusBarShadow(true) //启用状态栏阴影
                    .asCustom(selectEventPopView)
                    .show();
        });
        dataTopView = findViewById(R.id.view_data_top);
        dataTopView.setListener(this::onDataTopPickListener);

        selectEventPopView = new SelectEventPopView(getContext());
        setVerModel();
    }

    @Override
    public void initData() {

    }

    private void setVerModel() {
        rv = findViewById(R.id.rv);
        alermStatisAdapter = new AlermStatisAdapter();
        alermStatisAdapter.setOnItemChildClickListener(this::setOnItemChildClickListener);
        rv.setLayoutManager(new WrapLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        int zeroPx = Px2dpUtils.dip2px(getContext(), 0);
        rv.setPadding(zeroPx, zeroPx, zeroPx, zeroPx);
        rv.setAdapter(alermStatisAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {
        listByWarning(TimeFormatUtils.getFirstDayOfToday(), TimeFormatUtils.getLastDayOfToday());
    }

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

    private void listByWarning(String startTime, String endTime) {
        EventManager.getInstance().listByWarning("startTime", startTime, "endTime", endTime)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<List<WarnBean>>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<List<WarnBean>> baseCommonResponse) {
                        warnBeans = baseCommonResponse.getData();
                        alermStatisAdapter.setNewData(warnBeans);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    public void onDataTopPickListener(String startTime, String endTime) {
        listByWarning(startTime, endTime);
    }
}
