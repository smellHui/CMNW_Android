package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.util.UiHelper;
import com.tepia.cmnwsevice.view.main.views.ViewBase;

import static com.tepia.cmdbsevice.view.alarmstatistics.AlarmStatisticsTwoActivity.ALARM_SITE;
import static com.tepia.cmdbsevice.view.alarmstatistics.AlarmStatisticsTwoActivity.FAULT_SITE;
import static com.tepia.cmdbsevice.view.alarmstatistics.AlarmStatisticsTwoActivity.REPORT_SITE;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:故障总数，报警总数view
 */
public class CountShowView extends ViewBase {

    private TextView tv_alarmNum, tv_faultNum, tv_reportNum;

    public CountShowView(Context context) {
        super(context);
    }

    public CountShowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CountShowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getViewId() {
        return R.layout.view_show_count;
    }

    @Override
    public void initData() {
        tv_faultNum = findViewById(R.id.tv_faultNum);
        tv_alarmNum = findViewById(R.id.tv_alarmNum);
        tv_reportNum = findViewById(R.id.tv_reportNum);

        findViewById(R.id.fl_alarm).setOnClickListener((v) -> UiHelper.goToAlarmStatisticsTwoView(mContext, ALARM_SITE));
        findViewById(R.id.fl_gz).setOnClickListener((v) -> UiHelper.goToAlarmStatisticsTwoView(mContext, FAULT_SITE));
        findViewById(R.id.fl_report).setOnClickListener((v) -> UiHelper.goToAlarmStatisticsTwoView(mContext, REPORT_SITE));
    }

    public void setDate(String faultNum, String alarmNum, String reportNum) {
        tv_faultNum.setText(faultNum);
        tv_alarmNum.setText(alarmNum);
        tv_reportNum.setText(reportNum);
    }
}
