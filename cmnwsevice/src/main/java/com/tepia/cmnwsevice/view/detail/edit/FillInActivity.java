package com.tepia.cmnwsevice.view.detail.edit;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.databinding.FillInDataBindView;
import com.tepia.cmnwsevice.manager.UiHelper;
import com.tepia.cmnwsevice.model.dict.DictManager;
import com.tepia.cmnwsevice.model.event.CompleteCallbackEvent;
import com.tepia.cmnwsevice.model.order.OrderManager;
import com.tepia.cmnwsevice.model.order.OrderParamBean;
import com.tepia.cmnwsevice.model.order.WorkDetailBean;
import com.tepia.cmnwsevice.view.common.PhotoRecycleViewAdapter;
import com.tepia.cmnwsevice.view.common.RecyclerItemClickListener;
import com.tepia.photo_picker.PhotoPicker;
import com.tepia.photo_picker.PhotoPreview;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

    private static final int BEROR_PHOTOS = 0;//处理前标识
    private static final int AFTER_PHOTOS = 1;//处理后标识

    private int photoTag;

    private PhotoRecycleViewAdapter photoAdapter, photoAfterAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private ArrayList<String> selectedAfterPhotos = new ArrayList<>();
    private String orderId;

    private FillInDataBindView mView;
    private OptionsPickerView problemPickerView, repairPickerView, partsUsesPickerView;

    private Map<String, Map<String, String>> dict;
    private List<String> problemTypes, repairTypes, partsUses;
    private String handleDes, repairType, problemType, partsUse;
    private OrderParamBean orderParamBean;

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
        initPhotoView();
    }

    private void initPhotoView() {

        photoAdapter = new PhotoRecycleViewAdapter(getContext(), selectedPhotos);
        mView.rvPhotos.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        mView.rvPhotos.setAdapter(photoAdapter);
        mView.rvPhotos.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), (view, position) -> {
            photoTag = BEROR_PHOTOS;
            openPhotoPicker(photoAdapter, selectedPhotos, position);
        }));

        photoAfterAdapter = new PhotoRecycleViewAdapter(getContext(), selectedAfterPhotos);
        mView.rvAfterPhotos.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        mView.rvAfterPhotos.setAdapter(photoAfterAdapter);
        mView.rvAfterPhotos.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), (view, position) -> {
            photoTag = AFTER_PHOTOS;
            openPhotoPicker(photoAfterAdapter, selectedAfterPhotos, position);
        }));
    }

    private void openPhotoPicker(PhotoRecycleViewAdapter adapter, ArrayList<String> photos, int position) {
        if (adapter.getItemViewType(position) == PhotoRecycleViewAdapter.TYPE_ADD) {
            PhotoPicker.builder()
                    .setPhotoCount(4)
                    .setShowCamera(true)
                    .setPreviewEnabled(true)
                    .setSelected(photos)
                    .start((Activity) getContext());
        } else {
            PhotoPreview.builder()
                    .setPhotos(photos)
                    .setCurrentItem(position)
                    .start((Activity) getContext());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (resultCode == RESULT_OK && (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {
            List<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            boolean isBefor = photoTag == BEROR_PHOTOS;
            if (photos != null) {
                ArrayList<String> photoList = isBefor ? selectedPhotos : selectedAfterPhotos;
                photoList.clear();
                photoList.addAll(photos);
            }
            PhotoRecycleViewAdapter adapter = isBefor ? photoAdapter : photoAfterAdapter;
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        if (intent != null)
            orderId = intent.getStringExtra("orderId");

        dict = DictManager.getInstance().getDictMap();
        if (dict != null) {
            problemTypes = forMap(dict.get("source"));
            repairTypes = forMap(dict.get("repairType"));
        }
        partsUses = new ArrayList<>();
        partsUses.add("未使用");
        partsUses.add("使用");

        orderParamBean = new OrderParamBean();
        orderParamBean.setId(orderId);
    }

    @Override
    protected void initListener() {
        problemPickerView = initNoLinkOptionsPicker(problemTypes, (options1, options2, options3, v) -> {
            mView.tvProblemType.setText(problemTypes.get(options1));
            problemType = options1 + "";
            orderParamBean.setProblemType(problemType);
            orderParamBean.setProblemName(problemTypes.get(options1));
        });
        repairPickerView = initNoLinkOptionsPicker(repairTypes, (options1, options2, options3, v) -> {
            mView.tvRepairType.setText(repairTypes.get(options1));
            repairType = options1 + "";
            orderParamBean.setRepairType(repairType);
            orderParamBean.setRepairName(repairTypes.get(options1));
        });
        partsUsesPickerView = initNoLinkOptionsPicker(partsUses, (options1, options2, options3, v) -> {
            mView.tvPartsUse.setText(partsUses.get(options1));
            partsUse = options1 + "";
            orderParamBean.setPartsUse(partsUse);
            orderParamBean.setPartsUseName(partsUses.get(options1));
        });

        setRightTextEvent("工单详情", v -> UiHelper.goToOrderDetailView(this, orderId));
    }

    @Override
    protected void initRequestData() {
        OrderManager.getInstance().getOrderWorkingDetail(orderId)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<WorkDetailBean>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<WorkDetailBean> workDetail) {
                        if (workDetail == null) return;
                        WorkDetailBean workDetailBean = workDetail.getData();
                        if (workDetailBean == null) return;


                        long timeInMillis = System.currentTimeMillis() - TimeFormatUtils.getTimeInMillis(workDetailBean.getSendTime());
                        long timeinHour = timeInMillis / 3600 / 1000;
                        String temp = String.format("%.0f", Float.parseFloat(workDetailBean.getLimitHours()) - timeinHour);

                        if (Float.parseFloat(workDetailBean.getLimitHours()) - timeinHour > 0) {
                            mView.cbTipBar.setCurValuesStr(temp);
                        } else {
                            mView.cbTipBar.setCurValuesStr(temp);
                        }
                        int tempint = (int) (Float.parseFloat(temp) * 100 / Float.parseFloat(workDetailBean.getLimitHours()));
                        mView.cbTipBar.setCurrentValues(tempint);

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
        if (i == R.id.btn_query) {
            orderParamBean.setHandleDes(mView.etHandleDes.getText().toString().trim());
            orderParamBean.setBeforPhotos(selectedPhotos);
            orderParamBean.setAfterPhotos(selectedAfterPhotos);
            UiHelper.goToConfirmSubmitView(FillInActivity.this, orderParamBean);
        }
        if (i == R.id.btn_cancel) {
            finish();
        }
    }

    @Subscribe
    public void CompleteCallbackEvent(CompleteCallbackEvent event) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
