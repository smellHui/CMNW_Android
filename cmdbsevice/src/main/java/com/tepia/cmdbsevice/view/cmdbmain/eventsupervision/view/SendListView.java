package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.tepia.cmdbsevice.R;
import com.tepia.cmnwsevice.view.main.views.ViewBase;

/**
 * Author:xch
 * Date:2019/5/20
 * Description:已派单view
 */
public class SendListView extends ViewBase {


    public SendListView(Context context) {
        super(context);
    }

    public SendListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SendListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getViewId() {
        return R.layout.view_send_list;
    }

    @Override
    public void initData() {

    }
}
