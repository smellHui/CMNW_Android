package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view;

import android.content.Context;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.highlight.BarHighlighter;
import com.github.mikephil.charting.renderer.BarChartRenderer;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/5/24
 * Time            :       11:19
 * Version         :       1.0
 * 功能描述        :        圆角 barChart
 **/
public class RoundBarChart extends BarChart {
    public RoundBarChart(Context context) {
        super(context);
    }

    public RoundBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundBarChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();
        mRenderer = new RoundBarChartRenderer(this, mAnimator, mViewPortHandler);
    }
}
