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

    boolean checkTokenComplete = false;

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
        UserModel.getInstance().removeListener(this);
    }

    private void init() {
        initAutoLandingUser();
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
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (checkTokenComplete) {
                guideView.startActivityForIntent(intent, clazz);
            } else {
                handler.postDelayed(() -> {
                        guideView.startActivityForIntent(intent, clazz);
                }, 2500);
            }
        }, 2500);
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
    public void loginFail(int code) {

    }

    @Override
    public void timeOut() {

    }

    @Override
    public void checkTokenSuccess() {
        checkTokenComplete = true;
        init();
        if (UserInfo.isDriver()) {
            UserModel.getInstance().getUserInfo(UserInfo.getId() + "");
            clazz = DriverMainActivity.class;
        } else {
            UserModel.getInstance().getCarWashInfo();
            clazz = CarWashMainActivity.class;
        }
    }

    @Override
    public void checkTokenFailed() {
        checkTokenComplete = true;
    }

    @Override
    public void getCarWashInfoSuccess() {
        clazz = CarWashMainActivity.class;
    }
}
