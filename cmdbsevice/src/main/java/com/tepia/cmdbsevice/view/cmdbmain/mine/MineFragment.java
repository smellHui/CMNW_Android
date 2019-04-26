package com.tepia.cmdbsevice.view.cmdbmain.mine;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.utils.AppManager;
import com.tepia.cmdbsevice.R;
import com.tepia.cmnwsevice.model.user.UserBean;
import com.tepia.cmnwsevice.model.user.UserManager;
import com.tepia.cmnwsevice.view.login.LoginActivity;
import com.tepia.cmnwsevice.view.setting.view.CircleImageView;

/**
 * Author:xch
 * Date:2019/4/26
 * Description:
 */
public class MineFragment extends BaseCommonFragment {

    private CircleImageView iv_user;
    private TextView tv_name;
    private TextView tv_compty;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        findView(R.id.tv_logout).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(com.tepia.cmnwsevice.R.string.exit_message);
            builder.setCancelable(false);
            builder.setPositiveButton("确定", (dialog, which) -> {
                UserManager.getInstance().saveToken("");
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                AppManager.getInstance().finishAll();

            });
            builder.setNegativeButton("取消", (dialog, which) -> {

            });
            builder.create().show();
        });

        iv_user = findView(R.id.iv_user);
        tv_name = findView(R.id.tv_name);
        tv_compty = findView(R.id.tv_compty);
        UserBean userInfo = UserManager.getInstance().getUserInfo();
        if (null != userInfo) {
            Glide.with(this).
                    load(userInfo.getAvatar()).
                    thumbnail(0.5f)
                    .apply(new RequestOptions()
                            .centerCrop()
                            .placeholder(com.tepia.cmnwsevice.R.mipmap.tag_role_manager)
                            .error(com.tepia.cmnwsevice.R.mipmap.tag_role_manager)
                            .priority(Priority.HIGH)
                            .diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(iv_user);
            tv_name.setText(userInfo.getUsername().isEmpty() ? "--" : userInfo.getUsername().trim());
            tv_compty.setText(userInfo.getDescription().isEmpty() ? "--" : userInfo.getNickName().trim());
        }
    }

    @Override
    protected void initRequestData() {

    }
}
