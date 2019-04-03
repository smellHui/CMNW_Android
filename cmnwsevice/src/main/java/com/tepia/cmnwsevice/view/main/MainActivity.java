package com.tepia.cmnwsevice.view.main;


import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.model.TabEntity;
import com.tepia.cmnwsevice.view.main.myagent.MyAgentFragment;

import java.util.ArrayList;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/2 15:35
 * @修改人 ：
 * @修改时间 :       2019/4/2 15:35
 * @功能描述 :       主 tab 页面
 **/
@Route(path = AppRoutePath.app_cmnw_activity_tabmain)
public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements MainContract.View
        , OnTabSelectListener {

    CommonTabLayout tabLayout;

    private String[] mTitles = {"我的代办", "处理中", "运维记录"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int[] mIconUnselectIds = {                                              //
            R.mipmap.img_agrc_service, R.mipmap.img_agrc_service,
            R.mipmap.img_agrc_service};
    private int[] mIconSelectIds = {
            R.mipmap.img_agrc_service_select, R.mipmap.img_agrc_service_select,
            R.mipmap.img_agrc_service_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        tabLayout = findViewById(R.id.ly_tab);
        addFragmnet();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    private void addFragmnet() {
        mFragments.add(MyAgentFragment.launch());
        mFragments.add(DoingFragment.launch());
        mFragments.add(OperateFragment.launch());

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities, this, R.id.fl_content, mFragments);
        tabLayout.setOnTabSelectListener(this);
    }

    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

    }
}
