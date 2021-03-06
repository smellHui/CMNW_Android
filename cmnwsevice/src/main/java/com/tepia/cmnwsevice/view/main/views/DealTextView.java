package com.tepia.cmnwsevice.view.main.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tepia.cmnwsevice.R;


/**
 * Author:xch
 * Date:2019/4/4
 * Do:
 */
public class DealTextView extends ViewBase {

    private TextView tipTv;
    private TextView countTv;

    public DealTextView(Context context) {
        super(context);
    }

    public DealTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DealTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getViewId() {
        return R.layout.view_deal_text;
    }

    @Override
    public void initData() {
        tipTv = findViewById(R.id.tv_tip);
        countTv = findViewById(R.id.tv_count);
    }

    public void setTitle(@Nullable String title, @DrawableRes int id) {
        tipTv.setText(title);
        if (id == -1) id = R.mipmap.wddb_icn_todo;
        Drawable dra = getResources().getDrawable(id);
        dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());
        tipTv.setCompoundDrawables(dra, null, null, null);
    }

    public void setCount(String count) {
        countTv.setText(count);
    }


}
