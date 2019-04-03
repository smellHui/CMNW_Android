package com.tepia.cmnwsevice.view.main.myagent;


import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.cmnwsevice.model.RiverBean;
import com.tepia.cmnwsevice.model.api.ApiManager;

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

public class MyAgentPresenter extends BasePresenterImpl<MyAgentContract.View> implements MyAgentContract.Presenter {

    private int page = 1;
    private int pageSize = 20;

    public void refresh(){
        page = 1;
        querylist();
    }

    public void querylist() {
        ApiManager.getInstance()
                .queryList()
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<List<RiverBean>>>(true, "正在获取当前河道信息...") {

                    @Override
                    protected void _onNext(BaseCommonResponse<List<RiverBean>> baseCommonResponse) {
                        //mView.getRiverListSuccess(baseCommonResponse.getData());
                    }

                    @Override
                    protected void _onError(String message) {
                       // mView.getRiverListFailed(message);
//                ToastUtils.shortToast(message);
                    }
                });

    }
}
