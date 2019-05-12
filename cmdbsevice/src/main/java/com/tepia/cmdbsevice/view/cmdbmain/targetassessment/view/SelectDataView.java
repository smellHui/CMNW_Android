package com.tepia.cmdbsevice.view.cmdbmain.targetassessment.view;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.codbking.widget.DatePicker;

import com.codbking.widget.OnChangeLisener;
import com.codbking.widget.bean.DateType;
import com.google.common.base.Strings;
import com.lxj.xpopup.core.BasePopupView;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.AreaBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Arrays;
import java.util.Date;

/**
 * Author:xch
 * Date:2019/4/24
 * Description:
 */
public class SelectDataView extends BasePopupView implements OnChangeLisener {

    private static String[] dataCates = new String[]{"本年", "本季", "本月", "其他"};
    private volatile int cate = -1;//0,1,2,3  对应  "本年", "本季", "本月", "其他"
    //开始时间
    private Date startDate = new Date();
    //年分限制，默认上下5年
    private int yearLimt = 5;

    private LayoutInflater mInflater;

    private RadioGroup radioGroup;
    private FrameLayout wheelLayout;
    private DatePicker mDatePicker;
    private RadioButton rbStart, rbEnd;
    private ImageView img_intercept;
    private ImageView img_close;

    private TagAdapter tagAdapter;

    private onDataSelectPickListener listener;

    public SelectDataView(@NonNull Context context, onDataSelectPickListener listener) {
        super(context);
        this.listener = listener;
    }

    public SelectDataView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectDataView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getPopupLayoutId() {
        return R.layout.view_select_data;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        mInflater = LayoutInflater.from(getContext());
        img_close = findViewById(R.id.img_close);
        img_intercept = findViewById(R.id.img_intercept);
        img_intercept.setOnClickListener((v) -> {
        });
        rbStart = findViewById(R.id.rb_1);
        rbEnd = findViewById(R.id.rb_2);
        wheelLayout = findViewById(com.codbking.widget.R.id.wheelLayout);
        radioGroup = findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.btn_0) {
                cate = 0;
                img_intercept.setVisibility(VISIBLE);
            } else if (checkedId == R.id.btn_1) {
                cate = 1;
                img_intercept.setVisibility(VISIBLE);
            } else if (checkedId == R.id.btn_2) {
                cate = 2;
                img_intercept.setVisibility(VISIBLE);
            } else if (checkedId == R.id.btn_3) {
                cate = 3;
                rbStart.setChecked(true);
                img_intercept.setVisibility(GONE);
            }
        });

        mDatePicker = getDatePicker();
        this.wheelLayout.addView(mDatePicker);

        findViewById(R.id.btn_query).setOnClickListener(v -> {
            switch (cate) {
                case 0://本年
                    listener.onDataSelectPickListener(TimeFormatUtils.getFirstDayOfYear(), TimeFormatUtils.getLastDayOfYear(), 0);
                    break;
                case 1://本季
                    listener.onDataSelectPickListener(TimeFormatUtils.getThisQuarterStart(), TimeFormatUtils.getThisQuarterEnd(), 1);
                    break;
                case 2://本月
                    listener.onDataSelectPickListener(TimeFormatUtils.getFirstDayOfMonth(), TimeFormatUtils.getLastDayOfMonth(), 2);
                    break;
                case 3://其他
                    if (Strings.isNullOrEmpty(startTime)) {
                        ToastUtils.shortToast("请选择开始日期");
                        return;
                    }
                    if (Strings.isNullOrEmpty(endTime)) {
                        ToastUtils.shortToast("请选择结束日期");
                        return;
                    }
                    if (mStartDate.compareTo(mEndDate) > 0) {
                        ToastUtils.shortToast("开始日期不能大于结束日期");
                        return;
                    }
                    listener.onDataSelectPickListener(startTime, endTime, 3);
                    break;
            }
            dismiss();
        });
        img_close.setOnClickListener((v) -> dismiss());
    }


    private DatePicker getDatePicker() {
        DatePicker picker = new DatePicker(getContext(), DateType.TYPE_YMD);
        picker.setStartDate(startDate);
        picker.setYearLimt(yearLimt);
        picker.setOnChangeLisener(this);
        picker.init();
        return picker;
    }

    private String startTime, endTime;
    private Date mStartDate, mEndDate;

    @Override
    public void onChanged(Date date) {
        if (rbStart.isChecked()) {
            mStartDate = date;
            startTime = TimeFormatUtils.dateToStr(date);
            rbStart.setText(startTime);
        }
        if (rbEnd.isChecked()) {
            mEndDate = date;
            endTime = TimeFormatUtils.dateToStr(date);
            rbEnd.setText(endTime);
        }
    }

    public interface onDataSelectPickListener {
        void onDataSelectPickListener(String startTime, String endTime, int cate);
    }
}
