package com.tepia.cmdbsevice.view.alarmstatistics.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.tepia.cmdbsevice.R;
import com.tepia.cmnwsevice.view.main.views.ViewBase;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:选择日期view
 */
public class ChoiceDateView extends ViewBase {
    public ChoiceDateView(Context context) {
        super(context);
    }

    public ChoiceDateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChoiceDateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getViewId() {
        return R.layout.view_choice_date;
    }

    @Override
    public void initData() {

    }
}
