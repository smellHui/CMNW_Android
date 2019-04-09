package com.tepia.cmnwsevice.view.detail.edit;

import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.databinding.FillInDataBindView;
import com.tepia.cmnwsevice.manager.UiHelper;
import com.tepia.cmnwsevice.model.dict.DictManager;
import com.tepia.cmnwsevice.model.order.OrderManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:工单填报
 */
public class FillInActivity extends BaseActivity implements View.OnClickListener {

    private String orderId;

    private FillInDataBindView mView;
    private OptionsPickerView problemPickerView, repairPickerView, partsUsesPickerView;

    private Map<String, Map<String, String>> dict;
    private List<String> problemTypes, repairTypes, partsUses;
    private String handleDes, repairType, problemType, partsUse;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fill_in;
    }

    @Override
    public void initView() {
        mView = DataBindingUtil.bind(mRootView);
        mView.setOnClickListener(this);
        setCenterTitle("工单填报");
        showBack();
    }

    @Override
    public void initData() {
        dict = DictManager.getInstance().getDictMap();
        if (dict != null) {
            problemTypes = forMap(dict.get("source"));
            repairTypes = forMap(dict.get("repairType"));
        }
        partsUses = new ArrayList<>();
        partsUses.add("未使用");
        partsUses.add("使用");
    }

    @Override
    protected void initListener() {
        problemPickerView = initNoLinkOptionsPicker(problemTypes, (options1, options2, options3, v) -> {
            mView.tvProblemType.setText(problemTypes.get(options1));
            problemType = options1 + "";
        });
        repairPickerView = initNoLinkOptionsPicker(repairTypes, (options1, options2, options3, v) -> {
            mView.tvRepairType.setText(repairTypes.get(options1));
            repairType = options1 + "";
        });
        partsUsesPickerView = initNoLinkOptionsPicker(partsUses, (options1, options2, options3, v) -> {
            mView.tvPartsUse.setText(partsUses.get(options1));
            partsUse = options1 + "";
        });
    }

    @Override
    protected void initRequestData() {

    }

    public void confirmSubmit(View view) {
        OrderManager.getInstance().doneOrder("id", orderId, "handleDes", handleDes
                , "repairType", repairType, "problemType", problemType, "partsUse", partsUse)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse>() {
                    @Override
                    protected void _onNext(BaseCommonResponse baseCommonResponse) {
                        UiHelper.goToConfirmSubmitView(FillInActivity.this);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });

    }

    private List<String> forMap(@Nullable Map<String, String> map) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }


    private OptionsPickerView initNoLinkOptionsPicker(List<String> list, OnOptionsSelectListener listener) {// 不联动的多级选项
        OptionsPickerView pvNoLinkOptions = new OptionsPickerBuilder(this, listener)
                .setCancelColor(0xFF4FB97E)
                .setSubmitColor(0xFF4FB97E)
                .build();
        pvNoLinkOptions.setNPicker(list, null, null);
        pvNoLinkOptions.setSelectOptions(0, 1, 1);
        return pvNoLinkOptions;
    }

    @Override
    public void onClick(View v) {

        int i = v.getId();
        if (i == R.id.tv_problemType) {
            problemPickerView.show();
        }
        if (i == R.id.tv_repairType) {
            repairPickerView.show();
        }
        if (i == R.id.tv_partsUse) {
            partsUsesPickerView.show();
        }
    }
}
