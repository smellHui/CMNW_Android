package com.tepia.cmnwsevice.view.main;


import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.model.PageBean;
import com.tepia.base.mvp.NetListListener;
import com.tepia.cmnwsevice.model.ExecuteStatus;
import com.tepia.cmnwsevice.model.api.ApiManager;
import com.tepia.cmnwsevice.model.order.OrderBean;
import com.tepia.cmnwsevice.model.order.OrderCountBean;
import com.tepia.cmnwsevice.model.order.OrderManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/2 15:41
 * @修改人 ：
 * @修改时间 :       2019/4/2 15:41
 * @功能描述 :
 **/

public class OrderPresenter {

    private static OrderPresenter orderPresenter;

    private List<Integer> executeStatusArray;

    private NetListListener<PageBean<OrderBean>> netListListener;

    public static OrderPresenter getInstance(int type, NetListListener<PageBean<OrderBean>> netListListener) {
        synchronized (ApiManager.class) {
            if (orderPresenter == null) {
                orderPresenter = new OrderPresenter(type, netListListener);
            }
        }
        return orderPresenter;
    }

    /**
     * @param type            {0,1,2 代表第几个页面}
     * @param netListListener
     */
    public OrderPresenter(int type, NetListListener<PageBean<OrderBean>> netListListener) {
        this.netListListener = netListListener;
        executeStatusArray = new ArrayList<>();
        switch (type) {
            case 0:
                executeStatusArray.add(ExecuteStatus.PENDING.getType());
                executeStatusArray.add(ExecuteStatus.RETURNED.getType());
                break;
            case 1:
                executeStatusArray.add(ExecuteStatus.EXECUTING.getType());
                break;
            case 2:
                executeStatusArray.add(ExecuteStatus.WAITCONFIRM.getType());
                executeStatusArray.add(ExecuteStatus.COMPLETE.getType());
                break;
        }
    }

    /**
     * 获取列表
     *
     * @param pageIndex
     * @param pageSize
     */
    public void querylist(int pageIndex, int pageSize) {
        OrderManager.getInstance().getOrderList("pageIndex", pageIndex, "pageSize", pageSize, "executeStatusArray", executeStatusArray)
                .safeSubscribe(new LoadingSubject<PageBean<OrderBean>>() {
                    @Override
                    protected void _onNext(PageBean<OrderBean> pageBean) {
                        netListListener.success(pageBean);
                    }

                    @Override
                    protected void _onError(String message) {
                        netListListener.error();
                    }
                });


    }

    public void getOrderCount(OrderCountListener orderCountListener) {
        OrderManager.getInstance().getOrderCount()
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<OrderCountBean>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<OrderCountBean> orderCount) {
                        orderCountListener.OrderCount(orderCount.getData());
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    public interface OrderCountListener {
        void OrderCount(OrderCountBean orderCount);
    }

}
