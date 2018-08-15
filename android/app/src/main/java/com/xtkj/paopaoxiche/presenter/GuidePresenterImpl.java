package com.xtkj.paopaoxiche.presenter;

import android.content.Intent;
import android.os.Handler;

import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.bean.LoginBean;
import com.xtkj.paopaoxiche.model.UserModel;
import com.xtkj.paopaoxiche.utils.PreferUtils;
import com.xtkj.paopaoxiche.view.CarWashMain.CarWashMainActivity;
import com.xtkj.paopaoxiche.view.DriverMain.DriverMainActivity;
import com.xtkj.paopaoxiche.view.LoginActivity;
import com.xtkj.paopaoxiche.contract.IGuideContract;

public class GuidePresenterImpl implements IGuideContract.IGuidePresenter, UserModel.LoginListener {

    IGuideContract.IGuideView guideView;

    Class clazz = LoginActivity.class;
    PreferUtils preferUtils;

    public GuidePresenterImpl(IGuideContract.IGuideView iGuideView) {
        guideView = iGuideView;
        guideView.setPresenter(this);
    }

    @Override
    public void onCreate() {
        UserModel.getInstance().addListener(this);
        preferUtils = PreferUtils.getInstance();
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
        UserModel.getInstance().initData();
    }

    private void checkToken() {
        if (UserInfo.isDriver()) {
            UserModel.getInstance().checkDriverToken();
        } else {
            UserModel.getInstance().checkCarWashToken();
        }
    }

    @Override
    public void autoLogin() {
        // TODO 这里需要http去判断TOKEN是否生效
        Intent intent = new Intent();
        new Handler().postDelayed(() -> guideView.startActivityForIntent(intent, clazz), 1000);
    }

    @Override
    public void getCodeSuccess() {

    }

    @Override
    public void getCodeFail() {

    }

    @Override
    public void loginSuccess(LoginBean.DataBean dataBean) {

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

    @Override
    public void modifyUserInfo(String modifyType) {

    }
}
