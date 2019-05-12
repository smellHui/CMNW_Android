package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lxj.xpopup.core.DrawerPopupView;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.AreaBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SelectEventPopView extends DrawerPopupView {

    private TagFlowLayout comptyFlowLayout, cateFlowLayout;
    private List<AreaBean> areaBeans, vendorBeans;
    private LayoutInflater mInflater;
    private Button queryBtn, reviseBtn;
    private CheckBox haulageCb, fanCb;

    private List<String> areaNames, vendorNames;
    private String stationType;//站点类型（0-提升井，1-处理站）

    private SelectEventListener listener;

    public void setListener(SelectEventListener listener) {
        this.listener = listener;
    }

    public SelectEventPopView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        mInflater = LayoutInflater.from(getContext());
        comptyFlowLayout = findViewById(R.id.flowlayout_compty);
        cateFlowLayout = findViewById(R.id.flowlayout_cate);
        queryBtn = findViewById(R.id.btn_query);
        reviseBtn = findViewById(R.id.btn_revise);
        haulageCb = findViewById(R.id.cb_haulage);
        fanCb = findViewById(R.id.cb_fan);

        queryBtn.setOnClickListener(this::queryClick);
        reviseBtn.setOnClickListener(this::reviseClick);

        cateFlowLayout.setAdapter(new TagAdapter<AreaBean>(areaBeans) {

            @Override
            public View getView(FlowLayout parent, int position, AreaBean areaBean) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_tv,
                        cateFlowLayout, false);
                tv.setText(areaBean.getName());
                return tv;
            }
        });

        comptyFlowLayout.setAdapter(new TagAdapter<AreaBean>(vendorBeans) {

            @Override
            public View getView(FlowLayout parent, int position, AreaBean areaBean) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_tv,
                        comptyFlowLayout, false);
                tv.setText(areaBean.getName());
                return tv;
            }
        });
    }

    private void queryClick(View view) {
        if (areaNames == null) areaNames = new ArrayList<>();
        if (vendorNames == null) vendorNames = new ArrayList<>();

        transformData(areaNames, cateFlowLayout, areaBeans);
        transformData(vendorNames, comptyFlowLayout, vendorBeans);

        if (fanCb.isChecked()) {
            stationType = "1";
        }
        if (haulageCb.isChecked()) {
            stationType = "0";
        }
        if (fanCb.isChecked() && haulageCb.isChecked() || (!fanCb.isChecked() && !haulageCb.isChecked())) {
            stationType = null;
        }

        if (listener != null)
            listener.selectEvent(areaNames, vendorNames, stationType);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void transformData(List<String> list, TagFlowLayout tagFlowLayout, List<AreaBean> areaBeans) {
        list.clear();
        Set<Integer> areaIndexs = tagFlowLayout.getSelectedList();
        areaIndexs.forEach((i) -> list.add(areaBeans.get(i).getCode()));
    }

    private void reviseClick(View view) {

    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.view_select_event_pop;
    }


    public void setAreaData(List<AreaBean> areaBeans) {
        this.areaBeans = areaBeans;
    }

    public void setVendorData(List<AreaBean> vendorBeans) {
        this.vendorBeans = vendorBeans;
    }

    public interface SelectEventListener {
        void selectEvent(List<String> areaNames, List<String> vendorNames, String stationType);
    }
}
