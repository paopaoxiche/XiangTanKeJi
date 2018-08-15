package com.xtkj.paopaoxiche.presenter;

import android.content.Intent;

import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.bean.LoginBean;
import com.xtkj.paopaoxiche.contract.ILoginContract;
import com.xtkj.paopaoxiche.model.UserModel;
import com.xtkj.paopaoxiche.utils.PreferUtils;
import com.xtkj.paopaoxiche.view.CarWashMain.CarWashMainActivity;
import com.xtkj.paopaoxiche.view.DriverMain.DriverMainActivity;


public class LoginPresenterImpl implements ILoginContract.ILoginPresenter, UserModel.LoginListener {

    ILoginContract.ILoginView loginView;

    public LoginPresenterImpl(ILoginContract.ILoginView iLoginView) {
        loginView = iLoginView;
        iLoginView.setPresenter(this);
    }

    @Override
    public void onCreate() {
        String account = PreferUtils.getInstance().getString(AppConstant.ACCOUNT);
        loginView.initAccount(account);
        UserModel.getInstance().addListener(this);
//        checkToken();
    }

    @Override
    public void onDestroy() {
        UserModel.getInstance().removeListener(this);
        UserModel.release();
    }

    @Override
    public void getMessageCode(String phone) {
        UserModel.getInstance().getMessageCode(phone);
    }

    @Override
    public void doLogin(String account, int roleType, long code) {
        int type = 1;
        if (UserInfo.isDriver()) type = 0;

        UserModel.getInstance().doLogin(account, code, roleType);

        PreferUtils.getInstance().putString(AppConstant.ACCOUNT, account);
    }

    private void login() {
        Intent intent;
        if (UserInfo.isDriver()) {
            intent = new Intent(loginView.getContext(), DriverMainActivity.class);
        } else {
            intent = new Intent(loginView.getContext(), CarWashMainActivity.class);
        }
        loginView.login(intent);
    }

    @Override
    public void getCodeSuccess() {
        loginView.showToast("获取验证码成功");
    }

    @Override
    public void getCodeFail() {
        loginView.showToast("获取验证码失败， 请稍后再试");
        loginView.resetSentMsgButton();
    }

    @Override
    public void loginSuccess(LoginBean.DataBean dataBean) {
        loginView.showToast("登陆成功");
        login();
    }

    @Override
    public void loginFail() {
        loginView.showToast("登录失败，请检查账号或验证码");
    }

    @Override
    public void timeOut() {
        loginView.showToast(AppConstant.NET_ERROR);
    }

    @Override
    public void checkTokenSuccess() {

    }

    @Override
    public void ModifyUserInfo(String modifyType) {

    }
}
