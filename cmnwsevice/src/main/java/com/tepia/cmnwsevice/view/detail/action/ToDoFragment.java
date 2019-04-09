package com.tepia.cmnwsevice.view.detail.action;

import android.os.Bundle;
import android.view.View;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.utils.ToastUtils;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.model.event.StartDoCallbackEvent;
import com.tepia.cmnwsevice.model.order.OrderManager;
import com.tepia.cmnwsevice.view.main.views.StartDoView;

import org.greenrobot.eventbus.EventBus;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:
 */
public class ToDoFragment extends BaseCommonFragment {

    private String orderId;

    private StartDoView startDoView;

    public static ToDoFragment launch(String orderId) {
        ToDoFragment fragment = new ToDoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("orderId", orderId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_todo;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if (bundle != null)
            orderId = bundle.getString("orderId");
    }

    @Override
    protected void initView(View view) {
        startDoView = findView(R.id.view_start_do);

        startDoView.setListener(() -> {
//            EventBus.getDefault().post(new StartDoCallbackEvent());
                    OrderManager.getInstance().startOrder("id", orderId
                            , "lgtd", "", "lttd", "")
                            .safeSubscribe(new LoadingSubject<BaseResponse>() {
                                @Override
                                protected void _onNext(BaseResponse baseResponse) {
                                    EventBus.getDefault().post(new StartDoCallbackEvent());
                                }

                                @Override
                                protected void _onError(String message) {
                                    ToastUtils.shortToast(message);
                                }
                            });
                }
        );
    }

    @Override
    protected void initRequestData() {

    }
}
