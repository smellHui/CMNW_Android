package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.tepia.cmdbsevice.R;
import com.tepia.cmnwsevice.view.main.views.ViewBase;

public class TownshipStatisticsView extends ViewBase {
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

    }
}
