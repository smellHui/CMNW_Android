package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.TopTotalBean;
import com.tepia.cmnwsevice.view.main.views.ViewBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:实时督办图表
 */
public class RealTimeSuperView extends ViewBase {

    protected Typeface tfRegular;
    protected Typeface tfLight;
    private BarChart chart;
    private BarDataSet set1, set2;
    private XAxis xAxis;
    private YAxis leftAxis, rightAxis;

    public RealTimeSuperView(Context context) {
        super(context);
    }

    public RealTimeSuperView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RealTimeSuperView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getViewId() {
        return R.layout.view_real_time_super;
    }

    @Override
    public void initData() {
        tfRegular = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Regular.ttf");
        tfLight = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Light.ttf");
        chart = findViewById(R.id.chart1);
        chart.getDescription().setEnabled(false);
        chart.setPinchZoom(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        initLegend();
        initXAxis();
        initAxisLeft();
        initAxisRight();
        initBarData();
    }

    /**
     * 初始化解释性文字 位置，文字颜色，大小
     */
    private void initLegend() {
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setXEntrySpace(20f);
        legend.setFormToTextSpace(8f);
        legend.setTextSize(12f);
        legend.setTextColor(Color.parseColor("#666666"));
        legend.setTypeface(tfLight);
    }

    /**
     * 横x轴公司类型
     */
    private void initXAxis() {
        xAxis = chart.getXAxis();
        xAxis.setTypeface(tfLight);
        xAxis.setTextColor(Color.parseColor("#37394C"));
        xAxis.setTextSize(12f);
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setYOffset(10f);
        xAxis.setCenterAxisLabels(true);
    }

    /**
     * 初始化左Y轴
     */
    private void initAxisLeft() {
        leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(tfLight);
        leftAxis.setTextColor(Color.parseColor("#37394C"));
        leftAxis.setTextSize(12f);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);
    }

    /**
     * 初始化右Y轴
     */
    private void initAxisRight() {
        rightAxis = chart.getAxisRight();
        rightAxis.setTypeface(tfLight);
        rightAxis.setTextColor(Color.parseColor("#37394C"));
        rightAxis.setTextSize(12f);
        rightAxis.setValueFormatter(new LargeValueFormatter());
        rightAxis.setAxisMaximum(50f);
        rightAxis.setAxisMinimum(0f);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawAxisLine(false);
    }

    /**
     * 设置数据轴类型，颜色
     */
    private void initBarData() {
        // create 4 DataSets
        set1 = new BarDataSet(new ArrayList<>(), "故障数");
        set1.setColor(Color.parseColor("#F46B6D"));
        set2 = new BarDataSet(new ArrayList<>(), "报警数");
        set2.setColor(Color.parseColor("#FEAA18"));

        BarData data = new BarData(set1, set2);
        data.setValueFormatter(new LargeValueFormatter());
        data.setValueTypeface(tfLight);
        chart.setFitBars(true);

        chart.setData(data);
    }

    public void setData(List<TopTotalBean> vendorTotals) {
        if (vendorTotals == null || vendorTotals.isEmpty()) return;
        ArrayList<BarEntry> values1 = new ArrayList<>();
        ArrayList<BarEntry> values2 = new ArrayList<>();
        for (int i = 0; i < vendorTotals.size(); i++) {
            TopTotalBean topTotalBean = vendorTotals.get(i);
            values1.add(new BarEntry(i, Float.valueOf(topTotalBean.getFaultNum())));
            values2.add(new BarEntry(i, Float.valueOf(topTotalBean.getAlarmNum())));
        }
        BarData barData = chart.getData();
        if (barData != null && barData.getDataSetCount() > 0) {
            set1 = (BarDataSet) barData.getDataSetByIndex(0);
            set2 = (BarDataSet) barData.getDataSetByIndex(1);
            set1.setValues(values1);
            set2.setValues(values2);
            barData.notifyDataChanged();
            chart.notifyDataSetChanged();
        }

        float groupSpace = 0.54f;
        float barSpace = 0.03f; // x4 DataSet
        float barWidth = 0.2f; // x4 DataSet
        barData.setBarWidth(barWidth);

        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(barData.getGroupWidth(groupSpace, barSpace) * 5);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value < 0 || value >= vendorTotals.size()) return "";
                TopTotalBean topTotalBean = vendorTotals.get((int) value);
                return topTotalBean != null ? topTotalBean.getName() : "";
            }
        });

        List<Integer> nums = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            vendorTotals.forEach((ven) -> {
                nums.add(Integer.valueOf(ven.getFaultNum()));
                nums.add(Integer.valueOf(ven.getAlarmNum()));
            });
            int maxNum = nums.stream().max(Integer::compare).get();
            rightAxis.setAxisMaximum(maxNum * 2);
            leftAxis.setAxisMaximum(maxNum * 2);
        }

        chart.groupBars(0, groupSpace, barSpace);
        chart.invalidate();
    }
}
