package com.tepia.cmdbsevice.view.alarmstatistics.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmnwsevice.view.main.views.ViewBase;

/**
 * Author:xch
 * Date:2019/4/22
 * Description:"今日", "本周", "本月", "本年"切换
 */
public class DataTopView extends ViewBase {

    private static String[] mTitles = {"今日", "本周", "本月", "本年"};

    private onDataTopPickListener listener;
    private SegmentTabLayout tabLayout;

    public void setListener(onDataTopPickListener listener) {
        this.listener = listener;
    }

    public DataTopView(Context context) {
        super(context);
    }

    public DataTopView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DataTopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getViewId() {
        return R.layout.view_data_top;
    }

    @Override
    public void initData() {
        tabLayout = findViewById(R.id.tab_view);
        tabLayout.setTabData(mTitles);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (listener == null) return;
                switch (position) {
                    case 0://今日
                        listener.onDataTopPickListener(TimeFormatUtils.getFirstDayOfToday(), TimeFormatUtils.getLastDayOfToday());
                        break;
                    case 1://周
                        listener.onDataTopPickListener(TimeFormatUtils.getFirstDayOfWeek(), TimeFormatUtils.getLastDayOfWeek());
                        break;
                    case 2://月
                        listener.onDataTopPickListener(TimeFormatUtils.getFirstDayOfMonth(), TimeFormatUtils.getLastDayOfMonth());
                        break;
                    case 3://年
                        listener.onDataTopPickListener(TimeFormatUtils.getFirstDayOfYear(), TimeFormatUtils.getLastDayOfYear());
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    public interface onDataTopPickListener {
        void onDataTopPickListener(String startTime, String endTime);
    }
}
