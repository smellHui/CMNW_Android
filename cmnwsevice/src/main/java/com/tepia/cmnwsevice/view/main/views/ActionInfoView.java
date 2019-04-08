package com.tepia.cmnwsevice.view.main.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.tepia.cmnwsevice.R;



/**
 * Author:xch
 * Date:2019/4/8
 * Do:
 */
public class ActionInfoView extends ViewBase{


    public ActionInfoView(Context context) {
        super(context);
    }

    public ActionInfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ActionInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getViewId() {
        return R.layout.view_action_info;
    }

    @Override
    public void initData() {

    }
}
