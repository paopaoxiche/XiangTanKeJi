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

    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (checkTokenComplete) {
                guideView.startActivityForIntent(new Intent(), clazz);
            } else {
                handler.postDelayed(this, 2000);
            }
        }
    };

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
        handler.postDelayed(runnable, 2000);
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
        checkTokenComplete = true;
    }

    @Override
    public void checkTokenSuccess() {
        checkTokenComplete = true;
        init();
        if (UserInfo.isDriver()) {
            clazz = DriverMainActivity.class;
        } else {
            UserModel.getInstance().getCarWashInfo();
            clazz = CarWashMainActivity.class;
        }
        UserModel.getInstance().getUserInfo(UserInfo.getId() + "");
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
