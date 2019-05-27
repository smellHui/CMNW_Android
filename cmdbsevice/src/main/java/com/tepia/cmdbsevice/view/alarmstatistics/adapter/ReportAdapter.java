package com.tepia.cmdbsevice.view.alarmstatistics.adapter;

import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.WarnBean;
import com.tepia.cmdbsevice.view.alarmstatistics.model.FlowModel;

import java.util.List;

/**
 * Author:xch
 * Date:2019/5/24
 * Description:
 */
public class ReportAdapter extends BaseMultiItemQuickAdapter<FlowModel, BaseViewHolder> {

    private String eventId;

    public ReportAdapter(List<FlowModel> dates, String eventId) {
        super(dates);
        this.eventId = eventId;
        //1-已派单 2-已反馈 3-已审核 4-已完结 10-待反馈 20-待审核
//        addItemType(1, R.layout.item_report_dispatched);
        addItemType(1, R.layout.item_report_to_review);
        addItemType(2, R.layout.item_report_feedbacked);
        addItemType(3, R.layout.item_report_reviewed);
        addItemType(4, R.layout.item_report_complete);
        addItemType(10, R.layout.item_report_complete);
        addItemType(20, R.layout.item_report_to_review);
    }

    @Override
    protected void convert(BaseViewHolder helper, FlowModel item) {
        switch (helper.getItemViewType()) {
            case 1:
                item.setEventId(this.eventId);
                helper.addOnClickListener(R.id.btn_back);
                helper.addOnClickListener(R.id.btn_query);
//                helper.setText(R.id.tv_createdTime, String.format("派单时间：%s", item.getCreatedTime()));
//                helper.setText(R.id.tv_resultDes, String.format("事件描述：%s", item.getResultDes()));
//                helper.setText(R.id.tv_stnm, String.format("站点名称：%s", item.getCreatedTime()));
                break;
            case 2:
                helper.setText(R.id.tv_createdTime, String.format("反馈时间：%s", item.getCreatedTime()));
                helper.setText(R.id.tv_resultDes, String.format("反馈描述：%s", item.getResultDes()));
                break;
            case 3:
                boolean isPass = item.getResultType() == 0;
                helper.setText(R.id.tv_createdTime, String.format("审核时间：%s", item.getCreatedTime()));
                helper.setText(R.id.tv_resultDes, String.format("审核意见：%s", item.getResultDes()));
                helper.setText(R.id.tv_result, item.getResult());
                helper.setText(R.id.tv_statue_title, item.getStepName());
                helper.setTextColor(R.id.tv_result, Color.parseColor(isPass ? "#62C08D" : "#F38461"));
                TextView title = helper.getView(R.id.tv_statue_title);
                title.setCompoundDrawablesWithIntrinsicBounds(isPass ? R.mipmap.check_transparent : R.mipmap.img_tuihui, 0, 0, 0);
                break;
            case 4:
                helper.setText(R.id.tv_statue_title, item.getStepName());
                break;
            case 10:
                TextView tv = helper.getView(R.id.tv_statue_title);
                tv.setText(item.getStepName());
                tv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.img_daifankui, 0, 0, 0);
                break;
            case 20:

                break;
        }
    }
}
