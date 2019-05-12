package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationhisdata;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.view.EmptyLayoutUtil;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.databinding.ActivityStationHisDataBinding;
import com.tepia.cmdbsevice.databinding.LvHisDataHeaderViewBinding;
import com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.StationTypeBean;
import com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationbaseinfodetail.KeyValueBean;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.view.SelectDataView;
import com.tepia.cmnwsevice.model.station.HisDataBean;
import com.tepia.cmnwsevice.model.station.StationBean;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

import java.util.List;


/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/25 12:01
 * @修改人 ：
 * @修改时间 :       2019/4/25 12:01
 * @功能描述 :        历史数据
 **/
@Route(path = AppRoutePath.app_cmdb_station_his_data)
public class StationHisDataActivity extends MVPBaseActivity<StationHisDataContract.View, StationHisDataPresenter> implements StationHisDataContract.View {
    private static String[] mTitles = {"历史报警", "历史故障"};
    private ActivityStationHisDataBinding mBinding;
    private StationBean stationBean;
    private int currectTab = 0;
    private AdapterHisData adapterHisData;

    private LvHisDataHeaderViewBinding mheaderBinding;
    private SelectDataView selectDataView;
    private String startTime, endTime;
    private KeyValueBean selectedType = new KeyValueBean("全部类型", "10001,10002,10003");

    @Override
    public int getLayoutId() {
        return R.layout.activity_station_his_data;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initView() {
        setCenterTitle("历史数据");
        showBack();
        getRithtTv().setVisibility(View.VISIBLE);
        getRithtTv().setText("本季");
        getRithtTv().setTextColor(0xff4eb17b);
        getRithtTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                showChoiceDateDialog();
            }
        });
        mBinding = DataBindingUtil.bind(mRootView);
        mBinding.tl1.setTabData(mTitles);
        mBinding.tl1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                currectTab = position;
                refreshView(currectTab);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        initListView();
        selectDataView = new SelectDataView(getContext(), this::onDataSelectPickListener);

        this.startTime = TimeFormatUtils.getThisQuarterStart();
        this.endTime = TimeFormatUtils.getThisQuarterEnd();
    }

    private void refreshView(int currectTab) {
        switch (currectTab) {
            case 0: {
                String stationCode = stationBean.getCode();
                String type = selectedType.getValue();
                mPresenter.getWarningHistory(startTime, endTime, stationCode, type);
            }
            break;
            case 1:

            {
                String stationCode = stationBean.getCode();
                String type = selectedType.getValue();
                mPresenter.getFaultHistory(startTime, endTime, stationCode, type);
            }
            break;
            default:
                break;
        }
    }

    private void onDataSelectPickListener(String startTime, String endTime, int cate) {
        this.startTime = startTime;
        this.endTime = endTime;
        switch (cate) {
            case 0:
                setChoiceDateTv("本年");
                break;
            case 1:
                setChoiceDateTv("本季");
                break;
            case 2:
                setChoiceDateTv("本月");
                break;
            case 3:
                setChoiceDateTv(String.format("%s - %s", startTime.replace("-", ".").substring(5,startTime.length()), endTime.replace("-", ".").substring(5,startTime.length())));
                break;
        }
        refreshView(currectTab);
    }

    public void setChoiceDateTv(String str) {
        getRithtTv().setText(str);

    }

    public void showChoiceDateDialog() {
        new XPopup.Builder(getContext())
                .hasStatusBarShadow(true) //启用状态栏阴影
                .dismissOnTouchOutside(false)
                .asCustom(selectDataView)
                .show();
    }

    private void initListView() {
        mBinding.rvHisData.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterHisData = new AdapterHisData(R.layout.lv_warning_view, null);
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.lv_his_data_header_view, null);
        mheaderBinding = DataBindingUtil.bind(headerView);
        adapterHisData.setHeaderView(headerView);
        mBinding.rvHisData.setAdapter(adapterHisData);
    }

    @Override
    public void initData() {
        String temp = getIntent().getStringExtra("stationBean");
        if (!TextUtils.isEmpty(temp)) {
            stationBean = new Gson().fromJson(temp, StationBean.class);
        }
    }

    @Override
    protected void initListener() {
        mBinding.tvSelectType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                showSelectTypeDialog();
            }
        });
    }

    private void showSelectTypeDialog() {
        List<KeyValueBean> areaList = mPresenter.getTypeList();
        String[] stringItems;

        stringItems = new String[areaList.size()];
        for (int i = 0; i < areaList.size(); i++) {
            stringItems[i] = areaList.get(i).getKey();
        }
        final ActionSheetDialog dialog = new ActionSheetDialog(getContext(), stringItems, null);
        dialog.title("请选择行政区域")
                .titleTextSize_SP(14.5f)
                .widthScale(0.8f)
                .show();

        dialog.setOnOpenItemClickL(new OnOpenItemClick() {
            @Override
            public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                mBinding.tvSelectType.setText(stringItems[position]);
                selectedType = areaList.get(position);
                refreshView(currectTab);
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void initRequestData() {
        String stationCode = stationBean.getCode();
        String type = selectedType.getValue();
        mPresenter.getWarningHistory(startTime, endTime, stationCode, type);
    }

    @Override
    public void getFaultHistorySuccess(HisDataBean data) {
        adapterHisData.setNewData(data.getWarningList());
        mheaderBinding.tvCount.setText("" + data.getWarningCount());
        mheaderBinding.tvDurtion.setText("" + data.getSum() + "小时");
        if (CollectionsUtil.isEmpty(data.getWarningList())) {
            adapterHisData.setEmptyView(EmptyLayoutUtil.getView("没有故障记录"));
        }
    }

    @Override
    public void getWarningHistorySuccess(HisDataBean data) {
        adapterHisData.setNewData(data.getWarningList());
        mheaderBinding.tvCount.setText("" + data.getWarningCount());
        mheaderBinding.tvDurtion.setText("" + data.getSum());
        if (CollectionsUtil.isEmpty(data.getWarningList())) {
            adapterHisData.setEmptyView(EmptyLayoutUtil.getView("没有报警记录"));
        }
    }
}
