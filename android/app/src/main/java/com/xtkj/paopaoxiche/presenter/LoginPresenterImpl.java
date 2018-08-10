package com.xtkj.paopaoxiche.presenter;

import android.content.Intent;

import com.xtkj.paopaoxiche.application.UserInfo;
import com.xtkj.paopaoxiche.bean.LoginBean;
import com.xtkj.paopaoxiche.bean.NoDataBean;
import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.contract.ILoginContract;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.model.LoginModel;
import com.xtkj.paopaoxiche.service.UserService;
import com.xtkj.paopaoxiche.utils.PreferUtils;
import com.xtkj.paopaoxiche.view.CarWashMain.CarWashMainActivity;
import com.xtkj.paopaoxiche.view.DriverMain.DriverMainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenterImpl implements ILoginContract.ILoginPresenter, LoginModel.LoginListener {

    ILoginContract.ILoginView loginView;
    PreferUtils preferUtils;
    boolean isDriver = true;

    public LoginPresenterImpl(ILoginContract.ILoginView iLoginView) {
        loginView = iLoginView;
        iLoginView.setPresenter(this);
        init();
    }

    private void init() {
        preferUtils = PreferUtils.getInstance(loginView.getContext());
        UserInfo.setToken(preferUtils.getString(AppConstant.TOKEN));
        UserInfo.setId(preferUtils.getString(AppConstant.USER_ID));
        Authentication.setUser_id(UserInfo.getId());
        Authentication.setToken(UserInfo.getToken());
    }

    @Override
    public void onCreate() {
        String account = PreferUtils.getInstance(loginView.getContext()).getString(AppConstant.ACCOUNT);
        loginView.initAccount(account);

        LoginModel.getInstance().addListener(this);

        checkToken(isDriver);
    }

    @Override
    public void onDestroy() {
        LoginModel.getInstance().removeListener(this);
        LoginModel.release();
    }

    @Override
    public void getMessageCode(String phone) {
        LoginModel.getInstance().getMessageCode(phone);
    }

    @Override
    public void doLogin(String account, long code) {
        int type = 1;
        if (isDriver) type = 0;

        LoginModel.getInstance().doLogin(account, code, type);

        PreferUtils.getInstance(loginView.getContext()).putString(AppConstant.ACCOUNT, account);
    }

    @Override
    public void checkToken(Boolean isDriver) {

        if (isDriver) {
            RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                    .create(UserService.class)
                    .checkCarOwner()
                    .enqueue(new Callback<NoDataBean>() {
                        @Override
                        public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                            if (response.body().getCode() != 401) {
                                initAutoLandingUser();
                                login();
                            }

                        }

                        @Override
                        public void onFailure(Call<NoDataBean> call, Throwable t) {
                            loginView.showToast(AppConstant.NET_ERROR);
                        }
                    });
        } else {
            RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                    .create(UserService.class)
                    .checkCarWash()
                    .enqueue(new Callback<NoDataBean>() {
                        @Override
                        public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                            if (response.body().getCode() != 401) {
                                initAutoLandingUser();
                                login();
                            }
                        }

                        @Override
                        public void onFailure(Call<NoDataBean> call, Throwable t) {
                            loginView.showToast(AppConstant.NET_ERROR);
                        }
                    });
        }

    }

    @Override
    public void setIsDriver(boolean driver) {
        isDriver = driver;
    }

    private void initAutoLandingUser() {

        UserInfo.setToken(preferUtils.getString(AppConstant.TOKEN));
        UserInfo.setId(preferUtils.getString(AppConstant.USER_ID));
    }

    private void login() {
        Intent intent;
        if (isDriver) {
            intent = new Intent(loginView.getContext(), DriverMainActivity.class);
        } else {
            intent = new Intent(loginView.getContext(), CarWashMainActivity.class);
        }
        loginView.getContext().startActivity(intent);
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
    public void loginSuccess(String token, String id) {
        loginView.showToast("登陆成功");

        PreferUtils.getInstance(loginView.getContext()).putString(AppConstant.TOKEN, token);
        PreferUtils.getInstance(loginView.getContext()).putString(AppConstant.USER_ID, id);

        UserInfo.setToken(token);
        UserInfo.setId(id);

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
}
