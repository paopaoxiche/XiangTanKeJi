package com.xtkj.paopaoxiche.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.contract.ILoginContract;
import com.xtkj.paopaoxiche.presenter.LoginPresenterImpl;

import static android.Manifest.permission.READ_CONTACTS;

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

