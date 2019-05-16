package com.tepia.cmdbsevice.view.cmdbmain;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gyf.barlibrary.ImmersionBar;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.TabEntity;
import com.tepia.cmdbsevice.service.BackService;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.EventSupervisionFragment;
import com.tepia.cmdbsevice.view.cmdbmain.mine.MineFragment;
import com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.OnlineMonitorFragment;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.TargetAssessmentFragment;

import java.util.ArrayList;


/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/15 14:21
 * @修改人 ：
 * @修改时间 :       2019/4/15 14:21
 * @功能描述 :       主 tab 页
 **/
@Route(path = AppRoutePath.app_cmdb_main_tab)
public class CmdbMainActivity extends MVPBaseActivity<CmdbMainContract.View, CmdbMainPresenter> implements CmdbMainContract.View {

    private CommonTabLayout tabLayout;

    private String[] mTitles = {"在线监测", "事件督办", "统计分析", "个人中心"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int[] mIconUnselectIds = {R.mipmap.tab_icn_zxjc_normol, R.mipmap.tab_icn_sjdb_normol, R.mipmap.tab_icn_tjfx_normol, R.mipmap.tab_icn_me_normol};
    private int[] mIconSelectIds = {R.mipmap.tab_icn_zxjc_selected, R.mipmap.tab_icn_sjdb_selected, R.mipmap.tab_icn_mbkh_selected, R.mipmap.tab_icn_me_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private AppBean appBean;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 200;
    private long mExitTime;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_cmdb;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this,BackService.class));
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
                LogUtil.d("没有新版本");
            }

            @Override
            public void onUpdateAvailable(String result) {
                // 将新版本信息封装到AppBean中
                appBean = getAppBeanFromString(result);
                new AlertDialog.Builder(CmdbMainActivity.this)
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
                                            ActivityCompat.requestPermissions(CmdbMainActivity.this,
                                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                                        } else {
                                            startDownloadTask(CmdbMainActivity.this, appBean.getDownloadURL());

                                        }
                                    }
                                }).show();

            }
        });
    }

    /**
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.shortToast("再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
                AppManager.getInstance().exitApp();
                this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {
    }

    private void addFragmnet() {
        mFragments.add(new OnlineMonitorFragment());
        mFragments.add(new EventSupervisionFragment());
        mFragments.add(new TargetAssessmentFragment());
        mFragments.add(new MineFragment());

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities, this, R.id.fl_content, mFragments);
        tabLayout.setCurrentTab(1);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
}
