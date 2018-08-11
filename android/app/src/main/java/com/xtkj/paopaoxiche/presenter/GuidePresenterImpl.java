package com.xtkj.paopaoxiche.presenter;

import android.content.Intent;
import android.os.Handler;

import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.UserInfo;
import com.xtkj.paopaoxiche.model.LoginModel;
import com.xtkj.paopaoxiche.utils.PreferUtils;
import com.xtkj.paopaoxiche.view.CarWashMain.CarWashMainActivity;
import com.xtkj.paopaoxiche.view.DriverMain.DriverMainActivity;
import com.xtkj.paopaoxiche.view.LoginActivity;
import com.xtkj.paopaoxiche.contract.IGuideContract;

public class GuidePresenterImpl implements IGuideContract.IGuidePresenter, LoginModel.LoginListener {

    IGuideContract.IGuideView guideView;

    Class clazz = LoginActivity.class;
    PreferUtils preferUtils;

    public GuidePresenterImpl(IGuideContract.IGuideView iGuideView) {
        guideView = iGuideView;
        guideView.setPresenter(this);
    }

    @Override
    public void onCreate() {
        LoginModel.getInstance().addListener(this);
        preferUtils = PreferUtils.getInstance(guideView.getContext());
        init();
        checkToken();
    }

    @Override
    public void onDestroy() {

    }

    private void init() {
        initAutoLandingUser();
        Authentication.setUser_id(UserInfo.getId());
        Authentication.setToken(UserInfo.getToken());
    }

    private void initAutoLandingUser() {
        UserInfo.setToken(preferUtils.getString(AppConstant.TOKEN));
        UserInfo.setId(preferUtils.getString(AppConstant.USER_ID));
        UserInfo.setDriver(preferUtils.getBoolean(AppConstant.IS_DRIVER,true));
    }

    private void checkToken() {
        if (UserInfo.isDriver()) {
            LoginModel.getInstance().checkDriverToken();
        } else {
            LoginModel.getInstance().checkCarWashToken();
        }
    }

    @Override
    public void autoLogin() {
        // TODO 这里需要http去判断TOKEN是否生效
        Intent intent = new Intent();
        new Handler().postDelayed(() -> guideView.startActivityForIntent(intent, clazz), 2000);
    }

    @Override
    public void getCodeSuccess() {

    }

    @Override
    public void getCodeFail() {

    }

    @Override
    public void loginSuccess(String token, String id) {

    }

    @Override
    public void loginFail() {

    }

    @Override
    public void timeOut() {

    }

    @Override
    public void checkTokenSuccess() {
        init();
        if (UserInfo.isDriver()) {
            clazz = DriverMainActivity.class;
        } else {
            clazz = CarWashMainActivity.class;
        }
    }
}
