package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.view.alarmstatistics.AlarmStatisticsActivity;
import com.tepia.cmnwsevice.view.main.views.ViewBase;

public class CountShowView extends ViewBase {

    private FrameLayout alarmFl;

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
        alarmFl = findViewById(R.id.fl_alarm);
        alarmFl.setOnClickListener((v) -> mContext.startActivity(new Intent(mContext, AlarmStatisticsActivity.class)));
    }
}
