package com.tepia.cmdbsevice.view.alarmstatistics.view;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.lxj.xpopup.core.DrawerPopupView;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.AreaBean;
import com.tepia.cmdbsevice.view.alarmstatistics.model.SelectParamModel;
import com.tepia.cmdbsevice.view.alarmstatistics.view.flowlayout.FlowLayout;
import com.tepia.cmdbsevice.view.alarmstatistics.view.flowlayout.TagAdapter;
import com.tepia.cmdbsevice.view.alarmstatistics.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.tepia.cmdbsevice.view.alarmstatistics.AlarmStatisticsTwoActivity.ALARM_SITE;
import static com.tepia.cmdbsevice.view.alarmstatistics.AlarmStatisticsTwoActivity.FAULT_SITE;
import static com.tepia.cmdbsevice.view.alarmstatistics.AlarmStatisticsTwoActivity.REPORT_SITE;

public class SelectEventPopView extends DrawerPopupView {

    private static final String[] DATE_TXT = new String[]{"本年", "本季", "本月"};
    private static final String[] STATE_POLICE_TXT = new String[]{"已派单", "已完结"};
    private static final String[] STATE_FAULT_TXT = new String[]{"已督办", "待反馈", "已完结"};
    private static final String[] STATE_REPORT_TXT = new String[]{"已派单", "待审核", "退回中", "已完结"};

    private String[] states;

    private TagFlowLayout dateFlowLayout, stateFlowLayout, comptyFlowLayout, townFlowLayout;
    private List<AreaBean> areaBeans, vendorBeans;
    private LayoutInflater mInflater;
    private Button queryBtn, reviseBtn;
    private CheckBox haulageCb, fanCb;
    private TextView allTownTv, allComptyTv;//全选
    private TextView startTimeTv, endTimeTv;

    private TagAdapter dateAdapter, stateAdapter, comptyAdapter, townAdapter;
    private List<String> areaNames, vendorNames;
    private String stationType;//站点类型（0-提升井，1-处理站）
    private int stateType;//工单类型 故障，报警，上报
    private String startDate = null, endDate = null;

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
        dateFlowLayout = findViewById(R.id.flowlayout_create_date);
        stateFlowLayout = findViewById(R.id.flowlayout_state);
        comptyFlowLayout = findViewById(R.id.flowlayout_compty);
        townFlowLayout = findViewById(R.id.flowlayout_cate);
        queryBtn = findViewById(R.id.btn_query);
        reviseBtn = findViewById(R.id.btn_revise);
        haulageCb = findViewById(R.id.cb_haulage);
        fanCb = findViewById(R.id.cb_fan);
        allTownTv = findViewById(R.id.btn_town_all);
        allComptyTv = findViewById(R.id.btn_compty_all);
        startTimeTv = findViewById(R.id.tv_start_time);
        endTimeTv = findViewById(R.id.tv_end_time);

        queryBtn.setOnClickListener(this::queryClick);
        reviseBtn.setOnClickListener(this::reviseClick);
        allTownTv.setOnClickListener(this::toggleClick);
        allComptyTv.setOnClickListener(this::toggleClick);
        startTimeTv.setOnClickListener(this::pickDate);
        endTimeTv.setOnClickListener(this::pickDate);

        dateAdapter = createTagAdapter(Arrays.asList(DATE_TXT));
        dateFlowLayout.setAdapter(dateAdapter);
        dateFlowLayout.setOnSelectListener(this::dateSelect);

        stateAdapter = createTagAdapter(Arrays.asList(states));
        stateFlowLayout.setAdapter(stateAdapter);

        comptyAdapter = createTagAdapter(vendorBeans);
        comptyFlowLayout.setAdapter(comptyAdapter);
        comptyFlowLayout.setOnSelectListener(this::comptySelect);

