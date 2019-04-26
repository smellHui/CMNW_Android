package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor;


import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.ScreenUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.ScrollLayout.ContentScrollView;
import com.tepia.base.view.ScrollLayout.ScrollLayout;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.base.view.dialog.permissiondialog.Px2dpUtils;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.databinding.FragmentOnlineMonitorBinding;
import com.tepia.cmnwsevice.model.station.AreaBean;
import com.tepia.cmnwsevice.model.station.StationBean;
import com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.onlinemonitormap.OnlineMonitorMapFragment;
import com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationdetail.StationDetailFragment;
import com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationlist.StationListFragment;

import java.util.ArrayList;
import java.util.List;

import static com.tepia.base.view.ScrollLayout.ScrollLayout.Status.CLOSED;
import static com.tepia.base.view.ScrollLayout.ScrollLayout.Status.EXIT;
import static com.tepia.base.view.ScrollLayout.ScrollLayout.Status.OPENED;

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
    private OnlineMonitorMapFragment onlineMonitorMapFragment;
    private AdapterStationTypeList adapterStationTypeList;
    private AdapterStationStatusList adapterStationStatusList;
    private AdapterStationSaixuanList adapterStationVender;
    private AdapterStationSaixuanList adapterStationArea;
    private StationListFragment stationListFragment;
    private StationTypeBean selectedArea = new StationTypeBean("全部行政区域", "all");
    private StationTypeBean selectedStatus = new StationTypeBean("全部设备状态", "all");
    private StationTypeBean selectedOrgan = new StationTypeBean("全部运维企业", "all");
    private String stationType = "1";
    private ScrollLayout scrollownLayout;
    private ScrollLayout.Status preStatus = OPENED;

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
        initRightView();
        initLisitener();
        initMapOptLayerView();
        initSearchView();
        initScrollLayout(mRootView);
        initContentView();
        showLayer(0);
    }

    /**
     * 初始化 list 测站view
     */
    private void initContentView() {
        mBinding.flSearchContent.tvSelectArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                showSelectArea();
            }
        });
        mBinding.flSearchContent.tvSelectOrgan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                showSelectOrgan();
            }
        });
        mBinding.flSearchContent.tvSelectStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                showSelectStatus();
            }
        });
    }

    private void showSelectStatus() {
        List<StationTypeBean> statusList = mPresenter.getStationStatusList();
        statusList.add(0, new StationTypeBean("全部设备状态", "all"));
        String[] stringItems;

        stringItems = new String[statusList.size()];
        for (int i = 0; i < statusList.size(); i++) {
            stringItems[i] = statusList.get(i).getName();
        }
        final ActionSheetDialog dialog = new ActionSheetDialog(getContext(), stringItems, null);
        dialog.title("请选择设备状态")
                .titleTextSize_SP(14.5f)
                .widthScale(0.8f)
                .show();

        dialog.setOnOpenItemClickL(new OnOpenItemClick() {
            @Override
            public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                mBinding.flSearchContent.tvSelectStatus.setText(stringItems[position]);
                selectedStatus = statusList.get(position);
                saixuanListFragment();
                dialog.dismiss();
            }
        });
    }

    private void showSelectOrgan() {
        List<StationTypeBean> organList = mPresenter.getVenderList();
        organList.add(0, new StationTypeBean("全部运维企业", "all"));
        String[] stringItems;

        stringItems = new String[organList.size()];
        for (int i = 0; i < organList.size(); i++) {
            stringItems[i] = organList.get(i).getName();
        }
        final ActionSheetDialog dialog = new ActionSheetDialog(getContext(), stringItems, null);
        dialog.title("请选择运维企业")
                .titleTextSize_SP(14.5f)
                .widthScale(0.8f)
                .show();

        dialog.setOnOpenItemClickL(new OnOpenItemClick() {
            @Override
            public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                mBinding.flSearchContent.tvSelectOrgan.setText(stringItems[position]);
                selectedOrgan = organList.get(position);
                saixuanListFragment();
                dialog.dismiss();
            }
        });
    }

    private void showSelectArea() {
        List<StationTypeBean> areaList = mPresenter.getAreaList();
        areaList.add(0, new StationTypeBean("全部行政区划", "all"));
        String[] stringItems;

        stringItems = new String[areaList.size()];
        for (int i = 0; i < areaList.size(); i++) {
            stringItems[i] = areaList.get(i).getName();
        }
        final ActionSheetDialog dialog = new ActionSheetDialog(getContext(), stringItems, null);
        dialog.title("请选择行政区域")
                .titleTextSize_SP(14.5f)
                .widthScale(0.8f)
                .show();

        dialog.setOnOpenItemClickL(new OnOpenItemClick() {
            @Override
            public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                mBinding.flSearchContent.tvSelectArea.setText(stringItems[position]);
                selectedArea = areaList.get(position);
                saixuanListFragment();
                dialog.dismiss();
            }
        });
    }

    private void saixuanListFragment() {
        String conditions = getSaiXuanStrs(stationType);
        if (stationListFragment != null) {
            stationListFragment.saixuan(conditions);
        }
    }

    private void initSearchView() {
        mBinding.loSearchHeader.loBtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                if (mBinding.loSearchHeader.loSearchEt.getVisibility() == View.VISIBLE) {
                    if (mBinding.loSearchHeader.ivSearch.getVisibility() == View.VISIBLE) {
                        String temp = mBinding.loSearchHeader.etSearch.getText().toString();
                        if (TextUtils.isEmpty(temp)) {
                            ToastUtils.shortToast("请输入要搜索的名称");
                            initAndShowSearchHisList();
                        } else {
                            mPresenter.putSearchHis(temp);

                        }
                    } else {
                        showLayer(1);
                        mBinding.loSearchHeader.etSearch.setText("");
                    }
                } else {
                    showLayer(1);
                }
            }
        });
        mBinding.loSearchHeader.etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (!CollectionsUtil.isEmpty(mPresenter.getSearchHisList())) {
                        initAndShowSearchHisList();
                    }

                } else {

                }
            }
        });
        mBinding.loSearchHeader.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String temp = mBinding.loSearchHeader.etSearch.getText().toString();
                if (!TextUtils.isEmpty(temp)) {
                    if (!CollectionsUtil.isEmpty(mPresenter.getSearchTipList(temp))) {
                        showLayer(2);
                        initAndShowSearchTipList();
                    }
                } else {
                    if (!CollectionsUtil.isEmpty(mPresenter.getSearchHisList())) {
                        showLayer(1);
                        initAndShowSearchHisList();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mBinding.loSearchHeader.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                showLayer(0);
            }
        });
    }

    private void initMapOptLayerView() {
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
        mBinding.loMapOptLayer.tvClz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                showLayer(3);
            }
        });
        mBinding.loMapOptLayer.tvTsj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                showLayer(3);
                stationType = "2";
                mBinding.flSearchContent.tvStationNum.setText("提升井（" + mPresenter.getStationNum(getSaiXuanStrs("2")) + ")");
                initListFragment(getSaiXuanStrs("2"));
                if (preStatus == CLOSED) {
                    preStatus = OPENED;
                }
                scrollownLayout.setToOpen();
            }
        });
        mBinding.loMapOptLayer.tvClz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                showLayer(3);
                stationType = "1";
                mBinding.flSearchContent.tvStationNum.setText("处理站（" + mPresenter.getStationNum(getSaiXuanStrs("1")) + ")");
                initListFragment(getSaiXuanStrs("1"));
                if (preStatus == CLOSED) {
                    preStatus = OPENED;
                }
                scrollownLayout.setToOpen();
            }
        });
        mBinding.loMapOptLayer.ivZoomin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                if (onlineMonitorMapFragment != null) {
                    onlineMonitorMapFragment.zoomin();
                }
            }
        });
        mBinding.loMapOptLayer.ivZoomout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                if (onlineMonitorMapFragment != null) {
                    onlineMonitorMapFragment.zoomout();
                }
            }
        });
    }

    /**
     * 初始化滑动布局
     *
     * @param view
     */
    private void initScrollLayout(View view) {
        scrollownLayout = view.findViewWithTag("scroll_down_layout");
        ContentScrollView itemScrollView = view.findViewWithTag("item_scroll_view_layout");
        FrameLayout flContainer = findView(R.id.fl_container);

        //设置列表滑动布局
        //关闭状态时最上方预留高度
        scrollownLayout.setMinOffset(Px2dpUtils.dip2px(getContext(), 100));
        //打开状态时内容显示区域的高度
        scrollownLayout.setMaxOffset(ScreenUtil.getScreenHeightPix(getContext()) / 2);
        //最低部退出状态时可看到的高度，0为不可见
        scrollownLayout.setExitOffset(Px2dpUtils.dip2px(getContext(), 100));
        //解决recycleView底部显示不全的问题
//        listRecylcler.setPadding(0, 0, 0, ScreenUtil.getScreenHeightPix(getContext()) - imgLocationTop - Px2dpUtils.dip2px(mContext, 36 + 55));
        //是否支持下滑退出，支持会有下滑到最底部时的回调
        scrollownLayout.setIsSupportExit(true);
        //是否支持横向滚动
        scrollownLayout.setAllowHorizontalScroll(true);
        scrollownLayout.setOnScrollChangedListener(new ScrollLayout.OnScrollChangedListener() {
            @Override
            public void onScrollProgressChanged(float currentProgress) {

            }

            @Override
            public void onScrollFinished(ScrollLayout.Status currentStatus) {
                changeViewWith(currentStatus);
                preStatus = currentStatus;
            }

            @Override
            public void onChildScroll(int top) {

            }
        });
        preStatus = OPENED;
        scrollownLayout.setToOpen();

        scrollownLayout.getBackground().setAlpha(0);


    }

    private void changeViewWith(ScrollLayout.Status currentStatus) {
        switch (currentStatus) {
            case EXIT:
                if (onlineMonitorMapFragment != null) {
                    onlineMonitorMapFragment.moveDownMap();
                }
                showLayer(0);
                break;
            case CLOSED:
                if (onlineMonitorMapFragment != null) {

                    onlineMonitorMapFragment.moveUpMap();
                }
                mBinding.flSearchContent.loStationNum.setVisibility(View.GONE);
                mBinding.flSearchContent.loStationSetect.setVisibility(View.VISIBLE);
                break;
            case OPENED:
                if (onlineMonitorMapFragment != null) {
                    if (preStatus == EXIT) {
                        onlineMonitorMapFragment.moveUpMap();
                    } else if (preStatus == CLOSED) {
                        onlineMonitorMapFragment.moveDownMap();
                    }
                }
                mBinding.flSearchContent.loStationNum.setVisibility(View.VISIBLE);
                mBinding.flSearchContent.loStationSetect.setVisibility(View.GONE);
                break;
            default:
                break;
        }
        switch (stationType) {
            case "1":
                mBinding.flSearchContent.tvStationNum.setText("处理站（" + mPresenter.getStationNum(getSaiXuanStrs("1")) + ")");
                break;
            case "2":
                mBinding.flSearchContent.tvStationNum.setText("提升井（" + mPresenter.getStationNum(getSaiXuanStrs("2")) + ")");
                break;
        }
    }


    private void initRightView() {
        mBinding.loRight.loMapType1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                if (onlineMonitorMapFragment != null) {
                    onlineMonitorMapFragment.changLayer("image");
                }
            }
        });
        mBinding.loRight.loMapType1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                if (onlineMonitorMapFragment != null) {
                    onlineMonitorMapFragment.changLayer("vender");
                }
            }
        });
        {
            mBinding.loRight.rvStationTypeList.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
            adapterStationTypeList = new AdapterStationTypeList(R.layout.lv_station_type_item_view, mPresenter.getStationTypeList());
            mBinding.loRight.rvStationTypeList.setAdapter(adapterStationTypeList);
        }
        {
            mBinding.loRight.rvStationStatusList.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
            adapterStationStatusList = new AdapterStationStatusList(R.layout.lv_station_status_item_view, mPresenter.getStationStatusList());
            mBinding.loRight.rvStationStatusList.setAdapter(adapterStationStatusList);
        }
        {
            mBinding.loRight.rvStationOrganList.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

            adapterStationVender = new AdapterStationSaixuanList(R.layout.lv_station_saixun_item_view, mPresenter.getVenderList());
            mBinding.loRight.rvStationOrganList.setAdapter(adapterStationVender);
        }
        {
            mBinding.loRight.rvStationAreaList.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
            adapterStationArea = new AdapterStationSaixuanList(R.layout.lv_station_saixun_item_view, mPresenter.getAreaList());
            mBinding.loRight.rvStationAreaList.setAdapter(adapterStationArea);
        }
        mBinding.loRight.tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                adapterStationTypeList.setNewData(mPresenter.getStationTypeList());
                adapterStationStatusList.setNewData(mPresenter.getStationStatusList());
                adapterStationVender.setNewData(mPresenter.getVenderList());
                adapterStationArea.setNewData(mPresenter.getAreaList());
            }
        });
        mBinding.loRight.tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                if (onlineMonitorMapFragment != null) {
                    mBinding.drawerLayout.closeDrawer(Gravity.RIGHT);
                    onlineMonitorMapFragment.saixuan(getSaiXuanStrs());
                }
            }
        });
    }

    private String getSaiXuanStrs(String type) {
        String conditions = "stationType = " + type;
        if (!selectedArea.getCode().equals("all")) {
            conditions += " AND administrativeDivision = " + selectedArea.getCode();
        }
        if (!selectedOrgan.getCode().equals("all")) {
            conditions += " AND enterpriseCode = '" + selectedOrgan.getCode() + "'";
        }
        if (!selectedStatus.getCode().equals("all")) {
            conditions += " AND stationStatus = " + selectedStatus.getCode();
        }
        return conditions;
    }

    private String getSaiXuanStrs() {
        String conditions = "";
        {
            List<StationTypeBean> statuslist = adapterStationStatusList.getSelectData();
            if (!CollectionsUtil.isEmpty(statuslist)) {
                String temp = "(";
                for (StationTypeBean bean : statuslist) {
                    temp += "'" + bean.getCode() + "',";
                }
                temp = temp.substring(0, temp.length() - 1) + ")";
                if (TextUtils.isEmpty(conditions)) {
                    conditions += " stationStatus in " + temp;
                } else {
                    conditions += " AND stationStatus in " + temp;
                }
            }
        }
        {
            List<StationTypeBean> statuslist = adapterStationTypeList.getSelectData();
            if (!CollectionsUtil.isEmpty(statuslist)) {
                String temp = "(";
                for (StationTypeBean bean : statuslist) {
                    temp += "'" + bean.getCode() + "',";
                }
                temp = temp.substring(0, temp.length() - 1) + ")";
                if (TextUtils.isEmpty(conditions)) {
                    conditions += " stationType in " + temp;
                } else {
                    conditions += " AND stationType in " + temp;
                }
            }
        }
        {
            List<StationTypeBean> statuslist = adapterStationVender.getSelectData();
            if (!CollectionsUtil.isEmpty(statuslist)) {
                String temp = "(";
                for (StationTypeBean bean : statuslist) {
                    temp += "'" + bean.getCode() + "',";
                }
                temp = temp.substring(0, temp.length() - 1) + ")";
                if (TextUtils.isEmpty(conditions)) {
                    conditions += " enterpriseCode in " + temp;
                } else {
                    conditions += " AND enterpriseCode in " + temp;
                }
            }
        }
        {
            List<StationTypeBean> statuslist = adapterStationArea.getSelectData();
            if (!CollectionsUtil.isEmpty(statuslist)) {
                String temp = "(";
                for (StationTypeBean bean : statuslist) {
                    temp += "'" + bean.getCode() + "',";
                }
                temp = temp.substring(0, temp.length() - 1) + ")";
                if (TextUtils.isEmpty(conditions)) {
                    conditions += " administrativeDivision in " + temp;
                } else {
                    conditions += " AND administrativeDivision in " + temp;
                }
            }
        }
        return conditions;
    }

    /**
     * 初始化 地图 fragment
     */
    private void initMapFragment() {
        FragmentTransaction ft2 = getChildFragmentManager().beginTransaction();
        onlineMonitorMapFragment = new OnlineMonitorMapFragment();
        onlineMonitorMapFragment.setOnPointClickListener(new OnlineMonitorMapFragment.OnPointClickListener() {

            @Override
            public void onPointClick(StationBean bean) {
                ARouter.getInstance().build(AppRoutePath.app_cmdb_station_detail)
                        .withString("stationBean", new Gson().toJson(bean))
                        .navigation();
            }
        });
        ft2.replace(R.id.fl_map_container, onlineMonitorMapFragment);
        ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft2.addToBackStack(null);
        ft2.commit();

    }

    private void initLisitener() {


    }

    private void initListFragment(String... temp) {
        FragmentTransaction ft2 = getChildFragmentManager().beginTransaction();
        stationListFragment = new StationListFragment();
        stationListFragment.conditions = temp;
        stationListFragment.setOnListItemClickListener(new StationListFragment.OnListItemClickListener() {
            @Override
            public void onItemClick(StationBean stationBean) {
                ARouter.getInstance().build(AppRoutePath.app_cmdb_station_detail)
                        .withString("stationBean", new Gson().toJson(stationBean).toString())
                        .navigation();
            }
        });
        ft2.replace(R.id.fl_container, stationListFragment);
        ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft2.addToBackStack(null);
        ft2.commit();


    }

    /**
     * 初始化 并显示 提示list
     */
    private void initAndShowSearchTipList() {

        mBinding.loSearchTip.rvSearchTip.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterSearchTipList adapterSearchTipList = new AdapterSearchTipList(R.layout.lv_search_tip_list, mPresenter.getSearchTipList(mBinding.loSearchHeader.etSearch.getText().toString()));
        mBinding.loSearchTip.rvSearchTip.setAdapter(adapterSearchTipList);
//        View footview = LayoutInflater.from(getContext()).inflate(R.layout.lv_search_foot_view, null);
//        adapterSearchTipList.setFooterView(footview);

        adapterSearchTipList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                mBinding.loSearchHeader.etSearch.setText(adapterSearchTipList.getData().get(position).getName());
                ARouter.getInstance().build(AppRoutePath.app_cmdb_station_detail)
                        .withString("stationBean", new Gson().toJson(adapterSearchTipList.getData().get(position)).toString())
                        .navigation();

            }
        });

    }

    /**
     * 根据不同状态 显示不同 图层
     *
     * @param status
     */
    private void showLayer(int status) {
        switch (status) {
            case 0: {
                mBinding.loSearchHeader.loBtSearch.setVisibility(View.VISIBLE);
                mBinding.loSearchHeader.loSearchEt.setVisibility(View.GONE);
                mBinding.loMapOptLayer.loMapOptLayer.setVisibility(View.VISIBLE);
                mBinding.loSearchTip.loSearch.setVisibility(View.GONE);
                mBinding.flSearchContent.flSearchContent.setVisibility(View.GONE);
            }
            break;
            case 1: {
                mBinding.loSearchHeader.loBtSearch.setVisibility(View.VISIBLE);
                mBinding.loSearchHeader.loSearchEt.setVisibility(View.VISIBLE);
                mBinding.loMapOptLayer.loMapOptLayer.setVisibility(View.VISIBLE);
                mBinding.loSearchTip.loSearch.setVisibility(View.VISIBLE);
                if (CollectionsUtil.isEmpty(mPresenter.getSearchHisList())) {
                    mBinding.loSearchTip.loSearchTip.setVisibility(View.GONE);
                } else {
                    mBinding.loSearchTip.loSearchTip.setVisibility(View.VISIBLE);
                }
                mBinding.loSearchTip.loSearchTip.setVisibility(View.VISIBLE);
                mBinding.loSearchTip.rvSearchHis.setVisibility(View.VISIBLE);
                mBinding.loSearchTip.rvSearchTip.setVisibility(View.GONE);
                mBinding.flSearchContent.flSearchContent.setVisibility(View.GONE);
            }
            break;
            case 2: {
                mBinding.loSearchHeader.loBtSearch.setVisibility(View.VISIBLE);
                mBinding.loSearchHeader.loSearchEt.setVisibility(View.VISIBLE);
                mBinding.loMapOptLayer.loMapOptLayer.setVisibility(View.GONE);
                mBinding.loSearchTip.loSearch.setVisibility(View.VISIBLE);
                mBinding.loSearchTip.loSearchTip.setVisibility(View.VISIBLE);
                mBinding.loSearchTip.rvSearchHis.setVisibility(View.GONE);
                mBinding.loSearchTip.rvSearchTip.setVisibility(View.VISIBLE);
                mBinding.flSearchContent.flSearchContent.setVisibility(View.GONE);
            }
            break;
            case 3: {
                mBinding.loSearchHeader.loBtSearch.setVisibility(View.VISIBLE);
                mBinding.loSearchHeader.loSearchEt.setVisibility(View.VISIBLE);
                mBinding.loMapOptLayer.loMapOptLayer.setVisibility(View.VISIBLE);
                mBinding.loSearchTip.loSearch.setVisibility(View.GONE);
                mBinding.flSearchContent.flSearchContent.setVisibility(View.VISIBLE);
            }
            break;
            default:
                break;
        }
    }


    /**
     * 初始化 搜索历史 list view
     */
    private void initAndShowSearchHisList() {

        mBinding.loSearchTip.rvSearchHis.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterSearchHisList adapterSearchHisList = new AdapterSearchHisList(R.layout.lv_search_his_list, mPresenter.getSearchHisList());
        mBinding.loSearchTip.rvSearchHis.setAdapter(adapterSearchHisList);
        View footview = LayoutInflater.from(getContext()).inflate(R.layout.lv_search_foot_view, null);

        footview.findViewById(R.id.tv_foot_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                mPresenter.clearSearchHisList();
            }
        });
        adapterSearchHisList.setFooterView(footview);

        adapterSearchHisList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                mBinding.loSearchHeader.etSearch.setText(adapterSearchHisList.getData().get(position));
                showLayer(2);
//                initAndShowSearchTipList(adapterSearchHisList.getData().get(position));
            }
        });
    }

    @Override
    protected void initRequestData() {
//        mPresenter.getStationList();

    }
}
