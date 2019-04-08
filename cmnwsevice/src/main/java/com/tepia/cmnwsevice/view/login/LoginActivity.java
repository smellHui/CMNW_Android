package com.tepia.cmnwsevice.view.login;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.model.user.UserManager;
import com.tepia.cmnwsevice.view.main.MainActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/2 15:41
 * @修改人 ：
 * @修改时间 :       2019/4/2 15:41
 * @功能描述 :        登录页面
 **/
@Route(path = AppRoutePath.app_cmnw_login)
public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    private Button loginButton;
    private LinearLayout rootLayout;
    private EditText etUsername;
    private EditText etPassword;
    private TextInputLayout usernameLayout;
    private TextInputLayout passwordLayout;
    private ImageView ivCleanText;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(UserManager.getInstance().getToken())) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }
            }
        }, 0);
    }

    @Override
    public void initView() {
        rootLayout = findViewById(R.id.rootLayout);
        loginButton = findViewById(R.id.login_button);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        usernameLayout = findViewById(R.id.username_layout);
        passwordLayout = findViewById(R.id.password_layout);
        ivCleanText = findViewById(R.id.iv_clean_text);
        ivCleanText.setVisibility(View.GONE);
        controlKeyboardLayout(rootLayout, loginButton);
        initEditText();
        initClick();
    }

    private void initEditText() {
        //去表情符
        InputFilter emojiFilter = new InputFilter() {
            Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                    Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Matcher emojiMatcher = emoji.matcher(source);
                if (emojiMatcher.find()) {
                    ToastUtils.shortToast("格式不支持表情");
                    return "";
                }
                return null;
            }
        };
        etUsername.setFilters(new InputFilter[]{emojiFilter});
        etPassword.setFilters(new InputFilter[]{emojiFilter});
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if (text.length() > 0) {
                    ivCleanText.setVisibility(View.VISIBLE);
                } else {
                    ivCleanText.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ivCleanText.setOnClickListener(v -> {
            etUsername.setText("");
        });
    }


    private void initClick() {
        loginButton.setOnClickListener(v -> {
//            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//            startActivity(intent);
            String userName = etUsername.getText().toString().replace(" ", "");
            String passWord = etPassword.getText().toString().replace(" ", "");
            if (TextUtils.isEmpty(userName)) {
//                usernameLayout.setError("账号不能为空");
//                usernameLayout.setErrorEnabled(true);
                ToastUtils.shortToast("请输入账号");
                return;
            }
            if (TextUtils.isEmpty(passWord)) {
//                passwordLayout.setError("账号不能为空");
//                passwordLayout.setErrorEnabled(true);
                ToastUtils.shortToast("请输入密码");
                return;
            }
            if (!NetUtil.isNetworkConnected(Utils.getContext())) {
                ToastUtils.shortToast(R.string.no_network);
            } else {
                if (!DoubleClickUtil.isFastDoubleClick()) {
                    mPresenter.login("username", userName, "password", passWord);
                }
            }

        });
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

    /**
     * @param root         最外层布局，需要调整的布局
     * @param scrollToView 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     */
    private void controlKeyboardLayout(final View root, final View scrollToView) {
        // 注册一个回调函数，当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时调用这个回调函数。
        root.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect rect = new Rect();
                        // 获取root在窗体的可视区域
                        root.getWindowVisibleDisplayFrame(rect);
                        // 当前视图最外层的高度减去现在所看到的视图的最底部的y坐标
                        int rootInvisibleHeight = root.getRootView()
                                .getHeight() - rect.bottom;

                        // 若rootInvisibleHeight高度大于150(这个值有待动态计算)，则说明当前视图上移了，说明软键盘弹出了
                        if (rootInvisibleHeight > 350) {
                            //软键盘弹出来的时候
                            int[] location = new int[2];
                            // 获取scrollToView在窗体的坐标
                            scrollToView.getLocationInWindow(location);
                            // 计算root滚动高度，使scrollToView在可见区域的底部
                            int srollHeight = (location[1] + scrollToView
                                    .getHeight()) - rect.bottom;
                            if (srollHeight > 0) {
                                root.scrollTo(0, srollHeight);
                            }

                        } else {
                            // 软键盘没有弹出来的时候
                            root.scrollTo(0, 0);
                        }
                    }
                });
    }

    @Override
    public void loginSuccess() {
        ARouter.getInstance().build(AppRoutePath.app_cmnw_activity_tabmain).navigation();
        finish();
    }
}
