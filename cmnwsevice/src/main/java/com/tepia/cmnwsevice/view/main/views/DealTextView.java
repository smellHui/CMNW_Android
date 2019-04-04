package com.tepia.cmnwsevice.view.main.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tepia.cmnwsevice.R;

import androidx.annotation.Nullable;

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
        Drawable dra = getResources().getDrawable(R.mipmap.img_agrc_service);
        dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());
        tipTv.setCompoundDrawables(dra, null, null, null);
    }

    public void setTitle(@Nullable String title){
        tipTv.setText(title);
    }

}
