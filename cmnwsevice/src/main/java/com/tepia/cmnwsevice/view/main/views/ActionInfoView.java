package com.tepia.cmnwsevice.view.main.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tepia.cmnwsevice.R;


/**
 * Author:xch
 * Date:2019/4/8
 * Do:
 */
public class ActionInfoView extends ViewBase {

    private TextView tv_title, tv_content;

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
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);
    }

    public void setData(String title, String content) {
        tv_content.setText(content);
        tv_title.setText(title);
    }
}
