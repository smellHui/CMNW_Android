package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision;


import android.view.View;

import com.flyco.tablayout.SegmentTabLayout;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupPosition;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.ToastUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.SelectEventPopView;

import java.io.BufferedReader;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/15 14:43
 * @修改人 ：
 * @修改时间 :       2019/4/15 14:43
 * @功能描述 :        tab-事件督办
 **/

public class EventSupervisionFragment extends BaseCommonFragment {

    private String[] mTitles = {"日", "周", "月", "年"};

    private SelectEventPopView selectEventPopView;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_event_supervision;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView(View view) {
        setCenterTitle("事件督办");
        setRightImgEvent(R.mipmap.img_agrc_service,(v)->{
            ToastUtils.shortToast("右边");
            new XPopup.Builder(getContext())
                    .popupPosition(PopupPosition.Right)//右边
                    .hasStatusBarShadow(true) //启用状态栏阴影
                    .asCustom(selectEventPopView)
                    .show();
        });
        selectEventPopView = new SelectEventPopView(getContext());
        SegmentTabLayout tabLayout_1 = findView(R.id.tl_1);
        tabLayout_1.setTabData(mTitles);

    }

    @Override
    protected void initRequestData() {

    }
}