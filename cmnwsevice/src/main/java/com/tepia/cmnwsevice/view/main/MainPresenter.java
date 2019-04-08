package com.tepia.cmnwsevice.view.main;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.cmnwsevice.model.order.OrderBean;
import com.tepia.cmnwsevice.model.order.OrderManager;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/2 14:01
 * @修改人 ：
 * @修改时间 :       2019/4/2 14:01
 * @功能描述 :
 **/

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {

    public void getOrderCount(Object... params) {
        OrderManager.getInstance().getOrderCount().safeSubscribe(new LoadingSubject<BaseCommonResponse>() {
            @Override
            protected void _onNext(BaseCommonResponse baseCommonResponse) {

            }

            @Override
            protected void _onError(String message) {

            }
        });
    }
}
