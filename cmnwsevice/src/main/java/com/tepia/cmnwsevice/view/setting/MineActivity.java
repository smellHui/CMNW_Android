package com.tepia.cmnwsevice.view.setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.AppManager;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.model.order.OrderCountBean;
import com.tepia.cmnwsevice.model.order.OrderManager;
import com.tepia.cmnwsevice.model.user.UserBean;
import com.tepia.cmnwsevice.model.user.UserManager;
import com.tepia.cmnwsevice.view.login.LoginActivity;
import com.tepia.cmnwsevice.view.main.OrderPresenter;
import com.tepia.cmnwsevice.view.setting.utils.GlideCircleWithBorder;
import com.tepia.cmnwsevice.view.setting.view.PointImageView;

import java.util.ArrayList;
import java.util.List;

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
    private ImageView ivUser;
    private TextView tvUsername;
    private TextView tvDescription;
    private TextView tvLogout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine;
    }

    @Override
    public void initView() {
        setCenterTitle(getString(R.string.setting_title));
        llEventList = findViewById(R.id.ll_event_list);
        ivUser = findViewById(R.id.iv_user);
        tvUsername = findViewById(R.id.tv_username);
        tvDescription = findViewById(R.id.tv_description);
        tvLogout = findViewById(R.id.tv_logout);
        initUserMessage();
        initEventItems();
        initClick();
    }

    private void initClick() {
        tvLogout.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.exit_message);
            builder.setCancelable(false);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    UserManager.getInstance().saveToken("");
                    Intent intent = new Intent(MineActivity.this, LoginActivity.class);
                    startActivity(intent);
                    AppManager.getInstance().finishAll();

                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();
        });
    }

    /**
     * 设置工单状态
     */
    private void initEventItems() {
        //设置消息数角标
//         RelativeLayout childAt = (RelativeLayout) llEventList.getChildAt(0);
        PointImageView ivToExecute = findViewById(R.id.iv_toExecute_event);
        PointImageView ivToBack = findViewById(R.id.iv_toBack_event);
        PointImageView ivOnExecute = findViewById(R.id.iv_onExecute_event);
        PointImageView ivToExamine = findViewById(R.id.iv_toExamine_event);
        PointImageView ivDone = findViewById(R.id.iv_done_event);
        OrderManager.getInstance().getOrderCount()
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<OrderCountBean>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<OrderCountBean> orderCount) {
                        OrderCountBean data = orderCount.getData();
                        try {
                            int toExecute = Integer.parseInt(data.getToExecute());
                            setPointImage(ivToExecute,toExecute);
                            int toBack = Integer.parseInt(data.getToBack());
                            setPointImage(ivToBack,toBack);
                            int onExecute = Integer.parseInt(data.getOnExecute());
                            setPointImage(ivOnExecute,onExecute);
                            int toExamine = Integer.parseInt(data.getToExamine());
                            setPointImage(ivToExamine,toExamine);
                            int done = Integer.parseInt(data.getDone());
                            setPointImage(ivDone,done);
                        }catch (Exception e){

                        }

                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    /**
     * 设置角标
     * @param img
     * @param size
     */
    private void setPointImage(PointImageView img,int size){
        if (size>0){
            img.setMessageNum(size);//设置消息条数
            img.setPointMode(PointImageView.NUMBER_POINT);
            img.setHaveMesage(true);
        }
    }

    /**
     * 设置用户信息
     */
    private void initUserMessage() {
        UserBean userInfo = UserManager.getInstance().getUserInfo();
        if (null != userInfo) {
//                    Glide.with(this).load("http://05imgmini.eastday.com/mobile/20181013/20181013_da58d8665e2d35cd7c2ad4db1a820288_cover_mwpm_03200403.jpg")
//                .apply(new RequestOptions().error(this.getResources().getDrawable(R.mipmap.tag_role_manager))
//                        .placeholder(R.mipmap.tag_role_manager).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .transform(new GlideCircleWithBorder(this, 2, Color.WHITE)))
//                .into(ivUser);
            Glide.with(this).
                    load(userInfo.getAvatar()).
                    thumbnail(0.5f)
                    .apply(new RequestOptions()
                            .centerCrop()
                            .placeholder(R.mipmap.tag_role_manager)
                            .error(R.mipmap.tag_role_manager)
                            .priority(Priority.HIGH)
                            .diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(ivUser);
            tvUsername.setText(userInfo.getUsername().isEmpty()?"--":userInfo.getUsername().trim());
            tvDescription.setText(userInfo.getNickName().isEmpty()?"--":userInfo.getNickName().trim());
        }

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
