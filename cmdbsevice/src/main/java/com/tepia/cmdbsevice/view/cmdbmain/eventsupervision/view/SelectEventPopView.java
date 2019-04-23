package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lxj.xpopup.core.DrawerPopupView;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.AreaBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

public class SelectEventPopView extends DrawerPopupView {

    private TagFlowLayout comptyFlowLayout, cateFlowLayout;
    private List<AreaBean> areaBeans, vendorBeans;
    private LayoutInflater mInflater;

    public SelectEventPopView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        mInflater = LayoutInflater.from(getContext());
        comptyFlowLayout = findViewById(R.id.flowlayout_compty);
        cateFlowLayout = findViewById(R.id.flowlayout_cate);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.view_select_event_pop;
    }

    public void setAreaData(List<AreaBean> areaBeans) {
        cateFlowLayout.setAdapter(new TagAdapter<AreaBean>(areaBeans) {

            @Override
            public View getView(FlowLayout parent, int position, AreaBean areaBean) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_tv,
                        cateFlowLayout, false);
                tv.setText(areaBean.getName());
                return tv;
            }
        });
    }

    public void setVendorData(List<AreaBean> vendorBeans) {
        comptyFlowLayout.setAdapter(new TagAdapter<AreaBean>(vendorBeans) {

            @Override
            public View getView(FlowLayout parent, int position, AreaBean areaBean) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_tv,
                        cateFlowLayout, false);
                tv.setText(areaBean.getName());
                return tv;
            }
        });
    }
}
