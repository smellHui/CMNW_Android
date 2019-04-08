package com.tepia.cmnwsevice.view.setting;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.view.BadgeView;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.view.setting.utils.GlideCircleWithBorder;
import com.tepia.cmnwsevice.view.setting.view.PointImageView;

import static com.tepia.base.view.BadgeView.POSITION_TOP_RIGHT;

/**
  * Created by      Android studio
  *
  * @author :wwj (from Center Of Wuhan)
  * Date    :2019/4/8
  * Version :1.0
  * 功能描述 : 我的
 **/

public class MineActivity extends BaseActivity {

    private LinearLayout llEventList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine;
    }

    @Override
    public void initView() {
        setCenterTitle(getString(R.string.setting_title));
        //设置消息数角标
        llEventList = findViewById(R.id.ll_event_list);
        RelativeLayout childAt = (RelativeLayout) llEventList.getChildAt(0);
        PointImageView img = childAt.findViewById(R.id.iv_mine_event);
        img.setMessageNum(149);//设置消息条数
        img.setPointMode(PointImageView.NUMBER_POINT);
        img.setHaveMesage(true);
        //设置圆角图片
        ImageView ivUser = findViewById(R.id.iv_user);

//        Glide.with(this).load("http://05imgmini.eastday.com/mobile/20181013/20181013_da58d8665e2d35cd7c2ad4db1a820288_cover_mwpm_03200403.jpg")
//                .apply(new RequestOptions().error(this.getResources().getDrawable(R.mipmap.tag_role_manager))
//                        .placeholder(R.mipmap.tag_role_manager).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .transform(new GlideCircleWithBorder(this, 2, Color.WHITE)))
//                .into(ivUser);
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
}
