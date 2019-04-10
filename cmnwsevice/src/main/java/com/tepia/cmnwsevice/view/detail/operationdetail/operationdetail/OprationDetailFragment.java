package com.tepia.cmnwsevice.view.detail.operationdetail.operationdetail;


import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.databinding.FragmentOprationDetailBinding;
import com.tepia.cmnwsevice.model.dict.DictManager;
import com.tepia.cmnwsevice.model.order.FileBean;
import com.tepia.cmnwsevice.model.order.OperationBean;
import com.tepia.cmnwsevice.view.common.AdapterPhotoShow;
import com.tepia.photo_picker.PhotoPreview;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/8 17:34
 * @修改人 ：
 * @修改时间 :       2019/4/8 17:34
 * @功能描述 :      运维详情
 **/
@Route(path = "")
public class OprationDetailFragment extends MVPBaseFragment<OprationDetailContract.View, OptationDetailPresenter> implements OprationDetailContract.View {

    public String orderId;
    private FragmentOprationDetailBinding mBinding;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_opration_detail;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        mBinding = DataBindingUtil.bind(view);
    }

    @Override
    protected void initRequestData() {
        mPresenter.getOrderOprationDetail(orderId);
    }

    @Override
    public void getOrderOprationDetailSuccess(OperationBean data) {
        refreshView(data);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void refreshView(OperationBean data) {
        mBinding.tvWorkerName.setText(data.getExecuteUserName());
        mBinding.tvWorkerPhone.setText(data.getExecuteUserPhone());
        mBinding.tvStartTime.setText(data.getExecuteStartTime());
        mBinding.tvEndTime.setText(data.getExecuteEndTime());
        mBinding.tvDelayHours.setText(data.getDelayHours() + "小时");
        mBinding.tvHandlerTime.setText(data.getHandleHours() + "小时");
        if (Float.parseFloat(data.getOverHours()) > 0) {
            mBinding.tvOverTime.setText("是 （超时 " + data.getOverHours() + "小时)");
        } else {
            mBinding.tvOverTime.setText("否");
        }
        mBinding.tvRepairType.setText(DictManager.getInstance().getDictMap().get("repairType").get(data.getRepairType()));
        mBinding.tvProblemType.setText(DictManager.getInstance().getDictMap().get("problemType").get(data.getProblemType()));
        if ("1".equals(data.getPartsUse())) {
            mBinding.tvPartsUse.setText("已使用");
        } else {
            mBinding.tvPartsUse.setText("未使用");
        }
        mBinding.tvHandleDes.setText(data.getHandleDes());
        try {
            List<FileBean> fileList = data.getFileList();
            Map<String, List<FileBean>> map = fileList.stream().collect(Collectors.groupingBy(FileBean::getBizType));
            {
                List<FileBean> templist = map.get("start");
                ArrayList<String> list = new ArrayList<>();
                for (FileBean temp : templist) {
                    list.add(temp.getSrc());
                }
                mBinding.rvPhotos.setLayoutManager(new GridLayoutManager(getContext(), 4));
                AdapterPhotoShow adapterPhotoShow = new AdapterPhotoShow(getContext(), R.layout.item_photos, list);
                adapterPhotoShow.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


                        PhotoPreview.builder()
                                .setPhotos(list)
                                .setShowDeleteButton(false)
                                .setCurrentItem(position)
                                .start(getBaseActivity());

                    }
                });
                mBinding.rvPhotos.setAdapter(adapterPhotoShow);
            }
            {
                List<FileBean> templist = map.get("end");
                ArrayList<String> list = new ArrayList<>();
                for (FileBean temp : templist) {
                    list.add(temp.getSrc());
                }
                mBinding.rvAfterPhotos.setLayoutManager(new GridLayoutManager(getContext(), 4));
                AdapterPhotoShow adapterPhotoShow = new AdapterPhotoShow(getContext(), R.layout.item_photos, list);
                adapterPhotoShow.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        PhotoPreview.builder()
                                .setPhotos(list)
                                .setShowDeleteButton(false)
                                .setCurrentItem(position)
                                .start(getBaseActivity());

                    }
                });
                mBinding.rvAfterPhotos.setAdapter(adapterPhotoShow);
            }
        } catch (Exception e) {
        }

    }
}
