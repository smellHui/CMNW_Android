package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationdetail;

import android.databinding.DataBindingUtil;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.databinding.ActivityStationVrBinding;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/5/15
 * Time            :       11:58
 * Version         :       1.0
 * 功能描述        :
 **/
@Route(path = AppRoutePath.app_cmdb_station_detail_gylc)
public class StatoinGYLCActivity extends BaseActivity {
    private WebView webVr;
    private String vrUrl;

    private ChromeClientCallbackManager.ReceivedTitleCallback mCallback;
    private ActivityStationVrBinding mBinding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_station_vr;
    }

    @Override
    public void initView() {
        mBinding = DataBindingUtil.bind(mRootView);
    }

    @Override
    public void initData() {
        vrUrl = getIntent().getStringExtra("vrUrl");
        vrUrl = "http://47.110.70.104:10018";
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {
//        mBinding.webVr.loadUrl(vrUrl);
        AgentWeb.with(this)//传入Activity
                .setAgentWebParent(mBinding.webVr, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setReceivedTitleCallback(mCallback) //设置 Web 页面的 title 回调
                .createAgentWeb()//
                .ready()
                .go(vrUrl);

    }
}
