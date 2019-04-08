package com.tepia.cmnwsevice.view.detail.operationdetail.operationdetail;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.cmnwsevice.model.order.OperationBean;
import com.tepia.cmnwsevice.model.order.OrderManager;

/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/4/8 18:06
 * @修改人         ：      
 * @修改时间       :       2019/4/8 18:06
 * @功能描述       :
 **/

public class OptationDetailPresenter extends BasePresenterImpl<OprationDetailContract.View> implements OprationDetailContract.Presenter{

    public void getOrderOprationDetail(String orderId) {
        OrderManager.getInstance().getOrderOperationDetail(orderId).safeSubscribe(new LoadingSubject<BaseCommonResponse<OperationBean>>() {
            @Override
            protected void _onNext(BaseCommonResponse<OperationBean> baseCommonResponse) {
                mView.getOrderOprationDetailSuccess(baseCommonResponse.getData());
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
       /* OrderManager.getInstance().sendOrder("id","1114816161280811009","executeId").safeSubscribe(new LoadingSubject<BaseCommonResponse>() {
            @Override
            protected void _onNext(BaseCommonResponse baseCommonResponse) {
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
        OrderManager.getInstance().getOrderUserList(orderId).safeSubscribe(new LoadingSubject<BaseCommonResponse>() {
            @Override
            protected void _onNext(BaseCommonResponse baseCommonResponse) {
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });*/
    }
}
