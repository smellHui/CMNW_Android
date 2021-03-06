package com.tepia.cmnwsevice.view.detail.operationdetail.operationdetail;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.cmnwsevice.model.order.OperationBean;

/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/4/8 17:57
 * @修改人         ：      
 * @修改时间       :       2019/4/8 17:57
 * @功能描述       :
 **/

public class OprationDetailContract {
    interface View extends BaseView {

        void getOrderOprationDetailSuccess(OperationBean data);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
