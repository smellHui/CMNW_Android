package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.adapter;

import android.util.Log;
import android.view.animation.OvershootInterpolator;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.WarnBean;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.ExpandableLayout;

/**
 * Author:xch
 * Date:2019/5/20
 * Description:
 */
public class ReportToLeadAdapter extends BaseQuickAdapter<WarnBean, BaseViewHolder> implements ExpandableLayout.OnExpansionUpdateListener {

    private int selectedItem = -1;
    private int pageType;

    public ReportToLeadAdapter(int pageType) {
        super(R.layout.item_report_to_lead);
        this.pageType = pageType;
    }

    @Override
    protected void convert(BaseViewHolder helper, WarnBean item) {
        helper.setIsRecyclable(false);
        boolean isSelected = helper.getAdapterPosition() == selectedItem;
//        ExpandableLayout expandableLayout = helper.getView(R.id.expandable_layout);
//        expandableLayout.setOnExpansionUpdateListener(this);
//        expandableLayout.setExpanded(isSelected, false);
        helper.addOnClickListener(R.id.rl_item);
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    @Override
    public void onExpansionUpdate(float expansionFraction, int state) {
        Log.d("ExpandableLayout", "State: " + state);
//        if (state == ExpandableLayout.State.EXPANDING) {
//            getRecyclerView().smoothScrollToPosition(selectedItem);
//        }
    }
}
