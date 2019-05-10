package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.TopTotalBean;
import com.tepia.cmnwsevice.view.main.views.ViewBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:实时督办图表
 */
public class RealTimeSuperView extends ViewBase implements OnChartValueSelectedListener {

//    private final static String[] cateStrs = new String[]{"上海电气", "山东中车", "北控水务", "远达环保", "其他企业"};
    protected Typeface tfRegular;
    protected Typeface tfLight;
    private BarChart chart;
    private BarDataSet set1, set2;
    private XAxis xAxis;

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
        chart.setOnChartValueSelectedListener(this);
        chart.getDescription().setEnabled(false);
        chart.setPinchZoom(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        Legend l = chart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setXEntrySpace(20f);
        l.setTextSize(12f);
        l.setTextColor(Color.parseColor("#666666"));
        l.setDrawInside(false);
        l.setTypeface(tfLight);

        xAxis = chart.getXAxis();
        xAxis.setTypeface(tfLight);
        xAxis.setTextColor(Color.parseColor("#37394C"));
        xAxis.setTextSize(12f);
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setYOffset(10f);
        xAxis.setCenterAxisLabels(true);
//        xAxis.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value) {
//                if (value < 0 || value >= cateStrs.length) {
//                    return "";
//                }
//                return cateStrs[(int) value];
//            }
//        });

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(tfLight);
        leftAxis.setTextColor(Color.parseColor("#37394C"));
        leftAxis.setTextSize(12f);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setTypeface(tfLight);
        rightAxis.setTextColor(Color.parseColor("#37394C"));
        rightAxis.setTextSize(12f);
        rightAxis.setValueFormatter(new LargeValueFormatter());
        rightAxis.setAxisMaximum(100f);
        rightAxis.setAxisMinimum(0f);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawAxisLine(false);
        rightAxis.setAxisMinimum(0f);

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

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

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
        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) chart.getData().getDataSetByIndex(1);
            set1.setValues(values1);
            set2.setValues(values2);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        }

        float groupSpace = 0.54f;
        float barSpace = 0.03f; // x4 DataSet
        float barWidth = 0.2f; // x4 DataSet
        // specify the width each bar should have
        chart.getBarData().setBarWidth(barWidth);

        // restrict the x-axis range
        xAxis.setAxisMinimum(0);
        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        xAxis.setAxisMaximum(chart.getBarData().getGroupWidth(groupSpace, barSpace) * 5);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                  if (value < 0 || value >= vendorTotals.size()) return "";
                TopTotalBean topTotalBean = vendorTotals.get((int) value);
                return topTotalBean != null ? topTotalBean.getName() : "";
            }
        });
        chart.groupBars(0, groupSpace, barSpace);
        chart.invalidate();
    }
}
