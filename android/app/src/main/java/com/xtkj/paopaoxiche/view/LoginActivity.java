package com.xtkj.paopaoxiche.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.contract.ILoginContract;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.presenter.LoginPresenterImpl;
import com.xtkj.paopaoxiche.utils.PhoneCheckUtils;
import com.xtkj.paopaoxiche.view.view.LoadingDialog;
import com.xtkj.paopaoxiche.widget.CountdownButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ILoginContract.ILoginView {

    ILoginContract.ILoginPresenter loginPresenter;

    @BindView(R.id.login_progress)
    ProgressBar loginProgress;
    @BindView(R.id.back_button)
    ImageButton backButton;
    @BindView(R.id.rl_toolbar)
    RelativeLayout rlToolbar;
    @BindView(R.id.edtTxt_account)
    TextInputEditText accountText;
    @BindView(R.id.account_text_input)
    TextInputLayout accountTextInput;
    @BindView(R.id.edtTxt_password)
    TextInputEditText codeText;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.send_msg)
    CountdownButton sendMsgButton;
    @BindView(R.id.driver_radio_button)
    RadioButton driverRadioButton;
    @BindView(R.id.cleaner_radio_button)
    RadioButton cleanerRadioButton;
    @BindView(R.id.role_radio_group)
    RadioGroup roleRadioGroup;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.login_form)
    ScrollView loginForm;

    int roletype = -1;

    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initViews();
        initValues();
        initListeners();

        Log.e("sha1", sHA1(this));

        new LoginPresenterImpl(this);
        loginPresenter.onCreate();
    }

    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void initViews() {
        loadingDialog = new LoadingDialog(this);
    }

    @Override
    protected void initValues() {

    }

    @Override
    protected void initListeners() {
        roleRadioGroup.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.driver_radio_button:
                roletype = 0;
                break;

            case R.id.cleaner_radio_button:
                roletype = 1;
                break;

            default:
                break;
        }
    }

    @Override
    public void setPresenter(ILoginContract.ILoginPresenter iLoginPresenter) {
        loginPresenter = iLoginPresenter;
    }

    @Override
    public void initAccount(String s) {
        accountText.getEditableText().append(s);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void resetSentMsgButton() {
        sendMsgButton.reset();
    }

    @Override
    public void login(Intent intent) {
        startActivity(intent);
        finish();
    }

    @Override
    public void register() {
        loadingDialog.dismiss();
        Intent intent = new Intent(this, RegisterWashActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("phone", accountText.getEditableText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @OnClick({R.id.send_msg, R.id.role_radio_group, R.id.login_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_msg:
                String phone = accountText.getEditableText().toString() + "";
                if (PhoneCheckUtils.isPhoneLegal(phone))
                    loginPresenter.getMessageCode(phone);
                else {
                    showToast("请输入正确的电话号码");
                    sendMsgButton.reset();
                }
                break;
            case R.id.role_radio_group:
                break;
            case R.id.login_button:
                String account = accountText.getEditableText().toString() + "";
                String codeString = codeText.getEditableText().toString() + "";
                if (account == null || !PhoneCheckUtils.isChinaPhoneLegal(account)
                        || codeString == null || codeString.length() < 6) {
                    showToast("电话号码或验证码输入不正确");
                    return;
                }
                if (roletype < 0) {
                    showToast("请选择用户类型");
                }
                Long code = Long.valueOf(codeText.getEditableText().toString() + "");

                loadingDialog.show();

                loginPresenter.doLogin(account, roletype, code);

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestroy();
    }
}

