package com.tepia.ar.arscan;

import android.content.Context;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;


/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/3/18 17:50
 * @修改人         ：      
 * @修改时间       :       2019/3/18 17:50
 * @功能描述       :
 **/

public class ARScanContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
