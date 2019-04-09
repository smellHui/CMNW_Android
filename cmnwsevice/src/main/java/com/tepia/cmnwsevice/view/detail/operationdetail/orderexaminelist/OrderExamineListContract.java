package com.tepia.cmnwsevice.view.detail.operationdetail.orderexaminelist;

import android.content.Context;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.cmnwsevice.model.order.ExamineBean;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class OrderExamineListContract {
    interface View extends BaseView {

        void getOrderExamineListSuccess(List<ExamineBean> data);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
