package com.tepia.cmnwsevice.view.detail.edit;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.adapter.OrderPhotoAdapter;
import com.tepia.cmnwsevice.databinding.ConfirmSubmitDataBindView;
import com.tepia.cmnwsevice.manager.UiHelper;
import com.tepia.cmnwsevice.model.event.CompleteCallbackEvent;
import com.tepia.cmnwsevice.model.order.FileBean;
import com.tepia.cmnwsevice.model.order.OrderManager;
import com.tepia.cmnwsevice.model.order.OrderParamBean;
import com.tepia.cmnwsevice.model.order.WorkDetailBean;
import com.tepia.cmnwsevice.view.common.PhotoRecycleViewAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:确认提交页面
 */
public class ConfirmSubmitActivity extends BaseActivity implements View.OnClickListener {

    private ConfirmSubmitDataBindView mView;
    private OrderParamBean orderParamBean;
    private ArrayList<FileBean> fileList;

    private OrderPhotoAdapter photoAdapter, afterAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_submit;
    }

    @Override
    public void initView() {
        mView = DataBindingUtil.bind(mRootView);
        mView.setOnClickListener(this);
        mView.setOrderParamBean(orderParamBean);
        setCenterTitle("2019-3-31卫星村1号站");
        showBack();

        setRightTextEvent("工单详情", v -> UiHelper.goToOrderDetailView(this, orderParamBean.getId()));

        photoAdapter = new OrderPhotoAdapter(orderParamBean.getBeforPhotos());
        mView.rvBefor.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        mView.rvBefor.setAdapter(photoAdapter);

        afterAdapter = new OrderPhotoAdapter(orderParamBean.getAfterPhotos());
        mView.rvAfter.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        mView.rvAfter.setAdapter(afterAdapter);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        if (intent != null)
            orderParamBean = (OrderParamBean) intent.getSerializableExtra("orderParamBean");

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {
        OrderManager.getInstance().getOrderWorkingDetail(orderParamBean.getId())
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

    public void submit() {
//        UiHelper.goToFeedBackSucView(this);
        System.out.println("orderParamBean-->" + orderParamBean.toString());
        ArrayList<String> fileList = new ArrayList<>();
        fileList.addAll(orderParamBean.getBeforPhotos());
        fileList.addAll(orderParamBean.getAfterPhotos());
        if (!CollectionsUtil.isEmpty(fileList)) {
            this.fileList = new ArrayList<>();
            uploadFile(fileList, 0);
        } else {
            doneOrder(null);
        }
    }

    public void doneOrder(ArrayList<FileBean> fileList) {
        OrderManager.getInstance().doneOrder("id", orderParamBean.getId(), "handleDes", orderParamBean.getHandleDes()
                , "repairType", orderParamBean.getRepairType(), "problemType", orderParamBean.getProblemType(),
                "partsUse", orderParamBean.getPartsUse(), "fileList", fileList)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse>(true, "正在上传处理结果") {
                    @Override
                    protected void _onNext(BaseCommonResponse baseCommonResponse) {
                        UiHelper.goToFeedBackSucView(ConfirmSubmitActivity.this);
                        ConfirmSubmitActivity.this.finish();
                        EventBus.getDefault().post(new CompleteCallbackEvent());
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtils.shortToast(message);
                    }
                });
    }

    public void uploadFile(List<String> paths, int count) {
        OrderManager.getInstance().uploadFile(paths.get(count)).safeSubscribe(new LoadingSubject<BaseCommonResponse<String>>(true, "正在上传第" + (count + 1) + "张照片") {
            @Override
            protected void _onNext(BaseCommonResponse<String> baseCommonResponse) {
                FileBean fileBean = new FileBean();
                fileBean.setSrc(baseCommonResponse.getData());
                if (!CollectionsUtil.isEmpty(orderParamBean.getBeforPhotos())) {
                    if (count < orderParamBean.getBeforPhotos().size()) {
                        fileBean.setBizType("start");
                    } else {
                        fileBean.setBizType("end");
                    }
                } else {
                    fileBean.setBizType("end");
                }
                fileBean.setFileType("png");
                fileList.add(fileBean);
                if (count + 1 < paths.size()) {
                    uploadFile(paths, count + 1);
                } else {
                    doneOrder(fileList);
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_revise) {
            finish();
        }
        if (id == R.id.btn_submit) {
            submit();
        }
    }
}
