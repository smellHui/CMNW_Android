package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.tepia.cmdbsevice.R;
import com.tepia.cmnwsevice.view.main.views.ViewBase;

public class RealTimeSuperView extends ViewBase {
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

    }
}
