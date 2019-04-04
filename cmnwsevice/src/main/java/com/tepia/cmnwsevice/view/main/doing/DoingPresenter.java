package com.tepia.cmnwsevice.view.main.doing;


import com.tepia.base.http.LoadingSubject;
import com.tepia.base.model.BaseResp;
import com.tepia.base.model.PageBean;
import com.tepia.base.mvp.NetListListener;
import com.tepia.cmnwsevice.model.RiverBean;
import com.tepia.cmnwsevice.model.api.ApiManager;

import io.reactivex.schedulers.Schedulers;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/2 15:41
 * @修改人 ：
 * @修改时间 :       2019/4/2 15:41
 * @功能描述 :
 **/

public class DoingPresenter {

    private NetListListener<PageBean<RiverBean>> mView;

    public void setmView(NetListListener<PageBean<RiverBean>> mView) {
        this.mView = mView;
    }

    public void querylist() {
        ApiManager.getInstance()
                .queryList()
                .subscribeOn(Schedulers.io())
                .safeSubscribe(new LoadingSubject<BaseResp<PageBean<RiverBean>>>(true, "正在获取当前河道信息...") {

                    @Override
                    protected void _onNext(BaseResp<PageBean<RiverBean>> pageBean) {
                        mView.success(pageBean.getData());
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.error();
                    }
                });

    }
}
