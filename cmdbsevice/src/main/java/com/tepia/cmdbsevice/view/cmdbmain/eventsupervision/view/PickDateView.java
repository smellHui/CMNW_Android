package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmnwsevice.view.main.views.ViewBase;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

/**
 * Author:xch
 * Date:2019/5/22
 * Description:
 */
public class PickDateView extends ViewBase {
    private static final String[] CHANNELS = new String[]{"今日", "本周", "本月", "本年"};
    private SegmentTabLayout magicIndicator;

    private OnDateTabSelectListener dateTabSelectListener;

    public void setDateTabSelectListener(OnDateTabSelectListener dateTabSelectListener) {
        this.dateTabSelectListener = dateTabSelectListener;
    }

    public PickDateView(Context context) {
        super(context);
    }

    public PickDateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PickDateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getViewId() {
        return R.layout.view_pick_date;
    }

    @Override
    public void initData() {
        initMagicIndicator();
    }

    private void initMagicIndicator() {
        magicIndicator = findViewById(R.id.magic_indicator);
        magicIndicator.setTabData(CHANNELS);
        magicIndicator.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (dateTabSelectListener == null) return;
                switch (position) {
                    case 0://今日
                        dateTabSelectListener.onTabSelect(TimeFormatUtils.getFirstDayOfToday(), TimeFormatUtils.getLastDayOfToday());
                        break;
                    case 1://周
                        dateTabSelectListener.onTabSelect(TimeFormatUtils.getFirstDayOfWeek(), TimeFormatUtils.getLastDayOfWeek());
                        break;
                    case 2://月
                        dateTabSelectListener.onTabSelect(TimeFormatUtils.getFirstDayOfMonth(), TimeFormatUtils.getLastDayOfMonth());
                        break;
                    case 3://年
                        dateTabSelectListener.onTabSelect(TimeFormatUtils.getFirstDayOfYear(), TimeFormatUtils.getLastDayOfYear());
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    interface OnDateTabSelectListener {
        void onTabSelect(String startDate, String endDate);
    }

}
