package com.tepia.cmnwsevice.view.main;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.Utils;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.model.TabEntity;
import com.tepia.cmnwsevice.view.main.doing.DoingFragment;
import com.tepia.cmnwsevice.view.main.myagent.MyAgentFragment;
import com.tepia.cmnwsevice.view.main.operate.OperateFragment;

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

    private CommonTabLayout tabLayout;

    private String[] mTitles = {"我的代办", "处理中", "运维记录"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int[] mIconUnselectIds = {                                              //
            R.mipmap.tabbar_icn_dbsx_normal, R.mipmap.tabbar_icn_clz_normal,
            R.mipmap.tabbar_icn_ywjl_normal};
    private int[] mIconSelectIds = {
            R.mipmap.tabbar_icn_dbsx_selected, R.mipmap.tabbar_icn_clz_selected,
            R.mipmap.tabbar_icn_ywjl_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private AppBean appBean;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 200;

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
        PgyUpdateManager.register(this, new UpdateManagerListener() {
            @Override
            public void onNoUpdateAvailable() {
                LogUtil.d("fkldskfl");
            }

            @Override
            public void onUpdateAvailable(String result) {
                // 将新版本信息封装到AppBean中
                appBean = getAppBeanFromString(result);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("更新")
                        .setMessage("" + appBean.getReleaseNote())
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton(
                                "确定",
                                new DialogInterface.OnClickListener() {


                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //android 6.0动态申请权限
                                        if (ContextCompat.checkSelfPermission(Utils.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                                != PackageManager.PERMISSION_GRANTED) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                                            }
                                            ActivityCompat.requestPermissions(MainActivity.this,
                                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                                        } else {
                                            startDownloadTask(MainActivity.this, appBean.getDownloadURL());

                                        }
                                    }
                                }).show();

            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {
        mPresenter.getOrderCount();
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
