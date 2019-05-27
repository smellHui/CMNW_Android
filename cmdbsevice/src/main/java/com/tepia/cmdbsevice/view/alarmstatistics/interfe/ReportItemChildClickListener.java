package com.tepia.cmdbsevice.view.alarmstatistics.interfe;

import android.view.View;

import com.tepia.cmdbsevice.view.alarmstatistics.model.FlowModel;

/**
 * Author:xch
 * Date:2019/5/27
 * Description:上报审核按钮回调
 */
public interface ReportItemChildClickListener {
    void addReportItemChildClick(View view, FlowModel flowModel, String content);
}