        townAdapter = createTagAdapter(areaBeans);
        townFlowLayout.setAdapter(townAdapter);
        townFlowLayout.setOnSelectListener(this::townSelect);
    }

    private void dateSelect(Set<Integer> integers) {
        if (!integers.isEmpty()) {
            startDateLong = 0L;
            endDateLong = 0L;
            startDate = null;
            endDate = null;
            startTimeTv.setText(null);
            endTimeTv.setText(null);
        }
    }

    private long startDateLong, endDateLong;

    private void pickDate(View view) {
        Calendar ca = Calendar.getInstance();
        int mYear = ca.get(Calendar.YEAR);
        int mMonth = ca.get(Calendar.MONTH);
        int mDay = ca.get(Calendar.DAY_OF_MONTH);
        int id = view.getId();
        if (id == R.id.tv_start_time) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT, (view1, year, month, dayOfMonth) -> {
                startDate = dealDateFormat(year, month, dayOfMonth);
                startDateLong = TimeFormatUtils.strToDate(startDate).getTime();
                startTimeTv.setText(startDate);
                dateFlowLayout.setCheckAll(true);
                dateFlowLayout.toggleCheckAll();
            }, mYear, mMonth, mDay);
            if (endDateLong != 0L) {
                DatePicker datePicker = datePickerDialog.getDatePicker();
                datePicker.setMaxDate(endDateLong);
            }
            datePickerDialog.show();
        }
        if (id == R.id.tv_end_time) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT, (view1, year, month, dayOfMonth) -> {
                endDate = dealDateFormat(year, month, dayOfMonth);
                endDateLong = TimeFormatUtils.strToDate(endDate).getTime();
                endTimeTv.setText(endDate);
                dateFlowLayout.setCheckAll(true);
                dateFlowLayout.toggleCheckAll();
            }, mYear, mMonth, mDay);
            if (startDateLong != 0L) {
                DatePicker datePicker = datePickerDialog.getDatePicker();
                datePicker.setMinDate(startDateLong);
            }
            datePickerDialog.show();
        }
    }

    private String dealDateFormat(int year, int month, int dayOfMonth) {
        String monthStr = month < 9 ? "0" + (month + 1) : (month + 1) + "";
        String dayStr = dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth + "";
        return String.format("%s-%s-%s", year, monthStr, dayStr);
    }

    /**
     * 监听乡镇全选，取消全选状态
     *
     * @param integers
     */
    private void townSelect(@NonNull Set<Integer> integers) {
        if (areaBeans == null) return;
        boolean isAll = integers.size() == areaBeans.size();
        townFlowLayout.setCheckAll(isAll);
        allTownTv.setSelected(isAll);
    }

    /**
     * 监听企业全选，取消全选状态
     *
     * @param integers
     */
    private void comptySelect(@NonNull Set<Integer> integers) {
        if (vendorBeans == null) return;
        boolean isAll = integers.size() == vendorBeans.size();
        comptyFlowLayout.setCheckAll(isAll);
        allComptyTv.setSelected(isAll);
    }

    /**
     * 乡镇或企业  全选，取消全选
     *
     * @param view
     */
    private void toggleClick(@NonNull View view) {
        int id = view.getId();
        if (id == R.id.btn_town_all) {
            townFlowLayout.toggleCheckAll();
        } else if (id == R.id.btn_compty_all) {
            comptyFlowLayout.toggleCheckAll();
        }
    }

    private <T> TagAdapter createTagAdapter(List<T> beans) {
        return new TagAdapter<T>(beans) {

            @Override
            public View getView(FlowLayout parent, int position, T bean) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_tv,
                        comptyFlowLayout, false);
                if (bean instanceof AreaBean) {
                    tv.setText(((AreaBean) bean).getName());
                }
                if (bean instanceof String) {
                    tv.setText((String) bean);
                }
                return tv;
            }
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void queryClick(View view) {

        //工单生成时间
        Set<Integer> startTimes = dateFlowLayout.getSelectedList();
        if (!startTimes.isEmpty()) {
            int dateIndex = startTimes.stream().findFirst().get();
            switch (dateIndex) {
                case 0://本年
                    startDate = TimeFormatUtils.getFirstDayOfYear();
                    endDate = TimeFormatUtils.getLastDayOfYear();
                    break;
                case 1://本季
                    startDate = String.format("%s 00:00:00", TimeFormatUtils.getThisQuarterStart());
                    endDate = String.format("%s 23:59:59", TimeFormatUtils.getThisQuarterEnd());
                    break;
                case 2://本月
                    startDate = TimeFormatUtils.getFirstDayOfMonth();
                    endDate = TimeFormatUtils.getLastDayOfMonth();
                    break;
            }
        }

        //工单状态
        String status = null;
        Set<Integer> statuses = stateFlowLayout.getSelectedList();
        if (!statuses.isEmpty()) {
            status = statuses.stream().map(position -> {
                String value = null;
                switch (stateType) {
                    case FAULT_SITE:
                        switch (position) {
                            case 0:
                                value = "3";
                                break;
                            case 1:
                                value = "4";
                                break;
                            case 2:
                                value = "5";
                                break;
                        }
                        break;
                    case ALARM_SITE:
                        switch (position) {
                            case 0:
                                value = "1";
                                break;
                            case 1:
                                value = "5";
                                break;
                        }
                        break;
                    case REPORT_SITE:
                        switch (position) {
                            case 0:
                                value = "1";
                                break;
                            case 1:
                                value = "2";
                                break;
                            case 2:
                                value = "3";
                                break;
                            case 3:
                                value = "4";
                                break;
                        }
                        break;
                }
                return value;
            }).findFirst().get();
        }

        //运维企业，行政区划选择
        if (areaNames == null) areaNames = new ArrayList<>();
        if (vendorNames == null) vendorNames = new ArrayList<>();
        transformData(areaNames, townFlowLayout, areaBeans);
        transformData(vendorNames, comptyFlowLayout, vendorBeans);

        //站点选择
        boolean isFanCheck = fanCb.isChecked();
        boolean isHaulageCheck = haulageCb.isChecked();
        if (isFanCheck) {
            stationType = "1";
        }
        if (isHaulageCheck) {
            stationType = "0";
        }
        //站点都选择 stateType就不用传
        if ((isFanCheck && isHaulageCheck) || !(isFanCheck || isHaulageCheck)) {
            stationType = null;
        }

        SelectParamModel selectParamModel = new SelectParamModel();
        selectParamModel.setStartDate(startDate);
        selectParamModel.setEndDate(endDate);
        selectParamModel.setStationType(stationType);
        selectParamModel.setStatus(status);
        selectParamModel.setVendorNames(vendorNames);
        selectParamModel.setAreaNames(areaNames);

        if (listener != null)
            listener.selectEvent(selectParamModel);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void transformData(@NonNull List<String> list, @NonNull TagFlowLayout tagFlowLayout, List<AreaBean> areaBeans) {
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

    /**
     * 设置工单状态
     *
     * @param stateType
     */
    public void setStateData(int stateType) {
        this.stateType = stateType;
        switch (stateType) {
            case FAULT_SITE:
                states = STATE_FAULT_TXT;
                break;
            case ALARM_SITE:
                states = STATE_POLICE_TXT;
                break;
            case REPORT_SITE:
                states = STATE_REPORT_TXT;
                break;
        }
    }

    public interface SelectEventListener {
        void selectEvent(SelectParamModel selectParamModel);
    }
}
