package com.tepia.cmdbsevice.view.alarmstatistics.view;

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
import com.tepia.cmdbsevice.view.alarmstatistics.view.flowlayout.FlowLayout;
import com.tepia.cmdbsevice.view.alarmstatistics.view.flowlayout.TagAdapter;
import com.tepia.cmdbsevice.view.alarmstatistics.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SelectEventPopView extends DrawerPopupView {

    private static final String[] BTN_TXT = new String[]{"取消全选", "全选"};

    private TagFlowLayout comptyFlowLayout, townFlowLayout;
    private List<AreaBean> areaBeans, vendorBeans;
    private LayoutInflater mInflater;
    private Button queryBtn, reviseBtn;
    private CheckBox haulageCb, fanCb;
    private TextView allTownTv, allComptyTv;//全选

    private TagAdapter comptyAdapter, townAdapter;
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
        townFlowLayout = findViewById(R.id.flowlayout_cate);
        queryBtn = findViewById(R.id.btn_query);
        reviseBtn = findViewById(R.id.btn_revise);
        haulageCb = findViewById(R.id.cb_haulage);
        fanCb = findViewById(R.id.cb_fan);
        allTownTv = findViewById(R.id.btn_town_all);
        allComptyTv = findViewById(R.id.btn_compty_all);

        queryBtn.setOnClickListener(this::queryClick);
        reviseBtn.setOnClickListener(this::reviseClick);
        allTownTv.setOnClickListener(this::allClick);
        allComptyTv.setOnClickListener(this::allClick);

        comptyAdapter = createTagAdapter(vendorBeans);
        comptyFlowLayout.setAdapter(comptyAdapter);
        comptyFlowLayout.setOnSelectListener(this::comptySelect);

        townAdapter = createTagAdapter(areaBeans);
        townFlowLayout.setAdapter(townAdapter);
        townFlowLayout.setOnSelectListener(this::townSelect);
    }

    /**
     * 监听乡镇全选，取消全选状态
     *
     * @param integers
     */
    private void townSelect(Set<Integer> integers) {
        boolean isAll = integers.size() == areaBeans.size();
        townFlowLayout.setCheckAll(isAll);
        allTownTv.setText(isAll ? BTN_TXT[0] : BTN_TXT[1]);
    }

    /**
     * 监听企业全选，取消全选状态
     *
     * @param integers
     */
    private void comptySelect(Set<Integer> integers) {
        boolean isAll = integers.size() == vendorBeans.size();
        comptyFlowLayout.setCheckAll(isAll);
        allComptyTv.setText(isAll ? BTN_TXT[0] : BTN_TXT[1]);
    }

    /**
     * 乡镇或企业  全选，取消全选
     *
     * @param view
     */
    private void allClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_town_all) {
            townFlowLayout.toggleCheckAll();
        } else if (id == R.id.btn_compty_all) {
            comptyFlowLayout.toggleCheckAll();
        }
    }

    private TagAdapter createTagAdapter(List<AreaBean> areaBeans) {
        return new TagAdapter<AreaBean>(areaBeans) {

            @Override
            public View getView(FlowLayout parent, int position, AreaBean areaBean) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_tv,
                        comptyFlowLayout, false);
                tv.setText(areaBean.getName());
                return tv;
            }
        };
    }

    private void queryClick(View view) {
        if (areaNames == null) areaNames = new ArrayList<>();
        if (vendorNames == null) vendorNames = new ArrayList<>();

        transformData(areaNames, townFlowLayout, areaBeans);
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
