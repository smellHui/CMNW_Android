package com.tepia.cmdbsevice.view.cmdbmain.targetassessment.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tepia.cmdbsevice.R;
import com.tepia.cmnwsevice.view.main.views.ViewBase;

/**
 * Author:xch
 * Date:2019/4/24
 * Description:
 */
public class SpssTitleView extends ViewBase {

    private LinearLayout ll_title;

    public SpssTitleView(Context context) {
        super(context);
    }

    public SpssTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SpssTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getViewId() {
        return R.layout.view_spss_title;
    }

    @Override
    public void initData() {
        ll_title = findViewById(R.id.ll_title);
    }

    public void setData(@NonNull String... strings) {
        if (strings.length == 0) return;
        for (int i = 0; i < strings.length; i++) {
            TextView tv = new TextView(getContext());
            LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.weight = 1;
            tv.setLayoutParams(layoutParams);
            tv.setText(strings[i]);
            tv.setTextColor(Color.parseColor("#8993A9"));
            tv.setTextSize(14);
            tv.setGravity(Gravity.CENTER);
            ll_title.addView(tv);
        }
    }
}
