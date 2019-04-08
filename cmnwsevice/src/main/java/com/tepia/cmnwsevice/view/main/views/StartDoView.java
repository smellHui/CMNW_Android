package com.tepia.cmnwsevice.view.main.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.tepia.cmnwsevice.R;

//import androidx.annotation.Nullable;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:
 */
public class StartDoView extends ViewBase{

    public StartDoView(Context context) {
        super(context);
    }

    public StartDoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StartDoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getViewId() {
        return R.layout.view_start_do;
    }

    @Override
    public void initData() {

    }
}
