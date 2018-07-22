package com.xtkj.paopaoxiche.view;

import android.content.Intent;
import android.support.annotation.Nullable;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.contract.ILoginContract;
import com.xtkj.paopaoxiche.presenter.LoginPresenterImpl;
import com.xtkj.paopaoxiche.view.DriverMain.DriverMainActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements OnClickListener, RadioGroup.OnCheckedChangeListener, ILoginContract.ILoginView{

    ILoginContract.ILoginPresenter loginPresenter;

    RadioGroup roleRadioGroup;
    Button loginButton;

    boolean isDriver = true;

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
    }

    @Override
    protected void initValues() {

    }

    @Override
    protected void initListeners() {
        roleRadioGroup.setOnCheckedChangeListener(this);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                if (isDriver) {
                    Intent intent = new Intent(this, DriverMainActivity.class);
                    startActivity(intent);
                } else {

                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.driver_radio_button:
                isDriver = true;
                break;

            case R.id.cleaner_radio_button:
                isDriver = false;
                break;

            default:
                break;
        }
    }

    @Override
    public void setPresenter(ILoginContract.ILoginPresenter iLoginPresenter) {
        loginPresenter = iLoginPresenter;
    }
}

