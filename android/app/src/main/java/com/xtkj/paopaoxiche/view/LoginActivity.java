package com.xtkj.paopaoxiche.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.contract.ILoginContract;
import com.xtkj.paopaoxiche.presenter.LoginPresenterImpl;
import com.xtkj.paopaoxiche.utils.PhoneCheckUtils;
import com.xtkj.paopaoxiche.view.CarWashMain.CarWashMainActivity;
import com.xtkj.paopaoxiche.view.DriverMain.DriverMainActivity;
import com.xtkj.paopaoxiche.widget.CountdownButton;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements OnClickListener, RadioGroup.OnCheckedChangeListener, ILoginContract.ILoginView{

    ILoginContract.ILoginPresenter loginPresenter;

    RadioGroup roleRadioGroup;
    Button loginButton;
    TextInputEditText accountText;
    TextInputEditText codeText;
    CountdownButton sendMsgButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initValues();
        initListeners();

        new LoginPresenterImpl(this);
        loginPresenter.onCreate();
    }

    @Override
    protected void initViews() {
        roleRadioGroup = findViewById(R.id.role_radio_group);
        loginButton = findViewById(R.id.login_button);
        accountText = findViewById(R.id.edtTxt_account);
        codeText = findViewById(R.id.edtTxt_password);
        sendMsgButton = findViewById(R.id.send_msg);
    }

    @Override
    protected void initValues() {

    }

    @Override
    protected void initListeners() {
        roleRadioGroup.setOnCheckedChangeListener(this);
        sendMsgButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:

                String account = accountText.getEditableText().toString() + "";
                Long code = Long.valueOf(codeText.getEditableText().toString());

                loginPresenter.doLogin(account,code);

                break;
            case R.id.send_msg:
                String phone = accountText.getEditableText().toString() + "";
                if(PhoneCheckUtils.isPhoneLegal(phone))
                    loginPresenter.getMessageCode(phone);
                else{
                    showToast("请输入正确的电话号码");
                    sendMsgButton.reset();
                }
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.driver_radio_button:
                loginPresenter.setIsDriver(true);
                break;

            case R.id.cleaner_radio_button:
                loginPresenter.setIsDriver(true);
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
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void resetSentMsgButton() {
        sendMsgButton.reset();
    }


}

