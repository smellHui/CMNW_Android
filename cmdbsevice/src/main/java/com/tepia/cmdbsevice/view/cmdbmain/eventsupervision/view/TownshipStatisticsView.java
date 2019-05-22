package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.tepia.base.view.WrapLayoutManager;
import com.tepia.base.view.dialog.permissiondialog.Px2dpUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.TopTotalBean;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.adapter.CityCountAdapter;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.view.SpssTitleView;
import com.tepia.cmnwsevice.view.main.views.ViewBase;

import java.util.List;

public class TownshipStatisticsView extends ViewBase {

    private static String[] FAULT_RATES = {"行政区划", "运维企业", "站点总数", "报警总数", "故障总数"};

    private RecyclerView rv;
    private SpssTitleView spssTitleView;
    private CityCountAdapter cityCountAdapter;

    public TownshipStatisticsView(Context context) {
        super(context);
    }

    public TownshipStatisticsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TownshipStatisticsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getViewId() {
        return R.layout.view_town_ship_statistics;
    }

    @Override
    public void initData() {
        spssTitleView = findViewById(R.id.view_title);
        spssTitleView.setData(FAULT_RATES);
        setVerModel();
    }

    private void setVerModel() {
        rv = findViewById(R.id.rv);
        cityCountAdapter = new CityCountAdapter();
        rv.setLayoutManager(new WrapLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        int zeroPx = Px2dpUtils.dip2px(getContext(), 0);
        rv.setPadding(zeroPx, zeroPx, zeroPx, zeroPx);
        rv.setAdapter(cityCountAdapter);
    }

    public void setNewData(List<TopTotalBean> townTotals){
        cityCountAdapter.setNewData(townTotals);
    }

}
