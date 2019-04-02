package com.tepia.ar;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.tepia.ar.databinding.ActivityArscanResultBinding;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.DoubleClickUtil;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/3/18 18:12
 * @修改人 ：
 * @修改时间 :       2019/3/18 18:12
 * @功能描述 :      扫描结果展示页面
 **/
@Route(path = AppRoutePath.app_ar_scan_result)
public class ARScanResultActivity extends BaseActivity {


    private ActivityArscanResultBinding mBinding;
    private String result;
    private Bitmap bitmap;

    @Override
    public int getLayoutId() {
        return R.layout.activity_arscan_result;
    }

    @Override
    public void initView() {
        setCenterTitle("AR智能识别结果");
        showBack();
        mBinding = DataBindingUtil.bind(mRootView);
        result = decodeUnicode(result.replace("&#x", "\\u").replace(";", ""));
        mBinding.tvResult.setText(result);
        mBinding.btScanAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                finish();
            }
        });
        mBinding.ivResult.setImageBitmap(bitmap);
    }

    @Override
    public void initData() {
        result = getIntent().getStringExtra("result");
        String temp = getIntent().getStringExtra("bitmap");
        if (!TextUtils.isEmpty(temp)) {
            bitmap = new Gson().fromJson(temp, Bitmap.class);
        }
    }

    /*
     * unicode编码转中文
     */
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }
}
