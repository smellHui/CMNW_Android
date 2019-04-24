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
import android.widget.TextView;

import com.codbking.widget.DatePicker;

import com.codbking.widget.OnChangeLisener;
import com.codbking.widget.bean.DateType;
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
    private int cate = -1;//0,1,2,3  对应  "本年", "本季", "本月", "其他"
    //开始时间
    private Date startDate = new Date();
    //年分限制，默认上下5年
    private int yearLimt = 5;

    private LayoutInflater mInflater;

    private TagFlowLayout flowlayout;
    private FrameLayout wheelLayout;
    private DatePicker mDatePicker;
    private RadioButton rbStart, rbEnd;
    private ImageView img_intercept;

    private onDataSelectPickListener listener;

    public void setListener(onDataSelectPickListener listener) {
        this.listener = listener;
    }

    public SelectDataView(@NonNull Context context) {
        super(context);
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
        img_intercept = findViewById(R.id.img_intercept);
        img_intercept.setOnClickListener((v) -> {
        });
        rbStart = findViewById(R.id.rb_1);
        rbEnd = findViewById(R.id.rb_2);
        setNoEnable();
        wheelLayout = findViewById(com.codbking.widget.R.id.wheelLayout);
        flowlayout = findViewById(R.id.flowlayout);
        flowlayout.setOnTagClickListener((view, position, parent) -> {
            cate = position;
            if (position == 3) {
                rbStart.setEnabled(true);
                rbEnd.setEnabled(true);
                img_intercept.setVisibility(GONE);
            } else {
                setNoEnable();
                img_intercept.setVisibility(VISIBLE);
            }
            return true;
        });
        flowlayout.setAdapter(new TagAdapter<String>(Arrays.asList(dataCates)) {

            @Override
            public View getView(FlowLayout parent, int position, String str) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_datecate_tv,
                        flowlayout, false);
                tv.setText(str);
                return tv;
            }
        });

        mDatePicker = getDatePicker();
        this.wheelLayout.addView(mDatePicker);

        findViewById(R.id.btn_query).setOnClickListener(v -> {
            switch (cate) {
                case 0://本年
                    listener.onDataSelectPickListener(TimeFormatUtils.getFirstDayOfYear(), TimeFormatUtils.getLastDayOfYear());
                    break;
                case 1://本季
                    listener.onDataSelectPickListener(TimeFormatUtils.getFirstDayOfWeek(), TimeFormatUtils.getLastDayOfWeek());
                    break;
                case 2://本月
                    listener.onDataSelectPickListener(TimeFormatUtils.getFirstDayOfMonth(), TimeFormatUtils.getLastDayOfMonth());
                    break;
                case 3://其他
                    listener.onDataSelectPickListener(startTime, endTime);
                    break;
            }
            dismiss();
        });
    }

    private void setNoEnable() {
        rbStart.setEnabled(false);
        rbEnd.setEnabled(false);
        rbStart.setChecked(false);
        rbEnd.setChecked(false);
    }

    private DatePicker getDatePicker() {
        DatePicker picker = new DatePicker(getContext(), DateType.TYPE_YMD);
        picker.setStartDate(startDate);
        picker.setYearLimt(yearLimt);
        picker.setOnChangeLisener(this);
        picker.init();
        return picker;
    }

    private String startTime, endTime, time;

    @Override
    public void onChanged(Date date) {
        time = TimeFormatUtils.dateToStr(date);
        if (rbStart.isChecked()) {
            startTime = time;
            rbStart.setText(time);
        }
        if (rbEnd.isChecked()) {
            endTime = time;
            rbEnd.setText(time);
        }
    }

    public interface onDataSelectPickListener {
        void onDataSelectPickListener(String startTime, String endTime);
    }
}
