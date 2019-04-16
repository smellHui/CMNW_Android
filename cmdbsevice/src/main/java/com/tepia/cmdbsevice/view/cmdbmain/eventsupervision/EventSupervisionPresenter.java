package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision;

import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.cmdbsevice.model.event.EventManager;

/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/4/15 15:52
 * @修改人         ：      
 * @修改时间       :       2019/4/15 15:52
 * @功能描述       :
 **/

public class EventSupervisionPresenter extends BasePresenterImpl<EventSupervisionContract.View> implements EventSupervisionContract.Presenter{

    public void topTotal(Object... params) {
        EventManager.getInstance().topTotal(params);
    }
}
