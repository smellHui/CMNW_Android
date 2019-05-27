package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.fragment;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.model.PageBean;
import com.tepia.base.mvp.BaseListFragment;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.EventManager;
import com.tepia.cmdbsevice.model.event.WarnBean;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.adapter.ReportToLeadAdapter;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.ExpandableLayout;

import java.util.List;

/**
 * Author:xch
 * Date:2019/5/17
 * Description: 群众上报fragment
 */
public class ReportToLeadFragment extends BaseListFragment<WarnBean> {
    private static final int UNSELECTED = -1;
    private int selectedItem = UNSELECTED;
    private List<String> vendorCodeArray, areaCodeArray;
    private String stationType;

    @Override
    protected void initRequestData() {
        listByFault();
    }

    @Override
    public BaseQuickAdapter getBaseQuickAdapter() {
        return new ReportToLeadAdapter(1);
    }

    /**
     * 【查询】实时督办-故障列表
     */
    private void listByFault() {
        EventManager.getInstance().listByFault("pageSize", 20, "pageIndex", getPage()
                , "stationType", stationType, "vendorCodeArray", vendorCodeArray, "areaCodeArray", areaCodeArray)
                .safeSubscribe(new LoadingSubject<PageBean<WarnBean>>() {

                    @Override
                    protected void _onNext(PageBean<WarnBean> baseCommonResponse) {
                        success(baseCommonResponse);
                    }

                    @Override
                    protected void _onError(String message) {
                        error();
                    }
                });
    }

    private ExpandableLayout expandableLayout;

    @Override
    public void setOnItemChildClickListener(BaseQuickAdapter adapter, View view, int position) {
//        int id = view.getId();
//        if (id == R.id.rl_item) {
//            expandableLayout = (ExpandableLayout) adapter.getViewByPosition(selectedItem, R.id.expandable_layout);
//            if (expandableLayout != null) {
//                expandableLayout.collapse();
//            }
//            if (position == selectedItem) {
//                selectedItem = UNSELECTED;
//            } else {
//                expandableLayout = (ExpandableLayout) adapter.getViewByPosition(position, R.id.expandable_layout);
//                expandableLayout.expand();
//                selectedItem = position;
//            }
//            ((ReportToLeadAdapter) adapter).setSelectedItem(selectedItem);
//        }
    }

}
