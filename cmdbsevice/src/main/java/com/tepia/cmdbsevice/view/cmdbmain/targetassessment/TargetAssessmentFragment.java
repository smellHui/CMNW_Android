package com.tepia.cmdbsevice.view.cmdbmain.targetassessment;


import android.view.View;

import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.cmdbsevice.R;

/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/4/15 14:44
 * @修改人         ：
 * @修改时间       :       2019/4/15 14:44
 * @功能描述       :        tab - 任务考核
 **/

public class TargetAssessmentFragment extends MVPBaseFragment<TargetAssessmentContract.View, TargetAssessmentPresenter> implements TargetAssessmentContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_target_assessment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initRequestData() {

    }
}
