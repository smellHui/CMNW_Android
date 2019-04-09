package com.tepia.cmnwsevice.view.detail.operationdetail.orderexaminelist;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.cmnwsevice.model.order.ExamineBean;
import com.tepia.cmnwsevice.model.order.OrderManager;

import java.util.List;

/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/4/8 21:05
 * @修改人         ：
 * @修改时间       :       2019/4/8 21:05
 * @功能描述       :
 **/

public class OrderExamineListPresenter extends BasePresenterImpl<OrderExamineListContract.View> implements OrderExamineListContract.Presenter{

    public void getOrderExamineList(String orderId) {
        OrderManager.getInstance().getOrderExamineList(orderId).safeSubscribe(new LoadingSubject<BaseCommonResponse<List<ExamineBean>>>(true,"正在加载审核记录...") {
            @Override
            protected void _onNext(BaseCommonResponse<List<ExamineBean>> baseCommonResponse) {
                mView.getOrderExamineListSuccess(baseCommonResponse.getData());
            }

            @Override
            protected void _onError(String message) {

            }
        });
    }
}
